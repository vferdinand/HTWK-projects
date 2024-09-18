package Uebung_08;

import java.util.Random;

public class Sushibar extends Gaststaette {

	private String[]names = {"Jonas lange Nudeln","Bing Chilling","Fukushima In","Geschmacksexplsosion","Brunos lange Stäbchen","Hermanns Sojasekret","Dine and Shine","Heiße Pfanne"};
	private String name;

	public Sushibar() {
		Random z = new Random();
		this.name = this.getClass().getSimpleName() + " " + names[z.nextInt(names.length)];
	}

	@Override
	public String getFood() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return this.name;
	}

}
