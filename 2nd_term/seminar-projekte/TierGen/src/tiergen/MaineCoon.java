package tiergen;

public class MaineCoon extends Feloidea 
						implements Haustier{

	private String name;

	public MaineCoon() {
		super();
		this.art = "MaineCoon";
		this.futter = "Katzenfutter";
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
