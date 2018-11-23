package app.customers.domain.repository;

import java.sql.SQLException;
import app.customers.domain.entity.Customer;
import java.util.List;

public interface CustomerRepository {
	public void create(Customer customer) throws SQLException;
	public void delete(Customer customer) throws SQLException;
	public Customer read(long id) throws SQLException;
        public List<Customer> getAll() throws SQLException;
        public Customer findByName(String name) throws SQLException;
}
