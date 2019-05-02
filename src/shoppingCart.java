import java.util.ArrayList;

public class shoppingCart {

	private ArrayList<merchItem> Order = new ArrayList<merchItem>();

	public void addItem(merchItem product) 
	{
		Order.add(product);
	}	
	
	
	public void printItems()
	{
		System.out.println(Order.toString());
	}
	
}
