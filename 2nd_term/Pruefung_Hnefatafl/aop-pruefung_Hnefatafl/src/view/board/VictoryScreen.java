package view.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import model.enums.Player;
import view.GlobalGuiConfig;
import view.events.ViewObserver;
import view.sharedElements.ButtonView;

/**
 * VictoryScreen ist ein Panel, das angezeigt wird, wenn ein Spieler das Spiel
 * gewonnen hat. Es zeigt eine Siegesnachricht und bietet Optionen für ein neues
 * Spiel oder das Beenden der Anwendung.
 */
public class VictoryScreen extends JPanel {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = GlobalGuiConfig.applyScale(600);
	private static final int INSETS = GlobalGuiConfig.applyScale(5);
	private static final Color BACKGROUND_COLOR = new Color(0, 0, 0, 220);

	private static final String[] VICTORY_MESSAGES_ATTACKER = {
			"Der Koenig ist gestuerzt, das Reich in Aufruhe. Die Angreifer gewinnen!",
			"Der Koenig ist vom Feind umstellt, sein Heer geschlagen. Die Schlagkraft des Angreifs ist zu maechtig.",
			"Der Feind Ueberwindet die Burgmauer. Der Koenig muss kapitulieren, der Sieg gehoert dem Angreifer.", };

	private static final String[] VICTORY_MESSAGES_DEFENDER = {
			"Taktisches Kalkuel faehrt den Koenig zum Ziel. Er konnte den Verfolgern entkommen, der Sieg ist sein.",
			"Die Schutztruppen halten stand. Der Koenig erreicht unversehrt seinen Sieg. Die Jagd hat ein Ende.",
			"Der Feldzug des Feindes scheitert. Der Koenig konnte sich einer Gefangennahme entziehen und steht siegreichen Hauptes." };

	private JTextArea victoryMessage;
	private List<ViewObserver> observers;

	/**
	 * Konstruktor für VictoryScreen.
	 *
	 * @param observer Eine Liste von ViewObservern, die bei Ereignissen informiert
	 *                 werden.
	 */
	public VictoryScreen(List<ViewObserver> observer) {
		victoryMessage = new JTextArea();
		this.observers = observer;
		initialize();
		initializeVictoryMessage();
	}

	/**
	 * Initialisiert das VictoryScreen, einschließlich Hintergrundfarbe und Layout.
	 */
	private void initialize() {
		setBackground(BACKGROUND_COLOR);
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.NORTHWEST;
		gbc.insets = new Insets(INSETS, INSETS, INSETS, INSETS);

		gbc.gridwidth = 2;
		add(victoryMessage, gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		ButtonView buttonOne = new ButtonView("Neues Spiel");
		add(buttonOne, gbc);

		buttonOne.addActionListener(e -> {
			observers.forEach(o -> o.onGameRestartClicked());
		});

		gbc.gridx = 1;
		ButtonView buttonTwo = new ButtonView("Anwendung beenden");
		add(buttonTwo, gbc);

		buttonTwo.addActionListener(e -> {
			observers.forEach(o -> o.onApplicationCloseClicked());
		});
	}

	/**
	 * Initialisiert die Eigenschaften der Siegesnachricht.
	 */
	private void initializeVictoryMessage() {
		victoryMessage.setLineWrap(true);
		victoryMessage.setWrapStyleWord(true);
		victoryMessage.setEditable(false);
		victoryMessage.setOpaque(false);
		victoryMessage.setHighlighter(null);
		victoryMessage
				.setFont(new Font(victoryMessage.getFont().getName(), Font.PLAIN, GlobalGuiConfig.applyScale(16)));
		victoryMessage.setForeground(Color.WHITE);
		victoryMessage.setPreferredSize(new Dimension((int) (WIDTH * 0.7), 150));
	}

	/**
	 * Wählt eine zufällige Siegesnachricht basierend auf dem Gewinner aus.
	 *
	 * @param winner Der Spieler, der gewonnen hat.
	 */
	public void randomizeVictoryMessage(final Player winner) {
		Random random = new Random();

		if (winner == Player.ATTACKER) {
			victoryMessage.setText(VICTORY_MESSAGES_ATTACKER[random.nextInt(VICTORY_MESSAGES_ATTACKER.length)]);
		}

		if (winner == Player.DEFENDER) {
			victoryMessage.setText(VICTORY_MESSAGES_DEFENDER[random.nextInt(VICTORY_MESSAGES_DEFENDER.length)]);
		}
	}

}
