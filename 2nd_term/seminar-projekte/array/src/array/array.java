package array;

import java.util.Scanner;

public class array {
	
	static void swap(int[] values, int first, int second) {
		int value1 = values[first];
		int value2 = values[second];
		values[first] = value2;
		values[second] = value1;
	}
public static void main(String[] args) {
	
	int[] x = {1,2,3,4,5,6,7,8,9,10};		// achtung tauscht immer nach Index des arrays --> nicht nach Inhalt/Zahl
	
	Scanner s = new Scanner(System.in);
	
	
	System.out.print("Gebens sie die 1. zu tauschende Zahl ein: ");
	int input1 = s.nextInt();
	System.out.print("Geben sie die 2. zu tauschende Zahl ein: ");
	int input2 = s.nextInt();
	
	swap(x,input1,input2);
	
	for (int i = 0; i < x.length; i++)	//Ausgabe von array x
	System.out.println(x[i]);
	}
}