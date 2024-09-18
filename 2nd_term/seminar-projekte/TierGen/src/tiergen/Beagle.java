package tiergen;

public class Beagle extends Canoidea 
						implements Haustier{

	private String name;

	public Beagle() {
		super();
		this.art = "Beagle";
		this.futter = "Hundefutter";
	}

	@Override
	public void setName(String s) {
		// TODO Auto-generated method stub
		this.name = s;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public void streicheln() {
		// TODO Auto-generated method stub
		System.out.println("..wird getreichelt");
		
	}

	
}
