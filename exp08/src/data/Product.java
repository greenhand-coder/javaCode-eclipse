package data;

import java.util.Comparator;
import java.util.Date;

public class Product implements Comparable<Product>,Cloneable, java.io.Serializable {
	private String productOrder;
	private String productName;
	private double productPrice;
	private int productAmount;
	private Date productOnLayDate;
	
	Date date = new Date();
	
	public Product(){
		this.productOrder = null;
		this.productName = null;
		this.productPrice = 0;
		this.productAmount = 0;
		this.productOnLayDate = date; //?
	}
	
	public Product(String productOrder, String productName, double productPrice, 
			int productAmount,Date productOnLayDate) {
		this.productOrder = productOrder;
		this.productName = productName;
		this.productPrice = productPrice;
		this.productAmount = productAmount;
		this.productOnLayDate = productOnLayDate;
	}

	public String getProductOrder() {
		return productOrder;
	}

	public void setProductOrder(String productOrder) {
		this.productOrder = productOrder;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductAmount() {
		return productAmount;
	}

	public void setProductAmount(int productAmount) {
		this.productAmount = productAmount;
	}

	public Date getProductOnLayDate() {
		return productOnLayDate;
	}

	public void setProductOnLayDate(Date productOnLayDate) {
		this.productOnLayDate = productOnLayDate;
	}

	@Override
	public int compareTo(Product other) {
		// TODO Auto-generated method stub
		return this.productOrder.compareTo(other.productOrder);
	}
	
	@Override
	public Object clone() {
		try {
			Product other = (Product)super.clone();
			other.productOnLayDate = (Date)this.productOnLayDate.clone();
			return other;
		}catch(CloneNotSupportedException e) {
			return null;
		}
	}
	

}
