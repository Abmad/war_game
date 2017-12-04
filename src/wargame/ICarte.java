package wargame;

import java.awt.Graphics;

public interface ICarte {
	Element getElement(Position pos);

	Position trouvePositionVide(); // Trouve al�atoirement une position vide sur la carte

	Position trouvePositionJouableAlea(Position pos); // Trouve une position vide choisie
	// al�atoirement parmi les 8 positions adjacentes de pos



	boolean deplaceSoldat(Position pos, Soldat soldat);

	void mort(Soldat perso);

	boolean actionHeros(Position pos, Position pos2);

	void jouerSoldats(PanneauJeu pj);

	void toutDessiner(Graphics g);
}