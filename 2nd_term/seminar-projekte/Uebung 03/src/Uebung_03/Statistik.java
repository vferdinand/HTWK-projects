package Uebung_03;

import java.util.Arrays;
import java.util.Random;

public class Statistik {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Random zufall = new Random();
		
		//System.out.println(zufall.nextDouble()*10);
		int n =10;
		double[] werte = new double[n];
		
		for(int i = 0;i < werte.length;i++) {
			werte[i] = zufall.nextDouble()*10;
		}
		/*
		for(double d: werte) {
			System.out.println(d);
		} */
		
		double summe = 0.0;
		for(int i = 0;i < werte.length; i++) {
			summe += werte[i];
		}
		double mittelwert = summe/n;
		
		double quadratAbweichung = 0.0;
		for(double d: werte) {
			quadratAbweichung += (d - mittelwert)*(d - mittelwert);			
		}
		
		double sigma = Math.sqrt(1.0/(n-1)*quadratAbweichung); //1.0, nicht (1/(n-1)) damit es kein int ist und nicht automatisch 0 ausgegeben wird
		
		Arrays.sort(werte);
		
		double median = 0.0;
		if((n & 1) == 0) {
			//gerade
			median = 0.5 * (werte[n/2] + werte[n/2-1]);
		} else {
			median = werte[n/2];
		}
		
		
		System.out.printf("Mittelwert: %8.2f\n",mittelwert);
		System.out.printf("sigma:      %8.2f\n",sigma);
		System.out.printf("Median:     %8.2f\n",median);
	}

}
