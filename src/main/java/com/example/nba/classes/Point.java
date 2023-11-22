package com.example.nba.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Point {
    int idpoint;
    Joueur idjoueur;
    int points;
    Match match;
    public Point() {
    }
    public Point(int idpoint, Joueur idjoueur, int points, Match match) {
        this.idpoint = idpoint;
        this.idjoueur = idjoueur;
        this.points = points;
        this.match = match;
    }
    public int getIdpoint() {
        return idpoint;
    }
    public void setIdpoint(int idpoint) {
        this.idpoint = idpoint;
    }
    public Joueur getIdjoueur() {
        return idjoueur;
    }
    public void setIdjoueur(Joueur idjoueur) {
        this.idjoueur = idjoueur;
    }
    public int getpoints() {
        return points;
    }
    public void setpoints(int points) {
        this.points = points;
    }
    public Match getMatch() {
        return match;
    }
    public void setMatch(Match match) {
        this.match = match;
    }

    public void insert_point(Connection con,Joueur idjoueur,int points,Match match) throws Exception{
        boolean isNull = false;
        if (con == null) {
            isNull = true;
            con = Connexion.connexionPostgress("nba");            
        }
        String sql = "INSERT INTO point VALUES (DEFAULT , ? , ? , ? )";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, idjoueur.getIdjoueur());
            preparedStatement.setInt(2, points);
             preparedStatement.setInt(3, match.getIdmatch());
            preparedStatement.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }        
    }
    
}
