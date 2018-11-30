package app.project.application.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.common.domain.valueobject.Money;
import app.common.domain.valueobject.MoneyAbstraction;
import app.common.infrastructure.persistence.hibernate.UnitOfWorkHibernate;
import app.customers.domain.entity.Customer;
import app.project.application.dto.ProjectCreateDto;
import app.project.application.dto.ProjectListDto;
import app.project.domain.entity.Project;

@Component
public class ProjectCreateAssembler {
	@Autowired
	protected UnitOfWorkHibernate unitOfWork;
	
	public Project toEntity(ProjectCreateDto projectCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Project project = modelMapper.map(projectCreateDto, Project.class);
		return project;
	}
	
	public List<Project> toEntityList(List<ProjectCreateDto> projectCreateListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Project> projectListDto = modelMapper.map(projectCreateListDto, new TypeToken<List<Project>>() {}.getType());
		return projectListDto;
	}       
	
	
        
	private Converter<ProjectCreateDto, Project> getConverter() {
		Converter<ProjectCreateDto, Project> converter = new Converter<ProjectCreateDto, Project>() {
		    @Override
		    public Project convert(MappingContext<ProjectCreateDto, Project> context) {
		    	ProjectCreateDto projectCreateDto =  ProjectCreateDto.class.cast(context.getSource());
		    	MoneyAbstraction balance = new Money(projectCreateDto.getBalance(), projectCreateDto.getCurrency());
		        Project project = new Project();
		        project.setCode(projectCreateDto.getCode());
                        project.setName(projectCreateDto.getName());
		        project.setBalance(balance);
                        
		        Customer customer = new Customer();
		        customer.setId(projectCreateDto.getCustomerId());
		        project.setCustomer(customer);
		    	return project;
		    }
		};
		return converter;
	}
	

	public List<ProjectListDto> toDtoList(List<Project> projectListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<ProjectListDto> pprojectListDto = modelMapper.map(projectListDto, new TypeToken<List<ProjectListDto>>() {}.getType());
        System.out.println(" result" + pprojectListDto);
		return pprojectListDto;
	}
	
	
	
	public ProjectCreateDto toDto(Project project) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Project, ProjectCreateDto> map = new PropertyMap<Project, ProjectCreateDto>() {
		  protected void configure() {
			map().setCode(source.getCode());
                        map().setName(source.getName());
                        map().setBalance(source.getBalance().getAmount());
                        map().setCurrency(source.getBalance().getCurrencyAsString());
                        map().setLocked(source.getIsLocked());
                        map().setCustomerId(source.getCustomer().getId());
		  }
		};
		modelMapper.addMappings(map);
		ProjectCreateDto projectCreateDto = modelMapper.map(project, ProjectCreateDto.class);
		return projectCreateDto;
	}
        
}
