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

import javafx.geometry.Pos;
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


    @Override
    public Position trouvePositionVide() {
        throw new UnsupportedOperationException("Not supported yet."); // To change body of generated methods, choose
        // Tools | Templates.
    }

    @Override
    /**
     * retourn une position jouable soit un monstre dans la porte d'atk sinon une position adj ou il n'y a pas d'obstacle[
     */
    public Position trouvePositionJouableAlea(Position posElement) {
        ArrayList<Position> positonAdja = new ArrayList<Position>();
        ArrayList<Position> positonAdjaDispo = new ArrayList<Position>();
        positonAdja.add(new Position(posElement.getX() + 20, posElement.getY()));
        positonAdja.add(new Position(posElement.getX() + 20, posElement.getY() + 20));
        positonAdja.add(new Position(posElement.getX() + 20, posElement.getY() - 20));
        positonAdja.add(new Position(posElement.getX() - 20, posElement.getY()));
        positonAdja.add(new Position(posElement.getX() - 20, posElement.getY() + 20));
        positonAdja.add(new Position(posElement.getX() - 20, posElement.getY() - 20));
        positonAdja.add(new Position(posElement.getX(), posElement.getY() - 20));
        positonAdja.add(new Position(posElement.getX(), posElement.getY() + 20));

        Random randomno = new Random();
        for (Iterator<Position> i = positonAdja.iterator(); i.hasNext(); ) {
            Position position = i.next();
            if (!(getElement(position) instanceof Obstacle)) {
                if (20 < position.getX() && position.getX() < 641 && position.getY() < 581 && 0 < position.getY())
                    positonAdjaDispo.add(position);
            }

        }
        return positonAdjaDispo.get(randomno.nextInt(positonAdjaDispo.size() - 1));
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
        soldat.setJouer(true);
        return false;
    }

    public void mort(Soldat perso) {
        removeDrawable(perso);
    }

    public boolean actionHeros(Position source, Position destination) {

        Element clickedElement = getElement(source);
        Element destinationElement = getElement(destination);
        System.out.println("in action hero");
        if (clickedElement != null && clickedElement instanceof Heros) {
            Soldat hero = (Soldat) clickedElement;
            System.out.println(hero.peutJouer());
            if (!hero.peutJouer())
                return false;
            if (clickedElement.pos.estVoisine(destination)) {
                if (destinationElement == null) {
                    //deplacement normal
                    deplaceSoldat(destination, hero);
                    return true;
                } else {
                    //atk un monstre dans une celulle adjacente
                    if (destinationElement instanceof Monstre) {
                        Monstre monstre = (Monstre) destinationElement;
                        hero.combat(monstre);
                        if (monstre.getPointsDeVie() > 0)
                            monstre.combat(hero);
                        else
                            mort(monstre);
                        if (hero.getPointsDeVie() == 0)
                            mort(hero);
                    }
                }
            } else {
                if (hero.getPortee() > 1) {
                    if (destinationElement != null) {
                        if (destinationElement instanceof Monstre) {
                            System.out.println(destinationElement.toString());
                            Monstre monstre = (Monstre) destinationElement;
                            System.out.println(hero.estDansLaPortee(monstre));
                            if (hero.estDansLaPortee(monstre)) {
                                System.out.println("In Range");
                                hero.combat(monstre);
                                if (monstre.getPointsDeVie() > 0 && monstre.estDansLaPortee(hero))
                                    monstre.combat(hero);
                                else if (monstre.getPointsDeVie() == 0)
                                    mort(monstre);
                                if (hero.getPointsDeVie() == 0)
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
        for (Iterator<Element> i = lesElements.iterator(); i.hasNext(); ) {
            Element element = i.next();
            if (element instanceof Monstre) {
                //l'element est un monstre
                Monstre monstre = (Monstre) element;
                for (Iterator<Element> j = lesElements.iterator(); j.hasNext(); ) {
                    Element otherElement = j.next();
                    if (otherElement instanceof Heros) {
                        Heros hero = (Heros) otherElement;
                        if (monstre.estDansLaPortee(hero)) {
                            monstre.combat(hero);
                            if (hero.getPointsDeVie() <= 0)
                                mort(hero);
                            break;
                        }
                    }
                }
                Position posAlea = trouvePositionJouableAlea(monstre.pos);
//                System.out.println(posAlea.toString());
                Element elementAdj = getElement(posAlea);
//                System.out.println((elementAdj == null));
                if (elementAdj == null) {
                    deplaceSoldat(posAlea, monstre);
                } else {
                    if (elementAdj instanceof Heros) {
                        Heros hero = (Heros) elementAdj;
                        monstre.combat(hero);
                        if (hero.getPointsDeVie() <= 0)
                            mort(hero);
                    }
                }
            } else if (element instanceof Heros) {
                Heros hero = (Heros) element;
                if (hero.peutJouer()) {
                    hero.seReposer();
                }
            }

        }
        for (Iterator<Element> j = lesElements.iterator(); j.hasNext(); ) {
            Element element = j.next();
            if(element instanceof Soldat){
                ((Soldat)element).setJouer(false);
            }
        }
        System.out.println("end jouser soldat");
        pj.repaint();

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


    /**
     * @param clickPosition
     * @return si la case est vide retourn null si non l'element dans la case selectionnee
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
