package wargame;


import javax.swing.*;
import java.util.Random;

public class Soldat extends Element implements ISoldat, java.io.Serializable {

    protected boolean jouer;
    protected int pointsDeVie;
    protected int puissance;
    protected int porteeVisuelle;
    protected int tir;

    public int getPointsDeVie() {
        return pointsDeVie;
    }

    public void setPointsDeVie(int pointsDeVie) {

        this.pointsDeVie = pointsDeVie;
        if (this.getPointsDeVie() < 0)
            this.pointsDeVie = 0;
    }

    public int getPorteeVisuelle() {
        return porteeVisuelle;
    }


    public Soldat(Position posi) {
        super(posi);
        jouer = false;
    }


    @Override
    public int getPortee() {
        return this.getPortee();
    }


    public void seDeplace(Position newPos) {
        this.pos = newPos;
        jouer = true;

    }


    public void setJouer(boolean jouer) {
        this.jouer = jouer;
    }

    @Override
    public void combat(Soldat soldat) {
        int puissanceAtk = 0;
        Random randomn = new Random();
        String typeAtk = "";
        if (this.pos.estVoisine(soldat.pos)) {
            puissanceAtk = randomn.nextInt(this.getPuissance() + 1);
            typeAtk = "puissance";

        } else {
            puissanceAtk = randomn.nextInt(this.getTir() + 1);
            typeAtk = "tir";

        }
        JLabel jlog;
        System.out.println(this.toString() + " Attaque " + soldat.toString() + " Avec " + typeAtk + ": " + puissanceAtk);
        jlog = new JLabel(this.toString() + " Attaque " + soldat.toString() + " Avec " + typeAtk + ": " + puissanceAtk);
        Fenetre.panelLog.add(jlog);
        soldat.setPointsDeVie(soldat.getPointsDeVie() - puissanceAtk);
        System.out.println(soldat.toString() + " a subit: " + puissanceAtk);
        jlog = new JLabel(soldat.toString() + " a subit: " + puissanceAtk);
        Fenetre.panelLog.add(jlog);
        System.out.println(soldat.toString() + " Lui rest: " + soldat.getPointsDeVie());
        jlog = new JLabel(soldat.toString() + " Lui rest: " + soldat.getPointsDeVie());
        Fenetre.panelLog.add(jlog);
        jouer = true;

        Fenetre.panelLog.repaint();

        this.setJouer(true);

    }

    public boolean peutJouer() {
        return !jouer;

    }

    @Override
    public int getPuissance() {
        return this.puissance;
    }

    @Override
    public int getTir() {

        return this.tir;
    }

    /**
     * @param s
     * @return true si le soldat s est dans la porte du soldat actuel
     */
    public boolean estDansLaPortee(Soldat s) {
        int carre = (Carte.TAILLE_CARRE - 1);
        int tailleCarree = carre * this.getPortee();
        Position soldatA = this.pos;
        Position soldatS = s.pos;
        return (
                ((soldatS.getX() < soldatA.getX() + tailleCarree + carre && soldatS.getX() >= soldatA.getX() + carre)
                        || (soldatS.getX() >= soldatA.getX() - tailleCarree && soldatS.getX() < soldatA.getX()) || soldatS.getX() == soldatA.getX())
                        && ((soldatS.getY() >= soldatA.getY() - tailleCarree && soldatS.getY() < soldatA.getY())
                        || ((soldatS.getY() < soldatA.getY() + tailleCarree + carre) && (soldatS.getY() >= soldatA.getY() + carre)) || soldatS.getY() == soldatA.getY())

        );
    }



}
