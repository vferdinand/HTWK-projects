//Uebung11_alleObjekte
//PaintPanel.java
package zeichenprogramm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Optional;

import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PaintPanel extends JPanel {
	private PaintPanelData paintdata;

	// Konstruktor
	public PaintPanel(PaintPanelData data) {
		this.paintdata = data;
		this.setBackground(Color.ORANGE);
		MouseAdapter myMouseAdapter = new MyMouseAdapter();
		this.addMouseListener(myMouseAdapter);
		this.addMouseMotionListener(myMouseAdapter);
	}
	// Zeichnen
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;
		
		// TODO aktuelles Objekt zeichnen
		g2D.setColor(Color.RED);
		paintdata.getInProgress().ifPresent(go -> go.draw(g2D));
		
		
		// TODO Listenobjekte zeichnen
		g2D.setColor(Color.BLACK);
		paintdata.getFigurenliste().forEach(go -> go.draw(g2D));
	}

	private class MyMouseAdapter extends MouseAdapter {
		// Setze Startpunkt
		@Override
		public void mousePressed(MouseEvent e) {
			System.out.println(
					" \nStartpunkt auf (" + e.getX() + "," + e.getY() + ")");

			Point p = e.getPoint();
			
			paintdata.getInProgress().ifPresent(go -> go.setStartPoint(p));
			
			
			// TODO set POINT in GraphikObjekt
		}
		// Setze Endpunkt
		@Override
		public void mouseDragged(MouseEvent e) {
			System.out.println(
					"\nEndpunkt auf (" + e.getX() + "," + e.getY() + ")");
			paintdata.getInProgress().ifPresent(go -> go.setEndPoint(e.getPoint()));
			repaint();
		}
		// Speicherung der Objekte
		@Override
		public void mouseReleased(MouseEvent e) {
			System.out.println("Maustaste losgelassen");
			
			// TODO Objekt in Liste speichern
			System.out.println("Objekt in Liste gespeichert!");
			paintdata.getInProgress().ifPresent(go -> paintdata.add(go));
			
			// TODO Erzeuge neues Objekt zum Weiterzeichnen
			System.out.println("Neu: " + paintdata.getInProgress().get().getClass().getSimpleName());
			
			//zum hertellen mehrerer Objekte des gleichen Typs
			String neueKlasse = paintdata.getInProgress().get().getClass().getSimpleName();
			paintdata.setInProgress(GraphikObjekt.createGO(neueKlasse));
			
			repaint();
		}
	}

}
