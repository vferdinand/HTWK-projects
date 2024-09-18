package anglerverein;

public class Vorstand extends Angler {
	
	private static Vorstand instance = null;

	private Vorstand(String vn, String nn, double b) {
		super(vn, nn, b);
		this.fangrecht = 4;
		
	}
	public static Vorstand getInstance(String v, String n, double b) {
		if (instance == null) {
			instance = new Vorstand(v,n,b);
		}
		return instance;
	}
	public void halteRede() {
		// TODO Auto-generated method stub
		
	}

}
