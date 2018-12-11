package app.salesorder.application.service;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;
import javax.transaction.Transactional;

import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import app.salesorder.application.assembler.SalesorderCreateAssembler;
import app.salesorder.application.dao.SalesorderDAO;
import app.salesorder.application.dto.SalesorderCreateDto;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.domain.entity.Salesorder;
import app.salesorder.infrastructure.persistence.hibernate.SalesorderHibernateRepository;
import app.salesorderdetall.application.dao.SalesorderdetallDAO;
import app.salesorderdetall.application.dto.SalesorderdetallListDto;
import app.salesorderdetall.application.service.SalesorderdetallService;
import app.salesorderdetall.domain.entity.Saleorderdetall;

@Service
public class SalesorderService {
	
	  @Autowired
	  SalesorderCreateAssembler salesorderCreateAssembler;
	  
	    @Autowired
		SalesorderDAO salesorderDAO;
	    
	    @Autowired
	    SalesorderdetallDAO salesorderdetallDAO;
	    
		
		@Autowired
		SalesorderdetallService salesorderdetallService;
		

	    
	@Transactional
	public ResponseEntity<Object> salesordercreate(SalesorderCreateDto salesorderCreateDto) throws Exception {		
			
			Salesorder salesorder = new Salesorder();         
			salesorder.setSale_date(FechaHora());
			salesorder.setCustomer_id(salesorderCreateDto.getCustomer_id());
			salesorder.setEmployee_id(salesorderCreateDto.getEmployee_id());
			salesorder.setStatus(salesorderCreateDto.getStatus());
			
			Saleorderdetall saleorderdetall = new Saleorderdetall();
			saleorderdetall.setSale_order_id(salesorderCreateDto.getSalesorderdetall().getSale_order_id());
			saleorderdetall.setProduct_id(salesorderCreateDto.getSalesorderdetall().getProduct_id());
			saleorderdetall.setQuantity(salesorderCreateDto.getSalesorderdetall().getQuantity());
			saleorderdetall.setPrice(salesorderCreateDto.getSalesorderdetall().getPrice());
			saleorderdetall.setCurrency(salesorderCreateDto.getSalesorderdetall().getCurrency());
			saleorderdetall.setStatus(salesorderCreateDto.getSalesorderdetall().getStatus());
			salesorder.setSalesorderdetall(saleorderdetall);		
			salesorderDAO.saveSaveorder(salesorder);
			salesorderDAO.saveSaveorderd(saleorderdetall,saleorderdetall.getSale_order_id());
			return ResponseEntity.ok().body("ok");				
		}	
		
   @Transactional
	public List<SalesorderListDto> getAllOrderSales(int page, int size, String DateFrom, String DateTo) throws SQLException{
		  List<Salesorder> listado = salesorderDAO.getallSaveorder(page,size, DateFrom, DateTo);		 
		  List<Salesorder> salesorderListDto2 = new ArrayList<Salesorder>();		  
		  for (Salesorder p:listado) {	
			  Salesorder salesorderListDto1 = new Salesorder();
			  salesorderListDto1.setId(p.getId());
			  salesorderListDto1.setSale_date(p.getSale_date());
			  System.out.println("	FECHAA  " + salesorderListDto1.getSale_date());
			  salesorderListDto1.setCustomer_id(p.getCustomer_id());
			  salesorderListDto1.setEmployee_id(p.getEmployee_id());
			  salesorderListDto1.setStatus(p.getStatus());	
			  List<SalesorderdetallListDto> listado2 = salesorderdetallService.getIdSales(salesorderListDto1.getId());			 
			  for (SalesorderdetallListDto q:listado2) {
				  Saleorderdetall salesorderdetall = new Saleorderdetall();
				  salesorderdetall.setId(q.getId());	
				  salesorderdetall.setSale_order_id(q.getSale_order_id());
				  salesorderdetall.setProduct_id(q.getProduct_id());
				  salesorderdetall.setQuantity(q.getQuantity());
				  salesorderdetall.setPrice(q.getPrice());
				  salesorderdetall.setCurrency(q.getCurrency());
				  salesorderdetall.setStatus(q.getStatus());				
				  salesorderListDto1.setSalesorderdetall(salesorderdetall);
			  }					 
			  salesorderListDto2.add(salesorderListDto1);
		  } 		  
		  List<SalesorderListDto> listadoS = salesorderCreateAssembler.toDtoList(salesorderListDto2);		 
		  return listadoS;
	  }
   
   private Date FechaHora(){		
		Date utilDate = new Date(); 
		long lnMilisegundos = utilDate.getTime();
		Timestamp sqlTimestamp = new Timestamp(lnMilisegundos);			
		return sqlTimestamp;
	}
   
  
}
