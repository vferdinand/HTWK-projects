package model.pieces;

import java.util.Optional;

import model.BoardModel;
import model.enums.Cell;
import model.enums.PieceType;

/**
 * Klasse, die einen König repräsentiert, der ein Verteidiger ist.
 */
public class King extends Defender {

	/**
	 * Konstruktor für einen König.
	 *
	 * @param boardPieceIsOn Das Board, auf dem sich der König befindet.
	 * @param x              Die x-Koordinate des Königs.
	 * @param y              Die y-Koordinate des Königs.
	 */
	protected King(final BoardModel boardPieceIsOn, final int x, final int y) {
		super(boardPieceIsOn, x, y);
	}

	@Override
	public String toString() {
		return "K";
	}

	@Override
	public boolean isCaptured() {
		final short[] offsetX = { 0, -1, 0, 1 };
		final short[] offsetY = { 1, 0, -1, 0 };

		if (x == boardPieceIsOn.getSize() - 1 || x == 0 || y == boardPieceIsOn.getSize() - 1 || y == 0) {
			return false;
		}

		for (short i = 0; i < offsetX.length; i++) {
			final Optional<Piece> pieceAtOffset = boardPieceIsOn.getPiece(x + offsetX[i], y + offsetY[i]);
			final boolean isNextToDefender = pieceAtOffset.map(p -> p instanceof Defender).orElse(false);
			final boolean isNextToFreeCellThatIsNotThrone = pieceAtOffset.isEmpty()
					&& boardPieceIsOn.cellType(x + offsetX[i], y + offsetY[i]) != Cell.THRONE;

			if (isNextToDefender || isNextToFreeCellThatIsNotThrone) {
				return false;
			}
		}
		return true;
	}

	@Override
	public PieceType getType() {
		return PieceType.KING;
	}
}
