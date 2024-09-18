package anglerverein;

public class Kassenwart extends Angler {

	private static Kassenwart instance = null;
	
	private Kassenwart(String vn, String nn, double b) {
		super(vn, nn, b);
		
	}

	public static Kassenwart getInstance(String v, String n, double b) {
		if (instance == null) {
			instance = new Kassenwart(v,n,b);
		}
		return instance;
	}
	public void zahlePacht() {
		// TODO Auto-generated method stub
		
	}
}
