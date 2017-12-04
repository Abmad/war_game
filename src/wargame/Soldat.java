package wargame;


import java.util.Random;

public class Soldat extends Element implements ISoldat {

    protected int tour;
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

    public void setPuissance(int puissance) {
        this.puissance = puissance;
    }

    public int getPorteeVisuelle() {
        return porteeVisuelle;
    }

    public void setPorteeVisuelle(int porteeVisuelle) {
        this.porteeVisuelle = porteeVisuelle;
    }

    public void setTir(int tir) {
        this.tir = tir;
    }

    public Soldat(Position posi) {
        super(posi);
        jouer = false;
    }

    public void setTour(int tour) {
        this.tour = tour;
    }

    @Override
    public int getTour() {
        return tour;
    }


    @Override
    public int getPortee() {
        return  this.getPortee();
    }

    @Override
    public void joueTour(int tour) {
        this.setTour(getTour() + tour);

    }

    public void seDeplace(Position newPos) {
        this.pos = newPos;
        jouer = true;

    }

    @Override
    public void combat(Soldat soldat) {
        int puissanceAtk;

        Random randomn = new Random();
        if(soldat.pos.estVoisine(this.pos)){
            puissanceAtk = randomn.nextInt(this.getPuissance());

        }else{
            puissanceAtk =  randomn.nextInt(this.getTir());

        }
        soldat.setPointsDeVie(soldat.getPointsDeVie()-puissanceAtk);

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
        System.out.println("This: "+this.toString()+this.pos.toString());
        System.out.println("s: "+s.toString()+s.pos.toString());
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
