package app.salesorder.api.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import app.common.application.ApiResponseHandler;
import app.project.application.dto.ProjectCreateDto;
import app.project.domain.entity.Project;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.application.service.SalesorderService;
import app.salesorderdetall.application.dto.SalesorderdetallListDto;
import app.salesorderdetall.application.service.SalesorderdetallService;

@RestController
@RequestMapping("api/sales")
public class SalesorderController {

	@Autowired
	SalesorderService salesorderService;	
	
	@Autowired
	SalesorderdetallService salesorderdetallService;	
		
	@Autowired
	ApiResponseHandler apiResponseHandler;	
	
	@CrossOrigin(origins = "*")				
	@RequestMapping(method = RequestMethod.GET, value = "/order")
	public ResponseEntity<Object> getidaccount(int page, int size, String DateFrom, String DateTo){
	try {
		
		//	List<SalesorderListDto> listado = salesorderService.getAllOrderSales(page,size, DateFrom, DateTo);
		
		long Dato = 0;	
		List<SalesorderListDto> listado   = new ArrayList();
		listado = salesorderService.getAllOrderSales(page,size, DateFrom, DateTo);
		
	
		for (SalesorderListDto p:listado) {Dato = (p.getCustomer_id());}		
		List<SalesorderdetallListDto> listado2 = salesorderdetallService.getIdSales(Dato);
		

		
		 //return ResponseEntity.ok().body(listado2);
		 
		//  HttpHeaders responseHeaders = new HttpHeaders();
		//listado.set("MyResponseHeader", "MyValue");
		  // return new  ResponseEntity<Object>(listado, HttpStatus.OK);
		
       
      // return ResponseEntity.accepted().body(listado);
        
      return new ResponseEntity<Object>(listado2, HttpStatus.OK);			
	} catch(IllegalArgumentException ex) {
        ex.printStackTrace();
        return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
} catch(Exception ex) {
ex.printStackTrace();
return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
}
}
	
	
	
	
	
}
