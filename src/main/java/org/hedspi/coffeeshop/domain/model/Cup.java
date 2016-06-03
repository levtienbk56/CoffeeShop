package org.hedspi.coffeeshop.domain.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Cup extends Item {
	private Coffee coffee;

	private String size = "NORMAl";
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
		if (size.equals("BIG")) {
			price *= 1.2;
		}
		for (Condiment c : condiments) {
			price += c.getPrice();
		}
		return price;
	}

	public Object getCondimentsName() {
		String str = "";
		Iterator<Condiment> iterator = this.condiments.iterator();
		while (iterator.hasNext()) {
			str += iterator.next().getName();
			if (iterator.hasNext())
				str += ", ";
		}
		return str;
	}
	
}
