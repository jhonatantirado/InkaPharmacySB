package app.customers.application.deserializer;

import app.customers.application.dto.CustomerDto;
import java.io.IOException;

import org.modelmapper.ModelMapper;
import org.modelmapper.jackson.JsonNodeValueReader;
import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


public class CustomerCreateDeserializer extends JsonDeserializer<CustomerDto> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public CustomerDto deserialize(JsonParser jsonParser, DeserializationContext context)
			throws IOException, JsonProcessingException {
		CustomerDto customerCreateDto = null;
		String json = jsonParser.readValueAsTree().toString();
		JsonNode node = new ObjectMapper().readTree(json);
		modelMapper.getConfiguration()
		  .addValueReader(new JsonNodeValueReader());
		try {
                    customerCreateDto = modelMapper.map(node, CustomerDto.class);
		} catch(Exception ex) {
                    customerCreateDto = new CustomerDto();
		}
		return customerCreateDto;
	}
}
