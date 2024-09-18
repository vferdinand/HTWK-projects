package anglerverein;
import java.util.Random;
import java.util.Scanner;
import see.See;

public class Main 
{
	public static void main(String[] args) 
	{
		See.fischeAussetzen(); // das darf nicht ver�ndert werden
		Random z = new Random();
		
		Scanner reader = new Scanner(System.in);
		String ein = "";
		
		String[] vnamen = {"Hauke","Fiete","Hinnerk","Ole","Björn","Svea", "Freya","Beeke","Astrid","Peer","Olaf","Helga"};
		String[] nnamen = {"Hansen","Petersen","Claasen","Holmgren","Lindgren","Herrmanssdottir","Feddersen","Beust","Dierksen","Elendottir"};
		
		double[] beitraege = {5.5,7.5,10.0,12.5,15.50,20.0};
		int n = z.nextInt(10) + 5;
		Angler[] pj = new Angler[n];
		
		
		for(int i = 0; i < pj.length; i++) {
			pj[i] = new Angler(vnamen[z.nextInt(vnamen.length)],nnamen[z.nextInt(nnamen.length)],beitraege[z.nextInt(beitraege.length)]);
		}
			
		pj[0] = Kassenwart.getInstance("Hein","Snadhus",25.5);		
		pj[1] = Vorstand.getInstance("Rüdiger","Hoden",25.5);
		
		for(Angler a: pj) {
			System.out.println(a);
		}
	
		int counter = 0;
		do
		{
			int i = z.nextInt(pj.length);
			pj[i].angeln();
			counter++;
			if (counter%10 ==0) {
				Kassenwart k = pj[0];
				k.zahlePacht();
				
				Vorstand v = pj[1];
				v.halteRede();
			}
			System.out.print("Weiter? ");
			ein = reader.next();
		} while (ein.equals("j"));
		
		reader.close();		
		See.dispose();
	}
}
