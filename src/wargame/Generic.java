package wargame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileInputStream;

import javazoom.jl.player.Player;


public class Generic extends JFrame {
    public static SoundThread playMP3Thread;
    public static boolean soundEnabled = true;

    public Generic() {
        JPanel main = new JPanel();
        Container container = new Container();
        container.setLayout(new BorderLayout());
        JLabel title = new JLabel(new ImageIcon("images/logo.png"));
        JButton btnJouer = new JButton("Jouer");
        JButton btnCharger = new JButton("Charger");
        JButton btnOptions = new JButton("Son ON/OFF");
        JButton btnQuitter = new JButton("Quitter");
        container.setBackground(Color.black);
        main.setBackground(Color.black);
        this.setBackground(Color.black);

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
                Generic.this.clickBtnSound();
                Generic.playMP3Thread.suspend();

                Fenetre pn = new Fenetre(null);
                pn.setVisible(true);
            }
        });

        btnCharger.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Generic.this.clickBtnSound();
                LoadFenetre loadFenetre = new LoadFenetre(null);
                loadFenetre.setVisible(true);
            }
        });

        btnOptions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (Generic.soundEnabled) {
                    try {
                        Generic.playMP3Thread.suspend();
                        Generic.soundEnabled = false;
                    }catch (Exception ex){
                    ex.printStackTrace();
                    }
                } else {
                    try {
                        Generic.playMP3Thread.resume();
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
