package roemische_Zahlen;

import java.util.Scanner;

public class roemische_Zahlen {

	public static void main(String[] args){
		Scanner scanner = new Scanner(System.in);
		String choice = "ja";
		
		do {
			System.out.print("Gib eine Dezimalzahl kleiner als 4000 ein: ");
			int dzahl = scanner.nextInt();
			if (dzahl >= 4000) {
				System.out.println("Bitte gib eine Dezimalzahl kleiner als 400 ein.");
				continue;
			}
			String roemisch = toRoemisch(dzahl);
			System.out.println("Römische Darstellung: " + roemisch);
			
			System.out.print("WIllst du eine weiter Dezimalzahl umwandeln? (ja/nein)");
			choice = scanner.next();
		} while (choice.equalsIgnoreCase("ja"));
		
		System.out.println("Das Programm wird beendet.");
		scanner.close();
	}
	
	public static String toRoemisch(int dzahl) {
		if (dzahl <= 0 || dzahl >= 4000) {
			throw new IllegalArgumentException("Dezimalzahl außerhalb des gültigen Bereichs.");
		}
		
		String[] roemischeSymbole = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
		int[] dezimalWerte = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
		
		StringBuilder roemisch = new StringBuilder();
		int i = 0;
		
		while (dzahl > 0) {
			if (dzahl - dezimalWerte[i] >= 0) {
				roemisch.append(roemischeSymbole[i]);
				dzahl -= dezimalWerte[i];
			} else {
				i++;
			}
		}
		
		return roemisch.toString();
	}
}
