
public class Item {
protected int ItemNumber;
protected String name;
protected String supplier;
protected double price;

	public Item(int id, String Iname, String Isupplier, double Iprice) 
	{
		this.ItemNumber = id;
		this.name = Iname;
		this.supplier = Isupplier;
		this.price = Iprice;
	}
	public void SetName(String name)
	{
	this.name=name;
	}
	public String GetName()
	{
		return this.name;
	}
	public void SetItemNumber(int value)
	{
		this.ItemNumber =value;
	}
	public int GetItemNumber() {
		return this.ItemNumber;
	}
	
	public void SetSupplier(String supp)
	{
		this.supplier = supp;
	}
	
	public String GetSupplier()
	{
		return this.supplier;
	}
	
	public void SetPrice(double value)
	{
		this.price = value;
	}
	
	public double GetPrice()
	{
		return this.price;
	}
	
}