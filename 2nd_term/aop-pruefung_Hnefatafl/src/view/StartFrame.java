package view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import view.sharedElements.ButtonView;

/**
 * StartFrame ist das Fenster, das beim Start des Spiels angezeigt wird. Es
 * ermöglicht den Spielern, ihre Namen einzugeben und die Fenstergröße
 * auszuwählen.
 */
public class StartFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private final int WIDTH = GlobalGuiConfig.applyScale(500);
	private final int HEIGHT = GlobalGuiConfig.applyScale(300);
	private final int INSETS = GlobalGuiConfig.applyScale(5);

	private JTextArea startMessage;
	private static JTextField defender;
	private static JTextField attacker;
	private JLabel dLabel;
	private JLabel aLabel;
	private JLabel sizeLabel;
	private JPanel container;
	private JComboBox<String> sizeDropdownMenu;
	private String[] sizeType = { "klein", "mittel", "gross" };

	/**
	 * Konstruktor für StartFrame. Initialisiert die Komponenten des Fensters.
	 */
	public StartFrame() {
		startMessage = new JTextArea();
		sizeDropdownMenu = new JComboBox<String>(sizeType);
		initialize();
	}

	/**
	 * Initialisiert das Startfenster, einschließlich Layout, Größe und Komponenten.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setTitle("starte Hnefatafl...");
		setResizable(false);
		setLocationRelativeTo(null);

		container = new JPanel();
		container.setBorder(new EmptyBorder(new Insets(INSETS, INSETS, INSETS, INSETS)));
		container.setLayout(new GridBagLayout());

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(INSETS, INSETS, INSETS, INSETS);
		gbc.weightx = 1.0;
		gbc.fill = GridBagConstraints.HORIZONTAL;

		gbc.gridwidth = 2;
		startMessage.setText("Wer spielt? Gebt bitte eure Namen ein.");
		startMessage.setEditable(false);
		startMessage.setOpaque(false);
		startMessage.setFont(new Font(startMessage.getFont().getName(), Font.PLAIN, GlobalGuiConfig.applyScale(16)));
		container.add(startMessage, gbc);

		gbc.insets = new Insets(INSETS, INSETS, 0, INSETS);
		gbc.gridwidth = 1;
		gbc.gridy = 1;
		dLabel = new JLabel("Verteidiger");
		container.add(dLabel, gbc);

		gbc.gridx = 1;
		aLabel = new JLabel("Angreifer");
		container.add(aLabel, gbc);

		gbc.insets = new Insets(0, INSETS, INSETS, INSETS);
		gbc.gridy = 2;
		gbc.gridx = 0;
		defender = new JTextField();
		container.add(defender, gbc);

		gbc.gridx = 1;
		attacker = new JTextField();
		container.add(attacker, gbc);

		gbc.insets = new Insets(INSETS, INSETS, 0, INSETS);
		gbc.gridy = 3;
		gbc.gridx = 0;
		sizeLabel = new JLabel("Fenstergroesse auswaehlen:");
		container.add(sizeLabel, gbc);

		gbc.insets = new Insets(0, INSETS, INSETS, INSETS);
		gbc.gridy = 4;
		sizeDropdownMenu.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				if (sizeDropdownMenu.getSelectedItem() == "klein") {
					GlobalGuiConfig.setGLOBAL_SCALING_FACTOR(1.0);
				} else if (sizeDropdownMenu.getSelectedItem() == "mittel") {
					GlobalGuiConfig.setGLOBAL_SCALING_FACTOR(1.5);
				} else if (sizeDropdownMenu.getSelectedItem() == "gross") {
					GlobalGuiConfig.setGLOBAL_SCALING_FACTOR(2);
				}
			}
		});
		container.add(sizeDropdownMenu, gbc);

		gbc.insets = new Insets(INSETS, INSETS, INSETS, INSETS);
		gbc.gridwidth = 2;
		gbc.gridy = 5;
		ButtonView startButton = new ButtonView("start");
		container.add(startButton, gbc);

		add(container);

		startButton.addActionListener(e -> this.dispose());

		setVisible(true);
	}

	/**
	 * Gibt den Namen des Angreifers zurück. Wenn kein Name eingegeben wurde, wird
	 * "Spieler eins" zurückgegeben.
	 *
	 * @return Der Name des Angreifers.
	 */
	public static String getAttackerName() {
		if (attacker.getText().isBlank()) {
			return "Spieler eins";
		}
		return attacker.getText();
	}

	/**
	 * Gibt den Namen des Verteidigers zurück. Wenn kein Name eingegeben wurde, wird
	 * "Spieler zwei" zurückgegeben.
	 *
	 * @return Der Name des Verteidigers.
	 */
	public static String getDefenderName() {
		if (defender.getText().isBlank()) {
			return "Spieler zwei";
		}
		return defender.getText();
	}
}
