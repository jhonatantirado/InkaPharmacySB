package app.customers.application.dto;

import app.customers.application.deserializer.CustomerCreateDeserializer;
import app.customers.domain.entity.*;
import app.project.domain.entity.Project;
import app.user.domain.entity.User;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.util.Set;

@JsonDeserialize(using = CustomerCreateDeserializer.class)
public class CustomerDto {
    private long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private Boolean isActive;
 
    
    public CustomerDto() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getFullName() {
        return String.format("%s, %s", this.lastName, this.firstName);
    }
    
    public String getIdentityDocument() {
            return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
            this.identityDocument = identityDocument;
    }

    public Boolean getIsActive() {
            return isActive;
    }

    public void setIsActive(Boolean isActive) {
            this.isActive = isActive;
    }

    
}
