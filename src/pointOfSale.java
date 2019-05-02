
public class pointOfSale {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("The Main Method as begun!");
		
		shoppingCart shoppingCart = new shoppingCart();
		
		//Load up the inventory
		merchItem cabbage = new merchItem("Cabbage", "green leafy veg", 1.25, 5455, 12);
		merchItem lettuce = new merchItem("Cabbage", "green leafy veg", 1.25, 5455, 12);
		merchItem orange = new merchItem("Cabbage", "green leafy veg", 1.25, 5455, 12);
		merchItem milk = new merchItem("Cabbage", "green leafy veg", 1.25, 5455, 12);
		merchItem soda = new merchItem("Cabbage", "green leafy veg", 1.25, 5455, 12);
		
		shoppingCart.addItem(cabbage);
		shoppingCart.addItem(lettuce);
		shoppingCart.addItem(orange);
		shoppingCart.addItem(milk);
		shoppingCart.addItem(soda);
		
		shoppingCart.printItems();
		
		//This is a change in the file
		
		
		System.out.println("The Main Method has ended.");
		
		
	}

}
