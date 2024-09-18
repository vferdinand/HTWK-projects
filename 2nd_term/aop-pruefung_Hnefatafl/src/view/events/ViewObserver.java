package view.events;

/**
 * Interface für die Beobachter, die auf Ereignisse in der GUI reagieren.
 */
public interface ViewObserver {

	// Board
	public void onCellClicked(final CellClickedEvent e);

	// VictoryScreen
	public void onGameRestartClicked();

	public void onApplicationCloseClicked();

	// ButtonArea
	public void onPlaceAttackerClicked();

	public void onPlaceDefenderClicked();

	public void onRemovePieceClicked();

	public void onChangeActivePlayerClicked();

	public void onAttackerVictoryClicked();

	public void onDefenderVictoryClicked();
}
