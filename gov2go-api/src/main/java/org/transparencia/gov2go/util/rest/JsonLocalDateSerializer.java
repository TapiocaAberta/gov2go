package org.transparencia.gov2go.util.rest;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

/**
 * 
 * @author pedro-hos
 * 
 * Essa classe irá formatar a saída de da Data :D
 *
 */
public class JsonLocalDateSerializer extends JsonSerializer<LocalDate>{

	@Override
	public void serialize(LocalDate localDate, 
						  JsonGenerator jsonGenerator, 
						  SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
		
		String dataFormatada = localDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		jsonGenerator.writeString(dataFormatada);
		
	}

}
