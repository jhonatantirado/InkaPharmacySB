package app.employee.infrastructure.persistence.hibernate;

import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import app.common.infrastructure.persistence.hibernate.BaseHibernateRepository;
import app.employee.domain.entity.Employee;
import app.employee.domain.repository.EmployeeRepository;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

@Transactional(rollbackOn=Exception.class)
@Repository
public class EmployeeHibernateRepository extends BaseHibernateRepository<Employee> implements EmployeeRepository {

	public EmployeeHibernateRepository() {
		
		super(Employee.class);
	}

	@Override
	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Employee find(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Employee customer) {
		// TODO Auto-generated method stub
		
	}

	
		

}
