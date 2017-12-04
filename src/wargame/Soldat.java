package wargame;


import java.util.Random;

public class Soldat extends Element implements ISoldat {

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
        if(this.getPointsDeVie() < 0)
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
        return  this.getPortee();
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
        System.out.println(soldat.pos.estVoisine(this.pos));
        if(soldat.pos.estVoisine(this.pos)){
            puissanceAtk = randomn.nextInt(this.getPuissance());

        }else if(this.getPorteeVisuelle() > 1){
            puissanceAtk =  randomn.nextInt(this.getTir());

        }
        soldat.setPointsDeVie(soldat.getPointsDeVie()-puissanceAtk);
        jouer = true;

    }

    public boolean peutJouer(){
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
     *
     * @param s
     * @return true si le soldat s est dans la porte du soldat actuel
     */
    public boolean estDansLaPortee(Soldat s){
        int tailleCarree = Carte.TAILLE_CARRE * this.getPortee();
        Position soldatActuel = this.pos;
        Position soldatS= s.pos;
        return (

//                (pos.getY()  > 0 && pos.getX()  > 0 &&
//                        pos.getY() < Carte.MAX_MAP_HEIGHT && pos.getX() > Carte.MAX_MAP_WIDTH  )&&
                // si le point exist dans le rang haut ou bas
                ((((soldatS.getY() >= soldatActuel.getY() - (tailleCarree - 1) && soldatS.getY() < soldatActuel.getY()) ||
                        soldatS.getY() <= soldatActuel.getY() + (tailleCarree + (tailleCarree/this.getPortee())) - 1 && soldatS.getY() > soldatActuel.getY() + tailleCarree - 1)
                        &&
                        soldatS.getX() >= soldatActuel.getX() - (tailleCarree - 1) && soldatS.getX() < soldatActuel.getX() + (tailleCarree * (tailleCarree/this.getPortee())) - 1)
                        ||
                        //si le point exist dans les cotes
                        ((soldatS.getY() > soldatActuel.getY() && soldatS.getY() <= soldatActuel.getY() + tailleCarree - 1) &&
                                (soldatS.getX() >= soldatActuel.getX() - (tailleCarree - 1) && soldatS.getX() < soldatActuel.getX() || soldatS.getX() > soldatActuel.getX() + tailleCarree - 1 &&
                                        soldatS.getX() <= soldatActuel.getX() + (tailleCarree * (tailleCarree/this.getPortee())) - 1))
                )
        );
    }


}
