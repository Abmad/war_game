package wargame;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import sun.jvm.hotspot.utilities.HashtableEntry;
import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

public class Carte implements ICarte {

    public static final int MAX_MAP_WIDTH = 650;
    public static final int MAX_MAP_HEIGHT = 600;
    public static final int TAILLE_CARRE = 21;

    Position p = new Position(0, 0);
    public LinkedList drawables = new LinkedList();
    protected ArrayList<Element> lesElements = new ArrayList();
    private int nb;

    public Carte() {
        int random_obsacles = (int) (Math.random() * (Obstacle.MAX_OBSTACLE - Obstacle.MIN_OBSTACLE)) + Obstacle.MIN_OBSTACLE;
        for (int i = 0; i < random_obsacles; i++) {
            Obstacle o = new Obstacle(TypeObstacle.getObstacleAlea(), new Position());
            addDrawable(o);
            lesElements.add(o);
        }
        for (int i = 0; i < Heros.MAX_HEROS; i++) {
            Heros h = new Heros(TypesH.getTypeHAlea(), new Position("Hero"));
            addDrawable(h);
            lesElements.add(h);
        }
        for (int i = 0; i < Monstre.MAX_MONSTRES; i++) {
            Monstre m = new Monstre(TypesM.getTypeMAlea(), new Position("Monstre"));
            addDrawable(m);
            lesElements.add(m);
        }
        Fenetre.lab1.setText("Il rest " + Heros.getNbH() + " Hero et " + Monstre.getNbM() + " Monstre");
        nb++;
        //System.out.println(nb);
    }

    public void addDrawable(Element d) {
        drawables.add(d);
    }

    public void removeDrawable(Element d) {
        drawables.remove(d);

    }

    public void clear() {
        drawables.clear();

    }

//    public Element getElement(Position posi) {
//
//        for (int i = 0; i < lesElements.size(); i++) {
//            if (lesElements.get(i).pos.toString().equals(posi.toString())) {
//                if (lesElements.get(i) instanceof Obstacle) {
//                   // System.out.println("Obstacle");
//                } else if (lesElements.get(i) instanceof Heros) {
//                   // System.out.println("Hero");
//                } else if (lesElements.get(i) instanceof Monstre) {
//                    //System.out.println("Monstre");
//                }
//                //System.out.println("elem");
//                return lesElements.get(i);
//            } else {
//               // System.out.println("ri");
//               // System.out.println(lesElements.get(i).pos.toString());
//            }
//
//        }
//        return null;
//
//    }

    @Override
    public Position trouvePositionVide() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    public Position trouvePositionVide(Position pos) {
        ArrayList<Position> PositonAdja = new ArrayList();
        Position p1 = new Position(pos.getX() + 20, pos.getY());
        Position p2 = new Position(pos.getX() + 20, pos.getY() + 20);
        Position p3 = new Position(pos.getX() + 20, pos.getY() - 20);
        Position p4 = new Position(pos.getX() - 20, pos.getY());
        Position p5 = new Position(pos.getX() - 20, pos.getY() + 20);
        Position p6 = new Position(pos.getX() - 20, pos.getY() - 20);
        Position p7 = new Position(pos.getX(), pos.getY() - 20);
        Position p8 = new Position(pos.getX(), pos.getY() + 20);
        Random randomno = new Random();
        for (int i = 0; i < lesElements.size(); i++) {
            if (lesElements.get(i).pos.getX() != p1.getX() && lesElements.get(i).pos.getY() != p1.getY()) {
                if (20 < p1.getX() && p1.getX() < 641 && p1.getY() < 581 && 0 < p1.getY()) {
                    PositonAdja.add(p1);
                }
            }
            if (lesElements.get(i).pos.getX() != p2.getX() && lesElements.get(i).pos.getY() != p2.getY()) {
                if (20 < p2.getX() && p2.getX() < 641 && p2.getY() < 581 && 0 < p2.getY()) {
                    PositonAdja.add(p2);
                }
            }
            if (lesElements.get(i).pos.getX() != p3.getX() && lesElements.get(i).pos.getY() != p3.getY()) {
                if (20 < p3.getX() && p3.getX() < 641 && p3.getY() < 581 && 0 < p3.getY()) {
                    PositonAdja.add(p3);
                }
            }
            if (lesElements.get(i).pos.getX() != p4.getX() && lesElements.get(i).pos.getY() != p4.getY()) {
                if (20 < p4.getX() && p4.getX() < 641 && p4.getY() < 581 && 0 < p4.getY()) {
                    PositonAdja.add(p4);
                }
            }
            if (lesElements.get(i).pos.getX() != p5.getX() && lesElements.get(i).pos.getY() != p5.getY()) {
                if (20 < p5.getX() && p5.getX() < 641 && p5.getY() < 581 && 0 < p5.getY()) {
                    PositonAdja.add(p5);
                }
            }
            if (lesElements.get(i).pos.getX() != p6.getX() && lesElements.get(i).pos.getY() != p6.getY()) {
                if (20 < p6.getX() && p6.getX() < 641 && p6.getY() < 581 && 0 < p6.getY()) {
                    PositonAdja.add(p6);
                }
            }
            if (lesElements.get(i).pos.getX() != p7.getX() && lesElements.get(i).pos.getY() != p7.getY()) {
                if (20 < p7.getX() && p7.getX() < 641 && p7.getY() < 581 && 0 < p7.getY()) {
                    PositonAdja.add(p7);
                }
            }
            if (lesElements.get(i).pos.getX() != p8.getX() && lesElements.get(i).pos.getY() != p8.getY()) {
                if (20 < p8.getX() && p8.getX() < 641 && p8.getY() < 581 && 0 < p8.getY()) {
                    PositonAdja.add(p8);
                }
            }
        }

        return PositonAdja.get(randomno.nextInt(PositonAdja.size() - 1));
    }

    public ArrayList<Heros> ArrayHeros() {
        ArrayList<Heros> H = new ArrayList();

        for (int i = 0; i < lesElements.size(); i++) {
            if (lesElements.get(i) instanceof Heros) {
                H.add((Heros) lesElements.get(i));
            }
        }
        return H;
    }

    public Heros trouveHeros() {
        ArrayList<Heros> H = new ArrayList();
        Random randomno = new Random();
        for (int i = 0; i < lesElements.size(); i++) {
            if (lesElements.get(i) instanceof Heros) {
                H.add((Heros) lesElements.get(i));
            }
        }
        return H.get(randomno.nextInt(H.size() - 1));
    }

    @Override
    public Heros trouveHeros(Position pos) {
        ArrayList<Heros> H = new ArrayList();
        ArrayList<Heros> HP = ArrayHeros();
        Random randomno = new Random();
        Position p1 = new Position(pos.getX() + 20, pos.getY());
        Position p2 = new Position(pos.getX() + 20, pos.getY() + 20);
        Position p3 = new Position(pos.getX() + 20, pos.getY() - 20);
        Position p4 = new Position(pos.getX() - 20, pos.getY());
        Position p5 = new Position(pos.getX() - 20, pos.getY() + 20);
        Position p6 = new Position(pos.getX() - 20, pos.getY() - 20);
        Position p7 = new Position(pos.getX(), pos.getY() - 20);
        Position p8 = new Position(pos.getX(), pos.getY() + 20);
        for (int i = 0; i < HP.size(); i++) {

            if (HP.get(i).pos.toString().equals(p1.toString())) {
                H.add(HP.get(i));
            } else if (HP.get(i).pos.toString().equals(p2.toString())) {
                H.add(HP.get(i));
            } else if (HP.get(i).pos.toString().equals(p3.toString())) {
                H.add(HP.get(i));
            } else if (HP.get(i).pos.toString().equals(p4.toString())) {
                H.add(HP.get(i));
            } else if (HP.get(i).pos.toString().equals(p5.toString())) {
                H.add(HP.get(i));
            } else if (HP.get(i).pos.toString().equals(p6.toString())) {
                H.add(HP.get(i));
            } else if (HP.get(i).pos.toString().equals(p7.toString())) {
                H.add(HP.get(i));
            }
            if (HP.get(i).pos.toString().equals(p8.toString())) {
                H.add(HP.get(i));
            } else {
                return null;
            }
        }

        return H.get(randomno.nextInt(H.size()));

    }

    @Override
    public boolean deplaceSoldat(Position pos, Soldat soldat) {
            removeDrawable(soldat);
            pos = pos.positionDessinable();
//            System.out.println("Deplacable position: "+pos.toString());
            soldat.seDeplace(pos);
            addDrawable(soldat);

        return false;
    }

    public void mort(Soldat perso) {
        removeDrawable(perso);
    }

    public boolean actionHeros(Position source, Position destination) {
        Element clickedElement = getElement(source);
        Element destinationElement = getElement(destination);

        if (clickedElement != null && clickedElement instanceof Heros) {
            Soldat hero = (Soldat) clickedElement;
            if(!hero.peutJouer())
                return false;
            if (clickedElement.pos.estVoisine(destination)) {
                if (destinationElement == null) {
                    //deplacement normal
                    deplaceSoldat(destination,hero);
                    return true;
                } else {
                    //atk un monstre dans une celulle adjacente
                    if(destinationElement instanceof Monstre){
                        Monstre monstre = (Monstre)destinationElement;
                        hero.combat(monstre);
                        if(monstre.getPointsDeVie()>0)
                            monstre.combat(hero);
                        else
                            mort(monstre);
                        if(hero.getPointsDeVie() == 0)
                            mort(hero);
                    }
                }
            } else {
                if (hero.getPortee() > 1) {
                    if (destinationElement != null) {
                        if(destinationElement instanceof Monstre){
                            System.out.println(destinationElement.toString());
                            Monstre monstre = (Monstre)destinationElement;
                            System.out.println(hero.estDansLaPortee(monstre));
                            if(hero.estDansLaPortee(monstre)){
                                System.out.println("In Range");
                                hero.combat(monstre);
                                if(monstre.getPointsDeVie()>0 && monstre.estDansLaPortee(hero))
                                    monstre.combat(hero);
                                else if (monstre.getPointsDeVie() == 0)
                                    mort(monstre);
                                if(hero.getPointsDeVie() == 0)
                                    mort(hero);
                            }
                        }

                    }
                }
            }
        }

        return false;
    }

    public void jouerSoldats(PanneauJeu pj) {
        Position pos = new Position();
        for (int i = 0; i < lesElements.size(); i++) {
            if (lesElements.get(i) instanceof Monstre) {
                Element e = trouveHeros();
                if (trouveHeros(lesElements.get(i).pos) == null) {
                    removeDrawable(lesElements.get(i));
                    deplaceSoldat(trouvePositionVide(lesElements.get(i).pos), ((Monstre) lesElements.get(i)));
                    addDrawable(lesElements.get(i));
                    pj.repaint();

                } else if ((trouveHeros(lesElements.get(i).pos) != null)) {
                    ((Monstre) lesElements.get(i)).combat((Soldat) e);
                    //System.out.println("zab=>simox, machi hchuma akhay simox =>alaoui");
                    if (((Soldat) e).getPointsDeVie() == 0) {
                        mort((Soldat) e);
                        pj.repaint();
                    }
                }
            }
        }
    }

    public void toutDessiner(Graphics g) {
        for (int i = 20; i < MAX_MAP_WIDTH; i = i + 20) {
            for (int j = 0; j < MAX_MAP_HEIGHT; j = j + 20) {
                g.drawRect(i, j, TAILLE_CARRE, TAILLE_CARRE);
            }
        }
        for (Iterator iter = drawables.iterator(); iter.hasNext(); ) {
            Element ob = (Element) iter.next();

            if (ob instanceof Obstacle) {

                ((Obstacle) ob).seDessiner(g);
            } else if (ob instanceof Heros) {

                ((Heros) ob).seDessinerH(g, IConfig.COULEUR_HEROS);
            } else if (ob instanceof Monstre) {

                ((Monstre) ob).seDessinerM(g);

            }

        }

    }


    public Element testPosition(Position pos) {
        for (int i = 0; i < lesElements.size(); i++) {
            if (!(lesElements.get(i).pos.toString().equals(pos.toString()))) {
                return null;// Position vide
            } else if (lesElements.get(i).pos.toString().equals(pos.toString())) {
                return lesElements.get(i);// Il'y a un monstre dans la position
            }
        }
        return null;
    }

    /**
     * retourn l'element selectionneé
     * si la case est vide retourn null si non l'element dans la case selectionnee
     */
    public Element getElement(Position clickPosition) {
        Element selectedElement = null;
        for (Iterator<Element> i = lesElements.iterator(); i.hasNext(); ) {
            selectedElement = i.next();
            if (clickPosition.getX() >= selectedElement.pos.getX() && clickPosition.getX() < selectedElement.pos.getX() + (TAILLE_CARRE - 1)
                    && clickPosition.getY() >= selectedElement.pos.getY() && clickPosition.getY() < selectedElement.pos.getY() + (TAILLE_CARRE - 1))
                break;
            else
                selectedElement = null;
        }
        return selectedElement;
    }


}
