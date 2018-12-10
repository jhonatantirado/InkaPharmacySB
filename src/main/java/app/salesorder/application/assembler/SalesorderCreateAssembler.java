package app.salesorder.application.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.common.infrastructure.persistence.hibernate.UnitOfWorkHibernate;
import app.project.application.dto.ProjectCreateDto;
import app.project.application.dto.ProjectListDto;
import app.project.domain.entity.Project;
import app.salesorder.application.dto.SalesorderCreateDto;
import app.salesorder.application.dto.SalesorderListDto;
import app.salesorder.domain.entity.Salesorder;

@Component
public class SalesorderCreateAssembler {

	@Autowired
	protected UnitOfWorkHibernate unitOfWork;
	
	
	public Salesorder toEntity(SalesorderCreateDto salesorderCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Salesorder salesorder = modelMapper.map(salesorderCreateDto, Salesorder.class);
		return salesorder;
	}
	
	public List<Salesorder> toEntityList(List<SalesorderCreateDto> salesorderCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Salesorder> salesordertListDto = modelMapper.map(salesorderCreateDto, new TypeToken<List<Salesorder>>() {}.getType());
		return salesordertListDto;
	}  
	
	
	private Converter<SalesorderCreateDto, Salesorder> getConverter() {
		Converter<SalesorderCreateDto, Salesorder> converter = new Converter<SalesorderCreateDto, Salesorder>() {
		    @Override
		    public Salesorder convert(MappingContext<SalesorderCreateDto, Salesorder> context) {
		    	SalesorderCreateDto salesorderCreateDto =  SalesorderCreateDto.class.cast(context.getSource());		 
		    	Salesorder salesorder = new Salesorder();
		    	salesorder.setSale_date(salesorderCreateDto.getSale_date());
		    	System.out.println("fecha -->> " + salesorderCreateDto.getSale_date());  
		        salesorder.setCustomer_id(salesorderCreateDto.getCustomer_id());
		    	salesorder.setEmployee_id(salesorderCreateDto.getEmployee_id());
		    	salesorder.setStatus(salesorderCreateDto.getStatus());
		    	return salesorder;
		    }
		};
		return converter;
	}
	
	
	
	public List<SalesorderListDto> toDtoList(List<Salesorder> salesorderListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<SalesorderListDto> ssalesorderListDto = modelMapper.map(salesorderListDto, new TypeToken<List<SalesorderListDto>>() {}.getType());
        System.out.println(" result" + ssalesorderListDto);
		return ssalesorderListDto;
	}
	
	public SalesorderCreateDto toDto(Salesorder salesorder) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Salesorder, SalesorderCreateDto> map = new PropertyMap<Salesorder, SalesorderCreateDto>() {
		  protected void configure() {
			map().setSale_date(source.getSale_date());
            map().setCustomer_id(source.getCustomer_id());
            map().setEmployee_id(source.getEmployee_id());
            map().setStatus(source.getStatus());
		  }
		};
		modelMapper.addMappings(map);
		SalesorderCreateDto salesorderCreateDto = modelMapper.map(salesorder, SalesorderCreateDto.class);
		return salesorderCreateDto;
	}
}
