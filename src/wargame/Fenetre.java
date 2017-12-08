package wargame;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.EventListener;

import javax.swing.*;

/**
 * La Jframe où s'affiche le jeu
 */
public class Fenetre extends JFrame implements EventListener {

    public Thread mainGameMusicThread;
    public static boolean mainGameRunned = false;
    public static JLabel lab1 = new JLabel();
    public static MouseAdapter panneauJeuMouseAdapter;
    public static PanneauJeu p2;
    public Position source = new Position();
    Options options;
    boolean isFirstClick = true;
    boolean canRepaint = false;
    JButton btnOptions = new JButton("Option");
    public static JPanel panelLog;
    public Fenetre(PanneauJeu pj) {

        panelLog = new JPanel();
//        panelLog.setSize(500, Carte.MAX_MAP_HEIGHT - 100);
//        panelLog.setBackground(Color.black);
//        panelLog.setLayout(new GridLayout(1000,1));
        this.setName("fenetre");
        mainGameRunned = true;
        mainGameMusicThread = new SoundThread("sounds/Aaron_Mist_-_06_-_Song_of_the_Sun.mp3");
        if (Generic.soundEnabled)
            mainGameMusicThread.start();
        this.setVisible(true);
        this.setTitle("WarGame");
        this.setSize(Carte.MAX_MAP_WIDTH + panelLog.getWidth() + 50, Carte.MAX_MAP_HEIGHT + 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

        btnFinTour.setIcon(new ImageIcon("images/btnOrangeS.png"));
        btnFinTour.setBorder(BorderFactory.createEmptyBorder());
        btnFinTour.setHorizontalTextPosition(SwingConstants.CENTER);

        btnOptions.setIcon(new ImageIcon("images/btnGreenS.png"));
        btnOptions.setBorder(BorderFactory.createEmptyBorder());
        btnOptions.setHorizontalTextPosition(SwingConstants.CENTER);

        JLabel lab2 = new JLabel();

        p1.setLayout(new FlowLayout());

        p3.setLayout(new BorderLayout());

//        JPanel logpanneau = new JPanel();
//        logpanneau.setBackground(Color.black);
//        logpanneau.setLayout(new GridLayout());
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

        //mouse adapter
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
        //link mouse adapter to p2
        p2.addMouseListener(panneauJeuMouseAdapter);


        p2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                Graphics g = p2.getGraphics();
                Element element = p2.c.getElement(new Position(e.getX(), e.getY()));
                if (element != null) {
                    if(element instanceof Obstacle) {
                        g.setColor(Color.black);
                        g.fillRect(element.pos.getX() - 30, element.pos.getY() - 60, 100, 50);
                        g.setColor(Color.white);
                        g.drawString(element.toString(), (element.pos.getX()), (element.pos.getY() - 30));
                        canRepaint = true;
                    }else {
                        String classe;
                        String pdvPo;
                        String psTir;
                        if(element instanceof Heros) {
                            Heros hero = (Heros) element;
                            classe = "Classe: " + hero.TYPE.toString();
                            pdvPo = "PDV: " + hero.getPointsDeVie() + " | PO: " + hero.getPortee();
                            psTir = "PSS: " + hero.getPuissance() + " | TIR: " + hero.getTir();

                        }else {
                            Monstre monstre = (Monstre) element;
                            classe = "  " + monstre.TYPE.toString();
                            pdvPo = "PDV: " + monstre.getPointsDeVie() + " | PO: " + monstre.getPortee();
                            psTir = "PSS: " + monstre.getPuissance() + " | TIR: " + monstre.getTir();
                        }
                        g.setColor(Color.black);
                        g.fillRect(element.pos.getX() - 30, element.pos.getY() - 65, 120, 60);
                        g.setColor(Color.white);
                        g.drawString(classe, (element.pos.getX()-20), (element.pos.getY() - 50));
                        g.drawString(pdvPo, (element.pos.getX()-20), (element.pos.getY() - 30));
                        g.drawString(psTir, (element.pos.getX()-20), (element.pos.getY() - 10));
                        canRepaint = true;

                    }
                }else{
                    if(canRepaint) {
                        p2.repaint();
                        canRepaint = false;
                    }
                }
            }
        });

        btnOptions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (options != null)
                    options.dispose();
                options = new Options(Fenetre.this);

            }
        });
        p1.add(btnFinTour);
        p1.add(btnOptions);
        p1.add(lab1);


        cont.add(p1, BorderLayout.NORTH);
        cont.add(p2, BorderLayout.CENTER);
//        cont.add(panelLog, BorderLayout.EAST);
//        cont.add(logpanneau,BorderLayout.EAST);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainGameMusicThread.stop();
                if (options != null)
                    options.dispose();


            }
        });
    }
}
