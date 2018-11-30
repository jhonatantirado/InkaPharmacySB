package app.employee.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.common.application.ApiResponseHandler;
import app.employee.aplication.dto.EmployeeListDto;
import app.employee.aplication.service.EmployeeService;


@RestController
@RequestMapping("api/employee")
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	ApiResponseHandler apiResponseHandler;
	
	
	@RequestMapping(method = RequestMethod.GET, path = "/all")
	public ResponseEntity<Object> getAllemployee() throws Exception {
            try {
                List<EmployeeListDto> listado = employeeService.getAll();
                return new ResponseEntity<Object>(listado, HttpStatus.OK);
            } catch(IllegalArgumentException ex) {
                    ex.printStackTrace();
                    return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()), HttpStatus.BAD_REQUEST);
            } catch(Exception ex) {
		ex.printStackTrace();
		return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

}
