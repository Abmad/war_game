package wargame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Fenetre extends JFrame implements EventListener {

    public static JLabel lab1 = new JLabel();
    public PanneauJeu p2 = new PanneauJeu();
    public Position source = new Position();
    public int clickCount=0;

    public Fenetre() {
        int a = 5, b = 6;
        this.setTitle("WarGame");
        this.setSize(Carte.MAX_MAP_WIDTH+50, Carte.MAX_MAP_HEIGHT+100);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);

        Container cont = new Container();
        cont.setLayout(new BorderLayout());
        add(cont);
        JPanel p1 = new JPanel();

        JPanel p3 = new JPanel();

        JButton b1 = new JButton("Fin du Tour");

        JLabel lab2 = new JLabel(p2.c.trouveHeros().toString());

        p1.setLayout(new FlowLayout());

        p3.setLayout(new BorderLayout());

        b1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                p2.c.jouerSoldats(p2);      //deplacer le monste

               // System.out.println(p2.c.trouveHeros(new Position(40, 40)));
                //System.out.println(p2.c.trouvePositionVide(new Position(40, 40)));

            }

            public void mousePressed(MouseEvent e) {
               // System.out.println("mousePressed en " + e.getX() + " " + e.getY());
            }

            public void mouseReleased(MouseEvent e) {
                //System.out.println("mouseReleased en " + e.getX() + " " + e.getY());
            }

            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {

            }
        });
        p2.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                for (int i = 0; i < p2.c.lesElements.size(); i++) {
                    if (p2.c.lesElements.get(i).pos.getX() < e.getX()
                            && p2.c.lesElements.get(i).pos.getX() + 20 > e.getX()
                            && p2.c.lesElements.get(i).pos.getY() < e.getY()
                            && p2.c.lesElements.get(i).pos.getY() + 20 > e.getY()) {
                        lab2.setText(p2.c.lesElements.get(i).toString());
                    }
                }

            }
            boolean isFirstClick = true;
            public void mousePressed(MouseEvent e) {
//                Element E=p2.c.getElement(new Position(e.getX(),e.getY()));
//                System.out.println(E.toString());
//                System.out.println(isFirstClick);
                if( isFirstClick){
                    source = new Position(e.getX(),e.getY());
                    isFirstClick = false;
                } else {
                    Position destination = new Position(e.getX(),e.getY());
                    p2.c.actionHeros(source,destination);
                    isFirstClick = true;
                    repaint();
                }

            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseDragged(MouseEvent e) {

            }

            @Override
            public void mouseMoved(MouseEvent e) {
              //  System.out.println("mouseReleased en " + e.getX() + " " + e.getY());
            }
        });
        p1.add(b1);
        p1.add(lab1);
        p3.add(lab2);

        cont.add(p1, BorderLayout.NORTH);
        cont.add(p2, BorderLayout.CENTER);
        cont.add(p3, BorderLayout.SOUTH);
        //System.out.println(Heros.getNbH());

    }
}
