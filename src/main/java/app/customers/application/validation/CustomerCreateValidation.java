package app.customers.application.validation;

import org.springframework.stereotype.Component;
import app.common.application.notification.Notification;
import app.customers.application.dto.CustomerCreateDto;

@Component
public class CustomerCreateValidation {
	public void validate(CustomerCreateDto customerCreateDto) {
            Notification notification = this.validateData(customerCreateDto);
            if (notification.hasErrors()) {
                throw new IllegalArgumentException(notification.errorMessage());
            }
	}
	
	public Notification validateData(CustomerCreateDto customerCreateDto) {
            Notification notification = new Notification();
            if (customerCreateDto == null) {
                    notification.addError("Missing customer parameters");
                    return notification;
            }
            if (customerCreateDto.getFirstName().trim().isEmpty()) {
                    notification.addError("Missing firstname parameter");
            }
            if (customerCreateDto.getLastName().trim().isEmpty()) {
                    notification.addError("Missing lastname parameter");
                    return notification;
            }
            if (customerCreateDto.getIdentityDocument().trim().isEmpty()) {
                    notification.addError("Missing identity document parameter");
                    return notification;
            }
            return notification;
	}
}
