package view.board;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import model.enums.Cell;

/**
 * CellView ist ein Panel, das eine einzelne Zelle des Spielbretts darstellt. Es
 * zeigt den Hintergrund und die Grenzen der Zelle basierend auf ihrem Typ an.
 */
public class CellView extends JPanel {

	private static final long serialVersionUID = 1L;
	private Cell celltype;

	final static private Color BORDER_COLOR = Color.BLACK;
	final static private int BORDER_THICKNESS = 1;

	final static private Color THRONE_COLOR = Color.LIGHT_GRAY;
	final static private Color CORNER_COLOR = Color.DARK_GRAY;
	final static private Color DEFAULT_COLOR = Color.WHITE;

	/**
	 * Konstruktor für CellView.
	 *
	 * @param celltype Der Typ der Zelle, die dargestellt werden soll.
	 */
	public CellView(Cell celltype) {
		this.celltype = celltype;
		initilize();
	}

	/**
	 * Initialisiert die CellView, einschließlich Layout, Rahmen und
	 * Hintergrundfarbe.
	 */
	private void initilize() {
		setLayout(new BorderLayout());
		setBorder(new LineBorder(BORDER_COLOR, BORDER_THICKNESS));
		setBackground(determineBackgroundColor());
	}

	/**
	 * Bestimmt die Hintergrundfarbe der Zelle basierend auf ihrem Typ.
	 *
	 * @return Die Hintergrundfarbe der Zelle.
	 */
	private Color determineBackgroundColor() {
		switch (celltype) {
		case THRONE:
			return THRONE_COLOR;
		case CORNER:
			return CORNER_COLOR;
		case DEFAULT:
			return DEFAULT_COLOR;
		default:
			return null;
		}
	}

}
