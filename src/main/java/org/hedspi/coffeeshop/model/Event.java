package org.hedspi.coffeeshop.model;

import java.sql.Timestamp;

public class Event extends Item {
	private String title;
	private Timestamp start;
	private Timestamp end;
	private String color;

	public Event() {

	}

	public Event(int id, String title, Timestamp start, Timestamp end, String color) {
		super(id);
		this.title = title;
		this.start = start;
		this.end = end;
		this.color = color;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Timestamp getStart() {
		return start;
	}

	public void setStart(Timestamp start) {
		this.start = start;
	}

	public Timestamp getEnd() {
		return end;
	}

	public void setEnd(Timestamp end) {
		this.end = end;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String toString() {
		return "{" + id + "," + title + "," + start + "," + end + "," + color + "}";
	}
}
