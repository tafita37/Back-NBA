package com.example.nba.classes;

import java.time.LocalTime;

public class Temps {
    int idtemps;
    Joueur joueur;
    Match idmatch;
    LocalTime debut;
    LocalTime fin;
    public Temps() {
    }
    public Temps(int idtemps, Joueur joueur,Match idmatch ,LocalTime debut, LocalTime fin) {
        this.idtemps = idtemps;
        this.joueur = joueur;
        this.idmatch = idmatch;
        this.debut = debut;
        this.fin = fin;
    }
    public int getIdtemps() {
        return idtemps;
    }
    public void setIdtemps(int idtemps) {
        this.idtemps = idtemps;
    }
    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public LocalTime getDebut() {
        return debut;
    }
    public void setDebut(LocalTime debut) {
        this.debut = debut;
    }
    public LocalTime getFin() {
        return fin;
    }
    public void setFin(LocalTime fin) {
        this.fin = fin;
    }
    public Match getIdmatch() {
        return idmatch;
    }
    public void setIdmatch(Match idmatch) {
        this.idmatch = idmatch;
    }
}
