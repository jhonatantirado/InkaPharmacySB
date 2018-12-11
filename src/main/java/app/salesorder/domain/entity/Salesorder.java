package app.salesorder.domain.entity;

import java.util.Date;

import app.salesorderdetall.domain.entity.Saleorderdetall;

public class Salesorder {	
	
	private long id;
	private Date sale_date;
	private long customer_id;
	private long employee_id;
	private int status;		
	private Saleorderdetall Salesorderdetall;
		
	public Salesorder() {		
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

	public long getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(long customer_id) {
		this.customer_id = customer_id;
	}

	public long getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Saleorderdetall getSalesorderdetall() {
		return Salesorderdetall;
	}

	public void setSalesorderdetall(Saleorderdetall salesorderdetall) {
		Salesorderdetall = salesorderdetall;
	}
		
}
