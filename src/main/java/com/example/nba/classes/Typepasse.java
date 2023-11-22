package com.example.nba.classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Typepasse {
    int idtypepasse;
    String type;
    public Typepasse() {
    }
    public Typepasse(int idtypepasse, String type) {
        this.idtypepasse = idtypepasse;
        this.type = type;
    }
    public int getIdtypepasse() {
        return idtypepasse;
    }
    public void setIdtypepasse(int idtypepasse) {
        this.idtypepasse = idtypepasse;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }

    public static Typepasse getTypepasseById(Connection connex, int typepasse) throws Exception {
        Typepasse lp = new Typepasse();
        boolean isNull = false;
        
        if (connex == null) {
            isNull = true;
            connex = Connexion.connexionPostgress("nba");            
        }
        
        try {
            Statement stat = connex.createStatement();
            String sql = "SELECT * FROM type_passe WHERE idtypepasse = " + typepasse + ";";
            ResultSet rs = stat.executeQuery(sql);
            
            if (rs.next()) {
                lp = new Typepasse(rs.getInt("idtypepasse"), rs.getString("type"));
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
    
}

