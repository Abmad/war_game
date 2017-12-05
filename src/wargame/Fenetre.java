package wargame;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import java.util.EventListener;

import javax.swing.*;

public class Fenetre extends JFrame implements EventListener {

    public static JLabel lab1 = new JLabel();
    public PanneauJeu p2;
    public Position source = new Position();
    boolean isFirstClick = true;
    JButton btnSauvegarder = new JButton("Save");
    JButton btnCharger = new JButton("Load");

    public Fenetre() {
        this.setTitle("WarGame");
        this.setSize(Carte.MAX_MAP_WIDTH + 50, Carte.MAX_MAP_HEIGHT + 100);
        this.setDefaultCloseOperation(3);
        this.setResizable(false);
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
        p2.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                p2.c.getPorteDeplacement(p2, new Position(e.getX(), e.getY()));
            }
        });
        p2.addMouseListener(new MouseAdapter() {
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

        });

        btnSauvegarder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String filename = "sauvegardes/save.wg";

                // Serialization
                try
                {
                    //Saving of object in a file
                    FileOutputStream file = new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    // Method for serialization of object
                    out.writeObject(p2);

                    out.close();
                    file.close();
                    JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès", "Savegarde", JOptionPane.INFORMATION_MESSAGE);


                }

                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, "Une erreure est survenue veuillez resseayer", "ERROR", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        btnCharger.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Deserialization
                try
                {
                    String filename = "sauvegardes/save.wg";
                    // Reading the object from a file
                    FileInputStream file = new FileInputStream(filename);
                    ObjectInputStream in = new ObjectInputStream(file);

                    // Method for deserialization of object
                    PanneauJeu loadedGame = (PanneauJeu)in.readObject();

                    in.close();
                    file.close();

                    JOptionPane.showMessageDialog(null, "Chargement reussis", "Savegarde", JOptionPane.INFORMATION_MESSAGE);
                    cont.remove(p2);

                    p2 = loadedGame;
                    cont.add(p2, BorderLayout.CENTER);
                    p2.repaint();

                }

                catch(IOException ex)
                {

                    System.out.println("IOException is caught");
                }

                catch(ClassNotFoundException ex)
                {
                    System.out.println("ClassNotFoundException is caught");
                }
            }
        });
        p1.add(btnFinTour);
        p1.add(btnCharger);
        p1.add(btnSauvegarder);
        p1.add(lab1);
        p3.add(lab2);

        cont.add(p1, BorderLayout.NORTH);
        cont.add(p2, BorderLayout.CENTER);
        cont.add(p3, BorderLayout.SOUTH);

    }
}
