package app.purchaseorder.application.dto;

import java.util.Date;

import app.pucharseorderdetall.domain.entity.Pucharseorderdetall;

public class PurchaseListDto  {
	private long id;
	private Date purchase_date; 
	private long provider_id; 
	private long employee_id;
    private Pucharseorderdetall pucharseorderdetall;
    
   public PurchaseListDto() {
		
	}
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getPurchase_date() {
		return purchase_date;
	}
	public void setPurchase_date(Date purchase_date) {
		this.purchase_date = purchase_date;
	}
	public long getProvider_id() {
		return provider_id;
	}
	public void setProvider_id(long provider_id) {
		this.provider_id = provider_id;
	}
	public long getEmployee_id() {
		return employee_id;
	}
	public void setEmployee_id(long employee_id) {
		this.employee_id = employee_id;
	}
	public Pucharseorderdetall getPucharseorderdetall() {
		return pucharseorderdetall;
	}
	public void setPucharseorderdetall(Pucharseorderdetall pucharseorderdetall) {
		this.pucharseorderdetall = pucharseorderdetall;
	}
    
}
