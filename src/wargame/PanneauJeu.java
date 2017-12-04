package wargame;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Iterator;

import javax.swing.*;

public class PanneauJeu extends JPanel implements MouseListener {
	
	public  Carte c=new Carte(); 
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		c.toutDessiner(g);


	}

	public void clear() {
		c.clear();
		repaint();
	}
	/* changer couleur en fqisqnt les evenements*/
	@Override
	public void mouseClicked(MouseEvent event) {
		// System.out.println("Mouse movement detected! Actual mouse position is: " + event.getX()+ "," + event.getY() + ".");
		
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
	public void mouseMoved(MouseEvent event) {
	    //TODO
//	    System.out.println("Mouse movement detected! Actual mouse position is: " + event.getX()+ "," + event.getY() + ".");
	  }   

}
