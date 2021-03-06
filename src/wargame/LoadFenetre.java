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

/**
 * Classe de gestion de chargement de jeu
 */
public class LoadFenetre extends JFrame {


    final File folder = new File("sauvegardes");
    private JFrame caller;
    ArrayList<JButton> savecGames = new ArrayList<JButton>();
    String saveName;

    public LoadFenetre(JFrame fp) {
        caller = fp;
        this.setUndecorated(true);
        JPanel btnPanel = new JPanel();
        JPanel panelContainer = new JPanel();
        JPanel main = new JPanel();
        Container container = new Container();
        container.setLayout(new BorderLayout());
//       JLabel title = new JLabel(new ImageIcon("images/logo.png"));
        JButton btnOk = new JButton("OK");
        JButton btnAnnuler = new JButton("Annuler");
        container.setBackground(Color.black);
        btnPanel.setBackground(Color.black);
        File files[] = folder.listFiles();
        main.setBackground(Color.black);
        JScrollPane jsp = new JScrollPane();
        if (files.length >= 7)
            main.setLayout(new GridLayout(files.length, 1));
        else
            main.setLayout(new GridLayout(7, 1));


        jsp.setPreferredSize(new Dimension(300, 350));
        jsp.setViewportView(main);
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
                        saveName = ((JButton) e.getSource()).getName();
                    }
                });
            }
        }


        btnOk.setForeground(Color.WHITE);
        btnAnnuler.setForeground(Color.WHITE);

        btnOk.setIcon(new ImageIcon("images/btnGreenS.png"));
        btnOk.setBorder(BorderFactory.createEmptyBorder());
        btnOk.setHorizontalTextPosition(SwingConstants.CENTER);
        btnAnnuler.setIcon(new ImageIcon("images/btnOrangeS.png"));
        btnAnnuler.setBorder(BorderFactory.createEmptyBorder());
        btnAnnuler.setHorizontalTextPosition(SwingConstants.CENTER);
        main.setBorder(new LineBorder(Color.YELLOW));
//        title.setText("WARGAME");
        this.add(container);
        this.setTitle("WarGame");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.black);
        panelContainer.setBackground(Color.black);

        btnPanel.setLayout(new GridLayout(1, 2));
        btnPanel.add(btnOk);
        btnPanel.add(btnAnnuler);

        btnPanel.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
//        panelContainer.add(main);
        panelContainer.add(jsp);
        container.add(panelContainer, BorderLayout.CENTER);
        container.add(btnPanel, BorderLayout.SOUTH);
        this.setVisible(true);

        btnOk.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                ArrayList panels = (ArrayList) loadGame(saveName);
                if (caller != null && "fenetre".equals(caller.getName())) {
                    caller.dispose();
                }
                Fenetre fn = new Fenetre(panels);
                LoadFenetre.this.dispose();
//                } else {
//                    Fenetre fn = new Fenetre(pnl);
//                    LoadFenetre.this.dispose();
//                }

                System.out.println(saveName);
            }
        });

        btnAnnuler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoadFenetre.this.dispose();
            }
        });


    }

    public ArrayList loadGame(String filename) {
        ArrayList loadedGame = new ArrayList();
        try {
            filename = "sauvegardes/" + filename;
            // Reading the object from a file
            FileInputStream file = new FileInputStream(filename);
            ObjectInputStream in = new ObjectInputStream(file);

            // Method for deserialization of object
            loadedGame = (ArrayList) in.readObject();

            in.close();
            file.close();

            JOptionPane.showMessageDialog(null, "Chargement reussis", "Savegarde", JOptionPane.INFORMATION_MESSAGE);


        } catch (IOException ex) {

            System.out.println("IOException is caught");
            System.out.println(ex.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("ClassNotFoundException is caught");
            System.out.println(ex.getMessage());
        }
        return loadedGame;
    }
}
