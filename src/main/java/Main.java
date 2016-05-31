import org.hedspi.coffeeshop.model.Coffee;

public class Main {

	public static void main(String[] args) {
		Coffee coffee = new Coffee(123);
		coffee.setName("capu");
		change(coffee);
		System.out.println(coffee.getId() + coffee.getName());

	}
	public static void change(Coffee coffee){
		coffee.setId(1111);
		coffee.setName("loli");
	}

}
