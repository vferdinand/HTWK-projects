package view.board;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import model.enums.PieceType;
import view.GlobalGuiConfig;

/**
 * PieceView ist ein Panel, das einen Spielstein darstellt. Es zeichnet den
 * Spielstein in der entsprechenden Farbe basierend auf seinem Typ.
 */
public class PieceView extends JPanel {

	private static final long serialVersionUID = 1L;

	private PieceType pieceType;

	final static private int PIECE_SIZE_IN_PERCENT_OF_AVAILABLE_SPACE = 80;

	/**
	 * Konstruktor für PieceView.
	 *
	 * @param pieceType Der Typ des Spielsteins, der dargestellt werden soll.
	 */
	public PieceView(final PieceType pieceType) {
		this.pieceType = pieceType;
		initilize();
	}

	/**
	 * Initialisiert die PieceView, einschließlich Hintergrundfarbe.
	 */
	private void initilize() {
		setBackground(null);
	}

	/**
	 * Bestimmt die Farbe des Spielsteins basierend auf seinem Typ.
	 *
	 * @return Die Farbe des Spielsteins.
	 */
	private Color determinePieceColor() {
		switch (pieceType) {
		case KING:
			return GlobalGuiConfig.getKingPieceColor();
		case DEFENDER:
			return GlobalGuiConfig.getDefenderPieceColor();
		case ATTACKER:
			return GlobalGuiConfig.getAttackerPieceColor();
		default:
			return null;
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		int x = (getWidth() - calculatePieceWidth()) / 2;
		int y = (getHeight() - calculatePieceHeight()) / 2;
		g.setColor(determinePieceColor());
		g.fillOval(x, y, calculatePieceWidth(), calculatePieceHeight());
	}

	/**
	 * Berechnet die Breite des Spielsteins basierend auf dem verfügbaren Platz im
	 * umgebenden Panel.
	 *
	 * @return Die Breite des Spielsteins.
	 */
	private int calculatePieceWidth() {
		return (int) (getWidth() * (PIECE_SIZE_IN_PERCENT_OF_AVAILABLE_SPACE / 100.0));
	}

	/**
	 * Berechnet die Höhe des Spielsteins basierend auf dem verfügbaren Platz im
	 * umgebenden Panel.
	 *
	 * @return Die Höhe des Spielsteins.
	 */
	private int calculatePieceHeight() {
		return (int) (getHeight() * (PIECE_SIZE_IN_PERCENT_OF_AVAILABLE_SPACE / 100.0));
	}
}
