package view.menu;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.JPanel;

import model.enums.Player;
import view.GlobalGuiConfig;
import view.StartFrame;
import view.events.ViewObserver;

/**
 * Die Klasse MenuPanel stellt ein Panel dar, das die Benutzeroberfläche für das
 * Spielmenü enthält. Es zeigt die Namen der Spieler (Angreifer und Verteidiger)
 * sowie einen Bereich mit Schaltflächen für verschiedene Aktionen.
 */
public class MenuPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	final private static int WIDTH = GlobalGuiConfig.applyScale(300);
	final private static int HEIGHT = GlobalGuiConfig.applyScale(600);
	final public PlayerLabel attackerLabel;
	final public PlayerLabel defenderLabel;

	private List<ViewObserver> observers;

	/**
	 * Erstellt eine neue Instanz von MenuPanel mit der angegebenen Liste von
	 * ViewObserver.
	 *
	 * @param observer eine Liste von ViewObserver, die auf Benutzeraktionen
	 *                 reagieren
	 */
	public MenuPanel(List<ViewObserver> observer) {
		this.observers = observer;

		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setBackground(Color.WHITE);
		setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();

		final int inset = GlobalGuiConfig.applyScale(20);
		gbc.insets = new Insets(inset, inset, inset, inset);
		gbc.fill = GridBagConstraints.HORIZONTAL;
		gbc.weightx = 1.0;

		gbc.weighty = 0;
		gbc.anchor = GridBagConstraints.NORTHWEST;
		this.attackerLabel = new PlayerLabel(Player.ATTACKER, StartFrame.getAttackerName());
		add(attackerLabel, gbc);

		gbc.gridy = 1;
		this.defenderLabel = new PlayerLabel(Player.DEFENDER, StartFrame.getDefenderName());
		add(defenderLabel, gbc);

		gbc.gridy = 2;
		gbc.weighty = 1.0;
		gbc.anchor = GridBagConstraints.SOUTH;
		ButtonArea buttonArea = new ButtonArea(observers);
		add(buttonArea, gbc);
	}

}
