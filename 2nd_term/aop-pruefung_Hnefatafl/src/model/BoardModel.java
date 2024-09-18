package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import java.util.TreeSet;

import model.enums.Cell;
import model.enums.PieceType;
import model.enums.Player;
import model.events.BoardModelObserver;
import model.events.PieceMovedEvent;
import model.events.SinglePointPieceEvent;
import model.pieces.King;
import model.pieces.Piece;

/**
 * Modellierung des Spielbretts.
 * 
 * Das Spielbrett (nachfolgend "Brett") ist stets quadratisch. Es wird
 * vereinbart, dass die Position eines Spielfelds (nachfolgend "Zelle"), auf dem
 * Brett, durch ein kartesisches Koordinatensystem ausgedrückt wird. Das Brett
 * liegt im ersten Quadranten dieses Koordinatensystems. Die untere linke Zelle
 * liegt am Punkt (0,0). Jede Zelle hat die Höhe und Breite 1. Hält eine Zelle
 * einen Spielstein (nachfolgend "Stein") ist sie belegt, sonst ist sie frei.
 * 
 * <pre>
 * Folgende Begriffe sind synonym:
 * > x-Koordinate == Stelle == Spalte == Vertikale
 * > y-Koordinate == Höhe == Zeile == Horizontale
 * </pre>
 */
public class BoardModel {

	/**
	 * Speichert die Größe des Bretts.
	 */
	private int size;

	/**
	 * Hauptdatenstruktur zur Speicherung von Steinen auf dem Feld.
	 * 
	 * Die Daten werden in einer 2D Struktur gespeichert. Dabei wird das Brett
	 * entsprechend der folgenden Abbildung modelliert. Über den KEY der HashMap,
	 * kann eine Zeile ausgewählt werden. Über den KEY der TreeMap, kann dann der
	 * Stein in einer Spalte dieser Zeile abgefragt werden.
	 * 
	 * Es werden die Positionen und die Typen der Steine gespeichert.
	 * 
	 * <pre style="font-family: monospace">
	 * | - |----------------
	 * | H | T r e e M a p |
	 * | a |----------------
	 * | s | T r e e M a p |
	 * | h |----------------
	 * | M | T r e e M a p |
	 * | a |----------------
	 * | p | T r e e M a p |
	 * | - |----------------
	 * </pre>
	 */
	private HashMap<Integer, TreeMap<Integer, Piece>> horizontal = new HashMap<>();

	/**
	 * Hilfsdatenstruktur zur Speicherung von Steinen auf dem Feld.
	 * 
	 * Die Daten werden in einer 2D Struktur gespeichert. Dabei wird das Brett
	 * entsprechend der folgenden Abbildung modelliert. Über den KEY der HashMap,
	 * kann eine Spalte ausgewählt werden. Über das TreeSet, kann abgefragt werden,
	 * ob sich ein Stein in einer Splate (dieser Zeile) befindet.
	 * 
	 * Es werden nur die Postionen der Steine gespeichert, nicht die Typen der
	 * Steine.
	 * 
	 * <pre style="font-family: monospace">
	 * -----------------
	 * | H a s h M a p |
	 * -----------------
	 * | T | T | T | T |
	 * | r | r | r | r |
	 * | e | e | e | e |
	 * | e | e | e | e |
	 * | S | S | S | S |
	 * | e | e | e | e |
	 * | t | t | t | t |
	 * | - | - | - | - |
	 * </pre>
	 */
	private HashMap<Integer, TreeSet<Integer>> vertical = new HashMap<>();

	/**
	 * Speicherort aller Beobachter des Bretts.
	 *
	 * Diese Datenstruktur dient der Implentation des Observer-Patterns für Events,
	 * welche vom Brett ausgehen können. Interessierte Beobachter werden in dieser
	 * Liste gespeichert und im Event-Fall benachrichig.
	 */
	private List<BoardModelObserver> observers = new ArrayList<>();

	/**
	 * Speicherort des Spielers, welcher aktuell am Zug ist.
	 */
	private Player activePlayer = Player.ATTACKER;

	/**
	 * Standardkonstruktor.
	 * 
	 * Legt die Größe des Bretts auf 11 fest.
	 */
	public BoardModel() {
		this.size = 11;
	}

	/**
	 * Konstruktor für variable Brettgröße.
	 * 
	 * <pre>
	 * Es gibt folgende Limits für die Brettgröße:
	 * - Die Größe liegt im Intervall [11, 51].
	 * - Die Größe ist ungerade.
	 * </pre>
	 * 
	 * @throws IllegalArgumentException wenn die Größe außerhalb des erlaubten
	 *                                  Bereichs liegt.
	 */
	public BoardModel(final int size) {

		if (size < 11) {
			throw new IllegalArgumentException("Board has to have a minimum size of 11.");
		}

		if (size > 51) {
			throw new IllegalArgumentException("Board cannot exceed size of 51.");
		}

		if (size % 2 == 0) {
			throw new IllegalArgumentException("Board size cannot be even.");
		}

		this.size = size;
	}

	/**
	 * Abfrage eines Steins auf spezifizierter Zelle.
	 * 
	 * Liefert den Stein auf der angegebenen Zelle zurück, oder null, falls die
	 * angegebene Zelle frei ist.
	 * 
	 * @param x Koordinate der Zelle in der Horizontalen
	 * @param y Koordinate der Zelle in der Vertikalen
	 * @return Piece; Piece, falls Zelle belegt; null, sonst
	 */
	public Optional<Piece> getPiece(final int x, final int y) {
		final TreeMap<Integer, Piece> row = horizontal.get(y);

		if (row != null) {
			return Optional.ofNullable(row.get(x));

		}

		return Optional.empty();
	}

	/**
	 * Abfrage, ob Zelle einen Stein enthält.
	 * 
	 * @param x Koordinate der Zelle in der Horizontalen
	 * @param y Koordinate der Zelle in der Vertikalen
	 * @return boolean; TRUE, falls Zelle belegt; FALSE, falls Zelle frei
	 */
	public boolean containsPiece(final int x, final int y) {
		final TreeMap<Integer, Piece> row = horizontal.get(y);

		if (row == null) {
			return false;
		}

		return row.containsKey(x);
	}

	/**
	 * Platziert Stein in angegebener Zelle.
	 * 
	 * <pre>
	 * Bedingungen, unter denen der Stein platziert werden kann:
	 * - Die Zelle ist frei.
	 * - Die Zelle liegt innerhalb der Grenzen des Bretts.
	 * - Der Stein darf den Zelltypen betreten (nur der gaKönig darf auf Ecken und Zentrum).
	 * </pre>
	 * 
	 * @param piece Typ des Spielsteins (ATTACKER, DEFENDER, KING)
	 * @param x     Koordinate der Zelle in der Horizontalen
	 * @param y     Koordinate der Zelle in der Vertikalen
	 * @return boolean; TRUE, falls Stein erfolgreich plaziert; FALSE, falls Stein
	 *         nicht plaziert
	 */
	public boolean placePiece(final PieceType pieceType, final int x, final int y) {
		if (containsPiece(x, y)) {
			return false;
		}

		if (!isWithinBounds(x, y)) {
			return false;
		}

		if (cellType(x, y) != Cell.DEFAULT && pieceType != PieceType.KING) {
			return false;
		}

		Piece piece = Piece.create(pieceType, this, x, y);
		placePieceOnBoard(piece);
		observers.forEach(o -> o.onPiecePlaced(new SinglePointPieceEvent(piece.getType(), x, y)));

		return true;
	}

	/**
	 * Setzt einen Stein auf dem Brett.
	 * 
	 * Der Stein wird in der angegebenen Position auf dem Brett platziert.
	 * 
	 * @param piece Stein, der platziert werden soll
	 */
	private void placePieceOnBoard(final Piece piece) {
		final int x = piece.getX();
		final int y = piece.getY();

		vertical.computeIfAbsent(x, k -> new TreeSet<>()).add(y);
		horizontal.computeIfAbsent(y, k -> new TreeMap<>()).put(x, piece);
	}

	/**
	 * Entfernt Stein aus angegebener Zelle.
	 * 
	 * Tut nichts, falls Zelle bereits leer.
	 * 
	 * @param x Koordinate der Zelle in der Horizontalen
	 * @param y Koordinate der Zelle in der Vertikalen
	 * @return Piece; Piece, entfernter Sein, falls vorhanden; null, sonst
	 */
	public Optional<Piece> removePiece(final int x, final int y) {

		final Optional<Piece> pieceToRemove = getPiece(x, y);

		pieceToRemove.ifPresent(piece -> {
			removePieceFromBoard(x, y);
			observers.forEach(o -> o.onPieceRemoved(new SinglePointPieceEvent(piece.getType(), x, y)));
		});

		return pieceToRemove;
	}

	/**
	 * Entfernt einen Stein von dem Brett.
	 * 
	 * Der Stein wird von der angegebenen Position auf dem Brett entfernt.
	 * 
	 * @param x x-Koordinate des Steins
	 * @param y y-Koordinate des Steins
	 */
	private void removePieceFromBoard(final int x, final int y) {

		vertical.computeIfPresent(x, (key, value) -> {
			value.remove(y);
			return value.isEmpty() ? null : value;
		});

		horizontal.computeIfPresent(y, (key, value) -> {
			value.remove(x);
			return value.isEmpty() ? null : value;
		});
	}

	/**
	 * Überprüft, ob ein Zug legal ist.
	 * 
	 * Ein Zug ist legal, wenn die Zielzelle innerhalb der Grenzen des Bretts liegt,
	 * die Zielzelle frei ist und der Zug entweder horizontal oder vertikal ist.
	 * 
	 * @param fromX Start x-Koordinate des Steins
	 * @param fromY Start y-Koordinate des Steins
	 * @param toX   Ziel x-Koordinate des Steins
	 * @param toY   Ziel y-Koordinate des Steins
	 * @return boolean; true, wenn der Zug legal ist; false, sonst
	 */
	public boolean movePiece(final int fromX, final int fromY, final int toX, final int toY) {

		if (!isLegalMove(fromX, fromY, toX, toY)) {
			return false;
		}

		Optional<Piece> pieceToMove = getPiece(fromX, fromY);

		pieceToMove.ifPresent(piece -> {
			movePieceOnBoard(piece, toX, toY);
			observers.forEach(o -> o.onPieceMoved(new PieceMovedEvent(piece.getType(), fromX, fromY, toX, toY)));
			toggleActivePlayer();
			checkForCaptures(piece);
			checkForKingsVictory(piece);
		});

		return pieceToMove.isPresent();
	}

	/**
	 * Bewegt einen Stein auf dem Brett.
	 * 
	 * Der Stein wird von der Ausgangsposition zu der Zielposition bewegt.
	 * 
	 * @param piece Stein, der bewegt werden soll
	 * @param toX   x-Koordinate der Zielposition
	 * @param toY   y-Koordinate der Zielposition
	 */
	private void movePieceOnBoard(Piece piece, final int toX, final int toY) {
		removePieceFromBoard(piece.getX(), piece.getY());
		piece.setXY(toX, toY);
		placePieceOnBoard(piece);
	}

	/**
	 * Überprüft, ob ein Stein gefangen hat.
	 * 
	 * Nimmt einen Stein entgegen und schaut, ob dieser Stein andere Steine gefangen
	 * nimmt. Sollte dies der Fall sein, werden die gefangenen Steine entfernt und
	 * das entsprechnede Event ausgelöst.
	 * 
	 * @param piece Stein, der überprüft werden soll
	 */
	private void checkForCaptures(final Piece piece) {
		piece.forEachAdjacentPiece(p -> {
			if (p.isCaptured()) {
				observers.forEach(o -> o.onPieceCaptured(new SinglePointPieceEvent(p.getType(), p.getX(), p.getY())));
				removePieceFromBoard(p.getX(), p.getY());

				if (p instanceof King) {
					observers.forEach(o -> o.onVictory(Player.ATTACKER));
				}

			}
		});
	}

	/**
	 * Überprüft, ob der König gewonnen hat.
	 * 
	 * Der König gewinnt, wenn er in einer Ecke des Bretts steht.
	 * 
	 * @param piece Stein, der überprüft werden soll
	 */
	private void checkForKingsVictory(final Piece piece) {
		if ((piece instanceof King) && cellType(piece.getX(), piece.getY()) == Cell.CORNER) {
			observers.forEach(o -> o.onVictory(Player.DEFENDER));
		}
	}

	/**
	 * Abfrage des Zelltypen an den spezifizierten Koordinaten.
	 * 
	 * <pre>
	 * Mögliche Zelltypen:
	 * - Cell.CORNER: Wenn die Zelle eine Ecke des Bretts ist. 
	 * - Cell.THRONE: Wenn die Zelle die Mitte des Bretts ist.
	 * - Cell.DEFAULT: Für alle anderen Zellen innerhalb der Grenzen.
	 * - null: Wenn die Zelle außerhalb des Bretts liegt.
	 * </pre>
	 * 
	 * @param x Koordinate der Zelle in der Horizontalen
	 * @param y Koordinate der Zelle in der Vertikalen
	 * @return Cell; Cell, falls Zelle existiert; null, sonst
	 */
	public Cell cellType(final int x, final int y) {

		if ((y == 0 || y == size - 1) && (x == 0 || x == size - 1)) {
			return Cell.CORNER;
		}

		if (y == size / 2 && x == size / 2) {
			return Cell.THRONE;
		}

		if (!isWithinBounds(x, y)) {
			return null;
		}

		return Cell.DEFAULT;
	}

	/**
	 * Überprüft, ob ein Zug legal ist.
	 * 
	 * Ein Zug ist legal, wenn die Zielzelle innerhalb der Grenzen des Bretts liegt,
	 * die Zielzelle frei ist und der Zug entweder horizontal oder vertikal ist.
	 * 
	 * @param fromX Start x-Koordinate des Steins
	 * @param fromY Start y-Koordinate des Steins
	 * @param toX   Ziel x-Koordinate des Steins
	 * @param toY   Ziel y-Koordinate des Steins
	 * @return boolean; true, wenn der Zug legal ist; false, sonst
	 */
	public boolean isLegalMove(final int fromX, final int fromY, final int toX, final int toY) {

		if (!isWithinBounds(toX, toY)) {
			return false;
		}

		if (containsPiece(toX, toY)) {
			return false;
		}

		Optional<Piece> pieceOptional = getPiece(fromX, fromY);
		if (!pieceOptional.isPresent()) {
			return false;
		}

		Piece piece = pieceOptional.get();
		if (!piece.isAllowedToBeMovedBy(activePlayer)) {
			return false;
		}

		final boolean movedPieceIsKing = getPiece(fromX, fromY).map(p -> p instanceof King).orElse(false);
		final boolean targetCellIsDefault = cellType(toX, toY) == Cell.DEFAULT;

		if (!targetCellIsDefault && !movedPieceIsKing) {
			return false;
		}

		if (fromX == toX) {
			return isVerticalMoveClear(fromY, toY, toX);
		}

		if (fromY == toY) {
			return isHorizontalMoveClear(fromX, toX, toY);
		}

		return false;
	}

	/**
	 * Fügt einen Beobachter hinzu.
	 * 
	 * Ein Beobachter wird hinzugefügt. Ab diesem Moment wird er über Geschehnisse
	 * auf dem Brett informiert.
	 * 
	 * @param observer neuer Beobachter
	 * @return void
	 */
	public void addObserver(BoardModelObserver observer) {
		observers.add(observer);
	}

	/**
	 * Entfernt einen Beobachter.
	 * 
	 * Ein Beobachter wird entfernt. Ab diesem Moment wird er nicht mehr über
	 * Geschehnisse auf dem Brett informiert.
	 * 
	 * @param observer zu entfernender Beobachter
	 * @return void
	 */
	public void removeObserver(BoardModelObserver observer) {
		observers.remove(observer);
	}

	/**
	 * Überprüft, ob eine Zelle auf dem Brett liegt.
	 * 
	 * @param x Koordinate der Zelle in der Horizontalen
	 * @param y Koordinate der Zelle in der Vertikalen
	 * @return boolean; TRUE, falls Zelle auf Brett liegt; FALSE, falls Zelle nicht
	 *         existiert
	 */
	private boolean isWithinBounds(final int x, final int y) {
		if (x >= size || x < 0) {
			return false;
		}
		if (y >= size || y < 0) {
			return false;
		}
		return true;
	}

	/**
	 * Überprüft, ob horizontaler Zug eines Steins durch anderen Stein blockiert
	 * ist.
	 * 
	 * Die Methode ermittelt den horizontalen in-order Vorgänger und Nachfolger des
	 * zu verschiebenden Steins. Aus deren x-Koordinaten und der x-Koordinate des zu
	 * verschiebenden Steins wird ermittelt, ob der Zug blockiert wird. Es werden
	 * ausschließlich die Positionen der Steine zueinander betrachtet. ACHTUNG: Auf
	 * ungültige Eingaben (z. B. solche, die außerhalb des Bretts liegen) wird nicht
	 * geprüft. Aus Sicht dieser Methode ist das Brett unendlich groß, auch ins
	 * Negatative.
	 * 
	 * @param fromX Start x-Koordinate (aktuelle Position des zu verschiebenden
	 *              Steins)
	 * @param toX   Ziel x-Koordinate (angestrebte Position des zu verschiebenden
	 *              Steins)
	 * @param y     Zeile (Koordinate) auf welcher Zug stattfinden soll
	 * @return boolean; TRUE, falls der Zug möglich ist; FALSE, falls der Zug
	 *         unmöglich ist
	 */
	private boolean isHorizontalMoveClear(final int fromX, final int toX, final int y) {
		final TreeMap<Integer, Piece> targetRow = horizontal.get(y);

		if (targetRow == null) {
			return true;
		}

		final Integer successorIndex = targetRow.lowerKey(fromX);
		final Integer predecessorIndex = targetRow.higherKey(fromX);

		boolean isLegal = true;

		if (successorIndex != null) {
			isLegal &= successorIndex < toX;
		}

		if (predecessorIndex != null) {
			isLegal &= predecessorIndex > toX;
		}

		return isLegal;
	}

	/**
	 * Überprüft, ob vertikaler Zug eines Steins durch anderen Stein blockiert ist.
	 * 
	 * Die Methode ermittelt den vertikalen in-order-Vorgänger und -Nachfolger des
	 * zu verschiebenden Steins. Aus deren y-Koordinaten und der y-Koordinate des zu
	 * verschiebenden Steins wird ermittelt, ob der Zug blockiert wird. Es werden
	 * ausschließlich die Positionen der Steine zueinander betrachtet. ACHTUNG: Auf
	 * ungültige Eingaben (z. B. solche, die außerhalb des Bretts liegen) wird nicht
	 * geprüft. Aus Sicht dieser Methode ist das Brett unendlich groß, auch ins
	 * Negatative.
	 * 
	 * @param fromY Start y-Koordinate (aktuelle Position des zu verschiebenden
	 *              Steins)
	 * @param toY   Ziel y-Koordinate (angestrebte Position des zu verschiebenden
	 *              Steins)
	 * @param x     Zeile (Koordinate) auf welcher Zug stattfinden soll
	 * @return boolean; TRUE, falls der Zug möglich ist; FALSE, falls der Zug
	 *         unmöglich ist
	 */
	private boolean isVerticalMoveClear(final int fromY, final int toY, final int x) {
		final TreeSet<Integer> targetColumn = vertical.get(x);

		if (targetColumn == null) {
			return true;
		}

		final Integer successorIndex = targetColumn.lower(fromY);
		final Integer predecessorIndex = targetColumn.higher(fromY);

		boolean isLegal = true;

		if (successorIndex != null) {
			isLegal &= successorIndex < toY;
		}

		if (predecessorIndex != null) {
			isLegal &= predecessorIndex > toY;
		}

		return isLegal;
	}

	/**
	 * Gibt die Größe des Bretts zurück.
	 * 
	 * @return int; Größe des Bretts
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Erzeugt Startaufstellung der Steine.
	 */
	public void startingLineUp() {
		for (int i = (getSize() / 2) - 2; i <= (getSize() / 2) + 2; i++) {
			placePiece(PieceType.ATTACKER, 0, i);
			placePiece(PieceType.ATTACKER, getSize() - 1, i);
			placePiece(PieceType.ATTACKER, i, 0);
			placePiece(PieceType.ATTACKER, i, getSize() - 1);
		}
		placePiece(PieceType.ATTACKER, getSize() / 2, getSize() - 2);
		placePiece(PieceType.ATTACKER, getSize() - 2, getSize() / 2);
		placePiece(PieceType.ATTACKER, getSize() / 2, 1);
		placePiece(PieceType.ATTACKER, 1, getSize() / 2);

		for (int i = (getSize() / 2) - 1; i <= (getSize() / 2) + 1; i++) {
			for (int j = (getSize() / 2) - 1; j <= (getSize() / 2) + 1; j++) {
				placePiece(PieceType.DEFENDER, i, j);
			}
		}
		placePiece(PieceType.DEFENDER, (getSize() / 2) - 2, getSize() / 2);
		placePiece(PieceType.DEFENDER, (getSize() / 2) + 2, getSize() / 2);
		placePiece(PieceType.DEFENDER, getSize() / 2, (getSize() / 2) - 2);
		placePiece(PieceType.DEFENDER, getSize() / 2, (getSize() / 2) + 2);

		placePiece(PieceType.KING, (getSize() / 2), (getSize() / 2));
	}

	/**
	 * Schaltet den aktiven Spieler um.
	 * 
	 * Dieser Methode macht immer den Spieler zum aktiven Spieler, der es zum
	 * Zeitpunkt des Aufrufes nicht ist. Sie tauscht die aktiven Spieler also "hin
	 * und her".
	 * 
	 */
	public void toggleActivePlayer() {
		if (activePlayer == Player.DEFENDER) {
			activePlayer = Player.ATTACKER;
		} else {
			activePlayer = Player.DEFENDER;
		}
		observers.forEach(o -> o.onNewActivePlayer(activePlayer));
	}

	/**
	 * Überprüft, ob Zelle (an spezifizierten Koordianten) neben anderen freien
	 * Zellen liegt.
	 * 
	 * @param x Koordinate der Zelle.
	 * @param y Koordinate der Zelle.
	 * @return boolean; TRUE, falls frei Zelle gefunden; false, sonst
	 */
	public boolean isNextToFreeCell(final int x, final int y) {
		final short[] offsetX = { 0, -1, 0, 1 };
		final short[] offsetY = { 1, 0, -1, 0 };

		for (short i = 0; i < offsetX.length; i++) {
			if (!containsPiece(x + offsetX[i], y + offsetY[i]) && isWithinBounds(x + offsetX[i], y + offsetY[i])) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Getter für aktiven Spieler.
	 * 
	 * @return Player; entspricht aktivem Spieler
	 */
	public Player getActivePlayer() {
		return activePlayer;
	}

	/**
	 * Entfernt alle Spielsteine vom Brett.
	 */
	public void clearBoard() {
		vertical.clear();
		horizontal.clear();
		observers.forEach(o -> o.onBoardCleared());
	}

	/**
	 * Erzwingt Auslösen des Victory-Events für angegebenen Spieler.
	 */
	public void forceVictory(Player winner) {
		observers.forEach(o -> o.onVictory(winner));
	}

	/**
	 * Getter-Methode für horizontale Datenstruktur.
	 * 
	 * Wird nur für den Debugger benötigt.
	 * 
	 * @return HashMap<Integer, TreeMap<Integer, Piece>>; horizontale Datenstruktur
	 */
	HashMap<Integer, TreeMap<Integer, Piece>> getHorizontal() {
		return horizontal;
	}

	/**
	 * Getter-Methode für vertikale Datenstruktur.
	 * 
	 * Wird nur für den Debugger benötigt.
	 * 
	 * @return HashMap<Integer, TreeSet<Integer>>; vertikale Datenstruktur
	 */
	HashMap<Integer, TreeSet<Integer>> getVertical() {
		return vertical;
	}
}
