import java.util.ArrayList;

public class shoppingCart {

	private ArrayList<OrderItem> Order = new ArrayList<OrderItem>();

	
	//This doesn't really work, it doesn't really account for the quantity
	public void addItem(merchItem product, int quantity) 
	{
		OrderItem shop = new OrderItem(product, quantity);
		
		Order.add(shop);
	}	
	
	public void removeItem(merchItem product)
	{
		Order.remove(product);
	
	}
	
	public void printItems()
	{
		System.out.println(Order.toString());
	}
	
}
