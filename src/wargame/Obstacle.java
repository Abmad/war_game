package wargame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Obstacle extends Element implements java.io.Serializable{
	public static final int MAX_OBSTACLE = 35;
	public static final int MIN_OBSTACLE = 10;
	public enum TypeObstacle {
		ROCHER(IConfig.ICONE_ROCHER), FORET(IConfig.ICONE_FORET), EAU(IConfig.ICONE_EAU);
//		private final Color COULEUR;
private final String ICONE;
		TypeObstacle(String icone) {
			ICONE = icone;
//			COULEUR = couleur;
		}

		public static TypeObstacle getObstacleAlea() {
			return values()[(int) (Math.random() * values().length)];
		}
	}

	protected TypeObstacle TYPE;

	Obstacle(TypeObstacle type, Position pos) {
		super(pos);
		TYPE = type;
		
	}

	public String toString() {
		return "" + TYPE;
	}

	public void seDessiner(Graphics g) {
			try {
				Image bgImage = ImageIO.read(new File(this.TYPE.ICONE));
				g.drawImage(bgImage, pos.getX() , pos.getY(),Carte.TAILLE_CARRE,Carte.TAILLE_CARRE,null);
			}catch (IOException ex){
				System.out.println(ex.getMessage());
			}
	}

}