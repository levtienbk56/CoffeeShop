package org.hedspi.coffeeshop.model;

import java.util.HashMap;
import java.util.Map;

public class CupWrapper {
	private String id;
	private Coffee coffee;
	private String cupSize ="NORMAL";  // or BIG
	private int quantity;
	private Map<String, Condiment> condiments = new HashMap<String, Condiment>();
	private double price;

	public CupWrapper() {

	}

	public CupWrapper(String id, Coffee coffee, String size, int quantity, Map<String, Condiment> condiments,
			double price) {
		super();
		this.id = id;
		this.coffee = coffee;
		this.cupSize = size;
		this.quantity = quantity;
		this.condiments = condiments;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Coffee getCoffee() {
		return coffee;
	}

	public void setCoffee(Coffee coffee) {
		this.coffee = coffee;
	}

	public String getCupSize() {
		return cupSize;
	}

	public void setCupSize(String cupSize) {
		this.cupSize = cupSize;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Map<String, Condiment> getCondiments() {
		return condiments;
	}

	public void setCondiments(Map<String, Condiment> condiments) {
		this.condiments = condiments;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
