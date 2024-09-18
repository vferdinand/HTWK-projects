package Uebung_04;

import java.util.Random;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Card karte = new Card();
		karte.setFarbe("Kreuz");
		karte.setWert("acht");
		
	// non optimal way to print w/ toString Method
	//	System.out.println("karte: " + karte.getWwert() + " " karte.getFarbe()); 
	
	//	System.out.println(karte);
		
		Card[] skatspiel = new Card[32];
		
		genCardDeck(skatspiel);
		System.out.println("-----ungemischt-----");
		showCardDeck(skatspiel);
		shuffleCardDeck(skatspiel);
		System.out.println("-----ungemischt-----");
		showCardDeck(skatspiel);
		
		Card[] s1 = new Card[10];
		Card[] s2 = new Card[10];	
		Card[] s3 = new Card[10];
		Card[] skat = new Card[2];
		
		dealCards(skatspiel, s1, s2, s3, skat);
		
		System.out.println("--Spieler 1---");
		showCardDeck(s1);
		System.out.println("---Spieler 2---");
		showCardDeck(s2);
		System.out.println("--Spieler 3---");
		showCardDeck(s3);
		System.out.println("---Skat---");
		showCardDeck(skat);
	}

	private static void dealCards(Card[] deck, Card[] s1, Card[] s2, Card[] s3, Card[] skat) {
		
		//1. Runde 3 Karten
		for(int i = 0; i <= 2; i++) {
			s1[i] = deck[i];
			s2[i] = deck[i + 3];
			s3[i] = deck[i + 6];
		}
		//Skat
		skat[0] = deck[9];
		skat[1] = deck[10];
		
		//2. Runde 4 Karten
		for(int i = 0; i <= 3; i++) {
			s1[i] = deck[11 + i + 4];
			s2[i] = deck[11+ i + 8];
			s3[i] = deck[11+ i + 12];
		}
		//3. Runde 3 Korten
		for(int i = 0; i <= 2; i++) {
			s1[i] = deck[23 + i + 3];
			s2[i] = deck[23 + i + 6];
			s3[i] = deck[23 + i + 9];
		}
	}

	private static void shuffleCardDeck(Card[] deck) {
		
		for(int i = 0; i < deck.length; i++) {
			Random zufall = new Random();
			int j = zufall.nextInt(32);
					Card tmp = deck[i];
					deck[i] = deck[j];
					deck[j] = tmp;
		}
	
	} 

	private static void showCardDeck(Card[] d) {
		
		for(Card card: d) {
			System.out.println(card);
		}
		
		
	}

	private static void genCardDeck(Card[] deck) {
		
		final String farben[] = {"Kreuz", "Pik", "Herz", "Karo"};
		final String werte[] = {"Ass", "König", "Dame", "Bube", "10", "9", "8", "7"};
		
		int i = 0;
		for (String f : farben) {
			for (String w : werte) {
				Card c = new Card();
				c.setWert(w);
				c.setFarbe(f);
				deck[i] = c;
				i++;
			}
		}
	}

}
