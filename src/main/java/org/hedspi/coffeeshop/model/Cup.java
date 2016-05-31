package org.hedspi.coffeeshop.model;

import java.util.ArrayList;
import java.util.List;

public class Cup extends Item {
	private Coffee coffee;

	// TODO: fix enum
	private String size = "NORMAL"; // NORMAL(default), BIG
	private List<Condiment> condiments = new ArrayList<Condiment>();

	public Cup() {

	}

	public Cup(int id, Coffee coffee, String size, List<Condiment> condiments) {
		this.id = id;
		this.coffee = coffee;
		this.size = size;
		this.condiments = condiments;
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public List<Condiment> getCondiments() {
		return condiments;
	}

	public void setCondiments(List<Condiment> condiments) {
		this.condiments = condiments;
	}

	public void addCondiment(Condiment condiment) {
		if (condiment != null) {
			this.condiments.add(condiment);
		}
	}

	public String getCondimentsID() {
		String str = "";
		for (Condiment c : condiments) {
			str = str + c.getId() + " ";
		}
		return str.trim();
	}

	public double getPrice() {
		double price = 0.0;
		price += coffee.getPrice();
		for (Condiment c : condiments) {
			price += c.getPrice();
		}
		return price;
	}
}
