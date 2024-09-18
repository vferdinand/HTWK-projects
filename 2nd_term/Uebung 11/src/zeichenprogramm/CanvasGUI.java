package zeichenprogramm;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JTextField;

public class CanvasGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu dateiMenu = new JMenu("Datei");
	private JMenuItem beenden = new JMenuItem("Beenden");
	private JMenu zeichnenMenu = new JMenu("Zeichnen");
	private JMenuItem punkt = new JMenuItem("Punkt");
	private JMenuItem linie = new JMenuItem("Linie");
	private JMenuItem rechteck = new JMenuItem("Rechteck");
	private JMenuItem ellipse = new JMenuItem("Ellipse");
	private JTextField zeichnen = new JTextField("");

	private PaintPanel canvas;
	private PaintPanelData paintdata;

	public CanvasGUI(PaintPanelData data) {

		this.paintdata = data;

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Zeichenprogramm");
		this.setSize(500, 500);

		canvas = new PaintPanel(data);

		dateiMenu.add(beenden);
		beenden.addActionListener(e -> System.exit(0));

		zeichnenMenu.add(punkt);
		punkt.addActionListener(e -> selectGO(e));

		zeichnenMenu.add(linie);
		linie.addActionListener(e -> selectGO(e));

		zeichnenMenu.add(rechteck);
		rechteck.addActionListener(e -> selectGO(e));

		zeichnenMenu.add(ellipse);
		ellipse.addActionListener(e -> selectGO(e));

		menuBar.add(dateiMenu);
		menuBar.add(zeichnenMenu);
		menuBar.add(zeichnen);
		zeichnen.setEditable(false);
		zeichnen.setPreferredSize(new Dimension(60, 20));

		this.setJMenuBar(menuBar);
		Container p = this.getContentPane();
		p.add(canvas);
	}

	private void selectGO(ActionEvent e) {
		String text = e.getActionCommand();
		System.out.println(text);

		try {
			paintdata.setInProgress(GraphikObjekt.createGO(text));
		} catch (IllegalArgumentException ex) {
			text = text + ex.getMessage();
		}

		zeichnen.setText(text);

	}

}
