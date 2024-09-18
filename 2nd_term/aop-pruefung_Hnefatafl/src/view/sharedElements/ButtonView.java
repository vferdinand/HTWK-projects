package view.sharedElements;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import view.GlobalGuiConfig;

/**
 * Die Klasse ButtonView stellt eine benutzerdefinierte Schaltfläche dar, die
 * mit einem Label versehen ist und eine Hover-Animation bietet.
 * 
 * Die Schaltfläche hat eine standardmäßige Hintergrundfarbe und ändert ihre
 * Farbe, wenn der Mauszeiger darüber bewegt wird.
 */
public class ButtonView extends JButton {

	private static final long serialVersionUID = 1L;

	private static final int PADDING = GlobalGuiConfig.applyScale(10);

	/**
	 * Erstellt eine neue Instanz von ButtonView mit dem angegebenen Label.
	 *
	 * @param label der Text, der auf der Schaltfläche angezeigt wird
	 */
	public ButtonView(final String label) {
		super(label);
		initialize();
		initializeHoverAnimation();
	}

	/**
	 * Initialisiert die Schaltfläche, indem das Aussehen und Verhalten festgelegt
	 * werden.
	 */
	private void initialize() {
		setFocusPainted(false);
		setBorder(new EmptyBorder(PADDING, PADDING, PADDING, PADDING));
		setBackground(Color.LIGHT_GRAY);
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setFont(new Font(getFont().getName(), Font.PLAIN, GlobalGuiConfig.applyScale(12)));
	}

	/**
	 * Fügt eine Hover-Animation hinzu, die die Hintergrundfarbe der Schaltfläche
	 * ändert, wenn der Mauszeiger darüber bewegt wird.
	 */
	private void initializeHoverAnimation() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				setBackground(Color.GRAY);
			}

			@Override
			public void mouseExited(MouseEvent e) {
				setBackground(Color.LIGHT_GRAY);
			}
		});
	}
}
