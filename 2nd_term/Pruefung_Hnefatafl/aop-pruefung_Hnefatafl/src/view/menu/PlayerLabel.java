package view.menu;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.enums.Player;
import view.GlobalGuiConfig;

/**
 * Die Klasse PlayerLabel stellt ein Panel dar, das Informationen über einen
 * Spieler anzeigt, einschließlich des Namens, der Rolle (Angreifer oder
 * Verteidiger) und eines Indikators, der anzeigt, ob der Spieler am Zug ist.
 */
public class PlayerLabel extends JPanel {

	private static final long serialVersionUID = 1L;

	// TurnIndicator
	final private static String INDICATOR_MESSAGE = "Sie sind am Zug";
	final private static int FONT_SIZE = GlobalGuiConfig.applyScale(10);
	final private static Color BACKGROUN_COLOR = Color.LIGHT_GRAY;

	// NameLabels
	final private static int INSET = GlobalGuiConfig.applyScale(3);
	final private static int NAME_FONT_SIZE = GlobalGuiConfig.applyScale(20);
	final private static int ROLE_FONT_SIZE = GlobalGuiConfig.applyScale(15);

	private Player role;
	private String name;
	private JPanel turnIndicatorContainer;
	private JPanel nameLabelContainer;
	private CircleView circle;

	/**
	 * Erstellt eine neue Instanz von PlayerLabel mit der angegebenen Rolle und dem
	 * Namen des Spielers.
	 *
	 * @param role die Rolle des Spielers (Angreifer oder Verteidiger)
	 * @param name der Name des Spielers
	 */
	public PlayerLabel(final Player role, final String name) {
		this.role = role;
		this.name = name;
		this.turnIndicatorContainer = new JPanel();
		this.nameLabelContainer = new JPanel();
		this.circle = new CircleView(determinePlayerColor(), GlobalGuiConfig.applyScale(10));
		initializeTurnIndicator();
		initializeNameLabel();
		initialize();
	}

	/**
	 * Initialisiert das Layout und die Komponenten des PlayerLabel.
	 */
	private void initialize() {
		setBackground(null);
		setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(0, 0, 0, 0);
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;

		gbc.gridx = 0;
		gbc.gridy = 0;
		add(turnIndicatorContainer, gbc);

		gbc.gridy = 1;
		add(nameLabelContainer, gbc);
	}

	/**
	 * Initialisiert den Indikator, der anzeigt, dass der Spieler am Zug ist.
	 */
	private void initializeTurnIndicator() {
		turnIndicatorContainer.setBackground(BACKGROUN_COLOR);
		turnIndicatorContainer.setVisible(false);

		JLabel indicatorLabel = new JLabel(INDICATOR_MESSAGE);
		indicatorLabel.setFont(new Font(getFont().getName(), Font.PLAIN, FONT_SIZE));

		turnIndicatorContainer.add(indicatorLabel);
	}

	/**
	 * Initialisiert das Namenslabel und das Rollenslabel des Spielers.
	 */
	private void initializeNameLabel() {
		nameLabelContainer.setLayout(new GridBagLayout());
		nameLabelContainer.setBackground(null);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.WEST;
		gbc.insets = new Insets(INSET, INSET, INSET, INSET);

		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridheight = 1;
		gbc.gridwidth = 2;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		JLabel nameLabel = new JLabel(name);
		nameLabel.setFont(new Font(nameLabel.getFont().getName(), Font.PLAIN, NAME_FONT_SIZE));
		nameLabelContainer.add(nameLabel, gbc);

		gbc.gridy = 1;
		gbc.gridwidth = 1;
		gbc.weightx = 0;
		gbc.weighty = 0;
		nameLabelContainer.add(circle, gbc);

		gbc.gridx = 1;
		gbc.weightx = 1.0;
		gbc.weighty = 1.0;
		JLabel roleLabel = new JLabel(role.toString());
		roleLabel.setFont(new Font(roleLabel.getFont().getName(), Font.PLAIN, ROLE_FONT_SIZE));
		nameLabelContainer.add(roleLabel, gbc);
	}

	/**
	 * Bestimmt die Farbe des Spielers basierend auf seiner Rolle.
	 *
	 * @return die Farbe des Spielers
	 */
	private Color determinePlayerColor() {
		switch (role) {
		case DEFENDER:
			return GlobalGuiConfig.getDefenderPieceColor();
		case ATTACKER:
			return GlobalGuiConfig.getAttackerPieceColor();
		default:
			return null;
		}
	}

	/**
	 * Zeigt den Indikator an, dass der Spieler am Zug ist.
	 */
	public void showTurnIndicator() {
		turnIndicatorContainer.setVisible(true);

		Border customBorder = new Border() {
			@Override
			public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
				Graphics2D g2d = (Graphics2D) g;
				g2d.setColor(BACKGROUN_COLOR);
				g2d.setStroke(new BasicStroke(GlobalGuiConfig.applyScale(3)));

				g.setColor(BACKGROUN_COLOR);
				g.drawLine(x, y, x + width, y); // Oben
				g.drawLine(x, y, x, y + height); // Links
			}

			@Override
			public Insets getBorderInsets(Component c) {
				return new Insets(GlobalGuiConfig.applyScale(5), GlobalGuiConfig.applyScale(5), 0, 0);
			}

			@Override
			public boolean isBorderOpaque() {
				return true;
			}
		};

		nameLabelContainer.setBorder(customBorder);

		revalidate();
		repaint();
	}

	/**
	 * Versteckt den Indikator, dass der Spieler am Zug ist.
	 */
	public void hideTurnIndicator() {
		turnIndicatorContainer.setVisible(false);
		nameLabelContainer.setBorder(null);
		revalidate();
		repaint();
	}
}
