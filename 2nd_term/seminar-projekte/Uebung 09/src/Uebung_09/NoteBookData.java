package Uebung_09;

import java.util.HashMap;
import java.util.Map;

public class NoteBookData {

	private Map<String,String> notes = new HashMap<>();
	
	public String holeTermin(String wochentag) {
		System.out.println("[data] hole Termin für: " + wochentag);
		return notes.get(wochentag);
	}

	public void speichernTermin(String tag, String termin) {
		System.out.println("[data] speichern: " + tag + " termin: " + termin);
		notes.put(tag, termin);
	}

}
