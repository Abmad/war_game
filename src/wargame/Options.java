package wargame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Options extends JFrame {
    public static SoundThread playMP3Thread;
    public static boolean soundEnabled = true;
    private Fenetre caller;
    public Options(Fenetre jf) {
        this.setUndecorated(true);
        caller = jf;
        JPanel main = new JPanel();
        Container container = new Container();
        container.setLayout(new BorderLayout());
        JLabel title = new JLabel(new ImageIcon("images/logo.png"));
        JButton btnJouer = new JButton("Nouveau jeu");
        JButton btnCharger = new JButton("Charger");
        JButton btnSon = new JButton("Son ON/OFF");
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnQuitter = new JButton("Annuler");
        container.setBackground(Color.black);
        main.setBackground(Color.black);
        this.setBackground(Color.black);

//        title.setText("WARGAME");
        this.add(container);
        this.setTitle("WarGame");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        main.setLayout(new GridLayout(6, 3));
        main.add(title);
        main.add(btnJouer);
        main.add(btnCharger);
        main.add(btnSauvegarder);
        main.add(btnSon);
        main.add(btnQuitter);
        main.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
        container.add(main, BorderLayout.CENTER);
        this.setVisible(true);


        btnJouer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Options.this.caller.dispose();
                Fenetre pn = new Fenetre(null);
                Options.this.dispose();
            }
        });

        btnCharger.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                LoadFenetre loadFenetre = new LoadFenetre(caller);
                loadFenetre.setVisible(true);
            }
        });

        btnSon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Generic.soundEnabled) {
                    try {
                        Options.this.caller.mainGameMusicThread.suspend();
                        Generic.soundEnabled = false;
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        Options.this.caller.mainGameMusicThread.resume();
                        Generic.soundEnabled = true;
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }


            }
        });

        btnQuitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
               Options.this.dispose();
            }
        });


        btnSauvegarder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                SauvegarderFenetre save = new SauvegarderFenetre();
            }
        });

    }

}
