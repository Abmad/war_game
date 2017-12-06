package wargame;

import java.awt.Graphics;

import com.sun.tools.javah.Gen;
import wargame.ISoldat.TypesH;
import wargame.ISoldat.TypesM;
import wargame.Obstacle.TypeObstacle;

public class Element implements java.io.Serializable {
	protected Position pos;
	

	public Element(Position posi) {
      this.pos=posi;
	}



}
