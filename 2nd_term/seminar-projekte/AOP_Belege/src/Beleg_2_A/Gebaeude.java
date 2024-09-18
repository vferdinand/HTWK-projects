package Beleg_2_A;

public class Gebaeude extends Gegenstand {
	
	public Gebaeude(int id,String position) {
		super(id, position);
	}
	
	static class Wohngebaeude extends Gebaeude {

		public Wohngebaeude(int id, String position) {
			super(id, position);
		}
	}
	
	static class Stall extends Gebaeude {

		public Stall(int id, String position) {
			super(id, position);
		}
	}
	
	static class Wirtschaftsgebaeude extends Gebaeude {

		public Wirtschaftsgebaeude(int id, String position) {
			super(id, position);
		}
	}
}