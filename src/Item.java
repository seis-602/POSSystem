
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
	public void setName(String name)
	{
	this.name=name;
	}
	public String getName()
	{
		return this.name;
	}
	public void getItemNumber(int value)
	{
		this.ItemNumber =value;
	}
	public int getItemNumber() {
		return this.ItemNumber;
	}
	
	public void getSupplier(String supp)
	{
		this.supplier = supp;
	}
	
	public String getSupplier()
	{
		return this.supplier;
	}
	
	public void setPrice(double value)
	{
		this.price = value;
	}
	
	public double getPrice()
	{
		return this.price;
	}
	
}
