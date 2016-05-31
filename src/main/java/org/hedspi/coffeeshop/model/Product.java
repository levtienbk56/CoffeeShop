package org.hedspi.coffeeshop.model;

public class Product extends Item {
	private String name = "";
	private double price = 0.0;
	private boolean enabled = true;

	public Product() {

	}

	public Product(int id) {
		super(id);
	}

	public Product(int id, String name, double price, boolean enabled) {
		super(id);
		this.name = name;
		this.price = price;
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String toString() {
		return "{" + id + "," + name + "," + price + "," + enabled + "}";

	}
}
