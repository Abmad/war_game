package wargame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import wargame.Obstacle.TypeObstacle;

import javax.imageio.ImageIO;

/**
 * Permet de gerer les heros du jeu
 */
public class Heros extends Soldat implements java.io.Serializable {

    public static final int MAX_HEROS = 10;
    protected TypesH TYPE;
    private static int nbH = 0;
    protected static int tour = 0;
    protected int Points;
    protected Carte carte;
    private String Nom;
    private String H[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X", "Z"};

    /**
     * Constructeur de la classe Hero
     * @param type
     * @param pos
     */
    public Heros(TypesH type, Position pos) {
        super(pos);
        TYPE = type;
        pointsDeVie = TYPE.getPoints();
        puissance = TYPE.getPuissance();
        tir = TYPE.getTir();
        porteeVisuelle = TYPE.getPortee();
//        Nom=H[nbH];
        nbH++;
    }

    /**
     * Permet de dessiner le heros
     * @param g
     * @param Couleur
     * @param p
     */
    public void seDessinerH(Graphics g, Color Couleur,PanneauJeu p) {
        g.setColor(Couleur);
        g.setColor(Color.white);
        try {
            Image bgImage = ImageIO.read(new File(this.TYPE.getICONE()));
            g.drawImage(bgImage, pos.getX() , pos.getY(),Carte.TAILLE_CARRE,Carte.TAILLE_CARRE,p);
        }catch (IOException ex){
            System.out.println(ex.getMessage());
        }

    }

    /**
     *   getter du Nombre de heros
     * @return
     */
    public static int getNbH() {
        return nbH;
    }

    /**
     *  setter du Nombre de heros
     * @param nbH
     */
    public static void setNbH(int nbH) {
        Heros.nbH = nbH;
    }

    /**
     *
     * @return les points d'un heros
     */
    public int getPoints() {
        return (this.TYPE).getPoints();

    }

    /**
     *
     * @param point
     */
    public void setPoints(int point) {

        Points = point;
    }

    /**
     *
     * @return la portee d'un hero
     */
    public int getPortee() {
        return this.TYPE.getPortee();																		// Tools | Templates.
    }


    /**
     * gere le deplacement de l'hero
     * @param newPos position cible
     */
    public void seDeplace(Position newPos) {
        this.pos = newPos;
        tour++;
    }

    /**
     *  toString de la classe heros
     * @return
     */
    public String toString() {
        return this.TYPE.toString()+"\n"
                + "PDV:" + this.getPointsDeVie() +" | PO:"+ this.getPortee() + "\n"
                + "PS:" +this.getPuissance() +" | TIR: "+ this.getTir()+ ")";

    }

    /**
     *  gere le repos du heros
     */
    public void seReposer(){
        int pdv = this.getPointsDeVie();
        pdv += 5;
        if(pdv >= this.TYPE.getPoints())
            pdv = this.TYPE.getPoints();
        this.setPointsDeVie(pdv);
        jouer = true;
    }

}
