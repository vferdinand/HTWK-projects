package Beleg_2_A;

public class Fahrzeug extends Gegenstand {

	public Fahrzeug(int id, String position) {
		super(id, position);
	}

	static class Traktor extends Gegenstand {

		public Traktor(int id, String position) {
			super(id, position);
		}
		 @Override
	        public String toString() {
	            return "Traktor[id: " + getId() + ", position: " + getPosition() + "]";
	        }
	}
	
	static class Maehdrescher extends Gegenstand {

		public Maehdrescher(int id, String position) {
			super(id, position);
		}
		 @Override
	        public String toString() {
	            return "Maehdrescher[id: " + getId() + ", position: " + getPosition() + "]";
	        }
	}
	
	static class Transporter extends Gegenstand {

		public Transporter(int id, String position) {
			super(id, position);
		}
		
		 @Override
	        public String toString() {
	            return "Transporter[id: " + getId() + ", position: " + getPosition() + "]";
	        }
	}
	
}
