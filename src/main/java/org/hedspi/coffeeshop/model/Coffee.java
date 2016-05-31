package org.hedspi.coffeeshop.model;

public class Coffee extends Product {

	public Coffee() {

	}

	public Coffee(int id) {
		super(id);
	}

	public Coffee(int id, String name, double price, boolean enabled) {
		super(id, name, price, enabled);
	}

	public String toString() {
		return "{" + getId() + "," + getName() + "," + getPrice() + "," + isEnabled() + "}";

	}
}
