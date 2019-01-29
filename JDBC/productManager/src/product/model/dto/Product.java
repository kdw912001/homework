package product.model.dto;

import java.io.Serializable;

public class Product implements Serializable{
	private static final long serialVersionUID = -794729022988670651L;
	private String productId;
	private String pname;
	private int price;
	private String description;
	
	public Product() {}

	public Product(String productId, String pname, int price, String description) {
		super();
		this.productId = productId;
		this.pname = pname;
		this.price = price;
		this.description = description;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return productId + ", " + pname + ", " + price + ", " + description;
	}
	
	
	
}
