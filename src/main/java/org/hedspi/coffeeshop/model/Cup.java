package org.hedspi.coffeeshop.model;

public class Cup {
	private int id;
	private int coffeeId;
	private int orderId;
<<<<<<< HEAD
	private boolean bigSize; // NORNAL, BIG
=======
	private String size = "NORMAL"; // NORNAL(default), BIG
	private String condiments;
>>>>>>> 02c6aff... Controller save order's data
	private double price;

	public Cup() {

	}

<<<<<<< HEAD
	public Cup(int id, int coffeeId, int orderId, boolean bigSize, double price) {
		super();
=======
	public Cup(int id, int coffeeId, int orderId, String size, String condiments, double price) {
>>>>>>> 02c6aff... Controller save order's data
		this.id = id;
		this.coffeeId = coffeeId;
		this.orderId = orderId;
		this.size = size;
		this.condiments = condiments;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCoffeeId() {
		return coffeeId;
	}

	public void setCoffeeId(int coffeeId) {
		this.coffeeId = coffeeId;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	
	public String getCondiments() {
		return condiments;
	}

	public void setCondiments(String condiments) {
		this.condiments = condiments;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	

}
