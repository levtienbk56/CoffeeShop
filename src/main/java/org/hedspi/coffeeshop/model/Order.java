package org.hedspi.coffeeshop.model;

import java.sql.Timestamp;

public class Order {
	private int id;
	private Timestamp timestamp;
	private Double price;
	
	public Order(){
		
	}

	public Order(int id, Timestamp timestamp, Double price) {
		super();
		this.id = id;
		this.timestamp = timestamp;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	

}
