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
    boolean isFirstClick = true;

    public Fenetre() {
        this.setTitle("WarGame");
        this.setSize(Carte.MAX_MAP_WIDTH+50, Carte.MAX_MAP_HEIGHT+100);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);

        Container cont = new Container();
        cont.setLayout(new BorderLayout());
        add(cont);
        JPanel p1 = new JPanel();

        JPanel p3 = new JPanel();

        JButton btnFinTour = new JButton("Fin du Tour");

        JLabel lab2 = new JLabel();

        p1.setLayout(new FlowLayout());

        p3.setLayout(new BorderLayout());

        btnFinTour.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                p2.c.jouerSoldats(p2);      //deplacer le monste
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
               Element element = p2.c.getElement(new Position(e.getX(),e.getY()));
               if(element != null)
                lab2.setText(element.toString());
               else
                   lab2.setText("");


            }

            public void mousePressed(MouseEvent e) {
                if( isFirstClick){
                    source = new Position(e.getX(),e.getY());
                    Element element =p2.c.getElement(source);
                    if(element != null && element instanceof Heros)
                    isFirstClick = false;
                } else {
                    Position destination = new Position(e.getX(),e.getY());
                    p2.c.actionHeros(source,destination);
                    isFirstClick = true;
                    repaint();
                }

            }

        });
        p1.add(btnFinTour);
        p1.add(lab1);
        p3.add(lab2);

        cont.add(p1, BorderLayout.NORTH);
        cont.add(p2, BorderLayout.CENTER);
        cont.add(p3, BorderLayout.SOUTH);

    }
}
