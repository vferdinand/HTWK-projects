package Uebung_10;

import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class LottoDaten {

	private Map<Integer, Set<Integer>> lottoDaten;

	public LottoDaten() {
		lottoDaten = new TreeMap<Integer, Set<Integer>>();
	}

	public Set<Integer> generiereTip() {
		Set<Integer> tip = new TreeSet<Integer>();
		System.out.println("[data] generiere Tip");
		Random zufall = new Random();
		do {
			int z = zufall.nextInt(49) + 1;
			System.out.println("ziehe: " + z);
			tip.add(z);
		} while (tip.size() < 6);
		
		System.out.println(tip);
		return tip;
	}

	public Map<Integer, Set<Integer>> getLottoDaten() {
		return lottoDaten;
	}

	public void setTip(Integer kw, Set<Integer> tip) {
		System.out.println("[data] set kw " + kw + " tip: " + tip);
		this.lottoDaten.put(kw, tip);
	}

}
