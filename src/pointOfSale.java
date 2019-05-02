
public class pointOfSale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("The Main Method as begun!");
		
		shoppingCart shoppingCart = new shoppingCart();
		
		//Load up the inventory
		merchItem cabbage = new merchItem("Cabbage", "green leafy veg", 125, 5455, 12);
		merchItem lettuce = new merchItem("Cabbage", "green leafy veg", 125, 5455, 12);
		merchItem orange = new merchItem("Cabbage", "green leafy veg", 125, 5455, 12);
		merchItem milk = new merchItem("Cabbage", "green leafy veg", 125, 5455, 12);
		merchItem soda = new merchItem("Cabbage", "green leafy veg", 125, 5455, 12);
		
		shoppingCart.addItem(cabbage, 12);
		shoppingCart.addItem(lettuce, 3);
		shoppingCart.addItem(orange, 1);
		shoppingCart.addItem(milk, 25);
		shoppingCart.addItem(soda, 2);
		
		shoppingCart.printItems();
		
		//This is a change in the file
		
		
		System.out.println("The Main Method has ended.");
		
		
	}

}
