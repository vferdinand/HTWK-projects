package Uebung_10;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Map;
import java.util.Set;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.Timer;

public class LottoGUI extends JFrame {

	private LottoDaten daten;

	// Menueleiste
	private JMenuBar menueLeiste;
	private JMenu dateiMenue;
	private JMenuItem beenden;

	// Panel1 - Tabelle
	private JPanel p1;
	private JTable tabelle;
	private String[] header = {"KW", "1.Zahl", "2.Zahl", "3.Zahl", "4.Zahl",
			"5.Zahl", "6.Zahl"};
	private Integer[][] datenMatrix;

	// Panel2 - Komponenten
	private JPanel p2;
	private JLabel kwLabel;
	private JTextField kw;
	private JButton generate;
	private JButton save;
	private JLabel messageLabel;

	public LottoGUI(LottoDaten model) {
		this.daten = model;

		// JFrame-Konfiguration
		this.setSize(600, 400);
		this.setLocation(300, 300);
		this.setTitle("Lottozahlen 6 aus 49");
		this.setLayout(new GridLayout(2, 1));
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);

		// Menue
		menueLeiste = new JMenuBar();
		dateiMenue = new JMenu("Datei");
		beenden = new JMenuItem("Beenden");
		
		beenden.addActionListener(e -> {
			System.exit(0);
		});
		// alles zuordnen
		menueLeiste.add(dateiMenue);
		dateiMenue.add(beenden);
		// zum Fenster hinzufuegen
		this.setJMenuBar(menueLeiste);

		// Panel
		p1 = new JPanel();
		p1.setLayout(new BorderLayout());

		p2 = new JPanel();
		p2.setLayout(null);

		this.add(p1);
		this.add(p2);

		// Panel1
		// Tabelle hinzufuegen
		datenMatrix = new Integer[52][7]; // 52 Wochen,KW+6Zahlen
		tabelle = new JTable(datenMatrix, header);
		p1.add(new JScrollPane(tabelle), BorderLayout.CENTER);

		// Panel2
		p2.setBackground(Color.ORANGE);
		kwLabel = new JLabel("Kalenderwoche:");
		kwLabel.setBounds(30, 20, 150, 20);
		p2.add(kwLabel);

		kw = new JTextField(null);
		kw.setBounds(180, 20, 150, 20);
		p2.add(kw);

		generate = new JButton("Generieren");
		generate.setBounds(180, 50, 150, 20);
		p2.add(generate);
		
		generate.addActionListener(e -> generieren());

		save = new JButton("Speichern");
		save.setBounds(350, 50, 150, 20);
		p2.add(save);

		save.addActionListener(e -> speichern());
		// ActionListener
		// TODO implement Action Listener
	}

	// TODO implement generieren und speichern

	private void speichern() {
		System.out.println("[GUI] speichern");
		updateTable();
		kw.setText("");
		repaint();
		removeMessage();
		vanishingMessage("Daten gespeichert");
	}

	private void generieren() {
		System.out.println("[GUI] generieren");
		Set<Integer> zahlen = daten.generiereTip();
		removeMessage();
		String woche = kw.getText();
		
		if(istGueltigeKW(woche)) {
			daten.setTip(Integer.parseInt(woche), zahlen);
			message(zahlen.toString());
		} else {
			alert("Bitte gültige Kalenderwoche eingeben!");
		}
		
	}

	private boolean istGueltigeKW(String woche) {
		int kw;
		try {
			kw = Integer.parseInt(woche);
		} catch (NumberFormatException e) {
			return false;
		}
		return kw >= 1 && kw <= 52;
	}

	private void alert(String text) {
		message(text, Color.red);
	}

	private void message(String text) {
		message(text, Color.black);
	}

	private void vanishingMessage(String text) {
		message(text);
		// Meldung verschwinden lassen
		Timer timer = new Timer(2000, ex -> {
			messageLabel.setText("");
		});
		timer.setRepeats(false);
		timer.start();
	}

	private void message(String text, Color c) {
		messageLabel = new JLabel(text);
		messageLabel.setBounds(180, 80, 300, 20);
		messageLabel.setForeground(c);
		p2.add(messageLabel);
		repaint();
	}

	private void removeMessage() {
		if (p2.findComponentAt(180, 80) == messageLabel) {
			p2.remove(messageLabel);
			repaint();
		}
	}

	private void updateTable() {
		int zeile = 0;
		for (Integer kw: daten.getLottoDaten().keySet()) {
			datenMatrix[zeile][0] = kw;
			int spalte = 1;
			for (Integer z:daten.getLottoDaten().get(kw)) {
				datenMatrix[zeile][spalte] = z;
				spalte++;
			}
			zeile++;
		}
	}

}
