package app.customers.application.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;
import app.common.domain.valueobject.Money;
import app.common.domain.valueobject.MoneyAbstraction;
import app.customers.application.dto.CustomerCreateDto;
import app.customers.application.dto.CustomerDto;
import app.customers.domain.entity.Customer;
import app.project.domain.entity.Project;
import app.user.domain.entity.User;
import app.user.domain.entity.UserType;
import java.util.HashSet;
import java.util.Set;

@Component
public class CustomerCreateAssembler {
	
	public Customer toEntity(CustomerCreateDto customerCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Customer customer = modelMapper.map(customerCreateDto, Customer.class);
		return customer;
	}
	
	public List<Customer> toEntityList(List<CustomerCreateDto> customerCreateListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Customer> customertList = modelMapper.map(customerCreateListDto, new TypeToken<List<Customer>>() {}.getType());
		return customertList;
	}       
        
	private Converter<CustomerCreateDto, Customer> getConverter() {
		Converter<CustomerCreateDto, Customer> converter = new Converter<CustomerCreateDto, Customer>() {
		    @Override
		    public Customer convert(MappingContext<CustomerCreateDto, Customer> context) {
		    	CustomerCreateDto customerCreateDto =  CustomerCreateDto.class.cast(context.getSource());
		        Customer customer = new Customer();
		        customer.setFirstName(customerCreateDto.getFirstName());
		        customer.setLastName(customerCreateDto.getLastName());
                        customer.setIsActive(customerCreateDto.getIsActive());
                        customer.setIdentityDocument(customerCreateDto.getIdentityDocument());
                        customer.setProjects(new HashSet<Project>());
                        customer.setUsers(new HashSet<User>());
                        
                        Project project = new Project();
                        MoneyAbstraction balance = new Money(customerCreateDto.getBalance(), customerCreateDto.getCurrency());
                        project.setCode(customerCreateDto.getCode());
                        project.setName(customerCreateDto.getName());
                        project.setBalance(balance);                        
                        project.setCustomer(customer);
                        customer.getProjects().add(project);
                        
                        User user = new User();
		        user.setUsername(customerCreateDto.getUsername());
		        user.setPassword(customerCreateDto.getPassword());
                        user.setRole(UserType.OWNER.toString());
                        user.setIsLocked(customerCreateDto.getIsActive());
                        user.setCustomer(customer);
                        customer.getUsers().add(user);
		    	return customer;
		    }
		};
		return converter;
	}
	
	public CustomerDto toDto(Customer customer) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Customer, CustomerDto> map = new PropertyMap<Customer, CustomerDto>() {
                    protected void configure() {
                          map().setId(source.getId());
                          map().setFirstName(source.getFirstName());
                          map().setLastName(source.getLastName());
                          map().setIdentityDocument(source.getIdentityDocument());
                          map().setIsActive(source.getIsActive());
                    }
		};
		modelMapper.addMappings(map);
		CustomerDto customeDto = modelMapper.map(customer, CustomerDto.class);
		return customeDto;
	}
        
}
