package org.hedspi.coffeeshop.domain.model;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order extends Item {
	private User user;
	private Date purchaseTime;
	private double total;
	private List<Cup> cups;

	public Order() {
		cups = new ArrayList<>();
		total = (double) 0;
	}

	public Order(int id, User user, List<Cup> cups, Timestamp purchaseTime, double total) {
		super(id);
		this.user = user;
		this.cups = cups;
		this.purchaseTime = purchaseTime;
		this.total = total;
	}

	/**
	 * if Time parsing error, set time=null
	 * 
	 * @param id
	 * @param user
	 * @param cups
	 * @param purchaseTime
	 * @param total
	 */
	public Order(int id, User user, List<Cup> cups, String purchaseTime, double total) {
		super(id);

		this.user = user;
		this.cups = cups;
		try {
			this.purchaseTime = Timestamp.valueOf(purchaseTime);
		} catch (Exception e) {
			e.printStackTrace();
			this.purchaseTime = null;
		}
		this.total = total;
	}

	/**
	 * if Time parsing error, set time=null
	 * 
	 * @param id
	 * @param username
	 * @param purchaseTime
	 * @param total
	 */
	public Order(int id, String username, String purchaseTime, double total) {
		super(id);
		this.user = new User(username);
		try {
			this.purchaseTime = Timestamp.valueOf(purchaseTime);
		} catch (Exception e) {
			e.printStackTrace();
			this.purchaseTime = null;
		}
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

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
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

	@Override
	public String toString() {
		return "(" + id + "," + user.getUsername() + "," + purchaseTime + "," + total + ")";
	}

}
