package app.project.domain.entity;

import app.common.application.notification.Notification;
import app.common.domain.valueobject.MoneyAbstraction;
import app.customers.domain.entity.Customer;

public class Project {
	private long id;
	private String code;
        private String name;
	private MoneyAbstraction balance;
	private boolean isLocked;
	private Customer customer;
	
	public Project() {
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

        public boolean hasIdentity() {
            return this.id > 0 && !this.code.trim().equals("");
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public MoneyAbstraction getBalance() {
            return this.balance;
        }

        public void setBalance(MoneyAbstraction balance) {
            this.balance = balance;
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
        
        
}
