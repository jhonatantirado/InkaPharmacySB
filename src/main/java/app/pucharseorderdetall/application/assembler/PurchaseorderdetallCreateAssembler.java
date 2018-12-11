package app.pucharseorderdetall.application.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import app.pucharseorderdetall.application.dto.PurchaseorderdetallCreateDto;
import app.pucharseorderdetall.application.dto.PurchaseorderdetallListDto;
import app.pucharseorderdetall.domain.entity.Pucharseorderdetall;

@Component
public class PurchaseorderdetallCreateAssembler {

	public List<PurchaseorderdetallListDto> toDtoList(List<Pucharseorderdetall> purchaseorderdetallListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<PurchaseorderdetallListDto> ppurchaseorderdetallListDto = modelMapper.map(purchaseorderdetallListDto, new TypeToken<List<PurchaseorderdetallListDto>>() {}.getType());
        System.out.println(" result" + ppurchaseorderdetallListDto);
		return ppurchaseorderdetallListDto;
	}
	
	private Converter<PurchaseorderdetallCreateDto, Pucharseorderdetall> getConverter() {
		Converter<PurchaseorderdetallCreateDto, Pucharseorderdetall> converter = new Converter<PurchaseorderdetallCreateDto, Pucharseorderdetall>() {
		    @Override
		    public Pucharseorderdetall convert(MappingContext<PurchaseorderdetallCreateDto, Pucharseorderdetall> context) {
		    	PurchaseorderdetallCreateDto purchaseorderdetallCreateDto =  PurchaseorderdetallCreateDto.class.cast(context.getSource());		 
		    	Pucharseorderdetall pucharseorderdetall = new Pucharseorderdetall();		
		    	pucharseorderdetall.setId(purchaseorderdetallCreateDto.getId());
		    	pucharseorderdetall.setCurrency(purchaseorderdetallCreateDto.getCurrency());
		    	pucharseorderdetall.setPrice(purchaseorderdetallCreateDto.getPrice());
		    	pucharseorderdetall.setProduct_id(purchaseorderdetallCreateDto.getProduct_id());
		    	pucharseorderdetall.setQuantity(purchaseorderdetallCreateDto.getQuantity());
		    	return pucharseorderdetall;
		    }
		};
		return converter;
	}
	
	
}
