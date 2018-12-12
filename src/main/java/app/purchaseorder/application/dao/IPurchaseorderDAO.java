package app.purchaseorder.application.dao;

import java.util.List;

import app.pucharseorderdetall.domain.entity.Pucharseorderdetall;
import app.purchaseorder.domain.entity.Purchaseorder;


public interface IPurchaseorderDAO {	
	public void saveSavepurchase(Purchaseorder purchaseorder);
    public void saveSavepurchase(Pucharseorderdetall pucharseorderdetall,long productid);	
	public List<Purchaseorder> getallSavepurchase(int page, int size,String DateFrom, String DateTo);
}
