package app.pucharseorderdetall.application.dao;

import java.util.List;

import app.pucharseorderdetall.domain.entity.Pucharseorderdetall;

public interface IPurchaseorderdetallDAO {

	 public List<Pucharseorderdetall> saveSaveorder(Pucharseorderdetall pucharseorderdetall);	
		public List<Pucharseorderdetall> getid(long idsaleorderdetall);
}
