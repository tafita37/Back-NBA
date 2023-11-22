package com.example.nba.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Joueur {
    int idjoueur;
    String joueur;
    Equipe equipe;
    public Joueur() {
    }
    public Joueur(int idjoueur, String joueur, Equipe equipe) {
        this.idjoueur = idjoueur;
        this.joueur = joueur;
        this.equipe = equipe;
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
    public Equipe getEquipe() {
        return equipe;
    }
    public void setEquipe(Equipe equipe) {
        this.equipe = equipe;
    }

    public static Joueur getJoueurById(Connection connex, int idjoueur) throws Exception{
        Joueur lp= new Joueur();  
        boolean isNull = false;
        if (connex == null) {
            isNull = true;
            connex = Connexion.connexionPostgress("nba");            
        }      
        try{
            Statement stat= connex.createStatement();
            String sql= "SELECT * FROM  joueur where idjoueur = " + idjoueur + "; ";
            ResultSet rs= stat.executeQuery(sql);
            
            while(rs.next()){
                Equipe e = Equipe.getEquipeById(connex,rs.getInt("idequipe"));
                lp= new Joueur(rs.getInt("idjoueur"), rs.getString("joueur"),e);
               
                
            }
        } catch(Exception e){
            throw e;
        } 
        finally{
            connex.close();
        }
        return lp;
    }

    public List<Joueur> getJoueurByIdEquipe(Connection connex, int idequipe) throws Exception{
        boolean isNull = false;
        if (connex == null) {
            isNull = true;
            connex = Connexion.connexionPostgress("nba");            
        }
        List<Joueur> list = new ArrayList<>();        
        try{
           Statement stat= connex.createStatement();
           String sql= "SELECT * FROM  joueur where idequipe = " + idequipe;
           ResultSet rs= stat.executeQuery(sql);
           
           while(rs.next()){
               Equipe e = Equipe.getEquipeById(connex,rs.getInt("idequipe"));
               Joueur j = new Joueur(rs.getInt("idjoueur"), rs.getString("joueur"), e);
                list.add(j);
               
           }
       } catch(Exception e){
           throw e;
       } 
       finally{
           connex.close();
       }
       return list;
   }

   public void insert_joueur(Connection con,String joueur,Equipe idequipe) throws Exception{
        boolean isNull = false;
        if (con == null) {
            isNull = true;
            con = Connexion.connexionPostgress("nba");            
        }
        String sql = "INSERT INTO joueur VALUES (DEFAULT , ? ,?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, joueur);
            preparedStatement.setInt(2, idequipe.getIdequipe());
            preparedStatement.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }        
    }  

}
