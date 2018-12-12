package app.pucharseorderdetall.application.service;

import java.sql.SQLException;
import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.pucharseorderdetall.application.assembler.PurchaseorderdetallCreateAssembler;
import app.pucharseorderdetall.application.dao.PurchaseorderdetallDAO;
import app.pucharseorderdetall.application.dto.PurchaseorderdetallListDto;
import app.salesorderdetall.application.dto.SalesorderdetallListDto;


@Service
public class PurchaseorderdetallService {
	
	
	@Autowired
	PurchaseorderdetallDAO purchaseorderdetallDAO;
	@Autowired
	PurchaseorderdetallCreateAssembler purchaseorderdetallCreateAssembler;
	
	 @Transactional
	  public List<PurchaseorderdetallListDto> getIdSales(long idsaleorderdetall) throws SQLException{
	     return purchaseorderdetallCreateAssembler.toDtoList(purchaseorderdetallDAO.getid(idsaleorderdetall));
		 
	    }

}
