package model.events;

import model.enums.Player;

/**
 * Interface für Beobachter des Bretts (Modells).
 * 
 * Wer dieses Interface implentiert, kann Beobachter des Bretts (Modell) werden.
 * Beobachter des Bretts werden bei Ereignissen auf dem Brett informiert.
 */
public interface BoardModelObserver {

	/**
	 * Event: Spielstein wurde erfolgreich bewegt.
	 * 
	 * Diese Methode wird ausgeführt, wenn ein Spielstein erfolgreich bewegt wurde.
	 * 
	 * @param e Ereignis-Objekt, das die Bewegung des Spielsteins beschreibt
	 */
	public void onPieceMoved(final PieceMovedEvent e);

	/**
	 * Event: Spielstein wurde erfolgreich plaziert.
	 * 
	 * Diese Methode wird ausgeführt, wenn ein Spielstein erfolgreich auf dem Brett
	 * plaziert wurde.
	 * 
	 * @param e Ereignis-Objekt, das die Platzierung des Spielsteins beschreibt
	 */
	public void onPiecePlaced(final SinglePointPieceEvent e);

	/**
	 * Event: Spielstein wurde entfernt.
	 * 
	 * Diese Methode wird ausgeführt, wenn ein Spielstein von dem Brett entfernt
	 * wurde.
	 * 
	 * @param e Ereignis-Objekt, das die Entfernung des Spielsteins beschreibt
	 */
	public void onPieceRemoved(final SinglePointPieceEvent e);

	/**
	 * Event: Spielstein wurde gefangen.
	 * 
	 * Diese Methode wird ausgeführt, wenn ein Spielstein von einem anderen
	 * Spielstein gefangen wurde.
	 * 
	 * @param e Ereignis-Objekt, das die Gefangennahme des Spielsteins beschreibt
	 */
	public void onPieceCaptured(final SinglePointPieceEvent e);

	/**
	 * Event: Spiel ist beendet.
	 * 
	 * Diese Methode wird ausgeführt, wenn das Spiel beendet ist und ein Spieler
	 * gewonnen hat.
	 * 
	 * @param winner Spieler, der das Spiel gewonnen hat
	 */
	public void onVictory(final Player winner);

	/**
	 * Event: Der andere Spieler ist am Zug.
	 * 
	 * Diese Methode wird ausgeführt, wenn sich der aktuelle Spieler am Zug ändert.
	 * 
	 * @param activePlayer Spieler, der nun am Zug ist.
	 */
	public void onNewActivePlayer(final Player activePlayer);

	/**
	 * Event: Das Spielfeld wurde von Steinen bereinigt.
	 * 
	 * Diese Methode wird ausgeführt, wenn das Spielfeld von allen Spielsteinen auf
	 * einmal bereinigt wurde.
	 */
	public void onBoardCleared();

}
