package Beleg_2_A;

import java.util.Random;

public class Landwirtschaftsverband implements Fachabteilung {
	
	private int id= 1000;
	private static final Random zufall = new Random();
	
	@Override
	public Gegenstand erzeugeGegenstand(String position) {
		int random = zufall.nextInt(3);
		switch (random) {
	    	case 0:
	    		return erzeugeWald(position);
	        case 1:
	            return erzeugeWiese(position);
	        case 2:
	            return erzeugeFeld(position);
	        default:
	            throw new IllegalStateException("Ung�ltiger Zufallswert");
		}
	}

    public Gegenstand erzeugeWald(String position) {
        return new Grundstueck.Wald(id++, position);
    }

    public Gegenstand erzeugeWiese(String position) {
        return new Grundstueck.Wiese(id++, position);
    }

    public Gegenstand erzeugeFeld(String position) {
        return new Grundstueck.Feld(id++, position);
    }
}
