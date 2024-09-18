package Uebung_08;

import java.util.Random;

public class Wurstbude extends Gaststaette {

	private String name;
	private String[] names = {"Hermanns Riesencurrywurst","Wurst Case","Melanies Mettwurst", "Holgers Inn","Heikos Grillecke","Brunos Biergarten","Martins vegane Würstchen"};

	public Wurstbude() {
		Random z = new Random();
		this.name = this.getClass().getSimpleName() + " " + names[z.nextInt(names.length)];
	}

	@Override
	public String getFood() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
