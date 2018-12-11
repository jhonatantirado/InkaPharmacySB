package app.salesorderdetall.application.service;

import java.sql.SQLException;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorderdetall.application.assembler.SalesorderdetallCreateAssembler;
import app.salesorderdetall.application.dao.SalesorderdetallDAO;
import app.salesorderdetall.application.dto.SalesorderdetallListDto;

@Service
public class SalesorderdetallService {
	
	 @Autowired
	 SalesorderdetallCreateAssembler salesorderdetallCreateAssembler;
	  
	 @Autowired
	 SalesorderdetallDAO salesorderdetallDAO;
	 
	 @Transactional
	  public List<SalesorderdetallListDto> getIdSales(long idsaleorderdetall) throws SQLException{
	    return salesorderdetallCreateAssembler.toDtoList(salesorderdetallDAO.getid(idsaleorderdetall));  
	    }

}
