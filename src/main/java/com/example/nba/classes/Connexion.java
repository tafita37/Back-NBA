/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.nba.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author TAFITA
 */
public class Connexion {
    public static Connection connexionOracle(String user, String mdp)
    throws Exception {
        Connection con=null;
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL",user, mdp); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return con;
    }

    public static Connection connexionPostgress(String database)
    throws Exception {
        Connection con=null;
        try {
            Class.forName("org.postgresql.Driver");
            con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/"+database,"postgres","227922"); 
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        } catch(SQLException e) {
            e.printStackTrace();
            throw e;
        }
        return con;
    }
    public static Connection connexionMysql(String database)
    throws Exception {
        Connection con=null;
        String url = "jdbc:mysql://localhost:3306/"+database;
        String utilisateur = "root";
        String motDePasse = "";

        try {
            
            con = DriverManager.getConnection(url, utilisateur, motDePasse);
            System.out.println("Connexion réussie à la base de données MySQL !");
                    
        } catch (SQLException e) {
            System.err.println("Erreur de connexion : " + e.getMessage());
        }
        return con;
    }
}
