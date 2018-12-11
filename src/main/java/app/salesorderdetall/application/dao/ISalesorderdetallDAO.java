package app.salesorderdetall.application.dao;

import java.util.List;
import app.salesorderdetall.domain.entity.Saleorderdetall;

public interface ISalesorderdetallDAO {
	
	 public List<Saleorderdetall> saveSaveorder(Saleorderdetall salesorderdetall);	
	public List<Saleorderdetall> getid(long idsaleorderdetall);

}
