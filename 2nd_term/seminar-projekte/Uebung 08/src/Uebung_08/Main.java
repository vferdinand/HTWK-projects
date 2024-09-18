package Uebung_08;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		Map<String,List<Eatable>> strassen = new HashMap<>();
		
		Scanner s = new Scanner(System.in);
		Random z = new Random();
		
		do {
			System.out.println("Bitte Straßennamen eingeben: ");
			String strasse = s.next();
			if (!strassen.containsKey(strasse)) {
				
				List<Eatable> lokale = new LinkedList<>();
				int n = z.nextInt(6) + 5;
				for(int i = 0; i < n; i++) {
					lokale.add(Gaststaette.erzeugeGaststaette());
				}
				
				strassen.put(strasse, lokale);
			}
			
			System.out.println("Weiter? (J/N): ");
		} while(s.next().trim().toUpperCase().startsWith("J"));
		
		for (String str:strassen.keySet()) {
			System.out.println("In der Straße " + str + " gibt es: ");
			int i = 1;
			for(Eatable lokal:strassen.get(str)) {
				System.out.println(i + ": " + lokal.getName());
				i++;
			}
		}
		
	}
}
