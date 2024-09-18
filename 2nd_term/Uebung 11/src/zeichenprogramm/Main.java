package zeichenprogramm;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) {
		PaintPanelData data = new PaintPanelData();
		JFrame canvas = new CanvasGUI(data);
		canvas.setVisible(true);
	}
}
