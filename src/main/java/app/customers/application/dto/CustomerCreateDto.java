package app.customers.application.dto;

import java.math.BigDecimal;
import app.common.application.dto.RequestBaseDto;

public class CustomerCreateDto extends RequestBaseDto {
    private long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private Boolean isActive;
    
    private String code;
    private String name;
    private BigDecimal balance;
    private String currency;
    
    private String username;
    private String password;
   
    public CustomerCreateDto() {
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
}
