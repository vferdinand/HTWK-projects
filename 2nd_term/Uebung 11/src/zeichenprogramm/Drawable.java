package zeichenprogramm;

import java.awt.Graphics2D;
import java.awt.Point;

public interface Drawable {
	void draw(Graphics2D g);
	void setStartPoint(Point p);
	void setEndPoint(Point p);
}
