package com.example.puissance4_test.models;

import java.util.ArrayList;

public class Plateau implements Cloneable {
    private static Plateau instance;
    public Space[][] spaces;
    public static int width = 5;
    public static int height = 5;

    private Plateau(int width, int height) {
        this.width = width;
        this.height = height;
        this.spaces = new Space[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                spaces[w][h] = new Space(w, h);
            }
        }
    }

    public static Plateau getInstance(){
        if(instance==null){
            instance = new Plateau(width,height);
        }
        return instance;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean checkVictory(Space lastSpace){
        if(checkColumn(lastSpace) || checkRow(lastSpace) || checkDiagonal(lastSpace)){
            return true;
        }
        return false;
    }

    private boolean checkRow(Space lastSpace) {
        boolean res = false;
        Joueur joueur = lastSpace.getContent().getJoueur();
        int i = 0;
        int y = lastSpace.getY();
        while(!res && i < width){
            int newX = lastSpace.getX() + (i - 3);
            if(isNotOut(newX,y)){
                int j = 0;
                do{
                    if(isNotOut(newX+j,y) &&!spaces[newX + j][y].isAvailable())
                        res = joueur.equals(spaces[newX + j][y].getContent().getJoueur());
                    else
                        res = false;
                    j++;
                }while (res && j<4);
            }
            i++;
        }
        return res;
    }

    private boolean checkColumn(Space lastSpace) {
        boolean res = false;
        Joueur joueur = lastSpace.getContent().getJoueur();
        int i = 0;
        int x = lastSpace.getX();
        while(!res && i < 4){
            int newY = lastSpace.getY() - (i + 3);
            if(isNotOut(x,newY)){
                int j = 0;
                do{
                    if(isNotOut(x,newY+j) && !spaces[x][newY + j].isAvailable())
                        res = joueur.equals(spaces[x][newY + j].getContent().getJoueur());
                    else
                        res = false;
                    j++;
                }while (res && j<4);
            }
            i++;
        }
        return res;
    }

    private boolean checkDiagonal(Space lastSpace) {
        return checkRisingDiagonal(lastSpace)||checkFailingDiagonal(lastSpace);
    }

    public Space[][] getSpaces() {
        return spaces;
    }


    public Space getNextSpaceAvailable(int x) {
        int y = 0;
        Space currentSpace = Plateau.getInstance().spaces[x][y];
        if (!currentSpace.isAvailable()) {
            while (!currentSpace.isAvailable() && y < height-1) {
                currentSpace = this.getNextSpace(currentSpace);
                y++;
            }
        }
        return currentSpace;
    }

    public Space getNextSpace(Space space){
        return Plateau.getInstance().spaces[space.getX()][space.getY()+1];
    }

    public Space[] getSpacesToPlace(){
        Space[] res = new Space[width];
        for(int y = 0;y<instance.getWidth();y++){
            res[y]=getNextSpaceAvailable(y);
        }
        return res;
    }

    @Override
    protected Plateau clone() throws CloneNotSupportedException {
        return (Plateau) super.clone();
    }

    public static Plateau manualClone(){
        Plateau initPlateau = Plateau.getInstance();
        Plateau returnedPlateau = new Plateau(width,height);
        returnedPlateau.spaces = initPlateau.spaces;
        return returnedPlateau;
    }

    private boolean isNotOut(int x,int y){
        return (x >= 0 && x <= width - 1)&&(y >=0 && y <= height-1);
    }

    public boolean checkFailingDiagonal(Space initSpace){
        boolean res = false;
        Joueur joueur = initSpace.getContent().getJoueur();
        int i = 0;
        while(!res && i < height){
            int newX = initSpace.getX() - (3 - i);
            int newY = initSpace.getY() - (i - 3);

            if(isNotOut(newX,newY)){
                int j = 0;
                do{
                    if(isNotOut(newX + j,newY - j) && !spaces[newX + j][newY - j].isAvailable())
                        res = joueur.equals(spaces[newX + j][newY - j].getContent().getJoueur());
                    else
                        res = false;
                    j++;
                }while (res && j<4);
            }
            i++;
        }
        return res;
    }

    public boolean checkRisingDiagonal(Space initSpace){
        boolean res = false;
        Joueur joueur = initSpace.getContent().getJoueur();
        int i = 0;
        while(!res && i < width){
            int newX = initSpace.getX() + (i - 3);
            int newY = initSpace.getY() + (i - 3);
            if(isNotOut(newX,newY)){
                int j = 0;
                do{
                    if(isNotOut(newX + j,newY + j) && !spaces[newX + j][newY + j].isAvailable())
                        res = joueur.equals(spaces[newX + j][newY + j].getContent().getJoueur());
                    else
                        res = false;
                    j++;
                }while (res && j<4);
            }
            i++;
        }
        return res;
    }

    public static void renew(){
        instance = new Plateau(width,height);
        Joueur.joueurs = new ArrayList<Joueur>();
    }
}
