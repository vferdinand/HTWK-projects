package Beleg_2_A;

import java.util.Random;

public class Landmaschinenhandel implements Fachabteilung {

	private int id= 8000;
	private static final Random zufall = new Random();
	 
	@Override
	public Gegenstand erzeugeGegenstand(String position) {
		int random = zufall.nextInt(3);
		switch (random) {
	    	case 0:
	    		return erzeugeTraktor(position);
	        case 1:
	            return erzeugeMaehdrescher(position);
	        case 2:
	            return erzeugeTransporter(position);
	        default:
	            throw new IllegalStateException("Ung�ltiger Zufallswert");
		}
	}
	
    public Gegenstand erzeugeTraktor(String position) {
        return new Fahrzeug.Traktor(id++, position);
    }

    public Gegenstand erzeugeMaehdrescher(String position) {
        return new Fahrzeug.Maehdrescher(id++, position);
    }

    public Gegenstand erzeugeTransporter(String position) {
        return new Fahrzeug.Transporter(id++, position);
    }
}
