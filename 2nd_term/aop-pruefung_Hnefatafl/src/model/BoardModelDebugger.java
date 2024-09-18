package model;

import model.enums.Player;
import model.events.BoardModelObserver;
import model.events.PieceMovedEvent;
import model.events.SinglePointPieceEvent;
import model.pieces.Piece;

public class BoardModelDebugger implements BoardModelObserver {

	private BoardModel board;

	public BoardModelDebugger(final BoardModel board) {
		this.board = board;
	}

	/**
	 * Gibt das aktuelle Modell des Spiels in der Konsole aus. Dies behinhaltet
	 * Haupt- und Hilfsdatenstruktur.
	 * 
	 * @see {@link #printPrimaryDataStructure()}
	 * @see {@link #printAuxiliaryDataStructure()}
	 */
	public void printBothDatastructures() {
		printPrimaryDataStructure();
		System.out.println();
		printAuxiliaryDataStructure();
		System.out.println();
	}

	/**
	 * Druckt die Hauptdatenstruktur. Diese Beinhaltet das Brett und die konkrete
	 * Belegung jeder Zelle mit ihrem jeweiligen Stein.
	 */
	public void printPrimaryDataStructure() {
		printColumnHeaders();
		for (int i = 0; i < board.getSize(); i++) {
			System.out.printf("%2d ", i);

			for (int j = 0; j < board.getSize(); j++) {
				Piece piece = board.getPiece(j, i).orElse(null);
				printPiece(piece);
			}

			System.out.println();
		}
	}

	/**
	 * Druckt die Hilfsdatenstruktur. Diese Beinhaltet das Brett und frei/belget
	 * Status des Bretts, nicht aber die belgenden Figuren.
	 */
	public void printAuxiliaryDataStructure() {
		printColumnHeaders();
		for (int i = 0; i < board.getSize(); i++) {
			System.out.printf("%2d ", i);

			for (int j = 0; j < board.getSize(); j++) {
				boolean conatinsPiece = board.getVertical().get(j) == null ? false
						: board.getVertical().get(j).contains(i);
				if (conatinsPiece) {
					System.out.printf("%-3s", "X");
				} else {
					System.out.printf("%-3s", "-");
				}
			}

			System.out.println();
		}
	}

	/**
	 * Druckt das Symbol eines Steins oder ein Platzhalter, wenn der Spielstein null
	 * ist.
	 */
	private void printPiece(Piece piece) {
		if (piece == null) {
			System.out.printf("%-3s", "-");
		} else {
			System.out.printf("%-3s", piece);
		}
	}

	/**
	 * Druckt die Spaltenüberschriften für das Brett.
	 */
	private void printColumnHeaders() {
		System.out.print("  ");
		for (int j = 0; j < board.getSize(); j++) {
			System.out.printf("%2d ", j);
		}
		System.out.println();
	}

	@Override
	public void onPieceMoved(PieceMovedEvent e) {
		System.out.println("MOVED - " + e.getPieceType() + ": (" + e.getFromX() + ", " + e.getFromY() + ") -> ("
				+ e.getToX() + ", " + e.getToY() + ")");

	}

	@Override
	public void onPiecePlaced(SinglePointPieceEvent e) {
		System.out.println("PLACED - " + e.getPieceType() + ": (" + e.getX() + ", " + e.getY() + ")");
		System.out.println("New Datastructure: ");
		printPrimaryDataStructure();

	}

	@Override
	public void onPieceRemoved(SinglePointPieceEvent e) {
		System.out.println("REMOVED - " + e.getPieceType() + ": (" + e.getX() + ", " + e.getY() + ")");
		System.out.println("New Datastructure: ");
		printPrimaryDataStructure();
	}

	@Override
	public void onPieceCaptured(SinglePointPieceEvent e) {
		System.out.println("CAPTURED - " + e.getPieceType() + ": (" + e.getX() + ", " + e.getY() + ")");
		System.out.println("New Datastructure: ");
		printPrimaryDataStructure();
	}

	@Override
	public void onVictory(Player winner) {
		System.out.println("VICTORY - " + winner);
		System.out.println("New Datastructure: ");
		printPrimaryDataStructure();
	}

	@Override
	public void onBoardCleared() {
		System.out.println("CLEARED");
		System.out.println("New Datastructure: ");
		printPrimaryDataStructure();
	}

	@Override
	public void onNewActivePlayer(Player activePlayer) {
		System.out.println("TOGGLE_PLAYER - new active Player: " + activePlayer);
	}
}
