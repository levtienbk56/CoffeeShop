package org.hedspi.coffeeshop.model;

public class Cup {
	private int id;
	private int coffeeId;
	private int orderId;
	private boolean bigSize; // NORNAL, BIG
	private double price;
	
	public Cup(){
		
	}

	public Cup(int id, int coffeeId, int orderId, boolean bigSize, double price) {
		super();
		this.id = id;
		this.coffeeId = coffeeId;
		this.orderId = orderId;
		this.bigSize = bigSize;
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

	public boolean isBigSize() {
		return bigSize;
	}

	public void setBigSize(boolean bigSize) {
		this.bigSize = bigSize;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
}
