package view.menu;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;

import view.GlobalGuiConfig;
import view.events.ViewObserver;
import view.sharedElements.ButtonView;

/**
 * Die Klasse ButtonArea stellt einen Bereich mit Schaltflächen dar, die
 * verschiedene Aktionen im Spiel ermöglichen. Diese Schaltflächen sind in einem
 * GridBagLayout angeordnet und reagieren auf Benutzerinteraktionen, indem sie
 * die entsprechenden Observer benachrichtigen.
 * 
 * <pre>
 * Die Schaltflächen umfassen:
 *   - Angreifer platzieren
 *   - Verteidiger platzieren
 *   - Spielstein entfernen
 *   - Aktiven Spieler ändern
 *   - Sieg Angreifer
 *   - Sieg Verteidiger
 * </pre>
 * 
 * @see ViewObserver
 * @see ButtonView
 */
public class ButtonArea extends JPanel {

	private static final long serialVersionUID = 1L;

	private static final int BUTTON_WIDTH = GlobalGuiConfig.applyScale(0);
	private static final int BUTTON_HEIGHT = GlobalGuiConfig.applyScale(60);
	private static final int BUTTON_INSETES = GlobalGuiConfig.applyScale(2);

	private List<ViewObserver> observers;

	/**
	 * Erstellt eine neue Instanz von ButtonArea mit der angegebenen Liste von
	 * ViewObserver.
	 *
	 * @param observer eine Liste von ViewObserver, die auf Benutzeraktionen
	 *                 reagieren.
	 */
	public ButtonArea(List<ViewObserver> observer) {
		this.observers = observer;
		initialize();
		createButtons();
	}

	/**
	 * Initialisiert die Benutzeroberfläche, indem das Layout und die
	 * Hintergrundfarbe festgelegt werden.
	 */
	private void initialize() {
		setLayout(new GridBagLayout());
		setBackground(null);
	}

	/**
	 * Erstellt die Schaltflächen und fügt sie dem Panel hinzu. Jede Schaltfläche
	 * ist mit einem ActionListener versehen, der die entsprechenden Methoden der
	 * ViewObserver aufruft, wenn die Schaltfläche gedrückt wird.
	 */
	private void createButtons() {
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(BUTTON_INSETES, BUTTON_INSETES, BUTTON_INSETES, BUTTON_INSETES);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		ButtonView button;

		gbc.gridx = 0;
		gbc.gridy = 0;
		button = new ButtonView("<html><center>Angreifer<br>platzieren</center></html>");
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		add(button, gbc);

		button.addActionListener(e -> observers.forEach(o -> o.onPlaceAttackerClicked()));

		gbc.gridx = 1;
		gbc.gridy = 0;
		button = new ButtonView("<html><center>Verteidiger<br>platzieren</center></html>");
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		add(button, gbc);

		button.addActionListener(e -> observers.forEach(o -> o.onPlaceDefenderClicked()));

		gbc.gridx = 0;
		gbc.gridy = 1;
		button = new ButtonView("<html><center>Spielstein<br>entfernen</center></html>");
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		add(button, gbc);

		button.addActionListener(e -> observers.forEach(o -> o.onRemovePieceClicked()));

		gbc.gridx = 1;
		gbc.gridy = 1;
		button = new ButtonView("<html><center>Aendere<br>aktiv. Spieler</center></html>");
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		add(button, gbc);

		button.addActionListener(e -> observers.forEach(o -> o.onChangeActivePlayerClicked()));

		gbc.gridx = 0;
		gbc.gridy = 2;
		button = new ButtonView("<html><center>Sieg<br>Angreifer</center></html>");
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		add(button, gbc);

		button.addActionListener(e -> observers.forEach(o -> o.onAttackerVictoryClicked()));

		gbc.gridx = 1;
		gbc.gridy = 2;
		button = new ButtonView("<html><center>Sieg<br>Verteidiger</center></html>");
		button.setPreferredSize(new Dimension(BUTTON_WIDTH, BUTTON_HEIGHT));
		add(button, gbc);

		button.addActionListener(e -> observers.forEach(o -> o.onDefenderVictoryClicked()));
	}
}
