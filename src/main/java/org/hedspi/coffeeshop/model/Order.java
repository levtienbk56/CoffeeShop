package org.hedspi.coffeeshop.model;

import java.sql.Timestamp;

public class Order {
	private int id;
	private String username;
	private Timestamp purchaseTime;
	private Double total;

	public Order() {

	}

	public Order(int id, String username, Timestamp timestamp, Double price) {
		this.id = id;
		this.username = username;
		this.purchaseTime = timestamp;
		this.total = price;
	}

	public Order(String username, Timestamp timestamp, Double price) {
		this.username = username;
		this.purchaseTime = timestamp;
		this.total = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Timestamp getPurchaseTime() {
		return purchaseTime;
	}

	public void setPurchaseTime(Timestamp purchaseTime) {
		this.purchaseTime = purchaseTime;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Order)) {
			return false;
		}

		Order that = (Order) other;
		return (this.id == that.id) && this.username.equals(that.username)
				&& this.purchaseTime.equals(that.purchaseTime) && (this.total == that.total);
	}

	public String toString() {
		return "{" + id + "," + username + "," + purchaseTime + "," + total + "}";
	}

}
