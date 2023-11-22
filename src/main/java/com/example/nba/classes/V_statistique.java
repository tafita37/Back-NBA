package com.example.nba.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class V_statistique {
    int idjoueur;
    String joueur;
    int idmatch;
    int idtypepasse;
    int nbr_rebond;
    int nbr_points;
    int third_points;
    int one_point;
    public V_statistique(int idjoueur, String joueur, int idmatch, int idtypepasse, int nbr_rebond, int nbr_points,int third_points,int one_point) {
        this.idjoueur = idjoueur;
        this.joueur = joueur;
        this.idmatch = idmatch;
        this.idtypepasse = idtypepasse;
        this.nbr_rebond = nbr_rebond;
        this.nbr_points = nbr_points;
        this.third_points = third_points;
        this.one_point = one_point;
    }
    public V_statistique() {
    }
    public int getIdjoueur() {
        return idjoueur;
    }
    public void setIdjoueur(int idjoueur) {
        this.idjoueur = idjoueur;
    }
    public String getJoueur() {
        return joueur;
    }
    public void setJoueur(String joueur) {
        this.joueur = joueur;
    }
    public int getIdmatch() {
        return idmatch;
    }
    public void setIdmatch(int idmatch) {
        this.idmatch = idmatch;
    }
    public int getIdtypepasse() {
        return idtypepasse;
    }
    public void setIdtypepasse(int idtypepasse) {
        this.idtypepasse = idtypepasse;
    }
    public int getNbr_rebond() {
        return nbr_rebond;
    }
    public void setNbr_rebond(int nbr_rebond) {
        this.nbr_rebond = nbr_rebond;
    }
    public int getNbr_points() {
        return nbr_points;
    }
    public void setNbr_points(int nbr_points) {
        this.nbr_points = nbr_points;
    }
    public int getThird_points() {
        return third_points;
    }
    public void setThird_points(int third_points) {
        this.third_points = third_points;
    } 

    public int get3points(Connection con,Joueur joueur,Match match)throws Exception{
        String sql = "SELECT COUNT(*) as total_3 FROM v_point WHERE idjoueur = ? AND idmatch = ? AND points = ? GROUP BY idjoueur, idmatch";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, joueur.getIdjoueur());
            preparedStatement.setInt(2, match.getIdmatch());
            preparedStatement.setInt(3, 3);
            ResultSet res = preparedStatement.executeQuery();            
            int point_ttl = 0;
            if (res.next()) {
                point_ttl = res.getInt("total_3");
            }

            res.close();
            return point_ttl;
        }
    }

    public int get1points(Connection con,Joueur joueur,Match match)throws Exception{
        String sql = "SELECT COUNT(*) as total_1 FROM v_point WHERE idjoueur = ? AND idmatch = ? AND points = ? GROUP BY idjoueur, idmatch";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, joueur.getIdjoueur());
            preparedStatement.setInt(2, match.getIdmatch());
            preparedStatement.setInt(3, 1);
            ResultSet res = preparedStatement.executeQuery();            
            int point_ttl = 0;
            if (res.next()) {
                point_ttl = res.getInt("total_1");
            }

            res.close();
            return point_ttl;
        }
    }

    public int getallpassedecbyjoueur(Connection con,Joueur joueur,Match match)throws Exception{
        String sql = "SELECT COUNT(*) as total_passe FROM v_passe WHERE idjoueur = ? AND idmatch = ? AND idtypepasse = ? GROUP BY idjoueur, idmatch";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, joueur.getIdjoueur());
            preparedStatement.setInt(2, match.getIdmatch());
            preparedStatement.setInt(3, 2);
            ResultSet res = preparedStatement.executeQuery();            
            int passe_ttl = 0;
            if (res.next()) {
                passe_ttl = res.getInt("total_passe");
            }

            res.close();
            return passe_ttl;
        }
    }

    public V_statistique getallstatistique(Connection con,Joueur joueur,Match match)throws Exception{
        boolean isNull = false;
        if (con == null) {
            isNull = true;
            con = Connexion.connexionPostgress("nba");
        }

        String sql = "SELECT * FROM v_statistique WHERE idjoueur = ? AND idmatch = ?";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setInt(1, joueur.getIdjoueur());
            preparedStatement.setInt(2, match.getIdmatch());
            ResultSet res = preparedStatement.executeQuery();

            V_statistique stat = new V_statistique();

            if (res.next()) {
                int idJ = res.getInt("idjoueur");
                String nomjoueur = res.getString("joueur");
                int idmatch = res.getInt("idmatch");
                int passe_total = getallpassedecbyjoueur(con, joueur, match);
                int nbr_rebond = res.getInt("nbr_rebond");
                int nbr_points = res.getInt("nbr_point");
                int third_points = get3points(con, joueur, match);
                int one_points = get1points(con, joueur, match);
                stat = new V_statistique(idJ, nomjoueur, idmatch, passe_total, nbr_rebond, nbr_points,third_points,one_points);
            }

            res.close();
            return stat;
        } finally {
            if (isNull) {
                con.close();
            }
        }
    }
    public int getOne_point() {
        return one_point;
    }
    public void setOne_point(int one_point) {
        this.one_point = one_point;
    }    

}



