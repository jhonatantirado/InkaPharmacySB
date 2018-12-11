package app.purchaseorder.api.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import app.common.application.ApiResponseHandler;
import app.common.application.Notification;
import app.common.application.UnitOfWork;
import app.common.application.enumeration.RequestBodyType;
import app.purchaseorder.application.dto.PurchaseCreateDto;
import app.purchaseorder.application.dto.PurchaseListDto;
import app.purchaseorder.application.service.PurchaseorderService;

@RestController
@RequestMapping("api/Purchase")
public class PurchaseController {

	@Autowired
	UnitOfWork unitOfWork;

	@Autowired
	PurchaseorderService purchaseorderService;

	@Autowired
	ApiResponseHandler apiResponseHandler;

	@CrossOrigin(origins = "*")
	@RequestMapping(method = RequestMethod.GET, value = "/order")
	public ResponseEntity<Object> getidaccount(int page, int size, String DateFrom, String DateTo) {
		try {
			List<PurchaseListDto> listado = purchaseorderService.getAllpurchaseorder(page, size, DateFrom, DateTo);
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
	public ResponseEntity<Object> Create(@RequestBody PurchaseCreateDto purchaseCreateDto) throws Exception {
		boolean status = false;
		try {
			Notification notification = this.validation(purchaseCreateDto);
			if (notification.hasErrors()) {
				throw new IllegalArgumentException(notification.errorMessage());
			}
			return new ResponseEntity<Object>(purchaseorderService.salesordercreate(purchaseCreateDto),
					HttpStatus.CREATED);
		} catch (IllegalArgumentException ex) {
			unitOfWork.rollback(status);
			return new ResponseEntity<Object>(apiResponseHandler.getApplicationError(ex.getMessage()),
					HttpStatus.BAD_REQUEST);
		} catch (Exception ex) {
			unitOfWork.rollback(status);
			return new ResponseEntity<Object>(apiResponseHandler.getApplicationException(),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private Notification validation(PurchaseCreateDto purchaseCreateDto) {
		Notification notification = new Notification();
		if (purchaseCreateDto == null || purchaseCreateDto.getRequestBodyType() == RequestBodyType.INVALID) {
			notification.addError("Invalid JSON data in request body.");
		}
		return notification;
	}

}
