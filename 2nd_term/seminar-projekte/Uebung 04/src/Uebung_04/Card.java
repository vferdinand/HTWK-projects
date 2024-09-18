package Uebung_04;

public class Card {

	private String farbe;
	private String wert;
	
	// Source Menu, Generate Getters and Setters
	public String getFarbe() {
		return farbe;
	}
	public void setFarbe(String farbe) {
		this.farbe = farbe;
	}
	public String getWert() {
		return wert;
	}
	public void setWert(String wert) {
		this.wert = wert;
	}

	//Hinweis, Methode zu überschreiben
	@Override
	public String toString() {
		return "[" + this.farbe + " " + this.wert + "]";
	}
}
