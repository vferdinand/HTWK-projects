package view;

import java.awt.Color;

/**
 * GlobalGuiConfig ist eine Utility-Klasse, die globale Konfigurationen für die
 * GUI enthält, einschließlich der Skalierungsfaktoren und der Farben der
 * Spielsteine. Diese Klasse kann nicht instanziiert werden.
 */
public final class GlobalGuiConfig {

	// Globate GUI-Skallierung
	private static double GLOBAL_SCALING_FACTOR = 1;

	// Spielsteinfarben
	private static final Color ATTACKER_PIECE_COLOR = new Color(176, 40, 40);;
	private static final Color DEFENDER_PIECE_COLOR = new Color(53, 115, 175);
	private static final Color KING_PIECE_COLOR = new Color(20, 26, 88);

	// Privater Konstruktor, um die Instanziierung zu verhindern
	private GlobalGuiConfig() {
	}

	/**
	 * Gibt den aktuellen globalen Skalierungsfaktor zurück.
	 *
	 * @return Der globale Skalierungsfaktor.
	 */
	public static double getGlobalScalingFactor() {
		return GLOBAL_SCALING_FACTOR;
	}

	/**
	 * Wendet den globalen Skalierungsfaktor auf einen ganzzahligen Wert an.
	 *
	 * @param value Der Wert, der skaliert werden soll.
	 * @return Der skalierte Wert.
	 */
	public static int applyScale(final int value) {
		return (int) (value * GLOBAL_SCALING_FACTOR);
	}

	/**
	 * Wendet den globalen Skalierungsfaktor auf einen double-Wert an.
	 *
	 * @param value Der Wert, der skaliert werden soll.
	 * @return Der skalierte Wert.
	 */
	public static int applyScale(final double value) {
		return (int) (value * GLOBAL_SCALING_FACTOR);
	}

	/**
	 * Gibt die Farbe des Angreifer-Spielsteins zurück.
	 *
	 * @return Die Farbe des Angreifer-Spielsteins.
	 */
	public static Color getAttackerPieceColor() {
		return ATTACKER_PIECE_COLOR;
	}

	/**
	 * Gibt die Farbe des Verteidiger-Spielsteins zurück.
	 *
	 * @return Die Farbe des Verteidiger-Spielsteins.
	 */
	public static Color getDefenderPieceColor() {
		return DEFENDER_PIECE_COLOR;
	}

	/**
	 * Gibt die Farbe des König-Spielsteins zurück.
	 *
	 * @return Die Farbe des König-Spielsteins.
	 */
	public static Color getKingPieceColor() {
		return KING_PIECE_COLOR;
	}

	/**
	 * Setzt den globalen Skalierungsfaktor.
	 *
	 * @param GLOBAL_SCALING_FACTOR Der neue globale Skalierungsfaktor.
	 */
	public static void setGLOBAL_SCALING_FACTOR(double GLOBAL_SCALING_FACTOR) {
		GlobalGuiConfig.GLOBAL_SCALING_FACTOR = GLOBAL_SCALING_FACTOR;
	}

}
