package app.employee.aplication.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.common.infrastructure.persistence.hibernate.UnitOfWorkHibernate;
import app.employee.aplication.dto.EmployeeCreateDto;
import app.employee.aplication.dto.EmployeeListDto;
import app.employee.domain.entity.Employee;

@Component
public class EmployeeCreateAssembler {

	@Autowired
	protected UnitOfWorkHibernate unitOfWork;
	
	
	
	public List<EmployeeListDto> toDtoList(List<Employee> employeeListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<EmployeeListDto> pprojectListDto = modelMapper.map(employeeListDto, new TypeToken<List<EmployeeListDto>>() {}.getType());
        System.out.println(" result" + pprojectListDto);
		return pprojectListDto;
	}
	
	
	private Converter<EmployeeCreateDto, Employee> getConverter() {
		Converter<EmployeeCreateDto, Employee> converter = new Converter<EmployeeCreateDto, Employee>() {
		    @Override
		    public Employee convert(MappingContext<EmployeeCreateDto, Employee> context) {
		    	EmployeeCreateDto employeeCreateDto =  EmployeeCreateDto.class.cast(context.getSource());
		    	
		    	Employee employee = new Employee();
		    	employee.setName(employeeCreateDto.getName());
		    	
		    	employee.setLast_name1(employeeCreateDto.getLast_name1());
		    	employee.setLast_name2(employeeCreateDto.getLast_name2());
		    	employee.setAddress(employeeCreateDto.getAddress());		    	
		    	employee.setTelephone(employeeCreateDto.getTelephone());
		    	employee.setRole_id(employeeCreateDto.getRole_id());		    	
		    	employee.setStore_id(employeeCreateDto.getStore_id());
		    	employee.setUsername(employeeCreateDto.getUsername());
		    	employee.setPassword(employeeCreateDto.getPassword());		    	
		    	employee.setEmail(employeeCreateDto.getEmail());
		    	employee.setStatus(employeeCreateDto.getStatus());	
		    	System.out.println("Converter" + employee);
		    	return employee;
		    }
		};
		return converter;
	}
	
	
	
	
}
