package app.customers.domain.entity;

import app.project.domain.entity.Project;
import app.user.domain.entity.User;
import java.util.Set;


public class Customer {
    private long id;
    private String firstName;
    private String lastName;
    private String identityDocument;
    private Boolean isActive;
    private Set<Project> projects;
    private Set<User> users;
    
    public Customer() {
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

    public Set<Project> getProjects() {
        return projects;
    }

    public void setProjects(Set<Project> projects) {
        this.projects = projects;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

}
