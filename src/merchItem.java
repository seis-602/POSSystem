
public class merchItem {

	String itemName;
	String itemDescription;
	int itemPriceCents; //item price in cents
	int itemUpcNumber;
	int itemInventory;
	
	merchItem(String name, String description, int price, int upc, int inventory)
	{
		this.itemName=name;
		this.itemDescription = description;
		this.itemPriceCents=price;
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
		return this.itemPriceCents;
	}
	
	public void setItemPrice(int priceCents) 
	{
		this.itemPriceCents= priceCents;
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
