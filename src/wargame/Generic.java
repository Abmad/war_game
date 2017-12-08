package wargame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


public class Generic extends JFrame {
    public static SoundThread playMP3Thread;
    public static boolean soundEnabled = true;
    Fenetre pn;
    LoadFenetre loadFenetre;
    JButton btnOptions;
    public Generic() {
        JPanel main = new JPanel();
        Container container = new Container();
        container.setLayout(new BorderLayout());
        JLabel title = new JLabel(new ImageIcon("images/logo.png"));
        JButton btnJouer = new JButton("Nouveau Jeu");
        JButton btnCharger = new JButton("Charger");
        btnOptions = new JButton("Désactiver Audio");
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
            btnOptions.setIcon(new ImageIcon("images/btnOrange.png"));
            btnOptions.setBorder(BorderFactory.createEmptyBorder());
            btnOptions.setHorizontalTextPosition(SwingConstants.CENTER);
            btnQuitter.setIcon(new ImageIcon("images/btnOrange.png"));
            btnQuitter.setBorder(BorderFactory.createEmptyBorder());
            btnQuitter.setHorizontalTextPosition(SwingConstants.CENTER);
            btnJouer.setForeground(Color.WHITE);
            btnJouer.setFont(new Font("Oria MN", Font.PLAIN, 15));
            btnCharger.setForeground(Color.WHITE);
            btnCharger.setFont(new Font("Oria MN", Font.PLAIN, 15));
            btnOptions.setForeground(Color.WHITE);
            btnOptions.setFont(new Font("Oria MN", Font.PLAIN, 15));
            btnQuitter.setForeground(Color.WHITE);
            btnQuitter.setFont(new Font("Oria MN", Font.PLAIN, 15));

        } catch (Exception ex) {
            System.out.println(ex);
        }
        playMP3Thread = new SoundThread("sounds/Aaron_Mist_-_07_-_New_Era_of_Me.mp3");
        playMP3Thread.start();

//        title.setText("WARGAME");
        this.add(container);
        this.setTitle("WarGame");
        this.setSize(400, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        main.setLayout(new GridLayout(5, 3));
        main.add(title);
        main.add(btnJouer);
        main.add(btnCharger);
        main.add(btnOptions);
        main.add(btnQuitter);
        main.setBorder(BorderFactory.createEmptyBorder(25, 50, 25, 50));
        container.add(main, BorderLayout.CENTER);
        this.setVisible(true);


        btnJouer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Heros.setNbH(0);
                Monstre.setNb(0);
                Generic.this.clickBtnSound();
                Generic.playMP3Thread.suspend();
                if(pn!=null)
                    pn.dispose();
                 pn = new Fenetre(null);

                 Generic.this.setVisible(false);

            }
        });

        btnCharger.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Generic.this.clickBtnSound();
                if(loadFenetre != null)
                    loadFenetre.dispose();
                 loadFenetre = new LoadFenetre(null);
            }
        });

        btnOptions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Generic.soundEnabled) {
                    try {
                        Generic.playMP3Thread.suspend();
                        Generic.soundEnabled = false;
                        Generic.this.btnOptions.setIcon(new ImageIcon("images/btnGreen.png"));
                        Generic.this.btnOptions.setText("Activer Audio");
                    }catch (Exception ex){
                    ex.printStackTrace();
                    }
                } else {
                    try {
                        Generic.playMP3Thread.resume();
                        Generic.soundEnabled = true;
                        Generic.this.btnOptions.setText("Désactiver Audio");
                        Generic.this.btnOptions.setIcon(new ImageIcon("images/btnOrange.png"));
                    }catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
        });

        btnQuitter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Generic.this.clickBtnSound();
                System.exit(0);
            }
        });


    }

    public void clickBtnSound() {
        if (Generic.soundEnabled) {
            try {
                FileInputStream fis = new FileInputStream("sounds/gun-gunshot-01.mp3");
                Player playMP3 = new Player(fis);
                playMP3.play();
            } catch (Exception exc) {
                System.out.println(exc);
            }
        }
    }
}
