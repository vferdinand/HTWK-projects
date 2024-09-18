//Uebung11_alleObjekte
//GraphikObjekt.java
package zeichenprogramm;

import java.awt.Graphics2D;
import java.awt.Point;

public abstract class GraphikObjekt implements Drawable {

	protected Point pStart;
	protected Point pEnd;

	public GraphikObjekt() {
		super();
		this.pStart = new Point();
		this.pEnd = new Point();
	}

	// Fabrikmethode zur Erzeugung eines Objektes
	public static Drawable createGO(String s) {
		switch (s) {
			case "Punkt" :
				System.out.println("Erstelle Punkt");
				return new Punkt();
			case "Linie" :
				System.out.println("Erstelle Linie");
				return new Linie();
			case "Rechteck" :
				System.out.println("Erstelle Rechteck");
				return new Rechteck();
			case "Ellipse" :
				System.out.println("Erstelle Ellipse");
				return new Ellipse();
		}
		return null;
	}

	// abstrakte Zeichenmethode
	public abstract void draw(Graphics2D g);

	// Startpunkt
	public void setStartPoint(Point p) {
		this.pStart = p;
	}
	// Endpunkt
	public void setEndPoint(Point pEnd) {
		this.pEnd = pEnd;
	}

}
