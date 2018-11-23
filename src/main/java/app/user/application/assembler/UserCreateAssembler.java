package app.user.application.assembler;

import app.customers.domain.entity.Customer;
import app.user.application.dto.UserCreateDto;
import app.user.domain.entity.User;
import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;


@Component
public class UserCreateAssembler {
	
	public User toEntity(UserCreateDto userCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		User user = modelMapper.map(userCreateDto, User.class);
		return user;
	}
	
	public List<User> toEntityList(List<UserCreateDto> userCreateListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<User> customertList = modelMapper.map(userCreateListDto, new TypeToken<List<User>>() {}.getType());
		return customertList;
	}       
        
	private Converter<UserCreateDto, User> getConverter() {
		Converter<UserCreateDto, User> converter = new Converter<UserCreateDto, User>() {
		    @Override
		    public User convert(MappingContext<UserCreateDto, User> context) {
		    	UserCreateDto userCreateDto =  UserCreateDto.class.cast(context.getSource());
                        
		        User user = new User();
		        user.setUsername(userCreateDto.getUsername());
		        user.setPassword(userCreateDto.getPassword());
                        user.setRole(userCreateDto.getRole());
                        user.setIsLocked(userCreateDto.isLocked());
                        
                        Customer customer = new Customer();
		        customer.setId(userCreateDto.getCustomerId());
		        user.setCustomer(customer);
		    	return user;
		    }
		};
		return converter;
	}
	
	public UserCreateDto toDto(User user) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<User, UserCreateDto> map = new PropertyMap<User, UserCreateDto>() {
                    protected void configure() {
                          map().setUsername(source.getUsername());
                          map().setPassword(source.getPassword());
                          map().setRole(source.getRole());
                          map().setLocked(source.getIsLocked());                        
                    }
		};
		modelMapper.addMappings(map);
		UserCreateDto userCreateDto = modelMapper.map(user, UserCreateDto.class);
		return userCreateDto;
	}
        
}
