package Beleg_2_A;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

public class main {

	private static final int size = 5;
	private static final Random zufall = new Random();
	private static final List<String>[][] spielRaster = new List[size][size];
   
	
    public static void main(String[] args) {
		initialisiereSpielRaster();
		platziereGegenstaende();
		zeigSpielRaster();
		simuliereRegen();
		simuliereGewitter();
	}
    
    private static boolean sindAllePositionenBelegt() {
    	for (int i = 0; i < size; i++) {
    		for (int j = 0; j < size; j++) {
    			if (spielRaster[i][j].size() == 1 && spielRaster[i][j].contains("Leer")) {
                    return false;
                }
    		}
    	}
    	return true;
    }
    
    private static String generierZufallsposition() {
    	char x = (char) ('A' + zufall.nextInt(size));
    	char y = (char) ('1' + zufall.nextInt(size));
    	return "" + x + y;
    }
    
    private static void iterierUeberRaster(BiConsumer<Integer, Integer> action) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				action.accept(i, j);
			}
		}
	}

    private static void initialisiereSpielRaster() {
    	iterierUeberRaster((i, j) -> {
    		spielRaster[i][j] = new ArrayList<>();
    		spielRaster[i][j].add("Leer");
    		});
    	spielRaster[size / 2][size / 2].set(0, "Fels[id: ####, position: " + (char) ('A' + size / 2) + (size / 2 + 1) + "]");
    }
    
	
   	
	private static void zeigSpielRaster() {
	    for (int i = 0; i < size; i++) {
	        for (int j = 0; j < size; j++) {
	            if (spielRaster[j][i].size() > 1) {
	                spielRaster[j][i].forEach(obj -> System.out.print(obj + " "));
	            } else if (spielRaster[j][i].size() == 1 && !spielRaster[j][i].contains("Leer")) {
	                System.out.print(spielRaster[j][i].get(0));
	            }
	            System.out.println();
	        }
	        System.out.println();
	    }
	}
    
	
	
	
	
	

   private static void platziereGegenstand(Fachabteilung abteilung) {
        String position = generierZufallsposition();
        int i = position.charAt(1) - '1';
        int j = position.charAt(0) - 'A';

        if (i == size/2 && j == size/2) {
            return;
        }
        
        Gegenstand gegenstand = abteilung.erzeugeGegenstand(position);

        if (spielRaster[i][j].stream().anyMatch(s -> s.contains(gegenstand.getClass().getSimpleName()))) {
            System.out.println("Konnte " + gegenstand + " nicht auf Position " + position + " platzieren, weil bereits ein Objekt der gleichen Klasse existiert.");
            return;
        }
       //Bedingung, dass keine Unterklasse von Gegenst�nde zweifach in die gleiche Position abgespeichert wird
        if (spielRaster[i][j].stream().anyMatch(s -> s.contains("Wohngebaeude") || s.contains("Stall") || s.contains("Wirtschaftsgebaeude") ||
        									 	s.contains("Wald")|| s.contains("Wiese") || s.contains("Feld"))) 
        									
		 									//	|| s.contains("Traktor")|| s.contains("Maehdrescher") || s.contains("Transporter")))
       {
            System.out.println("Konnte " + gegenstand + " nicht auf Position " + position + " platzieren, weil bereits ein Gebaeude oder Grundstueck existiert.");
            return;
        }
        
        //cleart "Leer"
        if (spielRaster[i][j].contains("Leer")) {
        	spielRaster[i][j].remove("Leer");
        }
        spielRaster[i][j].add(gegenstand.toString());
        
        System.out.println("Platziert " + gegenstand + " auf Position " + position);
    }
    
   private static void platziereGegenstaende() {
    	Fachabteilung[] abteilungArray = {
                new Bauinnung() {},
                new Landwirtschaftsverband() {},
                new Landmaschinenhandel() {}
            };
    	
    	while (!sindAllePositionenBelegt()) {
    		for (Fachabteilung abteilung : abteilungArray) {
    			platziereGegenstand(abteilung);
    		}
    	}
    } 
        
   
    
    private static void simuliereRegen() {
    	System.out.println("Es regnet..");
    	iterierUeberRaster((i, j) -> {
    		
    		//wenn auf einer Position ein Gebaudeobjekt und ein Fahrzeugobjet steht, wird das Objekt der Klasse Fahrzeug nicht "Nass"
    		boolean hatGebaeude = spielRaster[i][j].stream().anyMatch(s -> s.contains("Wohngebaeude") || s.contains("Stall") || s.contains("Wirtschaftsgebaeude"));
            spielRaster[i][j].stream().filter(s -> s.contains("Traktor") || s.contains("Maehdrescher") || s.contains("Transporter")).forEach(f -> {
                if (!hatGebaeude) {
                    System.out.println(f + " wird nass.");
                }
            });
        });
    }
    
    //wegen 'BiConsumer'-Lambda-Funktion: kann keine Variablen, die au�erhalb des Lambda-Ausdrucks deklariert wurden ver�ndern, wenn diese nicht final sind.
    private static void simuliereGewitter() {
    	System.out.println("Es gewittert..");
    	AtomicBoolean getroffen = new AtomicBoolean(false);
    	 while (!getroffen.get()) {
             iterierUeberRaster((i, j) -> {
            	 
            	 //Stall ist das einzige Geb�ude ohne Blitzableiter, es wird abgefragt, ob ein Objekt auf dem Spielraster "Stall" hei�t
                 spielRaster[i][j].stream().filter(s -> s.contains("Stall")).forEach(stall -> {
                     if (zufall.nextInt(100) < 5) {
                         System.out.println(stall + ", Fels[id: ####, position: " + (char) ('A' + size / 2) + (size / 2 + 1) + "] wurde vom Blitz getroffen!");
                         getroffen.set(true);
                     }
                 });
             });
         }
     }  
}    