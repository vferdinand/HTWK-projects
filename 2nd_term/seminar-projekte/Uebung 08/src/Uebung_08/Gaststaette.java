package Uebung_08;

import java.util.Random;

public abstract class Gaststaette implements Eatable{

	public abstract String getName();

	public static Eatable erzeugeGaststaette() {
		
		Random zufall = new Random();
		
		switch (zufall.nextInt(4)) {	// 3 versch. Unterklassen
		case 0:
			
			return new Suppenkueche();
		case 1:
			return new Wurstbude();
		case 2:
			return new Sushibar();
		case 3:
			return new Pizzeria();
		default:
			return null;
		}
	}

}
