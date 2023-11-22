package com.example.nba.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Passe {
    int idpasse;
    Joueur joueur;
    Match match;
    Typepasse idtype;
    public Passe() {
    }
    public Passe(int idpasse, Joueur joueur, Match match, Typepasse idtype) {
        this.idpasse = idpasse;
        this.joueur = joueur;
        this.match = match;
        this.idtype = idtype;
    }
    public int getIdpasse() {
        return idpasse;
    }
    public void setIdpasse(int idpasse) {
        this.idpasse = idpasse;
    }
    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }
    public Typepasse getIdtype() {
        return idtype;
    }
    public void setIdtype(Typepasse idtype) {
        this.idtype = idtype;
    }

    public void insert_passe(Connection con,Joueur idjoueur,Match match,Typepasse idtype) throws Exception{
        boolean isNull = false;
        if (con == null) {
            isNull = true;
            con = Connexion.connexionPostgress("nba");            
        }
        String sql = "INSERT INTO passe VALUES (DEFAULT , ? , ? , ? )";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, idjoueur.getIdjoueur());
            preparedStatement.setInt(2, match.getIdmatch());
            preparedStatement.setInt(3, idtype.getIdtypepasse());
          
            preparedStatement.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }        
    } 

}
