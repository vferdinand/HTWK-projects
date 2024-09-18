package Beleg_1_D;

import java.util.Scanner;

public class main {

	public static void main(String[] args) {
		
        Scanner scanner = new Scanner(System.in);
        System.out.println("Gib einen String ein: ");
        String input = scanner.nextLine();
        System.out.println("Eingabe: " + input);
        System.out.println("Gefilterte Eingabe: " + filteredString(input));
        System.out.println("Ausgabe: " + getTeilsummanden(filteredString(input)) + " = " + pruefsummeErgebnis(filteredString(input)));
        System.out.println("Resultat: " + pruefsummeErgebnis(filteredString(input)) + "%10 = " + pruefsummeErgebnis(filteredString(input))%10);
	}

	public static String filteredString(String input) {

		 return input.replaceAll("[^0-9]", "");
	}
	 
	 public static int pruefsummeErgebnis(String input) {

		 int summe = 0;
		 for (int i = 0; i < input.length(); i++) {
			 summe += (i + 1) * Character.getNumericValue(input.charAt(i));
		 }
		 return summe;
		
	}
	 
    public static String getTeilsummanden(String input) {

	    int summe = 0;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            summe += (i + 1) * Character.getNumericValue(input.charAt(i));
            sb.append((i + 1) * Character.getNumericValue(input.charAt(i))).append(" + ");
        }
        sb.setLength(sb.length() - 3);
        return sb.toString();
    }
}