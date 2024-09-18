package model.pieces;

import java.util.Optional;
import java.util.function.Consumer;

import model.BoardModel;
import model.enums.Cell;
import model.enums.PieceType;
import model.enums.Player;

/**
 * Abstrakte Klasse, die ein Spielstück repräsentiert.
 */
public abstract class Piece {

	protected int x;
	protected int y;
	protected BoardModel boardPieceIsOn;

	/**
	 * Konstruktor für ein Spielstück.
	 *
	 * @param boardPieceIsOn Das Board, auf dem sich das Stück befindet.
	 * @param x              Die x-Koordinate des Stücks.
	 * @param y              Die y-Koordinate des Stücks.
	 */
	protected Piece(final BoardModel boardPieceIsOn, final int x, final int y) {
		this.boardPieceIsOn = boardPieceIsOn;
		this.x = x;
		this.y = y;
	}

	/**
	 * Gibt die x-Koordinate des Stücks zurück.
	 *
	 * @return Die x-Koordinate.
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gibt die y-Koordinate des Stücks zurück.
	 *
	 * @return Die y-Koordinate.
	 */
	public int getY() {
		return y;
	}

	/**
	 * Setzt die x- und y-Koordinaten des Stücks.
	 *
	 * @param x Die neue x-Koordinate.
	 * @param y Die neue y-Koordinate.
	 */
	public void setXY(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Erstellt ein neues Spielstück basierend auf dem angegebenen Typ.
	 *
	 * @param pieceType Der Typ des Stücks.
	 * @param board     Das Board, auf dem sich das Stück befindet.
	 * @param x         Die x-Koordinate des Stücks.
	 * @param y         Die y-Koordinate des Stücks.
	 * @return Das neu erstellte Stück.
	 * @throws IllegalArgumentException Wenn der Stücktyp ungültig ist.
	 */
	public static Piece create(PieceType pieceType, final BoardModel board, final int x, final int y) {
		switch (pieceType) {
		case ATTACKER:
			return new Attacker(board, x, y);
		case DEFENDER:
			return new Defender(board, x, y);
		case KING:
			return new King(board, x, y);
		default:
			throw new IllegalArgumentException("There is no such piece: " + pieceType);
		}
	}

	/**
	 * Überprüft, ob dieses Stück feindlich gegenüber einem anderen Stück ist.
	 *
	 * @param other Das andere Stück.
	 * @return true, wenn das Stück feindlich ist, andernfalls false.
	 */
	public abstract boolean isHostileTo(final Piece other);

	/**
	 * Gibt den Typ des Stücks zurück.
	 *
	 * @return Der Typ des Stücks.
	 */
	public abstract PieceType getType();

	/**
	 * Überprüft, ob das Stück gefangen ist.
	 *
	 * @return true, wenn das Stück gefangen ist, andernfalls false.
	 */
	public boolean isCaptured() {

		if (0 < y && y < boardPieceIsOn.getSize() - 1) {

			final boolean isBelowEnemy = boardPieceIsOn.getPiece(x, y + 1).map(p -> p.isHostileTo(this)).orElse(false);
			final boolean isAboveEnemy = boardPieceIsOn.getPiece(x, y - 1).map(p -> p.isHostileTo(this)).orElse(false);
			final boolean isBelowCellThatCaptures = givenCellCanCapture(x, y + 1);
			final boolean isAboveCellThatCaptures = givenCellCanCapture(x, y - 1);

			if ((isBelowEnemy || isBelowCellThatCaptures) && (isAboveEnemy || isAboveCellThatCaptures)) {
				return true;
			}

		}

		if (0 < x && x < boardPieceIsOn.getSize() - 1) {

			final boolean isToTheRightOfEnemy = boardPieceIsOn.getPiece(x - 1, y).map(p -> p.isHostileTo(this))
					.orElse(false);
			final boolean isToTheLeftOfEnemy = boardPieceIsOn.getPiece(x + 1, y).map(p -> p.isHostileTo(this))
					.orElse(false);
			final boolean isToTheRichtOfCellThatCaptures = givenCellCanCapture(x - 1, y);
			final boolean isToTheLeftOfCellThatCaptures = givenCellCanCapture(x + 1, y);

			if ((isToTheRightOfEnemy || isToTheRichtOfCellThatCaptures)
					&& (isToTheLeftOfEnemy || isToTheLeftOfCellThatCaptures)) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Überprüft, ob die angegebene Zelle das Stück fangen kann.
	 *
	 * @param x Die x-Koordinate der Zelle.
	 * @param y Die y-Koordinate der Zelle.
	 * @return true, wenn die Zelle fangen kann, andernfalls false.
	 */
	private boolean givenCellCanCapture(final int x, final int y) {
		final Cell cell = boardPieceIsOn.cellType(x, y);
		if (cell == Cell.CORNER) {
			return true;
		} else if (cell == Cell.THRONE && !boardPieceIsOn.containsPiece(x, y)) {
			return true;
		}
		return false;
	}

	/**
	 * Überprüft, ob das Stück von einem bestimmten Spieler bewegt werden darf.
	 *
	 * @param player Der Spieler, der das Stück bewegen möchte.
	 * @return true, wenn der Spieler das Stück bewegen darf, andernfalls false.
	 */
	public boolean isAllowedToBeMovedBy(final Player player) {
		boolean thisIsAttacker = this instanceof Attacker;
		if (player == Player.ATTACKER && !(thisIsAttacker)) {
			return false;
		} else if (player == Player.DEFENDER && thisIsAttacker) {
			return false;
		}
		return true;
	}

	/**
	 * Führt eine Aktion für jedes benachbarte Stück aus.
	 *
	 * @param action Die Aktion, die auf jedes benachbarte Stück angewendet werden
	 *               soll.
	 */
	public void forEachAdjacentPiece(final Consumer<Piece> action) {
		final short[] offsetX = { 0, -1, 0, 1 };
		final short[] offsetY = { 1, 0, -1, 0 };

		for (short i = 0; i < offsetX.length; i++) {
			final Optional<Piece> offsetPiece = boardPieceIsOn.getPiece(getX() + offsetX[i], getY() + offsetY[i]);
			offsetPiece.ifPresent(p -> action.accept(p));
		}
	}

}
