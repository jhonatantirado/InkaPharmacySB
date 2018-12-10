package app.salesorder.application.service;

import java.sql.SQLException;
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
	  public List<SalesorderListDto> getAllOrderSales(int page, int size) throws SQLException{
		 // System.out.println("AQIIII SalesorderService getAll "  + salesorderCreateAssembler.toDtoList(salesorderHibernateRepository.getAll()));
	        return salesorderCreateAssembler.toDtoList(salesorderDAO.getallSaveorder(page,size));
	        
	    }
	
	  
	 
}
