package wargame;

import java.util.ArrayList;
import java.util.Random;

/**
 * Classe qui permet de gerer les positions de tout les elements
 */
public class Position implements IConfig,java.io.Serializable {
    private int x, y;
    private ArrayList<Integer> x1 = new ArrayList();
    private ArrayList<Integer> x2 = new ArrayList();
    private ArrayList<Integer> x3 = new ArrayList();
    private ArrayList<Integer> y1 = new ArrayList();

    //

    /**
     * initialiser les position de depalacement de la liste
     */
    public void initlist() {
        for (int i = (Carte.TAILLE_CARRE - 1); i < Carte.MAX_MAP_WIDTH; i += (Carte.TAILLE_CARRE - 1)) {
            x1.add(i);
        }
        for (int i = (Carte.TAILLE_CARRE - 1); i < (Carte.MAX_MAP_WIDTH / 2) - (Carte.TAILLE_CARRE - 1); i += (Carte.TAILLE_CARRE - 1)) {
            x2.add(i);
        }
        for (int i = (Carte.MAX_MAP_WIDTH / 2) + 15; i < Carte.MAX_MAP_WIDTH; i += (Carte.TAILLE_CARRE - 1)) {
            x3.add(i);
        }
        for (int j = 0; j < Carte.MAX_MAP_HEIGHT; j += (Carte.TAILLE_CARRE - 1)) {
            y1.add(j);
        }
    }

    /**
     * Constructeur
     * @param x
     * @param y
     */
    Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * contructeur qui genere les position des obstacels aleatoirement
     */
    Position() {
        initlist();
        Random randomno = new Random();
        this.setX(x1.get(randomno.nextInt(32)));
        this.setY(y1.get(randomno.nextInt(30)));
    }

    /**
     * Constructeur permet de generer les position des soldats selon leur type
     *
     * @param Type
     */
    Position(String Type) {
        initlist();
        Random randomno = new Random();
        if (Type.equals("Hero")) {

            this.setX(x2.get(randomno.nextInt(15)));
            this.setY(y1.get(randomno.nextInt(30)));
        } else if (Type == "Monstre") {

            this.setX(x3.get(randomno.nextInt(15)));
            this.setY(y1.get(randomno.nextInt(30)));
        }

    }

    /**
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @return
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param x
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @param y
     */
    public void setY(int y) {
        this.y = y;
    }

    public boolean estValide() {
        if (x < 0 || x >= LARGEUR_CARTE || y < 0 || y >= HAUTEUR_CARTE)
            return false;
        else
            return true;
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    /**
     * @param pos
     * @return true si pos est voisine de la position de l'element en cours sinon false
     */
    public boolean estVoisine(Position pos) {
        int tailleCarree = Carte.TAILLE_CARRE;
        return (

//                (pos.getY()  > 0 && pos.getX()  > 0 &&
//                        pos.getY() < Carte.MAX_MAP_HEIGHT && pos.getX() > Carte.MAX_MAP_WIDTH  )&&
          (tailleCarree-1 < pos.getX() && pos.getX() < 641 && pos.getY() < 581 && 0 < pos.getY()) &&
            // si le point exist dans le rang haut ou bas
                        (((pos.getY() >= this.getY() - (tailleCarree - 1) && pos.getY() < this.getY() ||
                                pos.getY() <= this.getY() + (tailleCarree * 2) - 1 && pos.getY() > this.getY() + tailleCarree - 1)
                                &&
                                pos.getX() >= this.getX() - (tailleCarree - 1) && pos.getX() < this.getX() + (tailleCarree * 2) - 1)
                                ||
                                //si le point exist dans les cotes
                                ((pos.getY() > this.getY() && pos.getY() <= this.getY() + tailleCarree - 1) &&
                                        (pos.getX() >= this.getX() - (tailleCarree - 1) && pos.getX() < this.getX() || pos.getX() > this.getX() + tailleCarree - 1 &&
                                                pos.getX() <= this.getX() + (tailleCarree * 2) - 1))
                        )
        );

    }

    /**
     * retourn la position dessinable depuis unre position
     * @return
     */
    public Position positionDessinable() {
        int x = this.getX(), y = this.getY();

        for (int i = 0; i < Carte.TAILLE_CARRE; i++) {

            if(y%(Carte.TAILLE_CARRE-1) == 0 && x%(Carte.TAILLE_CARRE-1) == 0 && x % 10 == 0 && y % 10 == 0)
                break;
            else {
                if (y % (Carte.TAILLE_CARRE - 1) != 0 || y % 10 != 0)
                    y--;
                if (x % (Carte.TAILLE_CARRE - 1) != 0 || x % 10 != 0)
                    x--;
            }


        }
        return new Position(x,y);
    }

}