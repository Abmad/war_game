package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import javax.swing.*;

import com.sun.tools.javah.Gen;
import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

/**
 * permet de gerer la carte du jeu
 */
public class Carte implements ICarte, java.io.Serializable {

    public static final int MAX_MAP_WIDTH = 650;
    public static final int MAX_MAP_HEIGHT = 600;
    public static final int TAILLE_CARRE = 21;
    private boolean repaintPortee = false;
    /**
     * list contenant les elements dessinable
     */
    public LinkedList drawables = new LinkedList();
    protected ArrayList<Element> lesElements = new ArrayList();
    protected ArrayList<Position> porteDeplacement = new ArrayList<>();

    /**
     * Constructeur de la classe carte
     */
    public Carte() {
        Heros.setNbH(0);
        Monstre.setNb(0);
        int random_obsacles = (int) (Math.random() * (Obstacle.MAX_OBSTACLE - Obstacle.MIN_OBSTACLE)) + Obstacle.MIN_OBSTACLE;
        for (int i = 0; i < random_obsacles; i++) {
            Obstacle o = new Obstacle(TypeObstacle.getObstacleAlea(), new Position());
            addDrawable(o);
            lesElements.add(o);
        }
        for (int i = 0; i < Heros.MAX_HEROS; i++) {
            Position p = new Position("Hero");
            if (getElement(p) == null) {
                Heros h = new Heros(TypesH.getTypeHAlea(), p);
                addDrawable(h);
                lesElements.add(h);
//                lesSoldats.add(h);
            }
        }
        for (int i = 0; i < Monstre.MAX_MONSTRES; i++) {
            Position p = new Position("Monstre");
            if (getElement(p) == null) {
                Monstre m = new Monstre(TypesM.getTypeMAlea(), p);
                addDrawable(m);
                lesElements.add(m);
//                lesSoldats.add(m);
            }
        }
        Fenetre.lab1.setText("Il rest " + Heros.getNbH() + " Hero et " + Monstre.getNbM() + " Monstre");

    }

    /**
     * @param d element a ajouter dans la list drawables
     */
    public void addDrawable(Element d) {
        drawables.add(d);
    }

    /**
     * @param d element a supprimer de la list drawables
     */
    public void removeDrawable(Element d) {
        drawables.remove(d);

    }


    @Override
    /**
     * @param posElement position de l'element
     * retourn une position adj ou il n'y a pas d'obstacle si il y'a un hero ou plus retourne le heros ayant les points de vies les plus bas
     */

    public Position trouvePositionJouableAlea(Position posElement) {
        ArrayList<Position> positonAdja = new ArrayList<Position>();
        ArrayList<Position> positonAdjaDispo = new ArrayList<Position>();
        ArrayList<Position> positonHeroAdjaDispo = new ArrayList<Position>();
        Position returnedPosition;
        positonAdja.add(new Position(posElement.getX() + 20, posElement.getY()));
        positonAdja.add(new Position(posElement.getX() + 20, posElement.getY() + 20));
        positonAdja.add(new Position(posElement.getX() + 20, posElement.getY() - 20));
        positonAdja.add(new Position(posElement.getX() - 20, posElement.getY()));
        positonAdja.add(new Position(posElement.getX() - 20, posElement.getY() + 20));
        positonAdja.add(new Position(posElement.getX() - 20, posElement.getY() - 20));
        positonAdja.add(new Position(posElement.getX(), posElement.getY() - 20));
        positonAdja.add(new Position(posElement.getX(), posElement.getY() + 20));

        Random randomno = new Random();
        for (int i = 0; i < positonAdja.size(); i++) {
            Position position = positonAdja.get(i);
            Element element = getElement(position);
            if (!(element instanceof Obstacle)) {
                if (20 < position.getX() && position.getX() < 641 && position.getY() < 581 && 0 < position.getY()) {
                    if (element instanceof Heros)
                        positonHeroAdjaDispo.add(position);
                    else
                        positonAdjaDispo.add(position);
                }
            }
        }
        Position posHeroMinPdv;
        if (positonHeroAdjaDispo.size() > 1) {
            posHeroMinPdv = positonHeroAdjaDispo.get(0);
            for (int i = 1; i < positonHeroAdjaDispo.size(); i++) {
                Heros hero1 = (Heros) getElement(positonHeroAdjaDispo.get(i));
                Heros hero2 = (Heros) getElement(posHeroMinPdv);
                if (hero1.getPointsDeVie() < hero2.getPointsDeVie())
                    posHeroMinPdv = positonHeroAdjaDispo.get(i);
            }
            returnedPosition = posHeroMinPdv;
        } else if (positonHeroAdjaDispo.size() == 1)
            returnedPosition = positonHeroAdjaDispo.get(0);
        else {
            returnedPosition = positonAdjaDispo.get(randomno.nextInt(positonAdjaDispo.size() - 1));
        }
        return returnedPosition;
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

    @Override
    /**
     * @param pos position cible
     * @param soldat le soldat a deplacer
     */
    public boolean deplaceSoldat(Position pos, Soldat soldat) {
        pos = pos.positionDessinable();
        int diffX = pos.getX() - soldat.pos.getX();
        int diffY = pos.getY() - soldat.pos.getY();

        if (((diffX < (TAILLE_CARRE - 1) * 2 && diffX >= 0) || (diffX > ((TAILLE_CARRE - 1) * (-2))) && diffX <= 0) && ((diffY < (TAILLE_CARRE - 1) * 2 && diffY >= 0) || (diffY > ((TAILLE_CARRE - 1) * (-2))) && diffY <= 0)) {
            removeDrawable(soldat);
            soldat.seDeplace(pos);
            addDrawable(soldat);
            soldat.setJouer(true);
            return true;
        }
        return false;
    }

    /**
     * @param perso le soldat qui va mourire ( qui a les points de vies reduient a 0)
     */
    public void mort(Soldat perso) {
        boolean finJeu = false;
        String message = "";
        removeDrawable(perso);
        lesElements.remove(perso);
        if (perso instanceof Monstre) {
            int nbMontres = Monstre.getNbM();
            nbMontres--;
            Monstre.setNb(nbMontres);
            if (nbMontres == 0) {
                finJeu = true;
                message = "Vous avez gagner! vous avez rendu la paix dans le monde!";

            }
        } else if (perso instanceof Heros) {
            int nbHeros = Heros.getNbH();
            nbHeros--;
            Heros.setNbH(nbHeros);
            if (nbHeros == 0) {
                finJeu = true;
                message = "Dommage! Vous avez perdu! Essayer une nouvelle partie?";
            }
        }
        Fenetre.__add_message_to_jlabel(perso.toString());
        Fenetre.__add_message_to_jlabel("| est mort!");
        Fenetre.cptNbrLogs += 2;
        Fenetre.p2.revalidate();
        Fenetre.lab1.setText("Il reste " + Heros.getNbH() + " Hero et " + Monstre.getNbM() + " Monstre");
        Fenetre.lab1.revalidate();
        if (finJeu) {
            String[] options = {"Nouvelle partie", "Quitter"};
            int result = JOptionPane.showOptionDialog(null, message, "Fin de partie"
                    , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
            if (result == 1) {
                System.exit(0);
            } else {
                Generic.pn.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
                Generic.pn.dispose();
                Generic.pn = new Fenetre(null);
                Generic.pn.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            }
        }


    }

    /**
     * la fonction principale pour le fonctionnnement du hero qui gere le deplacement et les attaques
     *
     * @param source      position source
     * @param destination position destination
     */
    public void actionHeros(Position source, Position destination) {

        Element clickedElement = getElement(source);
        Element destinationElement = getElement(destination);
        if (clickedElement != null && clickedElement instanceof Heros) {
            Soldat hero = (Soldat) clickedElement;
            if (!hero.peutJouer())
                return;
            if (clickedElement.pos.estVoisine(destination)) {
                if (destinationElement == null) {
                    //deplacement normal
                    deplaceSoldat(destination, hero);
                    return;
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
                            Monstre monstre = (Monstre) destinationElement;
                            if (hero.estDansLaPortee(monstre)) {
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

    }

    /**
     * permet de gerer la fin de tour le deplacement des monstre et leurs actions
     *
     * @param pj le panneau de jeu
     */
    public void jouerSoldats(PanneauJeu pj) {
        for (int i = 0; i < lesElements.size(); i++) {
            Element element = lesElements.get(i);
            if (element instanceof Monstre) {
                //l'element est un monstre
                Monstre monstre = (Monstre) element;
                for (int j = 0; j < lesElements.size(); j++) {
                    Element otherElement = lesElements.get(j);
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
                if (!monstre.peutJouer())
                    continue;
                Position posAlea = trouvePositionJouableAlea(monstre.pos);
                Element elementAdj = getElement(posAlea);
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
        for (int j = 0; j < lesElements.size(); j++) {
            Element element = lesElements.get(j);
            if (element instanceof Soldat) {
                ((Soldat) element).setJouer(false);
            }
        }
        pj.repaint();

    }

    /**
     * Permet de faire le dessin du jeu complet
     *
     * @param g graphics
     * @param p panneau jeu
     */
    public void toutDessiner(Graphics g, PanneauJeu p) {
        for (int i = 20; i < MAX_MAP_WIDTH; i = i + 20) {
            for (int j = 0; j < MAX_MAP_HEIGHT; j = j + 20) {
                g.drawRect(i, j, TAILLE_CARRE, TAILLE_CARRE);
            }
        }
        for (int i = 0; i < drawables.size(); i++) {
            Element ob = (Element) drawables.get(i);

            if (ob instanceof Obstacle) {

                ((Obstacle) ob).seDessiner(g);
            } else if (ob instanceof Heros) {
                ((Heros) ob).seDessinerH(g, IConfig.COULEUR_HEROS, p);
            } else if (ob instanceof Monstre) {

                ((Monstre) ob).seDessinerM(g, p);

            }

        }
        for(int i=0;i<porteDeplacement.size();i++){
            g.setColor(new Color(155,240,161));
            g.fillRect(porteDeplacement.get(i).getX(), porteDeplacement.get(i).getY() , TAILLE_CARRE-1, TAILLE_CARRE-1);
        }

    }


    /**
     * @param clickPosition
     * @return si la case est vide retourn null si non l'element dans la case selectionnee
     */
    public Element getElement(Position clickPosition) {
        Element selectedElement = null;
        for (int i = 0; i < lesElements.size(); i++) {
            selectedElement = lesElements.get(i);
            if (clickPosition.getX() >= selectedElement.pos.getX() && clickPosition.getX() < selectedElement.pos.getX() + (TAILLE_CARRE - 1)
                    && clickPosition.getY() >= selectedElement.pos.getY() && clickPosition.getY() < selectedElement.pos.getY() + (TAILLE_CARRE - 1))
                break;
            else
                selectedElement = null;
        }
        return selectedElement;
    }

    /**
     * permet de dessiner la portee de deplacement du soldat
     *
     * @param p
     * @param pos
     */
    public void drawPorteDeplacement(JPanel p, Position pos) {
        Element element = getElement(pos);
        porteDeplacement.clear();
        if (element != null && element instanceof Heros) {

            // System.out.println(element.toString());
            Graphics g = p.getGraphics();
            g.setColor(new Color(155,240,161));
            if (getElement(new Position(element.pos.getX() + TAILLE_CARRE, element.pos.getY())) == null) {
                if (20 < element.pos.getX() + TAILLE_CARRE && element.pos.getX() + TAILLE_CARRE < 641
                        && element.pos.getY() < 581) {
                    porteDeplacement.add(new Position(element.pos.getX() + TAILLE_CARRE,element.pos.getY()));
                    g.fillRect(element.pos.getX() + TAILLE_CARRE, element.pos.getY(), TAILLE_CARRE-1, TAILLE_CARRE-1);
                }
            }

            if (getElement(
                    new Position(element.pos.getX() + TAILLE_CARRE, element.pos.getY() + TAILLE_CARRE)) == null) {
                if (20 < element.pos.getX() + TAILLE_CARRE && element.pos.getX() + TAILLE_CARRE < 641
                        && element.pos.getY() + TAILLE_CARRE < 581) {
                    porteDeplacement.add(new Position(element.pos.getX() + TAILLE_CARRE,element.pos.getY() + TAILLE_CARRE));
                    g.fillRect(element.pos.getX() + TAILLE_CARRE, element.pos.getY() + TAILLE_CARRE, TAILLE_CARRE-1,
                            TAILLE_CARRE-1);
                }
            }

            if (getElement(
                    new Position(element.pos.getX() + TAILLE_CARRE, element.pos.getY() - TAILLE_CARRE + 10)) == null) {
                if (20 < element.pos.getX() + TAILLE_CARRE && element.pos.getX() + TAILLE_CARRE < 641
                        && element.pos.getY() - TAILLE_CARRE < 581) {
                    porteDeplacement.add(new Position(element.pos.getX() + TAILLE_CARRE,element.pos.getY() - TAILLE_CARRE));
                    g.fillRect(element.pos.getX() + TAILLE_CARRE, element.pos.getY() - TAILLE_CARRE, TAILLE_CARRE-1,
                            TAILLE_CARRE-1);
                }
            }

            if (getElement(new Position(element.pos.getX() - TAILLE_CARRE + 10, element.pos.getY())) == null) {
                if (20 < element.pos.getX() - TAILLE_CARRE && element.pos.getX() - TAILLE_CARRE < 641
                        && element.pos.getY() < 581) {
                    porteDeplacement.add(new Position(element.pos.getX() - TAILLE_CARRE,element.pos.getY()));
                    g.fillRect(element.pos.getX() - TAILLE_CARRE, element.pos.getY(), TAILLE_CARRE-1, TAILLE_CARRE-1);
                }
            }

            if (getElement(
                    new Position(element.pos.getX() - TAILLE_CARRE + 10, element.pos.getY() + TAILLE_CARRE)) == null) {
                if (20 < element.pos.getX() - TAILLE_CARRE && element.pos.getX() - TAILLE_CARRE < 641
                        && element.pos.getY() + TAILLE_CARRE < 581) {
                    porteDeplacement.add(new Position(element.pos.getX() - TAILLE_CARRE,element.pos.getY() + TAILLE_CARRE));
                    g.fillRect(element.pos.getX() - TAILLE_CARRE, element.pos.getY() + TAILLE_CARRE, TAILLE_CARRE-1,
                            TAILLE_CARRE-1);
                }
            }

            if (getElement(new Position(element.pos.getX() - TAILLE_CARRE + 10,
                    element.pos.getY() - TAILLE_CARRE + 10)) == null) {
                if (20 < element.pos.getX() - TAILLE_CARRE && element.pos.getX() - TAILLE_CARRE < 641
                        && element.pos.getY() - TAILLE_CARRE < 581) {
                    porteDeplacement.add(new Position(element.pos.getX() - TAILLE_CARRE,element.pos.getY() - TAILLE_CARRE));
                    g.fillRect(element.pos.getX() - TAILLE_CARRE, element.pos.getY() - TAILLE_CARRE, TAILLE_CARRE-1,
                            TAILLE_CARRE-1);
                }
            }

            if (getElement(new Position(element.pos.getX(), element.pos.getY() - TAILLE_CARRE + 10)) == null) {
                if (20 < element.pos.getX() && element.pos.getX() < 641 && element.pos.getY() - TAILLE_CARRE < 581) {
                    porteDeplacement.add(new Position(element.pos.getX(),element.pos.getY() - TAILLE_CARRE));
                    g.fillRect(element.pos.getX(), element.pos.getY() - TAILLE_CARRE, TAILLE_CARRE-1, TAILLE_CARRE-1);
                }
            }

            if (getElement(new Position(element.pos.getX(), element.pos.getY() + TAILLE_CARRE)) == null) {
                if (20 < element.pos.getX() && element.pos.getX() < 641 && element.pos.getY() + TAILLE_CARRE < 581) {
                    porteDeplacement.add(new Position(element.pos.getX(),element.pos.getY() + TAILLE_CARRE));
                    g.fillRect(element.pos.getX(), element.pos.getY() + TAILLE_CARRE, TAILLE_CARRE-1, TAILLE_CARRE-1);
                }
            }
            repaintPortee = true;
        } else {
            if (repaintPortee) {
                repaintPortee = false;
                p.repaint();
            }
        }

    }
}
