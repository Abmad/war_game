package wargame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.*;

public class PanneauJeu extends JPanel implements MouseListener,java.io.Serializable {
	
	public  Carte c=new Carte();
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		try {
			Image bgImage = ImageIO.read(new File("images/carteBg_1.jpg"));
			g.drawImage(bgImage, 20, 0, this);
//			g.drawImage(bgImage, 20, 250, this);
		}catch (IOException ex){
			System.out.println(ex.getMessage());
		}
		c.toutDessiner(g,this);


	}

	/* changer couleur en fqisqnt les evenements*/
	@Override
	public void mouseClicked(MouseEvent event) {

	}
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

}
