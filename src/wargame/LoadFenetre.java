package wargame;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class LoadFenetre extends JFrame {


    final File folder = new File("sauvegardes");
    private JFrame caller;
    ArrayList<JButton> savecGames = new ArrayList<JButton>();
    String saveName;

    public LoadFenetre(JFrame fp) {
        caller = fp;
        this.setUndecorated(true);
        JPanel btnPanel = new JPanel();
        JPanel main = new JPanel();
        Container container = new Container();
        container.setLayout(new BorderLayout());
//       JLabel title = new JLabel(new ImageIcon("images/logo.png"));
        JButton btnOk = new JButton("OK");
        JButton btnAnnuler = new JButton("Annuler");
        container.setBackground(Color.black);
        btnPanel.setBackground(Color.black);
        this.setBackground(Color.black);
        File files[] = folder.listFiles();
        main.setBackground(Color.black);
        main.setLayout(new GridLayout(files.length, 1));
        main.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
        for (final File fileEntry : files) {
            if (fileEntry.isDirectory()) {
                System.out.println("Error");
            } else {
                System.out.println(fileEntry.getName());
                JButton btnGame = new JButton(fileEntry.getName());
                btnGame.setName(fileEntry.getName());
                btnGame.setBorder(BorderFactory.createEmptyBorder());
                btnGame.setForeground(Color.WHITE);
                btnGame.setBackground(Color.black);
                btnGame.setFont(new Font("Oria MN", Font.PLAIN, 40));
                savecGames.add(btnGame);
                main.add(btnGame);
                btnGame.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Im here");
                        saveName = ((JButton) e.getSource()).getName();
                    }
                });
            }
        }

        main.setBorder(new LineBorder(Color.YELLOW));
//        title.setText("WARGAME");
        this.add(container);
        this.setTitle("WarGame");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        btnPanel.setLayout(new GridLayout(1, 2));
        btnPanel.add(btnOk);
        btnPanel.add(btnAnnuler);

        btnPanel.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
        container.add(main, BorderLayout.CENTER);
        container.add(btnPanel, BorderLayout.SOUTH);
        this.setVisible(true);

        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                PanneauJeu pnl = loadGame(saveName);
                if (caller != null && "fenetre".equals(caller.getName())) {
                    caller.dispose();
                    Fenetre fn = new Fenetre(pnl);
                    LoadFenetre.this.dispose();
                } else {
                    Fenetre fn = new Fenetre(pnl);
                    LoadFenetre.this.dispose();
                }

                System.out.println(saveName);
            }
        });


    }
    public PanneauJeu loadGame(String filename){
        PanneauJeu loadedGame = new PanneauJeu();
        try
        {
             filename = "sauvegardes/"+filename;
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
             loadedGame = (PanneauJeu)in.readObject();

            in.close();
            file.close();

            JOptionPane.showMessageDialog(null, "Chargement reussis", "Savegarde", JOptionPane.INFORMATION_MESSAGE);


        }

        catch(IOException ex)
        {

            System.out.println("IOException is caught");
            System.out.println(ex.getMessage());
        }

        catch(ClassNotFoundException ex)
        {
            System.out.println("ClassNotFoundException is caught");
            System.out.println(ex.getMessage());
        }
        return loadedGame;
    }
}
