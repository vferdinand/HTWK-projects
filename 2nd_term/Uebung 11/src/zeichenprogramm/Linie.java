//Uebung11_alleObjekte
//Linie.java
package zeichenprogramm;

import java.awt.Graphics2D;

public class Linie extends GraphikObjekt {

	// Zeichnen der Linie
	@Override
	public void draw(Graphics2D g) {
		g.drawLine(pStart.x, pStart.y, pEnd.x, pEnd.y);
	}

}
