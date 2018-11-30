package app.salesorder.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.common.application.ApiResponseHandler;
import app.project.application.dto.ProjectListDto;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.application.service.SalesorderService;

@RestController
@RequestMapping("api/sales")
public class SalesorderController {

	@Autowired
	SalesorderService salesorderService;
	
	
	@Autowired
	ApiResponseHandler apiResponseHandler;
	
	@RequestMapping(method = RequestMethod.GET, path = "/order")
	public ResponseEntity<Object> getAllOrderSales() throws Exception {
            try {
                List<SalesorderListDto> listado = salesorderService.getAll();
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
	
	
	
}
