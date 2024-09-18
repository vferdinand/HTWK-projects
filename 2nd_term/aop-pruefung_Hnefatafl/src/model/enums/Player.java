package model.enums;

/**
 * Enum, das die beiden Spieler repräsentiert.
 */
public enum Player {
	ATTACKER {
		@Override
		public String toString() {
			return "Angreifer";
		}
	},

	DEFENDER {
		@Override
		public String toString() {
			return "Verteidiger";
		}
	};
}
