package zeichenprogramm;

import java.awt.Graphics2D;

public class Rechteck extends GraphikObjekt {

	@Override
	public void draw(Graphics2D g) {
		g.drawRect(Math.min(pStart.x, pEnd.x), 
				Math.min(pStart.y, pEnd.y),
				Math.abs(pEnd.x -  pStart.x), 
				Math.abs(pEnd.y - pStart.y));
	}

}
