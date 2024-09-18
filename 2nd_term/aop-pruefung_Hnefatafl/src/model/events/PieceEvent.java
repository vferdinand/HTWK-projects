package model.events;

import model.enums.PieceType;

/**
 * Basis-Klasse für Ereignisse, die mit Steinen zusammenhängen.
 * 
 * Diese Klasse stellt eine gemeinsame Basis für alle Ereignisse dar, die mit
 * Steinen zusammenhängen. Sie enthält den Typ des Steins, der an dem Ereignis
 * beteiligt ist.
 * 
 * @param eventPieceType Typ des Steins, der an dem Ereignis beteiligt ist
 */
public abstract class PieceEvent {

	/**
	 * Typ des Steins, der an dem Ereignis beteiligt ist.
	 */
	protected PieceType eventPieceType;

	/**
	 * Erstellt ein neues Ereignis, das mit einem Stein zusammenhängt.
	 * 
	 * @param eventPieceType Typ des Steins, der an dem Ereignis beteiligt ist
	 */
	PieceEvent(final PieceType eventPieceType) {
		this.eventPieceType = eventPieceType;
	}

	/**
	 * Gibt den Typ des Steins zurück, der an dem Ereignis beteiligt ist.
	 * 
	 * @return PieceType; Typ des Steins, der an dem Ereignis beteiligt ist
	 */
	public PieceType getPieceType() {
		return eventPieceType;
	}
}
