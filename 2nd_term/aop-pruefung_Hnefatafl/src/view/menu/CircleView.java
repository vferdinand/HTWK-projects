package view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import view.GlobalGuiConfig;

/**
 * Die Klasse CircleView stellt eine grafische Darstellung eines Kreises dar,
 * der in einem JPanel gezeichnet wird. Der Kreis hat eine bestimmte Farbe und
 * einen Durchmesser, der beim Erstellen des Objekts festgelegt wird.
 */
public class CircleView extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int HEIGHT = GlobalGuiConfig.applyScale(15);
	private static final int WIDTH = GlobalGuiConfig.applyScale(15);

	private Color color;
	private int diameter;

	/**
	 * Erstellt eine neue Instanz von CircleView mit der angegebenen Farbe und dem
	 * Durchmesser.
	 *
	 * @param color    die Farbe des Kreises
	 * @param diameter der Durchmesser des Kreises in Pixeln
	 * @throws IllegalArgumentException wenn der Durchmesser kleiner oder gleich
	 *                                  null ist
	 */
	public CircleView(final Color color, final int diameter) {
		if (diameter <= 0) {
			throw new IllegalArgumentException("Durchmesser muss größer als null sein.");
		}
		this.color = color;
		this.diameter = diameter;
		setBackground(null);
		setPreferredSize(new Dimension(HEIGHT, WIDTH));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (getWidth() - diameter) / 2;
		int y = (getHeight() - diameter) / 2;
		g.setColor(color);
		g.fillOval(x, y, diameter, diameter);
	}
}