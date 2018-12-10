package app.employee.domain.repository;

import java.sql.SQLException;
import java.util.List;
import app.employee.domain.entity.Employee;

public interface EmployeeRepository {
	List<Employee> getAll();
	Employee find(String name);
    void save(Employee customer);

}
