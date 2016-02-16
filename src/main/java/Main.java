
public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1; i< 20; i++){
			System.out.println(randomColor(i));
		}
	}
	
	public static String randomColor(int id){
		String color="#";
		int i;
		for (i=1; i<7; i++){
			switch ((int)Math.pow(id, i) % 16) {  
			case 0:
				color += "0";
				break;
			case 1:
				color += "1";
				break;
			case 2:
				color += "2";
				break;
			case 3:
				color += "3";
				break;
			case 4:
				color += "4";
				break;
			case 5:
				color += "5";
				break;
			case 6:
				color += "6";
				break;
			case 7:
				color += "7";
				break;
			case 8:
				color += "8";
				break;
			case 9:
				color += "9";
				break;
			case 10:
				color += "a";
				break;
			case 11:
				color += "b";
				break;
			case 12:
				color += "c";
				break;
			case 13:
				color += "d";
				break;
			case 14:
				color += "e";
				break;
			case 15:
				color += "f";
				break;
			}
		}
		
		return color;
	}
}
