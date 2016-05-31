package org.hedspi.coffeeshop.model;

public class Condiment extends Product {

	public Condiment() {

	}

	public Condiment(int id) {
		super(id);
	}

	public Condiment(int id, String name, double price, boolean enabled) {
		super(id, name, price, enabled);
	}
	
	public String toString(){
		return "("+this.id+","+getName() +","+getPrice()+")";
	}
}
