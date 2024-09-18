package Uebung_08;

import java.util.Random;

public class Suppenkueche extends Gaststaette {

	private String[] names = {"Soup Hero","Soupway Surfer","Soupa dick","Michels","Hexenkessel","Brennender Topf","Schüsselkind","Jetz wirds flüssig","Mamas Küche"};
	private String name;

	public Suppenkueche() {
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
