package app.salesorderdetall.application.assembler;

import java.util.List;


import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import app.salesorderdetall.application.dto.SalesorderdetallCreateDto;
import app.salesorderdetall.application.dto.SalesorderdetallListDto;
import app.salesorderdetall.domain.entity.Saleorderdetall;

@Component
public class SalesorderdetallCreateAssembler  {

	public Saleorderdetall toEntity(SalesorderdetallCreateDto salesorderdetallCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		Saleorderdetall saleorderdetall = modelMapper.map(salesorderdetallCreateDto, Saleorderdetall.class);
		return saleorderdetall;
	}
	
	public List<Saleorderdetall> toEntityList(List<SalesorderdetallCreateDto> salesorderdetallCreateDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<Saleorderdetall> SaleorderdetallListDto = modelMapper.map(salesorderdetallCreateDto, new TypeToken<List<Saleorderdetall>>() {}.getType());
		return SaleorderdetallListDto;
	}  
	
	
	private Converter<SalesorderdetallCreateDto, Saleorderdetall> getConverter() {
		Converter<SalesorderdetallCreateDto, Saleorderdetall> converter = new Converter<SalesorderdetallCreateDto, Saleorderdetall>() {
		    @Override
		    public Saleorderdetall convert(MappingContext<SalesorderdetallCreateDto, Saleorderdetall> context) {
		    	SalesorderdetallCreateDto salesorderdetallCreateDto =  SalesorderdetallCreateDto.class.cast(context.getSource());		 
		    	Saleorderdetall saleorderdetall = new Saleorderdetall();
		    	saleorderdetall.setId(salesorderdetallCreateDto.getId());
		    	saleorderdetall.setSale_order_id(salesorderdetallCreateDto.getSale_order_id());
		    	saleorderdetall.setProduct_id(salesorderdetallCreateDto.getProduct_id());
		    	saleorderdetall.setQuantity(salesorderdetallCreateDto.getQuantity());
		    	saleorderdetall.setPrice(salesorderdetallCreateDto.getPrice());
		    	saleorderdetall.setCurrency(salesorderdetallCreateDto.getCurrency());
		    	saleorderdetall.setStatus(salesorderdetallCreateDto.getStatus());
		    	return saleorderdetall;
		    }
		};
		return converter;
	}
	
	
	
	public List<SalesorderdetallListDto> toDtoList(List<Saleorderdetall> salesorderdetallListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<SalesorderdetallListDto> ssalesorderdetallListDto = modelMapper.map(salesorderdetallListDto, new TypeToken<List<SalesorderdetallListDto>>() {}.getType());
        System.out.println(" result" + ssalesorderdetallListDto);
		return ssalesorderdetallListDto;
	}
	
	public SalesorderdetallCreateDto toDto(Saleorderdetall saleorderdetall) {
		ModelMapper modelMapper = new ModelMapper();
		PropertyMap<Saleorderdetall, SalesorderdetallCreateDto> map = new PropertyMap<Saleorderdetall, SalesorderdetallCreateDto>() {
		  protected void configure() {
			map().setId(source.getId());
			map().setSale_order_id(source.getSale_order_id());
			map().setProduct_id(source.getProduct_id());
			map().setQuantity(source.getQuantity());
			map().setPrice(source.getPrice());
			map().setCurrency(source.getCurrency());
			map().setStatus(source.getStatus());
		  }
		};
		modelMapper.addMappings(map);
		SalesorderdetallCreateDto salesorderdetallCreateDto = modelMapper.map(saleorderdetall, SalesorderdetallCreateDto.class);
		return salesorderdetallCreateDto;
	}
}
