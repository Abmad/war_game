package wargame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.ImageObserver;

public class Obstacle extends Element {
	public static final int MAX_OBSTACLE = 35;
	public static final int MIN_OBSTACLE = 10;
	public enum TypeObstacle {
		ROCHER(IConfig.COULEUR_ROCHER), FORET(IConfig.COULEUR_FORET), EAU(IConfig.COULEUR_EAU);
		private final Color COULEUR;

		TypeObstacle(Color couleur) {
			COULEUR = couleur;
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
		if (this.TYPE == TypeObstacle.ROCHER) {
			g.setColor(IConfig.COULEUR_ROCHER);
			g.fillRect(pos.getX(), pos.getY(), Carte.TAILLE_CARRE, Carte.TAILLE_CARRE);
		} else if (this.TYPE == TypeObstacle.EAU) {
			g.setColor(IConfig.COULEUR_EAU);
			g.fillRect(pos.getX(), pos.getY(), Carte.TAILLE_CARRE, Carte.TAILLE_CARRE);
		}

		else if (this.TYPE == TypeObstacle.FORET) {
			g.setColor(IConfig.COULEUR_FORET);

			g.fillRect(pos.getX(), pos.getY(), Carte.TAILLE_CARRE, Carte.TAILLE_CARRE);
		} else {
			g.setColor(IConfig.COULEUR_FORET);

			g.fillRect(pos.getX(), pos.getY(), Carte.TAILLE_CARRE, Carte.TAILLE_CARRE);
		}
	}

}