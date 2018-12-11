package app.purchaseorder.application.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.pucharseorderdetall.application.dto.PurchaseorderdetallListDto;
import app.pucharseorderdetall.application.service.PurchaseorderdetallService;
import app.purchaseorder.application.assembler.PurchaseCreateAssembler;
import app.purchaseorder.application.dao.PurchaseorderDAO;
import app.purchaseorder.application.dto.PurchaseListDto;
import app.purchaseorder.domain.entity.Purchaseorder;
import app.salesorder.application.assembler.SalesorderCreateAssembler;
import app.salesorder.application.dao.SalesorderDAO;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.domain.entity.Salesorder;
import app.salesorderdetall.application.dao.SalesorderdetallDAO;
import app.salesorderdetall.application.dto.SalesorderdetallListDto;
import app.salesorderdetall.application.service.SalesorderdetallService;
import app.salesorderdetall.domain.entity.Saleorderdetall;

@Service
public class PurchaseorderService {
	
	
	@Autowired
	PurchaseCreateAssembler purchaseCreateAssembler;
	  
	   @Autowired
	   PurchaseorderDAO purchaseorderDAO;
	
	   	    
		@Autowired
		PurchaseorderService purchaseorderService;
		
		@Autowired
		PurchaseorderdetallService purchaseorderdetallService;
		
	
	 @Transactional
		public List<PurchaseListDto> getAllPurchaseorder(int page, int size, String DateFrom, String DateTo) throws SQLException{
			  List<Purchaseorder> listado = purchaseorderDAO.getallSavepurchase(page,size, DateFrom, DateTo);		 
			  List<Purchaseorder> salesorderListDto2 = new ArrayList<Purchaseorder>();		  
			  for (Purchaseorder p:listado) {	
				  Purchaseorder salesorderListDto1 = new Purchaseorder();
				  salesorderListDto1.setId(p.getId());
				  salesorderListDto1.setPurchase_date(p.getPurchase_date());			
				  salesorderListDto1.setEmployee_id(p.getEmployee_id());
				  salesorderListDto1.setProvider_id(p.getProvider_id());
				  
				 
				 // List<PurchaseorderdetallListDto> listado2 = purchaseorderdetallService.getIdSales(salesorderListDto1.getId());			 
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

}
