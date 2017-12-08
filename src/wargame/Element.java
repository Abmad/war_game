package wargame;

import java.awt.Graphics;

import com.sun.tools.javah.Gen;
import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

/**
 * Classe de tout les elements (obstacle et soldat)
 */
public class Element implements java.io.Serializable {
	protected Position pos;
	

	public Element(Position posi) {
      this.pos=posi;
	}



}
