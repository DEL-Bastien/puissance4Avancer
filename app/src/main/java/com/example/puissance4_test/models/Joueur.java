package com.example.puissance4_test.models;

import java.util.ArrayList;
import java.util.List;

public class Joueur {
    public static List<Joueur> joueurs = new ArrayList<Joueur>();
    private Integer id;
    private String pseudo;
    private Color color;
    protected List<Pion> pions = new ArrayList<Pion>();

    public Joueur(Color color) {
        joueurs.add(this);
        this.id = joueurs.size();
        this.pseudo = "Joueur "+color.name();
        this.color = color;
        initTokens();
    }

    public void initTokens(){
        for (int i=0; i < 21;i++){
            Pion pion = new Pion(this);
            pions.add(pion);
        }
    }

    public Color getColor() {
        return color;
    }

    public Integer getId() {
        return id;
    }

    public String getPseudo() {
        return pseudo;
    }


    public Space placePion(int x){
        Space space = Plateau.getInstance().getNextSpaceAvailable(x);
        Pion pion = new Pion(this);

        if(pion.getJoueur() == null)
            return null;

        Plateau.getInstance().getSpaces()[x][space.getY()].setContent(pion);

        return space;
    }

    public List<Pion> getPions() {
        return pions;
    }
}
