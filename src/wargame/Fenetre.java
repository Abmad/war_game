package wargame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.*;
import java.io.*;
import java.util.EventListener;

import javax.swing.*;

public class Fenetre extends JFrame implements EventListener {

    public Thread mainGameMusicThread;
    public static boolean mainGameRunned = false;
    public static JLabel lab1 = new JLabel();
    public static MouseAdapter panneauJeuMouseAdapter;
    public static PanneauJeu p2;
    public Position source = new Position();
    Options options;
    boolean isFirstClick = true;
    JButton btnOptions = new JButton("Option");

    public Fenetre(PanneauJeu pj) {
        this.setName("fenetre");
        mainGameRunned = true;
        mainGameMusicThread = new SoundThread("sounds/Aaron_Mist_-_06_-_Song_of_the_Sun.mp3");
        if (Generic.soundEnabled)
            mainGameMusicThread.start();
        this.setVisible(true);
        this.setTitle("WarGame");
        this.setSize(Carte.MAX_MAP_WIDTH + 50, Carte.MAX_MAP_HEIGHT + 100);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setResizable(false);
        if (pj != null) {
            p2 = pj;
        } else
            p2 = new PanneauJeu();
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
        panneauJeuMouseAdapter = new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Element element = p2.c.getElement(new Position(e.getX(), e.getY()));
                if (element != null)
                    lab2.setText(element.toString());
                else
                    lab2.setText("");
            }

            public void mousePressed(MouseEvent e) {
                if (isFirstClick) {
                    source = new Position(e.getX(), e.getY());
                    Element element = p2.c.getElement(source);
                    if (element != null && element instanceof Heros)
                        isFirstClick = false;
                } else {
                    Position destination = new Position(e.getX(), e.getY());
                    p2.c.actionHeros(source, destination);
                    isFirstClick = true;
                    repaint();
                }
            }

        };

        p2.addMouseListener(panneauJeuMouseAdapter);



        btnOptions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                options = new Options(Fenetre.this);

            }
        });
        p1.add(btnFinTour);
        p1.add(btnOptions);
        p1.add(lab1);
        p3.add(lab2);

        cont.add(p1, BorderLayout.NORTH);
        cont.add(p2, BorderLayout.CENTER);
        cont.add(p3, BorderLayout.SOUTH);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainGameMusicThread.stop();
                if(options!=null)
                options.dispose();
            }
        });
    }
}
