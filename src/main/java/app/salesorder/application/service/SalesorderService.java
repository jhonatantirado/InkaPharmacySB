package app.salesorder.application.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.salesorder.application.assembler.SalesorderCreateAssembler;
import app.salesorder.application.dao.SalesorderDAO;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.infrastructure.persistence.hibernate.SalesorderHibernateRepository;

@Service
public class SalesorderService {
	
	  @Autowired
	  SalesorderCreateAssembler salesorderCreateAssembler;
	  
	    @Autowired
		SalesorderDAO salesorderDAO;
	    
	  @Autowired
	  SalesorderHibernateRepository salesorderHibernateRepository;
	  
	 
	  
	  @Transactional
	  public List<SalesorderListDto> getAllOrderSales(int page, int size, String DateFrom, String DateTo) throws SQLException{
	    return salesorderCreateAssembler.toDtoList(salesorderDAO.getallSaveorder(page,size, DateFrom, DateTo));  
	    }
}
