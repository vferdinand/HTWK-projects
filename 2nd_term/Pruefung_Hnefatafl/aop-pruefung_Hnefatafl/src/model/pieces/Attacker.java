package model.pieces;

import model.BoardModel;
import model.enums.PieceType;

/**
 * Klasse, die einen Angreifer repräsentiert.
 */
public class Attacker extends Piece {

	/**
	 * Konstruktor für einen Angreifer.
	 *
	 * @param boardPieceIsOn Das Board, auf dem sich der Angreifer befindet.
	 * @param x              Die x-Koordinate des Angreifers.
	 * @param y              Die y-Koordinate des Angreifers.
	 */
	protected Attacker(final BoardModel boardPieceIsOn, final int x, final int y) {
		super(boardPieceIsOn, x, y);
	}

	@Override
	public boolean isHostileTo(final Piece piece) {
		return piece instanceof Defender;
	}

	@Override
	public String toString() {
		return "A";
	}

	@Override
	public PieceType getType() {
		return PieceType.ATTACKER;
	}

}
