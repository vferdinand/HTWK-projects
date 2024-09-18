package Beleg_2_A;

public class Grundstueck extends Gegenstand {

	public Grundstueck(int id, String position) {
		super(id, position);
	}

	static class Wald extends Grundstueck {

		public Wald(int id, String position) {
			super(id, position);
		}
	}
	
	static class Wiese extends Grundstueck {

		public Wiese(int id, String position) {
			super(id, position);
		}
	}
	
	static class Feld extends Grundstueck {

		public Feld(int id, String position) {
			super(id, position);
		}
	}
}
