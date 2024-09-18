package model.pieces;

import model.BoardModel;
import model.enums.PieceType;

/**
 * Klasse, die einen Verteidiger repräsentiert.
 */
public class Defender extends Piece {

	/**
	 * Konstruktor für einen Verteidiger.
	 *
	 * @param boardPieceIsOn Das Board, auf dem sich der Verteidiger befindet.
	 * @param x              Die x-Koordinate des Verteidigers.
	 * @param y              Die y-Koordinate des Verteidigers.
	 */
	protected Defender(final BoardModel boardPieceIsOn, final int x, final int y) {
		super(boardPieceIsOn, x, y);
	}

	@Override
	public boolean isHostileTo(final Piece piece) {
		return piece instanceof Attacker;
	}

	@Override
	public String toString() {
		return "D";
	}

	@Override
	public PieceType getType() {
		return PieceType.DEFENDER;
	}

}
