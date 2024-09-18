package view.board;

import java.util.List;

import javax.swing.JLayeredPane;
import javax.swing.OverlayLayout;

import model.enums.Player;
import view.events.ViewObserver;

/**
 * BoardPanel ist ein Panel, das das Spielbrett und den Siegesbildschirm
 * verwaltet. Es erbt von JLayeredPane, um die Überlagerung von Komponenten zu
 * ermöglichen.
 */
public class BoardPanel extends JLayeredPane {

	private static final long serialVersionUID = 1L;

	public BoardView boardView;
	final private VictoryScreen victoryScreen;

	/**
	 * Konstruktor für BoardPanel.
	 *
	 * @param observer Eine Liste von ViewObservern, die bei Ereignissen informiert
	 *                 werden sollen.
	 */
	public BoardPanel(final List<ViewObserver> observer) {
		this.boardView = new BoardView(observer, 13);
		this.victoryScreen = new VictoryScreen(observer);
		initialize();
	}

	/**
	 * Initialisiert das BoardPanel, einschließlich Layout und Sichtbarkeit der
	 * Komponenten.
	 */
	private void initialize() {
		setLayout(new OverlayLayout(this));

		add(boardView);
		add(victoryScreen);

		setLayer(boardView, 0);
		setLayer(victoryScreen, 1);

		victoryScreen.setVisible(false);
	}

	/**
	 * Zeigt den Siegesbildschirm an und randomisiert die Siegesnachricht für den
	 * Gewinner.
	 *
	 * @param winner Der Spieler, der gewonnen hat.
	 */
	public void showVictoryScreen(final Player winner) {
		victoryScreen.randomizeVictoryMessage(winner);
		victoryScreen.setVisible(true);
	}

	/**
	 * Versteckt den Siegesbildschirm.
	 */
	public void hideVictoryScreen() {
		victoryScreen.setVisible(false);
	}

	/**
	 * Leert das Spielbrett von Steinen und aktualisiert die Anzeige.
	 */
	public void clearBoard() {
		boardView.clear();
		revalidate();
		repaint();
	}
}
