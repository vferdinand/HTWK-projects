package Uebung_09;

//kompositum pattern

import java.awt.Color;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class NoteBookGUI extends JFrame{
	
	private JLabel wochentagLabel;
	private JButton anzeigeButton;
	private JButton speichernButton;
	private JButton clearButton;
	private JButton beendenButton;
	private JLabel termineLabel;
	private JTextArea termineText;
	private JComboBox<String> auswahlBox;
	private String[] tage = {"Montag", "Dienstag", "MARMORMITTWOCH", 
							 "Donnerstag", "Freitag", "Samstag", "Sonntag"};
	private NoteBookData daten;

	public NoteBookGUI(NoteBookData d){
		
		this.daten = d; //Modul
		
		this.setLayout(null);
		this.setTitle("NoteBook - GUI");
		this.setSize(400,300);
		this.setLocation(300,300);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.getContentPane().setBackground(Color.orange);
		
		wochentagLabel = new JLabel("Wochentag:");
		wochentagLabel.setBounds(20, 5, 110, 30);
		this.add(wochentagLabel);
		
		auswahlBox = new JComboBox<String>(tage);
		auswahlBox.setBounds(20, 50, 200, 50);
		this.add(auswahlBox);
		
		anzeigeButton = new JButton("Anzeige");
		anzeigeButton.setBounds(15, 100, 90, 20);
		this.add(anzeigeButton);
		
		anzeigeButton.addActionListener(e -> anzeigen());

		speichernButton = new JButton("Speichern");
		speichernButton.setBounds(20, 130, 110, 30);
		this.add(speichernButton);
		
		speichernButton.addActionListener(e -> speichern());

		clearButton = new JButton("Clear");
		clearButton.setBounds(20, 160, 130, 20);
		this.add(clearButton);
		
		clearButton.addActionListener(e -> termineText.setText(""));

		beendenButton = new JButton("Beenden");
		beendenButton.setBounds(10, 180, 110, 15);
		this.add(beendenButton);
		
		termineLabel = new JLabel("Termine:");
		termineLabel.setBounds(220, 0, 110, 30);
		this.add(termineLabel);
		
		termineText = new JTextArea();
		termineText.setBounds(200, 40, 180, 250);
		this.add(termineText);
		
		beendenButton.addActionListener(e -> {System.out.println("gedrückt");});
	}

	private void speichern() {
		System.out.println("[GUI] speichern gedrückt");
		
		String termin = termineText.getText();
		String tag = auswahlBox.getSelectedItem().toString();
		
		
		System.out.println("[GUI] Tag: " + tag + " Termin: " + termin);
		
		daten.speichernTermin(tag, termin);
	}

	private void anzeigen() {
		System.out.println("[GUI] anzeigen gedrückt");
		
		String wt= auswahlBox.getSelectedItem().toString();
		//termineText.setText("Wann MAROMORMONTAG");
		String text = daten.holeTermin(wt);
		System.out.println("[gui] termin:" + text);
		
		if (text != null) {
			termineText.setText(text);
		}
	}	
}
