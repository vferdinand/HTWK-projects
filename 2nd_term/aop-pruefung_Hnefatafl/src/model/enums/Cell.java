package model.enums;

/**
 * Enum, das die verschiedenen Zelltypen auf dem Spielbrett repräsentiert.
 * 
 * Diese Enum definiert die möglichen Typen von Zellen, die auf dem Spielbrett
 * vorkommen können. Jeder Zelltyp hat eine spezifische Bedeutung im Kontext des
 * Spiels:
 * 
 * <pre>
 * - DEFAULT: Repräsentiert eine Standardzelle, die keine besondere Bedeutung hat.
 * - CORNER: Repräsentiert eine Zelle, die sich in einer Ecke desSpielbretts befindet.
 * - THRONE: Repräsentiert die zentrale Zelle des Spielbretts.
 * </pre>
 */
public enum Cell {
	DEFAULT, CORNER, THRONE;
}
