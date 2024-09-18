package view;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;

import model.enums.Player;
import model.events.BoardModelObserver;
import model.events.PieceMovedEvent;
import model.events.SinglePointPieceEvent;
import view.board.BoardPanel;
import view.events.ViewObserver;
import view.menu.MenuPanel;

/**
 * MainFrame ist das Hauptfenster der Anwendung, das das Spielbrett und das Menü
 * anzeigt. Es implementiert das BoardModelObserver-Interface, um auf Ereignisse
 * im Spielbrett zu reagieren.
 */
public class MainFrame extends JFrame implements BoardModelObserver {

	private static final long serialVersionUID = 1L;

	final private BoardPanel boardPanel;
	final private MenuPanel menuPanel;

	private List<ViewObserver> observers = new ArrayList<>();

	final static private int WIDTH = GlobalGuiConfig.applyScale(900);
	final static private int HEIGHT = GlobalGuiConfig.applyScale(600);

	/**
	 * Konstruktor für MainFrame.
	 *
	 * @param boardSize Die Größe des Spielbretts.
	 */
	public MainFrame(final int boardSize) {
		this.boardPanel = new BoardPanel(observers);
		this.menuPanel = new MenuPanel(observers);
		initialize();
		setVisible(true);
	}

	/**
	 * Initialisiert das Hauptfenster, einschließlich Layout, Größe und Komponenten.
	 */
	private void initialize() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(WIDTH, HEIGHT);
		setLayout(new BorderLayout());
		setResizable(false);
		add(boardPanel, BorderLayout.CENTER);
		add(menuPanel, BorderLayout.EAST);
		setTitle("Hnefatafl");
		setLocationRelativeTo(null);
		menuPanel.attackerLabel.showTurnIndicator();
	}

	@Override
	public void onPieceMoved(PieceMovedEvent e) {
		boardPanel.boardView.removePiece(e.getFromY(), e.getFromX());
		boardPanel.boardView.placePiece(e.getPieceType(), e.getToY(), e.getToX());
		boardPanel.boardView.getCell(e.getFromY(), e.getFromX()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		boardPanel.boardView.getCell(e.getToY(), e.getToX()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		revalidate();
		repaint();
	}

	@Override
	public void onPiecePlaced(SinglePointPieceEvent e) {
		boardPanel.boardView.placePiece(e.getPieceType(), e.getY(), e.getX());
		boardPanel.boardView.getCell(e.getY(), e.getX()).setCursor(new Cursor(Cursor.HAND_CURSOR));
		revalidate();
		repaint();
	}

	@Override
	public void onPieceRemoved(SinglePointPieceEvent e) {
		boardPanel.boardView.getCell(e.getY(), e.getX()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		boardPanel.boardView.removePiece(e.getY(), e.getX());
		revalidate();
		repaint();
	}

	@Override
	public void onPieceCaptured(SinglePointPieceEvent e) {
		boardPanel.boardView.getCell(e.getY(), e.getX()).setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		boardPanel.boardView.removePiece(e.getY(), e.getX());
		revalidate();
		repaint();
	}

	@Override
	public void onVictory(Player winner) {
		boardPanel.showVictoryScreen(winner);
		revalidate();
		repaint();
	}

	public void addObserver(ViewObserver vo) {
		observers.add(vo);
	}

	public void removeObserver(ViewObserver vo) {
		observers.remove(vo);
	}

	@Override
	public void onBoardCleared() {
		boardPanel.clearBoard();
		boardPanel.hideVictoryScreen();
		revalidate();
		repaint();
	}

	@Override
	public void onNewActivePlayer(Player activePlayer) {
		if (activePlayer == Player.ATTACKER) {
			menuPanel.attackerLabel.showTurnIndicator();
			menuPanel.defenderLabel.hideTurnIndicator();
		} else {
			menuPanel.attackerLabel.hideTurnIndicator();
			menuPanel.defenderLabel.showTurnIndicator();
		}

	}
}
