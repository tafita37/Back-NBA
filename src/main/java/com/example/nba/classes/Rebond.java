package com.example.nba.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Rebond {    
    int idrebond;
    Joueur idjoueur;
    Match idmatch;
    public Rebond() {
    }
    public Rebond(int idrebond, Joueur idjoueur, Match idmatch) {
        this.idrebond = idrebond;
        this.idjoueur = idjoueur;
        this.idmatch = idmatch;
    }    
    public int getIdrebond() {
        return idrebond;
    }
    public void setIdrebond(int idrebond) {
        this.idrebond = idrebond;
    }
    public Joueur getIdjoueur() {
        return idjoueur;
    }
    public void setIdjoueur(Joueur idjoueur) {
        this.idjoueur = idjoueur;
    }
    public Match getIdmatch() {
        return idmatch;
    }
    public void setIdmatch(Match idmatch) {
        this.idmatch = idmatch;
    }

    public void insert_rebond(Connection con,Joueur idjoueur,Match match) throws Exception{
        boolean isNull = false;
        if (con == null) {
            isNull = true;
            con = Connexion.connexionPostgress("nba");            
        }
        String sql = "INSERT INTO rebond VALUES (DEFAULT , ? , ? )";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, idjoueur.getIdjoueur());
             preparedStatement.setInt(2, match.getIdmatch());
            preparedStatement.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }        
    }
}
