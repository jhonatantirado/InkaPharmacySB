package app.salesorder.application.dao;

import java.util.List;
import app.salesorder.domain.entity.Salesorder;

public interface ISalesorderDAO {
	
    public List<Salesorder> saveSaveorder(Salesorder salesorder);	
	public List<Salesorder> getallSaveorder(int offset, int limit);

}
