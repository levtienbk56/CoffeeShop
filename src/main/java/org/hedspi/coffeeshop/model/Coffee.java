package org.hedspi.coffeeshop.model;

public class Coffee {
	private int id;
	private String name;
	private double price;
	private boolean enabled = true;

	public Coffee() {

	}

	public Coffee(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
	}

	public Coffee(int id, String name, double price, boolean enabled) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.enabled = enabled;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		return "{" + id + "," + name + "," + price + "}";

	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Coffee)) {
			return false;
		}

		Coffee that = (Coffee) other;
		return (this.id == that.id) && this.name.equals(that.name) && (this.price == that.price);

	}

}
