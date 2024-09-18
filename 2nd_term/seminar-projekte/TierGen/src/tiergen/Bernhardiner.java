package tiergen;

public class Bernhardiner extends Canoidea
						implements Haustier {

	private String name;

	public Bernhardiner() {
		super();
		this.art = "Bernhardiner";
		this.futter = "Hundefutter, Cognac";
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
