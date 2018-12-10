package app.salesorder.api.controller;

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
import app.common.application.ApiResponseHandler;
import app.project.application.dto.ProjectCreateDto;
import app.project.domain.entity.Project;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.application.service.SalesorderService;

@RestController
@RequestMapping("api/sales")
public class SalesorderController {

	@Autowired
	SalesorderService salesorderService;	
	
	@Autowired
	ApiResponseHandler apiResponseHandler;
	
	
	@CrossOrigin(origins = "*")				
	@RequestMapping(method = RequestMethod.GET, value = "/order")
	public ResponseEntity<Object> getidaccount(int page, int size, String DateFrom, String DateTo){
	try {		
		List<SalesorderListDto> listado = salesorderService.getAllOrderSales(page,size, DateFrom, DateTo);
        System.out.println("SalesorderController - /Order"  + listado);
        return new ResponseEntity<Object>(listado, HttpStatus.OK);			
	} catch(IllegalArgumentException ex) {
        ex.printStackTrace();
        return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
} catch(Exception ex) {
ex.printStackTrace();
return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
}
}
	
	@Transactional(rollbackFor=Exception.class, isolation = Isolation.READ_COMMITTED)
	@RequestMapping(
	    method = RequestMethod.GET,
	    path = "create/{projectId}",
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<Object> create(@PathVariable("/order") long projectId, @RequestBody ProjectCreateDto projectCreateDto) throws Exception {
            try {
            	//   projectCreateDto.setId(projectId);
        	 //  projectCreateValidation.validate(projectCreateDto);
             
        	 //  ProjectCreateDto projectResponse = projectCreateAssembler.toDto(project);
                
               // return new ResponseEntity<Object>(projectResponse, HttpStatus.CREATED);
            } catch(IllegalArgumentException ex) {
        	ex.printStackTrace();
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
	
	
	
}
