package zeichenprogramm;

import java.awt.Graphics2D;

public class Punkt extends GraphikObjekt {

	@Override
	public void draw(Graphics2D g) {
		g.fillOval(pStart.x + 1, pStart.y + 1, 7, 7);
	}

}
