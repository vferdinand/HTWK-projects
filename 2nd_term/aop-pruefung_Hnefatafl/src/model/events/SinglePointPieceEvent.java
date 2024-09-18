package model.events;

import model.enums.PieceType;

/**
 * Abstrakte Basis-Klasse für Ereignisse, die mit einem einzelnen Punkt auf dem
 * Brett zusammenhängen.
 * 
 * Diese Klasse stellt eine gemeinsame Basis für alle Ereignisse dar, die mit
 * einem einzelnen Punkt auf dem Brett zusammenhängen. Sie enthält den Typ des
 * Steins, der an dem Ereignis beteiligt ist, sowie die x- und y-Koordinaten des
 * Punkts.
 * 
 * @param eventPieceType Typ des Steins, der an dem Ereignis beteiligt ist
 * @param x              x-Koordinate des Events
 * @param y              y-Koordinate des Events
 */
public class SinglePointPieceEvent extends PieceEvent {
	/**
	 * x-Koordinate des Events.
	 */
	private final int x;

	/**
	 * y-Koordinate des Events.
	 */
	private final int y;

	/**
	 * Erstellt ein neues Ereignis, das mit einem einzelnen Punkt auf dem Brett
	 * zusammenhängt.
	 * 
	 * @param eventPieceType Typ des Steins, der an dem Ereignis beteiligt ist
	 * @param x              x-Koordinate des Punkts
	 * @param y              y-Koordinate des Punkts
	 */
	public SinglePointPieceEvent(PieceType eventPieceType, int x, int y) {
		super(eventPieceType);
		this.x = x;
		this.y = y;
	}

	/**
	 * Gibt die x-Koordinate des Events zurück.
	 * 
	 * @return int; x-Koordinate des Events
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gibt die y-Koordinate des Events zurück.
	 * 
	 * @return int; y-Koordinate des Events
	 */
	public int getY() {
		return y;
	}
}
