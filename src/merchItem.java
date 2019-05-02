
public class merchItem {

	String itemName;
	String itemDescription;
	double itemPrice;
	int itemUpcNumber;
	int itemInventory;
	
	merchItem(String name, String description, double price, int upc, int inventory)
	{
		this.itemName=name;
		this.itemDescription = description;
		this.itemPrice=price;
		this.itemUpcNumber=upc;
		this.itemInventory=inventory;
	}
	
	
	public String getItemName()
	{
		return this.itemName;
	}
	
	public String getItemDescription() 
	{
		return this.itemDescription;
	}
	
	public double getItemPrice() 
	{
		return this.itemPrice;
	}
	
	public void setItemPrice(double price) 
	{
		this.itemPrice = price;
	}
	
	public int getUpcNumber() 
	{
		return this.itemUpcNumber;
	}
	
	public void setUpcNumber(int upc) 
	{
		this.itemUpcNumber = upc;
	}
	
	public int getItemInvenetory() 
	{
		return this.itemInventory;
	}
	
	public void setItemInventory(int inventory) 
	{
		this.itemInventory = inventory;
	}
	
	public void stockItemInventory(int addInventory) 
	{
		this.itemInventory+= addInventory;
	}
	
	public void removeItemInventory(int removeInventory) 
	{
		this.itemInventory-= removeInventory;
	}
}
