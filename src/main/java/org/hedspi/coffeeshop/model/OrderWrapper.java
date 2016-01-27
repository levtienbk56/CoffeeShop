package org.hedspi.coffeeshop.model;

import java.util.HashMap;
import java.util.Map;

public class OrderWrapper {
	private double total;
	Map<String, CupWrapper> cups;

	public OrderWrapper() {
		total = 0;
		cups = new HashMap<String, CupWrapper>();
	}

	public OrderWrapper(double total, Map<String, CupWrapper> cups) {
		super();
		this.total = total;
		this.cups = cups;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public Map<String, CupWrapper> getCups() {
		return cups;
	}

	public void setCups(Map<String, CupWrapper> cups) {
		this.cups = cups;
	}

}
