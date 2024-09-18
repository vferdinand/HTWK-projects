package tiergen;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.io.*;
public class Main 
{

	
	public static void main(String[] args) throws Exception 
	{
		//Pfad anpassen!
		File f = new File(".\\Daten\\Tiere.txt");
		OutputStream ostream = new FileOutputStream(f);
		
		PrintWriter writer = new PrintWriter(ostream);
		Random zufall = new Random();
		Scanner reader = new Scanner(System.in);
		
		List<Feloidea> katzen = new ArrayList<Feloidea>();
		List<Canoidea> hunde = new ArrayList<Canoidea>();
		List<Haustier> streichelzoo = new ArrayList<Haustier>();
		
		System.out.print("Anzahl > ");
		int anzahl = reader.nextInt();
		
		for(int i = 0; i < anzahl; i++){
			switch(zufall.nextInt(8)){
				case 0:
					writer.println("Amurtiger");
					katzen.add(new Amurtiger());
					break;
				case 1:
					writer.println("Maine-Coon");	
					katzen.add(new MaineCoon());
					break;
				case 2:
					writer.println("Beagle");		
					hunde.add(new Beagle());
					break;
				case 3:
					writer.println("Erdmaennchen");		
					katzen.add(new Erdmaennchen());
					break;
				case 4:
					writer.println("Wolf");	
					hunde.add(new Wolf());
					break;
				case 5:
					writer.println("Tuerkisch Van");	
					katzen.add(new TuerkischVan());
					break;
				case 6:
					writer.println("Bernhardiner");		
					hunde.add(new Bernhardiner());
					break;
				case 7:
					writer.println("Kodiakbaer");		
					hunde.add(new Kodiabaer());
					break;
				default:
			}
		}
		System.out.println("Alle Katzen:");
		for(Feloidea k: katzen) {
			System.out.println(k);
		}
		System.out.println("Alle Hunde:");
		for(Canoidea h: hunde) {
			System.out.println(h);
		}
		
		String[] katzennamen = {"Zwiebel","Knobi","Papri","Marmi","Gnöckel","Tortelloni","Gnocci","Speer"};
		String[] hundenamen = {"Bruno","Pommes","Jonas","Rufus","Jens","Wagner","Ubri","Pedro"};
		
		//Streichelzoo befüllen
		for(Feloidea k: katzen) {
			if (k instanceof Haustier) {
				((Haustier) k).setName(katzennamen[zufall.nextInt(katzennamen.length)]);
				streichelzoo.add((Haustier) k);
			}
		}
		for(Canoidea h: hunde) {
			if (h instanceof Haustier) {
				((Haustier) h).setName(hundenamen[zufall.nextInt(hundenamen.length)]);
				streichelzoo.add((Haustier) h);
			}
		}
		
		System.out.println("Streichelzoo");
		for (Haustier h: streichelzoo) {
			System.out.println("Name: " + h.getName());
			System.out.println(h);
			h.streicheln();
		}
			
		reader.close();
		writer.close();
	}

}


