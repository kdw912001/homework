package ncs4.test2;
import java.io.*;
public class Fruit implements Serializable{
	private static final long serialVersionUID = -7629809088439217198L;
	
	private String name;
	private int price;
	private int quantity;
	
	public Fruit() {}

	public Fruit(String name, int price, int quantity) {
		this.name = name;
		this.price = price;
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String toString() {
		return this.name+", "+this.price+"원, "+this.quantity+"개";
	}
	
	
	
}
