package app.employee.aplication.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import app.employee.aplication.assembler.EmployeeCreateAssembler;
import app.employee.aplication.dto.EmployeeListDto;
import app.employee.infrastructure.persistence.hibernate.EmployeeHibernateRepository;


@Service
public class EmployeeService {
	
	@Autowired
	EmployeeCreateAssembler employeeCreateAssembler;

	@Autowired
	EmployeeHibernateRepository employeeHibernateRepository;
	

  	
	 public List<EmployeeListDto> getAll() throws SQLException{
	        return null;
	 }
	

}
