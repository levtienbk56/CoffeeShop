package org.hedspi.coffeeshop.domain.model;

public abstract class Item {
	int id;

	public Item() {

	}

	public Item(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
