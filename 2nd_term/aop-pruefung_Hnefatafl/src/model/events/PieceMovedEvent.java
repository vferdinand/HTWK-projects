package model.events;

import model.enums.PieceType;

/**
 * Ereignis, das ausgelöst wird, wenn ein Stein bewegt wird.
 * 
 * Dieses Ereignis wird ausgelöst, wenn ein Stein von einer Position zu einer
 * anderen bewegt wird.
 * 
 * @param eventPieceType Typ des Steins, der bewegt wird
 * @param fromX          x-Koordinate der Ausgangsposition des Steins
 * @param fromY          y-Koordinate der Ausgangsposition des Steins
 * @param toX            x-Koordinate der Zielposition des Steins
 * @param toY            y-Koordinate der Zielposition des Steins
 */
public class PieceMovedEvent extends PieceEvent {

	/**
	 * x-Koordinate der Ausgangsposition des Steins.
	 */
	private final int fromX;

	/**
	 * y-Koordinate der Ausgangsposition des Steins.
	 */
	private final int fromY;

	/**
	 * x-Koordinate der Zielposition des Steins.
	 */
	private final int toX;

	/**
	 * y-Koordinate der Zielposition des Steins.
	 */
	private final int toY;

	/**
	 * Erstellt ein neues Ereignis, das ausgelöst wird, wenn ein Stein bewegt wird.
	 * 
	 * @param eventPieceType Typ des Steins, der bewegt wird
	 * @param fromX          x-Koordinate der Ausgangsposition des Steins
	 * @param fromY          y-Koordinate der Ausgangsposition des Steins
	 * @param toX            x-Koordinate der Zielposition des Steins
	 * @param toY            y-Koordinate der Zielposition des Steins
	 */
	public PieceMovedEvent(PieceType eventPieceType, int fromX, int fromY, int toX, int toY) {
		super(eventPieceType);
		this.fromX = fromX;
		this.fromY = fromY;
		this.toX = toX;
		this.toY = toY;
	}

	/**
	 * Gibt die x-Koordinate der Ausgangsposition des Steins zurück.
	 * 
	 * @return int; x-Koordinate der Ausgangsposition des Steins
	 */
	public int getFromX() {
		return fromX;
	}

	/**
	 * Gibt die y-Koordinate der Ausgangsposition des Steins zurück.
	 * 
	 * @return int; y-Koordinate der Ausgangsposition des Steins
	 */
	public int getFromY() {
		return fromY;
	}

	/**
	 * Gibt die x-Koordinate der Zielposition des Steins zurück.
	 * 
	 * @return int; x-Koordinate der Zielposition des Steins
	 */
	public int getToX() {
		return toX;
	}

	/**
	 * Gibt die y-Koordinate der Zielposition des Steins zurück.
	 * 
	 * @return int; y-Koordinate der Zielposition des Steins
	 */
	public int getToY() {
		return toY;
	}
}
