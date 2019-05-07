<<<<<<< HEAD
public class Inventory extends Item {
	private double quantity;
	private double reorderThreshold;
	
	public Inventory(int id, String Iname, String Isupplier, double Iprice, double qty, double rth)
	{
		super(id, Iname, Isupplier, Iprice); 
		this.quantity = qty;
		this.reorderThreshold = rth;	
	}
	
	public void addInventory(double value) 
	{
		this.quantity += value;
	}
	
	public void removeInventory(double value) 
	{
		if (value > this.quantity)
		{
			//throw error 'Not enough in stock'
			System.out.println("Not enough in stock");
		}
		else
		{
			this.quantity -= value;
		}
	}
	
	public void checkReorder(int value) {
		if(this.quantity < this.reorderThreshold)
		{
			this.quantity += value;
		}
	}
		
}
=======

public class Inventory extends Item {
	private double quantity;
	private double reorderThreshold;
	
	public Inventory(int id, String Iname, String Isupplier, double Iprice, double qty, double rth)
	{
		super(id, Iname, Isupplier, Iprice); 
		this.quantity = qty;
		this.reorderThreshold = rth;	
	}
	
	public void addInventory(double value) 
	{
		this.quantity += value;
	}
	
	public void removeInventory(double value) 
	{
		if (value > this.quantity)
		{
			//throw error 'Not enough in stock'
			System.out.println("Not enough in stock");
		}
		else
		{
			this.quantity -= value;
		}
	}
	
	public void checkReorder(int value) {
		if(this.quantity < this.reorderThreshold)
		{
			this.quantity += value;
		}
	}
		
}
>>>>>>> 0c02d4e1c1b2fbb0216977e6985e50526f510675
