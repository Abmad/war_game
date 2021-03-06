package wargame;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import wargame.ISoldat.TypesH;

import javax.imageio.ImageIO;

/**
 * Classe de gestion des montres du jeu
 */
public class Monstre extends Soldat implements java.io.Serializable {
    public static final int MAX_MONSTRES = 15;
    protected TypesM TYPE;
    private static int nbM = 0;
    private int nb = 0;
    protected int Points;
    private String Nom;
    private String Mo[] = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31", "32"};

    public Monstre(TypesM type, Position pos) {
        super(pos);
        TYPE = type;
        Random randomno = new Random();
        pointsDeVie = TYPE.getPoints();
        puissance = TYPE.getPuissance();
        tir = TYPE.getTir();
        super.type = TYPE.toString();
        porteeVisuelle = TYPE.getPortee();
//        Nom=Mo[nbM];
        nbM++;
    }

    /**
     * gere le dessin des montres
     * @param g
     * @param p
     */
    public void seDessinerM(Graphics g, PanneauJeu p) {

        g.setColor(IConfig.COULEUR_MONSTRES);
//        g.fillRect(pos.getX(), pos.getY(), Carte.TAILLE_CARRE, Carte.TAILLE_CARRE);
        g.setColor(Color.white);
//        int i = 0;
        try {
            Image bgImage = ImageIO.read(new File(this.TYPE.getICONE()));
            g.drawImage(bgImage, pos.getX(), pos.getY(), Carte.TAILLE_CARRE, Carte.TAILLE_CARRE, p);
//			g.drawImage(bgImage, 20, 250, this);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
//        g.drawString(Nom, pos.getX() + 5, pos.getY() + 15);
//        i++;

    }

    /**
     *
     * @return le nombre de montre
     */
    public static int getNbM() {
        return nbM;
    }

    /**
     *
     * @param nbH Modifie le nombre des montres
     */
    public static void setNb(int nbH) {
        Monstre.nbM = nbH;
    }

    /**
     * points return les points de vie du montre
     * @return
     */
    public int getPoints() {
        return (this.TYPE).getPoints();
    }

    /**
     * Modifie les points de vie du monstre
     * @param point
     */
    public void setPoints(int point) {

        Points = point;
    }

    /**
     *
     * @return la portee du monstre
     */
    public int getPortee() {
        return this.TYPE.getPortee();
    }

    /**
     * toString du monstre
     * @return
     */
    public String toString() {
        return this.TYPE.toString() + "( " + this.getPointsDeVie() + "," + this.getPortee() + "," + this.getPuissance() + "," + this.getTir() + ")";

    }

}
