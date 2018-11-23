package app.salesorder.application.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.salesorder.application.assembler.SalesorderCreateAssembler;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.infrastructure.persistence.hibernate.SalesorderHibernateRepository;

@Service
public class SalesorderService {
	
	  @Autowired
	  SalesorderCreateAssembler salesorderCreateAssembler;
	  
	    
	  @Autowired
	  SalesorderHibernateRepository salesorderHibernateRepository;
	  
	  public List<SalesorderListDto> getAll() throws SQLException{
	        return salesorderCreateAssembler.toDtoList(salesorderHibernateRepository.getAll());
	    }
	

}
