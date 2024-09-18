package Beleg_2_A;

import java.util.Random;

public class Bauinnung implements Fachabteilung {
	
	private static int id= 5000;
	 private static final Random zufall = new Random();
	
	@Override
	public Gegenstand erzeugeGegenstand(String position) {
		int random = zufall.nextInt(3);
		switch (random) {
	    	case 0:
	    		return erzeugeWohngebaeude(position);
	        case 1:
	            return erzeugeStall(position);
	        case 2:
	            return erzeugeWirtschaftsgebaeude(position);
	        default:
	            throw new IllegalStateException("Ung�ltiger Zufallswert");
		}
	}
	
	public Gegenstand erzeugeWohngebaeude(String position) {
	    return new Gebaeude.Wohngebaeude(id++, position);
	}
	        
    public Gegenstand erzeugeStall(String position) {
    	return new Gebaeude.Stall(id++, position);
	}
		
    public Gegenstand erzeugeWirtschaftsgebaeude(String position) {
        return new Gebaeude.Wirtschaftsgebaeude(id++, position);
    }
}
