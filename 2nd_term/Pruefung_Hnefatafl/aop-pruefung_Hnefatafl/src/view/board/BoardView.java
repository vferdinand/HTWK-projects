package view.board;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JPanel;

import model.enums.Cell;
import model.enums.PieceType;
import view.GlobalGuiConfig;
import view.events.CellClickedEvent;
import view.events.ViewObserver;

/**
 * BoardView ist ein Panel, das das Spielbrett darstellt. Es ermöglicht die
 * Interaktion mit den Zellen des Brettes und zeigt die Spielsteine an.
 */
public class BoardView extends JPanel {

	private static final long serialVersionUID = 1L;
	private int size;

	final static private int WIDTH = GlobalGuiConfig.applyScale(600);
	final static private int HEIGHT = GlobalGuiConfig.applyScale(600);
	final private List<ViewObserver> observers;

	/**
	 * Konstruktor für BoardView.
	 *
	 * @param observer Eine Liste von ViewObservern, die bei Ereignissen informiert
	 *                 werden.
	 * @param size     Die Größe des Spielbretts (size x size).
	 */
	public BoardView(final List<ViewObserver> observer, int size) {
		this.observers = observer;
		this.size = size;
		initilize();
	}

	/**
	 * Initialisiert das BoardView, einschließlich Layout, Größe und Zellen.
	 */
	private void initilize() {
		setLayout(new GridLayout(size, size));
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		generateGrid();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int cellWidth = getWidth() / size;
				int cellHeight = getHeight() / size;
				int xOfCell = arg0.getX() / cellWidth;
				int yOfCell = arg0.getY() / cellHeight;

				observers.forEach(c -> c.onCellClicked(new CellClickedEvent(xOfCell, yOfCell)));

			}
		});
		setBackground(Color.WHITE);

	}

	/**
	 * Generiert das Gitter für das Spielbrett und fügt die Zellen hinzu.
	 */
	private void generateGrid() {
		for (int row = 0; row < size; row++) {
			for (int column = 0; column < size; column++) {
				Cell currentCellType = determineCellType(row, column);
				CellView cell = new CellView(currentCellType);
				add(cell);
			}
		}
	}

	/**
	 * Bestimmt den Typ der Zelle basierend auf ihrer Position.
	 *
	 * @param row    Die Zeile der Zelle.
	 * @param column Die Spalte der Zelle.
	 * @return Der Typ der Zelle (z.B. THRONE, CORNER, DEFAULT).
	 */
	private Cell determineCellType(int row, int column) {
		boolean isCenterRow = size / 2 == row;
		boolean isCenterColumn = column == size / 2;
		if (isCenterRow && isCenterColumn) {
			return Cell.THRONE;
		}

		boolean isEdgeRow = 0 == row || size - 1 == row;
		boolean isEdgeColumn = 0 == column || size - 1 == column;
		if (isEdgeRow && isEdgeColumn) {
			return Cell.CORNER;
		}

		return Cell.DEFAULT;
	}

	/**
	 * Gibt die CellView an der angegebenen Position zurück.
	 *
	 * @param row    Die Zeile der Zelle.
	 * @param column Die Spalte der Zelle.
	 * @return Die CellView an der angegebenen Position.
	 */
	public CellView getCell(int row, int column) {
		return (CellView) getComponent(row * size + column);
	}

	/**
	 * Platziert einen Spielstein in der angegebenen Zelle.
	 *
	 * @param pieceType Der Typ des Spielsteins, der platziert werden soll.
	 * @param row       Die Zeile, in der der Spielstein platziert werden soll.
	 * @param column    Die Spalte, in der der Spielstein platziert werden soll.
	 */
	public void placePiece(PieceType pieceType, int row, int column) {
		PieceView piece = new PieceView(pieceType);
		CellView cellView = getCell(row, column);
		cellView.add(piece);
	}

	/**
	 * Entfernt den Spielstein aus der angegebenen Zelle.
	 *
	 * @param row    Die Zeile der Zelle, aus der der Spielstein entfernt werden
	 *               soll.
	 * @param column Die Spalte der Zelle, aus der der Spielstein entfernt werden
	 *               soll.
	 */
	public void removePiece(int row, int column) {
		CellView cellView = getCell(row, column);
		cellView.removeAll();
	}

	/**
	 * Setzt Spielbrett auf leeren Zustand zurück.
	 */
	public void clear() {
		removeAll();
		generateGrid();
		revalidate();
		repaint();
	}
}
