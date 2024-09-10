import java.awt.*;;
import java.util.*;
import javax.swing.*;

public class MenuOrder {

	static Vector <Menu> meal = new Vector<Menu>();
	static Vector <Menu> drink = new Vector<Menu>();
	static Vector <Menu> snack = new Vector<Menu>();
	static Vector <Menu> side = new Vector<Menu>();
	static Vector <Menu> order = new Vector<Menu>();
	static Vector<Integer> price = new Vector<Integer>();
	

	
	static Menu new_Meal(String product_name,int price) {
		
		Menu new_Meal = new Menu(product_name,price);
		MenuOrder.addToMeal(new_Meal);	
		return new_Meal;
	}
	
	static Menu new_Drink(String product_name,int price) {
		
		Menu new_Drink = new Menu(product_name,price);
		MenuOrder.addToDrink(new_Drink);				
		return new_Drink;
		
	}
	
	static Menu new_Snack(String product_name,int price) {
		
		Menu new_Snack = new Menu(product_name,price);
		MenuOrder.addToSnack(new_Snack);				
		return new_Snack;
		
	}
	
	static Menu new_Side(String product_name,int price) {
		
		Menu new_Side = new Menu(product_name,price);
		MenuOrder.addToSide(new_Side);				
		return new_Side;
		
	}
	static Integer new_Price(int price) {
		
		Integer new_Price = new Integer(price);
		MenuOrder.addToPrice(new_Price);	
		return new_Price;
	}
	
	static  String total_Price(int sum) {
		
		sum=0;
		int m;
		for(int i=0; i<MenuOrder.getNumPrices(); i++) {			
			m=MenuOrder.getPrice(i);
			sum = sum+m;
	}
		return "총 가격 :"+sum+"원";
	}
	 // 이미지 크기조절하기!
	 static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
	       Image img = icon.getImage();  
	       Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);  
	       return new ImageIcon(resizedImage);
	}
	
	 
	 
	private static void addToPrice(Integer new_Price) {
		price.add(new_Price);
		
	}

	private static void addToSide(Menu new_Side) {
		
		side.add(new_Side);		
	}

	private static void addToSnack(Menu new_Snack) {
		snack.add(new_Snack);
		
	}

	private static void addToDrink(Menu new_Drink) {
		drink.add(new_Drink);
	}

	
	
	private static void addToMeal(Menu new_Meal) {
		meal.add(new_Meal);
		
	}
	
	static void setupMenu() {
		MenuOrder.new_Meal("제육덮밥", 6500);
		MenuOrder.new_Meal("만두라면", 4500);
		MenuOrder.new_Drink("콜라", 2000);
		MenuOrder.new_Drink("핫식스", 2000);
		MenuOrder.new_Snack("꼬북칩", 2000);
		MenuOrder.new_Snack("포스틱", 2000);
		MenuOrder.new_Side("김치", 0);
		MenuOrder.new_Side("단무지", 0);		
	}
		
	@SuppressWarnings("unused")
	private static void new_Order_HotDrink(Menu new_Meal) {
		order.add(new_Meal);
	}
	

	static String Customer_order() {
		Menu m;
		
		System.out.println("\n--모든 주문 리스트 --");
				
		for(int i=0; i<MenuOrder.getNumOrders(); i++) {
			m=MenuOrder.getOrder(i);
			System.out.print(i+1+". ");
			m.output();		
			
		}
		return null;
	
	}
	static Menu getOrder(int witch) {
			
		return order.elementAt(witch);
	}
	static int getNumOrders() {

		return order.size();
	}
	static int getNumPrices() {
		return price.size();
	}
	static Integer getPrice(int witch) {
		return price.elementAt(witch);
	}
	static Integer get_Price(int witch) {
		return price.get(witch);
	}
	
	public static void main(String[] args) {//Test
	

		Customer_order();
		price.get(1);
	}

	
}

 