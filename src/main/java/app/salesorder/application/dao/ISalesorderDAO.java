package app.salesorder.application.dao;

import java.util.Date;
import java.util.List;
import app.salesorder.domain.entity.Salesorder;
import app.salesorderdetall.domain.entity.Saleorderdetall;

public interface ISalesorderDAO {
	
    public void saveSaveorder(Salesorder salesorder);
    public void saveSaveorderd(Saleorderdetall saleorderdetall,long productid);	
	public List<Salesorder> getallSaveorder(int offset, int limit,String DateFrom, String DateTo);

}
