package org.hedspi.coffeeshop.test;
import org.hedspi.coffeeshop.domain.model.Coffee;

public class Main {
	String val;
	Laptop laptop = Laptop.HP;
	
	static enum Laptop{
		HP;
		public String toString(){
			return "hp";
		}
	}
	public static void main(String[] args) {
		Main obj = new Main();
		obj.val = "aaaaaaa";
		

	}
	public static void change(Coffee coffee){
		coffee.setId(1111);
		coffee.setName("loli");
	}

}
