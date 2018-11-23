package app.salesorder.application.validation;

import app.common.application.notification.Notification;
import app.salesorder.application.dto.SalesorderCreateDto;

public class SalesorderCreateValidation {

	public void validate(SalesorderCreateDto salesorderCreateDto) {
        Notification notification = this.validateData(salesorderCreateDto);
        if (notification.hasErrors()) {
            throw new IllegalArgumentException(notification.errorMessage());
        }
}
	
	
	public Notification validateData(SalesorderCreateDto salesorderCreateDto) {
		Notification notification = new Notification();
		if (salesorderCreateDto == null) {
			notification.addError("Missing project parameters");
			return notification;
		}
		if (salesorderCreateDto.getCustomer_id() == 0) {
			notification.addError("Missing id Customer parameter");
		}
        if (salesorderCreateDto.getEmployee_id() == 0) {
			notification.addError("Missing ID Employe parameter");
		}
        if (salesorderCreateDto.getStatus() == 0) {
			notification.addError("Missing Estus parameter");
			return notification;
		}
		if (salesorderCreateDto.getSale_date() == null) {
			notification.addError("Missing Fecha Incorrecta");
			return notification;
		}
		
		return notification;
	}
}
