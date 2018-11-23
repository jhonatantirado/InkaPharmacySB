package app.user.domain.entity;

import app.common.application.notification.Notification;
import app.common.domain.valueobject.MoneyAbstraction;
import app.customers.domain.entity.Customer;

public class User {
	private long id;
	private String username;
	private String password;
	private String role;
        private boolean isLocked;
	private Customer customer;
	
	public User() {
        }

        public void lock() {
            if (!this.isLocked) {
                this.isLocked = true;
            }
        }

        public void unLock() {
            if (this.isLocked) {
                this.isLocked = false;
            }
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public boolean getIsLocked() {
            return isLocked;
        }

        public void setIsLocked(boolean isLocked) {
            this.isLocked = isLocked;
        }

        public Customer getCustomer() {
            return customer;
        }

        public void setCustomer(Customer customer) {
            this.customer = customer;
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

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }
        
        
}
