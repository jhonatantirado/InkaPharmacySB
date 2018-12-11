package app.purchaseorder.application.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import app.pucharseorderdetall.domain.entity.Pucharseorderdetall;
import app.purchaseorder.domain.entity.Purchaseorder;

@Service
public class PurchaseorderDAO implements IPurchaseorderDAO{

	@Override
	public void saveSavepurchase(Purchaseorder purchaseorder) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveSavepurchase(Pucharseorderdetall pucharseorderdetall, long productid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Purchaseorder> getallSavepurchase(int offset, int limit, String DateFrom, String DateTo) {
		// TODO Auto-generated method stub
		return null;
	}

}
