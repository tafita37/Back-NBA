package com.example.nba.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Match {
    int idmatch;
    String match;
    Equipe equipe1;
    Equipe equipe2;
    public Match(int idmatch, String match, Equipe equipe1, Equipe equipe2) {
        this.idmatch = idmatch;
        this.match = match;
        this.equipe1 = equipe1;
        this.equipe2 = equipe2;
    }
    public Match() {
    }
    public int getIdmatch() {
        return idmatch;
    }
    public void setIdmatch(int idmatch) {
        this.idmatch = idmatch;
    }
    public String getMatch() {
        return match;
    }
    public void setMatch(String match) {
        this.match = match;
    }
    public Equipe getEquipe1() {
        return equipe1;
    }
    public void setEquipe1(Equipe equipe1) {
        this.equipe1 = equipe1;
    }
    public Equipe getEquipe2() {
        return equipe2;
    }
    public void setEquipe2(Equipe equipe2) {
        this.equipe2 = equipe2;
    }

    public static Match getMatchById(Connection connex, int id) throws Exception {
    Match lp = new Match();
    boolean isNull = false;
    
    if (connex == null) {
        isNull = true;
        connex = Connexion.connexionPostgress("nba");            
    }
    
    try {
        Statement stat = connex.createStatement();
        String sql = "SELECT * FROM  match WHERE idmatch = " + id;
        ResultSet rs = stat.executeQuery(sql);
        
        if (rs.next()) {
            Equipe eq1 = Equipe.getEquipeById(null, rs.getInt("idequipe1"));
            Equipe eq2 = Equipe.getEquipeById(null, rs.getInt("idequipe2"));
            lp = new Match(rs.getInt("idmatch"), rs.getString("match"), eq1, eq2);
        }
    } catch (Exception e) {
        throw e;
    } finally {
        if (isNull) {
            connex.close();
        }
    }
    
    return lp;
}

    public void insert_match(Connection con,String nommatch,Equipe idequipe1 , Equipe idequipe2) throws Exception{
        boolean isNull = false;
        if (con == null) {
            isNull = true;
            con = Connexion.connexionPostgress("nba");            
        }
        String sql = "INSERT INTO match VALUES (DEFAULT , ? ,? ,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, nommatch);
            preparedStatement.setInt(2, idequipe1.getIdequipe());
            preparedStatement.setInt(3, idequipe2.getIdequipe());
            preparedStatement.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }        
    }
}
