package app.project.application.validation;

import org.springframework.stereotype.Component;
import app.common.application.notification.Notification;
import app.project.application.dto.ProjectCreateDto;

@Component
public class ProjectCreateValidation {
	public void validate(ProjectCreateDto projectCreateDto) {
            Notification notification = this.validateData(projectCreateDto);
            if (notification.hasErrors()) {
                throw new IllegalArgumentException(notification.errorMessage());
            }
	}
	
	public Notification validateData(ProjectCreateDto projectCreateDto) {
		Notification notification = new Notification();
		if (projectCreateDto == null) {
			notification.addError("Missing project parameters");
			return notification;
		}
		if (projectCreateDto.getCode().trim().isEmpty()) {
			notification.addError("Missing Number parameter");
		}
                if (projectCreateDto.getName().trim().isEmpty()) {
			notification.addError("Missing name parameter");
		}
                if (projectCreateDto.getCurrency() == null) {
			notification.addError("Missing currency parameter");
			return notification;
		}
		if (projectCreateDto.getBalance() == null) {
			notification.addError("Missing Balance parameter");
			return notification;
		}
		if (projectCreateDto.getBalance().doubleValue() <= 0.0) {
			notification.addError("Balance must be grater than zero");
		}
		return notification;
	}
}
