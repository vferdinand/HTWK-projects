package tiergen;

public abstract class Zootier {
	
	private int id;
	protected String familie;
	protected String art;
	protected String futter;
	
	//static variable
	private static int counter = 1056;
	
	public Zootier() {
		this.id = counter++;
	}
	
	@Override
	public String toString() {
		return "Zootier [id=" + id + ", familie=" + familie + ", art=" + art + ", futter=" + futter + "]";
	}
	
	
}
