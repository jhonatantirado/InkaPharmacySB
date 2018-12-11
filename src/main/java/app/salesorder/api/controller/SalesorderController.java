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
import app.common.application.Notification;
import app.common.application.UnitOfWork;
import app.common.application.enumeration.RequestBodyType;
import app.customers.application.assembler.CustomerCreateAssembler;
import app.customers.application.dto.CustomerCreateDto;
import app.customers.application.dto.CustomerDto;
import app.customers.domain.entity.Customer;
import app.customers.domain.repository.CustomerRepository;
import app.project.application.dto.ProjectCreateDto;
import app.project.domain.entity.Project;
import app.salesorder.application.assembler.SalesorderCreateAssembler;
import app.salesorder.application.dao.ISalesorderDAO;
import app.salesorder.application.dto.SalesorderCreateDto;
import app.salesorder.application.dto.SalesorderDto;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.application.service.SalesorderService;
import app.salesorder.domain.entity.Salesorder;
import app.salesorderdetall.application.dto.SalesorderdetallListDto;
import app.salesorderdetall.application.service.SalesorderdetallService;

@RestController
@RequestMapping("api/sales")
public class SalesorderController {

	@Autowired
	UnitOfWork unitOfWork;
	
	@Autowired
	SalesorderService salesorderService;

	@Autowired
	SalesorderCreateAssembler salesorderCreateAssembler;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	ISalesorderDAO iSalesorderDAO;
	
	@Autowired
	ApiResponseHandler apiResponseHandler;

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/order")
	public ResponseEntity<Object> getidaccount(int page, int size, String DateFrom, String DateTo) {
		try {
			List<SalesorderListDto> listado = salesorderService.getAllOrderSales(page, size, DateFrom, DateTo);

			return new ResponseEntity<Object>(listado, HttpStatus.OK);
		} catch (IllegalArgumentException ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	 @CrossOrigin(origins = "*")
		@RequestMapping(method = RequestMethod.POST, path = "/order", consumes = "application/json; charset=UTF-8", produces = "application/json; charset=UTF-8")
		public ResponseEntity<Object> Create(@RequestBody SalesorderCreateDto salesorderCreateDto) throws Exception {
	            boolean status = false;
	            try {  
	            	Notification notification = this.validation(salesorderCreateDto);
	                if (notification.hasErrors()) {
	                    throw new IllegalArgumentException(notification.errorMessage());
	                }
	                
	              //  status = unitOfWork.beginTransaction();
	                return salesorderService.salesordercreate(salesorderCreateDto);
	                
	                //Salesorder salesorder = salesorderCreateAssembler.toEntity(salesorderCreateDto);                
	                //iSalesorderDAO.saveSaveorder(salesorder);
	                //unitOfWork.commit(status);                
	                //SalesorderCreateDto salesorderResponse = salesorderCreateAssembler.toDto(salesorder);                
	               // return new ResponseEntity<Object>(salesorderResponse, HttpStatus.CREATED);
	            } catch(IllegalArgumentException ex) {
	        	unitOfWork.rollback(status);
	        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
	            } catch(Exception ex) {
	        	unitOfWork.rollback(status);
			return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
	            }
	        }
	
	
	private Notification validation(SalesorderCreateDto salesorderCreateDto) {
		Notification notification = new Notification();
		if (salesorderCreateDto == null || salesorderCreateDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
	
	
}
