package anglerverein;
import java.util.Scanner;

import see.See;

public class Angler
{
	protected static double kassenstand = 0;
	
	private String vorname;
	private String nachname;
	private double mBeitrag;
	private int fang;
	protected int fangrecht = 2;
	
	
	public Angler(String vn, String nn, double b) {
		this.vorname = vn;
		this.nachname = nn;
		this.mBeitrag = b;
		this.fang = 0;
	}
	
	@Override
	public String toString() {
		return "Angler [vorname=" + vorname + ", nachname=" + nachname + ", mBeitrag=" + mBeitrag + "]";
	}

	public static double getKassenstand() {
		return kassenstand;
	}
	
	public void angeln() {
		if (this.fang < this.fangrecht ) {
			if(See.catchFish(false)) {
				
				/* static boolean catchFish(boolean test)
				 * test == true Es wird nur eine Ausschrift erzeugt
				 * test == false Es wird f�r 3 sec ein Bild des Fangs angezeigt
				 * Die Funktion liefert true, falls ein Wassertier gefangen wurde,
				 * false sonst
				 */
				this.fang++;
			}	
		} else {
				System.out.println("Mietgliedsbeirag zahlen!!");
				mitgliedsBeitragZahlen();
			}
			
	}

	private void mitgliedsBeitragZahlen() {
		System.out.println("Wollen Sie den Mitgliedsbeitrag zahlen (J/N)?");
		Scanner s = new Scanner(System.in);
		String antw = s.next();
		if (antw.toUpperCase().equals("J")) {
			this.zahleBeitrag();
			this.fang = 0;
			System.out.println("Beitrag gezahlt");
			System.out.println("Kassenstand: " + this.getKassenstand());
			this.angeln();
		} else {
			System.out.println("Keine Berechtigung zum angeln!");
		}
		
	}

	private void zahleBeitrag() {
		kassenstand += this.mBeitrag;
		
	}

		/*System.out.println("Es angelt: " + this);
		System.out.println(this);
		See.catchFish(false);
		*/
	
}
