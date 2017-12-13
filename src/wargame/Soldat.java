package wargame;


import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * classe qui gere les soldats (Montres et heros)
 */
public class Soldat extends Element implements ISoldat, java.io.Serializable {

    protected boolean jouer;
    protected int pointsDeVie;
    protected int puissance;
    protected int porteeVisuelle;
    protected int tir;
    protected String type;


    /**
     * retourn les points de vie du soldat
     *
     * @return
     */
    public int getPointsDeVie() {
        return pointsDeVie;
    }

    /**
     * modifie les points de vies du soldat
     *
     * @param pointsDeVie
     */
    public void setPointsDeVie(int pointsDeVie) {

        this.pointsDeVie = pointsDeVie;
        if (this.getPointsDeVie() < 0)
            this.pointsDeVie = 0;
    }

    /**
     * retourne la porte
     *
     * @return
     */
    public int getPorteeVisuelle() {
        return porteeVisuelle;
    }

    public String getType() {
        return type;
    }

    public Soldat(Position posi) {
        super(posi);
        jouer = false;
    }

    /**
     * retourn la porte du soldat
     *
     * @return
     */
    @Override
    public int getPortee() {
        return this.getPortee();
    }

    /**
     * gere le deplacement du soldat
     *
     * @param newPos
     */
    public void seDeplace(Position newPos) {
        this.pos = newPos;
        jouer = true;

    }

    /**
     * modifie la variable qui definit si le soldat a joue ou pas
     *
     * @param jouer
     */
    public void setJouer(boolean jouer) {
        this.jouer = jouer;
    }

    @Override
    public void combat(Soldat soldat) {
        int puissanceAtk = 0;
        Random randomn = new Random();
        String typeAtk = "";
//        System.out.println(this.toString()+this.pos.estVoisine(soldat.pos));
        if (this.pos.estVoisine(soldat.pos) || soldat.pos.estVoisine(this.pos)) {
            puissanceAtk = randomn.nextInt(this.getPuissance() + 1);
            typeAtk = "puissance";

        } else {
            puissanceAtk = randomn.nextInt(this.getTir() + 1);
            typeAtk = "tir";

        }
        Fenetre.cptNbrLogs += 8;
        if (Fenetre.cptNbrLogs > Fenetre.CPT_JLAB_LOG_INIT) {
            Fenetre.panelLogContainer.setLayout(new GridLayout(Fenetre.cptNbrLogs, 1));
        }
        Fenetre.__add_message_to_jlabel("| "+this.toString(),this);
        Fenetre.__add_message_to_jlabel("| | Attaque");
        Fenetre.__add_message_to_jlabel("| | " + soldat.getType(),soldat);
        Fenetre.__add_message_to_jlabel("| | Avec " + typeAtk + ": " + puissanceAtk);
        soldat.setPointsDeVie(soldat.getPointsDeVie() - puissanceAtk);
        Fenetre.__add_message_to_jlabel("| | " + soldat.getType(),soldat);
        Fenetre.__add_message_to_jlabel("| | Subit: " + puissanceAtk);
        Fenetre.__add_message_to_jlabel("| | Lui rest: " + soldat.getPointsDeVie());
        Fenetre.__add_message_to_jlabel("|____________________");
        jouer = true;

        Fenetre.panelLogContainer.revalidate();
        JScrollBar sb = Fenetre.jsp.getVerticalScrollBar();
        sb.setValue(sb.getMaximum());
        this.setJouer(true);

    }

    /**
     * retourne la puissance du soldat
     *
     * @return
     */
    public boolean peutJouer() {
        return !jouer;

    }

    /**
     * retourne la puissance du soldat
     *
     * @return
     */
    @Override
    public int getPuissance() {
        return this.puissance;
    }

    /**
     * @return
     */
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
