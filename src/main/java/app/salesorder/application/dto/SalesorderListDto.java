package app.salesorder.application.dto;

import java.math.BigDecimal;
import java.util.Date;

public class SalesorderListDto {

	private long id;
	private Date sale_date;
	private int customer_id;
	private int employee_id;
	private int status;	
		
	public SalesorderListDto() {
		
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getSale_date() {
		return sale_date;
	}
	public void setSale_date(Date sale_date) {
		this.sale_date = sale_date;
	}
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public int getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(int employee_id) {
		this.employee_id = employee_id;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
	
}
