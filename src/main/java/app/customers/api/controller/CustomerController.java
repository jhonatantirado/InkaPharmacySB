package app.customers.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.common.application.ApiResponseHandler;
import app.common.application.Notification;
import app.common.application.UnitOfWork;
import app.common.application.enumeration.RequestBodyType;
import app.common.domain.sender.Sender;
import app.customers.application.assembler.CustomerCreateAssembler;
import app.customers.application.dto.CustomerCreateDto;
import app.customers.application.dto.CustomerDto;
import app.customers.application.validation.CustomerCreateValidation;
import app.customers.domain.entity.Customer;
import app.customers.domain.repository.CustomerRepository;


@RestController
@RequestMapping("api/customers")
public class CustomerController {
	@Autowired
	UnitOfWork unitOfWork;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	CustomerCreateValidation customerCreateValidation;
	
	@Autowired
	CustomerCreateAssembler customerCreateAssembler;
	
	@Autowired
	ApiResponseHandler apiResponseHandler;
        
        @Autowired
	Sender sender;
        
	@RequestMapping(
	    method = RequestMethod.POST,
	    path = "",
	    consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, 
	    produces = MediaType.APPLICATION_JSON_UTF8_VALUE
	)
	public ResponseEntity<Object> Create(@RequestBody CustomerCreateDto customerCreateDto) throws Exception {
            boolean status = false;
            try {    
            	
            	Notification notification = this.validation(customerCreateDto);
                if (notification.hasErrors()) {
                    throw new IllegalArgumentException(notification.errorMessage());
                }
                status = unitOfWork.beginTransaction();                 
                Customer customer = customerCreateAssembler.toEntity(customerCreateDto);                
                customerRepository.create(customer);
                unitOfWork.commit(status);                
                CustomerDto customerResponse = customerCreateAssembler.toDto(customer);                
                return new ResponseEntity<Object>(customerResponse, HttpStatus.CREATED);
            } catch(IllegalArgumentException ex) {
        	unitOfWork.rollback(status);
        	return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
        	unitOfWork.rollback(status);
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
	
	
	private Notification validation(CustomerCreateDto customerCreateDto) {
		Notification notification = new Notification();
		if (customerCreateDto == null || customerCreateDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}
}
