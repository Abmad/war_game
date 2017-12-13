package wargame;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.*;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Map;

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
    public static int cptNbrLogs = 1;
    public static final int CPT_JLAB_LOG_INIT = 35;
    public Position source = new Position();
    Options options;
    boolean isFirstClick = true;
    static boolean canRepaint = false;
    JButton btnOptions = new JButton("Option");
    public static JPanel panelLogContainer;
    public static JScrollPane jsp;

    public Fenetre(ArrayList panels) {
        jsp = new JScrollPane();
        JPanel panelLog = new JPanel();
        if (panels != null && !panels.isEmpty() && panels.get(1) != null) {
            panelLogContainer = (JPanel) panels.get(1);
            cptNbrLogs = ((GridLayout) panelLogContainer.getLayout()).getRows();
            System.out.println(cptNbrLogs);
        } else {
            panelLogContainer = new JPanel();
            panelLogContainer.setLayout(new GridLayout(CPT_JLAB_LOG_INIT, 1));
            panelLogContainer.setBackground(Color.black);
            JLabel jlog = new JLabel("Bonne chance dans votre guerre!");
            jlog.setForeground(Color.WHITE);
            panelLogContainer.add(jlog);
        }
        jsp.setPreferredSize(new Dimension(250, Carte.MAX_MAP_HEIGHT));
        panelLogContainer.revalidate();
        jsp.setViewportView(panelLogContainer);
        panelLog.add(jsp);
        this.setName("fenetre");
        mainGameRunned = true;
        Generic.playMP3Thread.suspend();
        mainGameMusicThread = new SoundThread("sounds/Aaron_Mist_-_06_-_Song_of_the_Sun.mp3");
        if (Generic.soundEnabled)
            mainGameMusicThread.start();
        this.setVisible(true);
        this.setTitle("WarGame");
        this.setSize(Carte.MAX_MAP_WIDTH + 300, Carte.MAX_MAP_HEIGHT + 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        if (panels != null && !panels.isEmpty() && panels.get(0) != null) {
            p2 = (PanneauJeu) panels.get(0);
        } else
            p2 = PanneauJeu.getInstance();
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

        btnFinTour.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Fenetre.__add_message_to_jlabel("=== Fin de Votre tour ===");
                Fenetre.__add_message_to_jlabel("Le Tour des montres ");
                Fenetre.cptNbrLogs += 2;
                p2.c.jouerSoldats(p2);      // tour des monstres
                Fenetre.__add_message_to_jlabel("=== A vous de jouer! ===");
                Fenetre.cptNbrLogs++;
                if (Fenetre.cptNbrLogs > Fenetre.CPT_JLAB_LOG_INIT) {
                    Fenetre.panelLogContainer.setLayout(new GridLayout(Fenetre.cptNbrLogs, 1));
                }
                Fenetre.p2.revalidate();
                JScrollBar vertical = Fenetre.jsp.getVerticalScrollBar();
                vertical.setValue(vertical.getMaximum());

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
                    p2.c.drawPorteDeplacement(p2, new Position(e.getX(), e.getY()));
                    if (element != null && element instanceof Heros)
                        isFirstClick = false;
                } else {
                    p2.c.porteDeplacement.clear();
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
                showStates(element, g);

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
        cont.add(panelLog, BorderLayout.EAST);


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                mainGameMusicThread.stop();
                if (options != null)
                    options.dispose();


            }
        });
    }

    public static void __add_message_to_jlabel(String message) {
        JLabel jlog;
        jlog = new JLabel(message);
        jlog.setForeground(Color.WHITE);
        Fenetre.panelLogContainer.add(jlog);
    }

    public static void __add_message_to_jlabel(String message, Soldat soldat) {
        JLabel jlog;
        jlog = new JLabel(message);
        jlog.setForeground(Color.WHITE);
        Font font = jlog.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        jlog.setFont(font.deriveFont(attributes));
        Soldat s = soldat;
        jlog.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                p2.repaint();
            }
        });
        jlog.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                showStates(s, p2.getGraphics());

            }
        });

        Fenetre.panelLogContainer.add(jlog);

    }

    public static void showStates(Element element, Graphics g) {
        if (element != null) {
            if (element instanceof Obstacle) {
                if(element.pos.getY()>60) {
                    g.setColor(Color.black);
                    g.fillRect(element.pos.getX() - 30, element.pos.getY() - 60, 100, 50);
                    g.setColor(Color.white);
                    g.drawString(element.toString(), (element.pos.getX()), (element.pos.getY() - 30));
                }
                else {
                    g.setColor(Color.black);
                    g.fillRect(element.pos.getX() - 30, element.pos.getY() + 20, 100, 50);
                    g.setColor(Color.white);
                    g.drawString(element.toString(), (element.pos.getX()), (element.pos.getY() + 50));
                }
                canRepaint = true;
            } else {
                String classe;
                String pdvPo;
                String psTir;
                if (element instanceof Heros) {
                    Heros hero = (Heros) element;
                    classe = "Classe: " + hero.TYPE.toString();
                    pdvPo = "PDV: " + hero.getPointsDeVie() + " | PO: " + hero.getPortee();
                    psTir = "PSS: " + hero.getPuissance() + " | TIR: " + hero.getTir();

                } else {
                    Monstre monstre = (Monstre) element;
                    classe = "  " + monstre.TYPE.toString();
                    pdvPo = "PDV: " + monstre.getPointsDeVie() + " | PO: " + monstre.getPortee();
                    psTir = "PSS: " + monstre.getPuissance() + " | TIR: " + monstre.getTir();
                }

                if(element.pos.getY()>65) {
                    g.setColor(Color.black);
                    g.fillRect(element.pos.getX() - 30, element.pos.getY() - 65, 120, 60);
                    g.setColor(Color.white);
                    g.drawString(classe, (element.pos.getX() - 20), (element.pos.getY() - 50));
                    g.drawString(pdvPo, (element.pos.getX() - 20), (element.pos.getY() - 30));
                    g.drawString(psTir, (element.pos.getX() - 20), (element.pos.getY() - 10));
                }else{
                    g.setColor(Color.black);
                    g.fillRect(element.pos.getX() - 30, element.pos.getY() + 20, 120, 60);
                    g.setColor(Color.white);
                    g.drawString(classe, (element.pos.getX() - 20), (element.pos.getY() + 35));
                    g.drawString(pdvPo, (element.pos.getX() - 20), (element.pos.getY() + 55));
                    g.drawString(psTir, (element.pos.getX() - 20), (element.pos.getY() + 70));
                }
                canRepaint = true;

            }
        } else {
            if (canRepaint) {
                p2.repaint();
                canRepaint = false;
            }
        }
    }
}
