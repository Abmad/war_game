package wargame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class SauvegarderFenetre extends JFrame{

    private JPanel container = new JPanel();
    private JTextField jtf;
    private JLabel label = new JLabel("Nom de la sauvegarde");
    final File folder = new File("sauvegardes");
    private JButton btnOK = new JButton ("OK");
    public SauvegarderFenetre()  {
        this.setTitle("Sauvegarder");
        this.setSize(300, 150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        container.setBackground(Color.white);
        container.setLayout(new BorderLayout());

        jtf = new JTextField();
        JPanel top = new JPanel();

        Font police = new Font("Arial", Font.BOLD, 14);
        jtf.setFont(police);
        jtf.setPreferredSize(new Dimension(150, 30));
        jtf.setForeground(Color.BLUE);
        //On ajoute l'écouteur à notre composant

        top.add(label);
        top.add(jtf);
        top.add(btnOK);

        this.setContentPane(top);
        this.setVisible(true);

        btnOK.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                String saveName = jtf.getText();
                if("".equals(saveName))
                    return;
                File files[] = folder.listFiles();
                if(files!=null){
                for(int i=0;i<files.length;i++){
                    if(saveName.equals(files[i].getName())){
                        int dialogResult = JOptionPane.showConfirmDialog (null, "Une sauvegarde qui porte le meme nom existe deja voulez vous l'ecraser?","Warning",JOptionPane.YES_NO_OPTION);
                        if(dialogResult == JOptionPane.NO_OPTION)
                            return;

                        break;
                    }
                }
                }
                String filename = "sauvegardes/"+saveName;

                // Serialization
                try
                {
                    //Saving of object in a file
                    FileOutputStream file = new FileOutputStream(filename);
                    ObjectOutputStream out = new ObjectOutputStream(file);

                    // Method for serialization of object
                    out.writeObject(Fenetre.p2);

                    out.close();
                    file.close();
                    JOptionPane.showMessageDialog(null, "Sauvegarde effectuée avec succès", "Savegarde", JOptionPane.INFORMATION_MESSAGE);
                    SauvegarderFenetre.this.dispose();

                }

                catch(IOException ex)
                {
                    JOptionPane.showMessageDialog(null, "Une erreure est survenue veuillez resseayer", "ERROR", JOptionPane.ERROR_MESSAGE);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);

                }
            }
        });

    }
}
