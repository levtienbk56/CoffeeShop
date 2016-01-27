package org.hedspi.coffeeshop.model;

public class Condiment {
	private int id;
	private String name;
	private double price = 0;

	public Condiment() {

	}

	public Condiment(int id, String name, double price) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
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

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Condiment)) {
			return false;
		}

		Condiment that = (Condiment) other;
		return (this.id == that.id) && this.name.equals(that.name) && (this.price == that.price);
	}

}
