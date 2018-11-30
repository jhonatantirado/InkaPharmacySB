package app.employee.domain.repository;

import java.sql.SQLException;
import java.util.List;
import app.employee.domain.entity.Employee;

public interface EmployeeRepository {
	public void create(Employee employee) throws SQLException;
	public void delete(Employee employee) throws SQLException;
	public Employee read(long id) throws SQLException;
    public List<Employee> getAll() throws SQLException;
    public Employee findByName(String name) throws SQLException;

}
