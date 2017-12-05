package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import wargame.Obstacle.TypeObstacle;

public class Heros extends Soldat {

    public static final int MAX_HEROS = 10;
    protected TypesH TYPE;
    private static int nbH = 0;
    private int nb = 0;
    protected static int tour = 0;
    protected int Points;
    protected Carte carte;
    private String Nom;
    private String H[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Z"};

    public Heros(TypesH type, Position pos) {
        super(pos);
        TYPE = type;
        pointsDeVie = TYPE.getPoints();
        puissance = TYPE.getPuissance();
        tir = TYPE.getTir();
        porteeVisuelle = TYPE.getPortee();
        Nom=H[nbH];
        nbH++;
    }

    public void seDessinerH(Graphics g, Color Couleur) {
        g.setColor(Couleur);
        g.fillRect(pos.getX(), pos.getY(), Carte.TAILLE_CARRE, Carte.TAILLE_CARRE);
        g.setColor(Color.white);
        int i = 0;

        g.drawString(Nom, pos.getX() + 5, pos.getY() + 15);
        i++;

    }

    public static int getNbH() {
        return nbH;
    }

    public void setNbH(int nbH) {
        this.nbH = nbH;
    }

    public int getPoints() {
        return (this.TYPE).getPoints();

    }

    public void setPoints(int point) {

        Points = point;
    }
    public int getPortee() {
        return this.TYPE.getPortee();																		// Tools | Templates.
    }



    public void seDeplace(Position newPos) {
        this.pos = newPos;
        tour++;
    }

    public String toString() {
        return this.TYPE.toString() + "( " + this.getPointsDeVie() + "," + this.getPortee() + "," + this.getPuissance() +","+ this.getTir()+ ")";

    }

    public void seReposer(){
        int pdv = this.getPointsDeVie();
        pdv += 5;
        if(pdv >= this.TYPE.getPoints())
            pdv = this.TYPE.getPoints();
        this.setPointsDeVie(pdv);
        jouer = true;
    }

}
