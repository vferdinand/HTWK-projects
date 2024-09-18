package tiergen;

public class TuerkischVan extends Feloidea 
						implements Haustier {

	private String name;

	public TuerkischVan() {
		super();
		this.art = "TuerkischVan";
		this.futter = "Fisch";
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
