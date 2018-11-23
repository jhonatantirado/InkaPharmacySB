package app.user.application.validation;

import org.springframework.stereotype.Component;
import app.common.application.notification.Notification;
import app.user.application.dto.UserCreateDto;

@Component
public class UserCreateValidation {
	public void validate(UserCreateDto userCreateDto) {
            Notification notification = this.validateData(userCreateDto);
            if (notification.hasErrors()) {
                throw new IllegalArgumentException(notification.errorMessage());
            }
	}
	
	public Notification validateData(UserCreateDto userCreateDto) {
            Notification notification = new Notification();
            if (userCreateDto == null) {
                    notification.addError("Missing customer parameters");
                    return notification;
            }
            if (userCreateDto.getUsername().trim().isEmpty()) {
                    notification.addError("Missing username parameter");
            }
            if (userCreateDto.getPassword().trim().isEmpty()) {
                    notification.addError("Missing password parameter");
                    return notification;
            }
            if (userCreateDto.getRole().trim().isEmpty()) {
                    notification.addError("Missing role parameter");
                    return notification;
            }
            return notification;
	}
}
