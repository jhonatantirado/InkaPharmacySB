package app.salesorder.domain.entity;

import java.util.Date;

import app.customers.domain.entity.Customer;
import app.employee.domain.entity.Employee;

public class Salesorder {	
	
	private long id;
	private Date sale_date;
	private Customer customer;
	private Employee employee;	
	private int status;	
		
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

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	
	
}
