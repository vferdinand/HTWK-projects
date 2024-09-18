package piraten;

import java.util.Random;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random zufall = new Random();
		int n = 100000000;
		int imKreis = 0;
		for (int i = 0; i < n; i++) {
			double x = zufall.nextDouble();
			double y = zufall.nextDouble();
			
			if ((x*x + y*y) <= 1.0) {
				imKreis++;
			}
		}
		System.out.printf("pi/4 = %10.9f",4*((double)imKreis/n));
	}

}
