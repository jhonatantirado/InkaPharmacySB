package app.purchaseorder.application.assembler;

import java.util.List;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.modelmapper.spi.MappingContext;
import org.springframework.stereotype.Component;

import app.purchaseorder.application.dto.PurchaseCreateDto;
import app.purchaseorder.application.dto.PurchaseListDto;
import app.purchaseorder.domain.entity.Purchaseorder;

@Component
public class PurchaseCreateAssembler {

	
	public List<PurchaseListDto> toDtoList(List<Purchaseorder> purchaseListDto) {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(getConverter());
		List<PurchaseListDto> ssalesorderListDto = modelMapper.map(purchaseListDto, new TypeToken<List<PurchaseListDto>>() {}.getType());
       	return ssalesorderListDto;
	}
	
	private Converter<PurchaseCreateDto,Purchaseorder> getConverter() {
		Converter<PurchaseCreateDto, Purchaseorder> converter = new Converter<PurchaseCreateDto, Purchaseorder>() {
		    @Override
		    public Purchaseorder convert(MappingContext<PurchaseCreateDto, Purchaseorder> context) {
		    	PurchaseCreateDto purchaseCreateDto =  PurchaseCreateDto.class.cast(context.getSource());		 
		    	Purchaseorder purchaseorder = new Purchaseorder();
		    	purchaseorder.setPurchase_date(purchaseCreateDto.getPurchase_date());	
		    	purchaseorder.setProvider_id(purchaseCreateDto.getProvider_id());	
		    	purchaseorder.setEmployee_id(purchaseCreateDto.getEmployee_id());
		    	return purchaseorder;
		    }
		};
		return converter;
	}
	
}
