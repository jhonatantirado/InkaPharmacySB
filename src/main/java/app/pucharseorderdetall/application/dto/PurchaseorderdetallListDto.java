package app.pucharseorderdetall.application.dto;

public class PurchaseorderdetallListDto {

	private long id;
	private long sale_order_id; 
	private long product_id; 
	private int quantity;
	private int price;
	private String currency; 
	private int status;
	
	
	
public PurchaseorderdetallListDto() {
		
}


public long getId() {
	return id;
}


public void setId(long id) {
	this.id = id;
}


public long getSale_order_id() {
	return sale_order_id;
}


public void setSale_order_id(long sale_order_id) {
	this.sale_order_id = sale_order_id;
}


public long getProduct_id() {
	return product_id;
}


public void setProduct_id(long product_id) {
	this.product_id = product_id;
}


public int getQuantity() {
	return quantity;
}


public void setQuantity(int quantity) {
	this.quantity = quantity;
}


public int getPrice() {
	return price;
}


public void setPrice(int price) {
	this.price = price;
}


public String getCurrency() {
	return currency;
}


public void setCurrency(String currency) {
	this.currency = currency;
}


public int getStatus() {
	return status;
}


public void setStatus(int status) {
	this.status = status;
}



}
