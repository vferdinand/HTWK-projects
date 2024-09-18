package Beleg_2_A;

public abstract class Gegenstand {
	
	protected int id;
	protected String position;
	
	public Gegenstand(int id, String position) {
		this.id = id;
		this.position = position;
	}
	
	public int getId() {
		return id;
	}
	
	public String getPosition() {
		return position;
	}
	
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String toString() {
		return getClass().getSimpleName() + "[id: " + id + ", position: " + position + "]";
	}
}
