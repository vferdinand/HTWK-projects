package view;

import view.events.CellClickedEvent;
import view.events.ViewObserver;

public class ViewDebugger implements ViewObserver {

	@Override
	public void onCellClicked(CellClickedEvent e) {
		System.out.println("CELL_CLICKED: (" + e.getX() + ", " + e.getY() + ")");

	}

	@Override
	public void onGameRestartClicked() {
		System.out.println("GAME_RESTART_BUTTON");

	}

	@Override
	public void onApplicationCloseClicked() {
		System.out.println("GAME_CLOSE_BUTTON");

	}

	@Override
	public void onPlaceAttackerClicked() {
		System.out.println("PALCE_ATTACKER_BUTTON");

	}

	@Override
	public void onPlaceDefenderClicked() {
		System.out.println("PALCE_DEFENDER_BUTTON");

	}

	@Override
	public void onRemovePieceClicked() {
		System.out.println("REMOVE_PIECE_BUTTON");

	}

	@Override
	public void onChangeActivePlayerClicked() {
		System.out.println("CHANGE_ACTIVE_PLAYER_BUTTON");

	}

	@Override
	public void onAttackerVictoryClicked() {
		System.out.println("ATTACKER_VICTORY_BUTTON");

	}

	@Override
	public void onDefenderVictoryClicked() {
		System.out.println("DEFENDER_VICTORY_BUTTON");

	}

}
