package org.hedspi.coffeeshop.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends Item {
	private User user;
	private Date purchaseTime;
	private Double total;
	private List<Cup> cups;

	public Order() {
		cups = new ArrayList<>();
	}

	public Order(int id, User user, List<Cup> cups, Timestamp purchaseTime, Double total) {
		super(id);
		this.user = user;
		this.cups = cups;
		this.purchaseTime = purchaseTime;
		this.total = total;
	}
	
	

	public Order(int id, String username, Date purchaseTime, Double total) {
		super(id);
		this.user = new User(username);
		this.purchaseTime = purchaseTime;
		this.total = total;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Date getPurchaseTime() {
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

	public List<Cup> getCups() {
		return cups;
	}

	public void setCups(List<Cup> cups) {
		this.cups = cups;
	}

	public void addCup(Cup cup) {
		if (cup != null) {
			this.cups.add(cup);
		}
	}

}
