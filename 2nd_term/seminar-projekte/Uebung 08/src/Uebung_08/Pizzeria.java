package Uebung_08;

import java.util.Random;

public class Pizzeria extends Gaststaette {

	
	private String name;
	private String[] names = {"Hermanns Pizzahimmel","Martins veganer Kreis","Pi hoch drei","Rundes Ding","Luigis Tempel","Ananashimmel","Brunos angebrannte Stükchen", "Jonas Pizza und Bowlingparadies","Fernandos Salamipalast"};

	public Pizzeria() {
		Random z = new Random();
		this.name = this.getClass().getSimpleName() + " " + names[z.nextInt(names.length)];
	}

	@Override
	public String getFood() {
		// TODO Auto-generated method stub
		return  null;
		
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
