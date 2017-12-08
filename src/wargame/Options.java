package wargame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Options extends JFrame {
    public static SoundThread playMP3Thread;
    public static boolean soundEnabled = true;
    private Fenetre caller;
    SauvegarderFenetre save;
    LoadFenetre loadFenetre;
    JButton btnSon;
    public Options(Fenetre jf) {
//        this.setUndecorated(true);
        caller = jf;
        JPanel main = new JPanel();
        Container container = new Container();
        container.setLayout(new BorderLayout());
        JLabel title = new JLabel(new ImageIcon("images/logo.png"));
        JButton btnJouer = new JButton("Nouveau jeu");
        JButton btnCharger = new JButton("Charger");
         btnSon = new JButton("Son ON/OFF");
        JButton btnSauvegarder = new JButton("Sauvegarder");
        JButton btnQuitter = new JButton("Quitter");
        container.setBackground(Color.black);
        main.setBackground(Color.black);
        this.setBackground(Color.black);


        try {

            btnJouer.setIcon(new ImageIcon("images/btnGreen.png"));
            btnJouer.setBorder(BorderFactory.createEmptyBorder(10,0,0,0));
            btnJouer.setHorizontalTextPosition(SwingConstants.CENTER);
            btnCharger.setIcon(new ImageIcon("images/btnGreen.png"));
            btnCharger.setBorder(BorderFactory.createEmptyBorder());
            btnCharger.setHorizontalTextPosition(SwingConstants.CENTER);
            btnSauvegarder.setIcon(new ImageIcon("images/btnGreen.png"));
            btnSauvegarder.setBorder(BorderFactory.createEmptyBorder());
            btnSauvegarder.setHorizontalTextPosition(SwingConstants.CENTER);
            btnSon.setIcon(new ImageIcon("images/btnOrange.png"));
            btnSon.setBorder(BorderFactory.createEmptyBorder());
            btnSon.setHorizontalTextPosition(SwingConstants.CENTER);
            btnQuitter.setIcon(new ImageIcon("images/btnOrange.png"));
            btnQuitter.setBorder(BorderFactory.createEmptyBorder());
            btnQuitter.setHorizontalTextPosition(SwingConstants.CENTER);
            btnJouer.setForeground(Color.WHITE);
            btnJouer.setFont(new Font("Oria MN", Font.PLAIN, 15));
            btnCharger.setForeground(Color.WHITE);
            btnCharger.setFont(new Font("Oria MN", Font.PLAIN, 15));
            btnSon.setForeground(Color.WHITE);
            btnSon.setFont(new Font("Oria MN", Font.PLAIN, 15));
            btnQuitter.setForeground(Color.WHITE);
            btnQuitter.setFont(new Font("Oria MN", Font.PLAIN, 15));
            btnSauvegarder.setForeground(Color.WHITE);
            btnSauvegarder.setFont(new Font("Oria MN", Font.PLAIN, 15));

        } catch (Exception ex) {
            System.out.println(ex);
        }
//        title.setText("WARGAME");
        this.add(container);
        this.setTitle("WarGame");
        this.setSize(400, 550);
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
                if(loadFenetre!=null)
                    loadFenetre.dispose();
                 loadFenetre = new LoadFenetre(caller);
            }
        });

        btnSon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Generic.soundEnabled) {
                    try {
                        Options.this.caller.mainGameMusicThread.suspend();
                        Generic.soundEnabled = false;
                        Options.this.btnSon.setIcon(new ImageIcon("images/btnGreen.png"));
                        Options.this.btnSon.setText("Activer Audio");
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                } else {
                    try {
                        Options.this.caller.mainGameMusicThread.resume();
                        Generic.soundEnabled = true;
                        Options.this.btnSon.setText("Désactiver Audio");
                        Options.this.btnSon.setIcon(new ImageIcon("images/btnOrange.png"));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }


            }
        });

        btnQuitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
              System.exit(0);
            }
        });


        btnSauvegarder.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(save != null)
                    save.dispose();
                 save = new SauvegarderFenetre();
            }
        });

    }

}
