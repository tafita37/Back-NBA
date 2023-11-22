package com.example.nba.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.nba.classes.Equipe;
import com.example.nba.classes.Joueur;
import com.example.nba.classes.Match;
import com.example.nba.classes.Passe;
import com.example.nba.classes.Point;
import com.example.nba.classes.Rebond;
import com.example.nba.classes.Typepasse;
import com.example.nba.classes.V_statistique;

@RestController
public class Controller {
    @CrossOrigin
    @GetMapping("/insert_equipe")
    public void insert_equipe(@RequestParam String equipe)throws Exception{
        Equipe e = new Equipe();
        e.insert_equipe(null, equipe);
    }
    @CrossOrigin
    @GetMapping("/insert_match")
    public void insert_match(@RequestParam String match,@RequestParam String equipe1,@RequestParam String equipe2)throws Exception{        
        Match m = new Match();
        int equi1 = Integer.valueOf(equipe1);
        int equi2 = Integer.valueOf(equipe2);
        Equipe eq1 = Equipe.getEquipeById(null, equi1);
        Equipe eq2 = Equipe.getEquipeById(null, equi2);
        m.insert_match(null, match,eq1,eq2);
    }
    @CrossOrigin
    @GetMapping("/insert_joueur")
    public void insert_joueur(@RequestParam String joueur,@RequestParam String equipe)throws Exception{
        Joueur j = new Joueur();
        int equ = Integer.valueOf(equipe);
        Equipe eq = Equipe.getEquipeById(null, equ);
        j.insert_joueur(null, joueur,eq);
    }
    @CrossOrigin
    @GetMapping("/insert_passe")
    public void insert_passe(@RequestParam String joueur,@RequestParam String match,@RequestParam String typepasse)throws Exception{
        Passe p = new Passe();
        int idj = Integer.valueOf(joueur);
        int m = Integer.valueOf(match);
        int tp = Integer.valueOf(typepasse);
        Joueur j = Joueur.getJoueurById(null, idj);
        Match matc = Match.getMatchById(null, m);
        Typepasse typepas = Typepasse.getTypepasseById(null, tp);
        p.insert_passe(null, j,matc,typepas);
    }
    @CrossOrigin
    @GetMapping("/insert_point")
    public void insert_point(@RequestParam String joueur,@RequestParam String match,@RequestParam String point)throws Exception{
        Point p = new Point();
        int idj = Integer.valueOf(joueur);
        int m = Integer.valueOf(match);
        int points = Integer.valueOf(point);
        Joueur j = Joueur.getJoueurById(null, idj);
        Match matc = Match.getMatchById(null, m);
        p.insert_point(null, j,points,matc);
    }
    @CrossOrigin
    @GetMapping("/insert_rebond")
    public void insert_rebond(@RequestParam String joueur,@RequestParam String match)throws Exception{
        Rebond r = new Rebond();
        int idj = Integer.valueOf(joueur);
        int m = Integer.valueOf(match);
        Joueur j = Joueur.getJoueurById(null, idj);
        Match matc = Match.getMatchById(null, m);
        r.insert_rebond(null, j,matc);
    }
    @CrossOrigin
    @GetMapping("/getallequipe")
    public List<Equipe> getallequipe()throws Exception{
        Equipe e = new Equipe();
        List<Equipe> list = e.getlistequipe(null);    
        return list;
    }
    @CrossOrigin
    @GetMapping("/getalljoueurbyequipe")
    public List<Joueur> getalljoueur(@RequestParam String equipe)throws Exception{
        Joueur j = new Joueur();
        int idj = Integer.valueOf(equipe);
        List<Joueur> list = j.getJoueurByIdEquipe(null,idj);    
        return list;
    }
    @CrossOrigin
    @GetMapping("/getallstat")
    public List<V_statistique> getstatbylistjoueur(@RequestParam String equipe,@RequestParam String match)throws Exception{
        V_statistique stat = new V_statistique();  
        Joueur j = new Joueur();
        int idj = Integer.valueOf(equipe);
        int m = Integer.valueOf(match);
        Match matc = Match.getMatchById(null, m);
        List<Joueur> list = j.getJoueurByIdEquipe(null,idj);            
        List<V_statistique> liststat = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            V_statistique news = stat.getallstatistique(null, list.get(i), matc);
            liststat.add(news);
        }
        return liststat;
    }
}
