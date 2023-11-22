package com.example.nba.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Equipe {
    int idequipe;
    String equipe;
    Joueur[] listejoueur;
    public Equipe(int idequipe, String equipe) {
        this.idequipe = idequipe;
        this.equipe = equipe;
    }
    public Equipe(int idequipe, String equipe, Joueur[] listejoueur) {
        this.idequipe = idequipe;
        this.equipe = equipe;
        this.listejoueur = listejoueur;
    }
    public Equipe() {
    }
    public int getIdequipe() {
        return idequipe;
    }
    public void setIdequipe(int idequipe) {
        this.idequipe = idequipe;
    }
    public String getEquipe() {
        return equipe;
    }
    public void setEquipe(String equipe) {
        this.equipe = equipe;
    }
    public Joueur[] getListejoueur() {
        return listejoueur;
    }

    public void setListejoueur(Joueur[] listejoueur) {
        this.listejoueur = listejoueur;
    }       

    public List<Equipe> getlistequipe (Connection connex)throws Exception{
        List<Equipe> list = new ArrayList<>();
        boolean isNull = false;
        if (connex == null) {
            isNull = true;
            connex = Connexion.connexionPostgress("nba");            
        }
        try{
            Statement stat= connex.createStatement();
            String sql= "SELECT * FROM  equipe";
            ResultSet rs= stat.executeQuery(sql);
            
            while(rs.next()){
                Equipe e = new Equipe(rs.getInt("idequipe"), rs.getString("equipe"));
                list.add(e);                              
            }
        } catch(Exception e){
            throw e;
        } 
        finally{
            connex.close();
        }
        return list;
    }
    
    public static  Equipe getEquipeById(Connection connex,int idequipe) throws Exception{
        Equipe lp= new Equipe();        
        boolean isNull = false;
        if (connex == null) {
            isNull = true;
            connex = Connexion.connexionPostgress("nba");            
        }
        try{
            Statement stat= connex.createStatement();
            String sql= "SELECT * FROM  equipe where idequipe = " + idequipe + "; ";
            ResultSet rs= stat.executeQuery(sql);
            
            if(rs.next()){
                lp= new Equipe(rs.getInt("idequipe"), rs.getString("equipe"));                           
            }
        } catch(Exception e){
            throw e;
        } 
        finally{
            connex.close();
        }
        return lp;
    }

    public void insert_equipe(Connection con,String nomequipe) throws Exception{
        boolean isNull = false;
        if (con == null) {
            isNull = true;
            con = Connexion.connexionPostgress("nba");            
        }
        String sql = "INSERT INTO equipe VALUES (DEFAULT , ?)";
        try (PreparedStatement preparedStatement = con.prepareStatement(sql)) {
            preparedStatement.setString(1, nomequipe);
            preparedStatement.executeUpdate();            
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        } finally {
            con.close();
        }        
    } 

}
