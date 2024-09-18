package controller;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import model.BoardModel;
import model.enums.PieceType;
import model.enums.Player;
import view.MainFrame;
import view.StartFrame;
import view.events.ButtonClickedEvent;
import view.events.CellClickedEvent;
import view.events.ViewEvent;
import view.events.ViewObserver;

/**
 * Die Klasse Controller verwaltet die Interaktion zwischen dem Modell und der
 * Benutzeroberfläche. Sie implementiert das ViewObserver-Interface, um auf
 * Ereignisse von der Ansicht zu reagieren und das Modell entsprechend zu
 * aktualisieren.
 */
public class Controller implements ViewObserver {

	private BoardModel model;
	private MainFrame view;
	private StartFrame startView;
	private ViewEvent lastEvent;

	/**
	 * Erstellt eine neue Instanz von Controller und initialisiert das Modell sowie
	 * das Start-PopUp.
	 */
	public Controller() {
		this.model = new BoardModel(13);
		this.startView = new StartFrame();
		this.lastEvent = null;
		this.startView.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				showMainFrame();
			}
		});
	}

	/**
	 * Gibt das aktuelle BoardModel zurück.
	 *
	 * @return das BoardModel
	 */
	public BoardModel getModel() {
		return model;
	}

	/**
	 * Zeigt das Hauptfenster an und registriert den Controller als Observer.
	 */
	private void showMainFrame() {
		view = new MainFrame(model.getSize());
		view.addObserver(this);
		model.addObserver(view);
		model.startingLineUp();
	}

	@Override
	public void onCellClicked(final CellClickedEvent e) {
		final int x = e.getX();
		final int y = e.getY();
		final boolean eventQueueIsEmpty = lastEvent == null;
		final boolean ContainsPieceThatCanBeMovedByPlayerOnTurn = model.getPiece(x, y)
				.map(piece -> piece.isAllowedToBeMovedBy(model.getActivePlayer())).orElse(false);
		final boolean pieceCanMoved = model.isNextToFreeCell(x, y);

		if (eventQueueIsEmpty && ContainsPieceThatCanBeMovedByPlayerOnTurn && pieceCanMoved) {
			lastEvent = e;
		} else if (lastEvent instanceof ButtonClickedEvent) {
			handleButtons(e);
		} else if (lastEvent instanceof CellClickedEvent) {
			handleCellClick(e);
		}
	}

	/**
	 * Behandelt das Zellklickevent, dem ein Button-Klick vorausging.
	 * 
	 * Wurde eine Aktion über einen Button ausgewählt und wird dann eine Zelle
	 * angeklickt, wird die durch den Button beschriebene Aktion auf der Zelle
	 * ausgeführt.
	 *
	 * @param e das CellClickedEvent, das die Informationen über den Klick enthält
	 */
	private void handleButtons(CellClickedEvent e) {
		if (lastEvent == ButtonClickedEvent.PLACE_ATTACKER) {
			model.placePiece(PieceType.ATTACKER, e.getX(), e.getY());
			lastEvent = null;
		} else if (lastEvent == ButtonClickedEvent.PLACE_DEFENDER) {
			model.placePiece(PieceType.DEFENDER, e.getX(), e.getY());
			lastEvent = null;
		} else if (lastEvent == ButtonClickedEvent.REMOVE_PIECE) {
			model.removePiece(e.getX(), e.getY());
			lastEvent = null;
		}
	}

	/**
	 * Behandelt das Klicken auf eine Zelle, um ein Stück zu bewegen.
	 *
	 * @param e das CellClickedEvent, das die Informationen über den Klick enthält
	 */
	private void handleCellClick(CellClickedEvent e) {

		final int x = e.getX();
		final int y = e.getY();
		final CellClickedEvent previousClick = (CellClickedEvent) lastEvent;
		final boolean pieceWasMovedSuccessfully = model.movePiece(previousClick.getX(), previousClick.getY(), x, y);

		if (pieceWasMovedSuccessfully) {
			lastEvent = null;
			return;
		}

		final boolean ContainsPieceThatCanBeMovedByPlayerOnTurn = model.getPiece(x, y)
				.map(piece -> piece.isAllowedToBeMovedBy(model.getActivePlayer())).orElse(false);
		final boolean pieceCanMoved = model.isNextToFreeCell(x, y);

		if (ContainsPieceThatCanBeMovedByPlayerOnTurn && pieceCanMoved) {
			lastEvent = e;
		}
	}

	@Override
	public void onGameRestartClicked() {
		model.clearBoard();
		model.startingLineUp();
		lastEvent = null;
	}

	@Override
	public void onApplicationCloseClicked() {
		view.dispose();
	}

	@Override
	public void onPlaceAttackerClicked() {
		lastEvent = ButtonClickedEvent.PLACE_ATTACKER;
	}

	@Override
	public void onPlaceDefenderClicked() {
		lastEvent = ButtonClickedEvent.PLACE_DEFENDER;
	}

	@Override
	public void onRemovePieceClicked() {
		lastEvent = ButtonClickedEvent.REMOVE_PIECE;
	}

	@Override
	public void onChangeActivePlayerClicked() {
		model.toggleActivePlayer();
		lastEvent = null;
	}

	@Override
	public void onAttackerVictoryClicked() {
		model.forceVictory(Player.ATTACKER);
	}

	@Override
	public void onDefenderVictoryClicked() {
		model.forceVictory(Player.DEFENDER);
	}
}
