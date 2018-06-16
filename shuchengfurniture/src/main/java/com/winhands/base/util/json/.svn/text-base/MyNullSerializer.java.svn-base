/*   
 * Copyright (c) 2014 南京唯恒思 www.winhands.com. All Rights Reserved.   
 */     
package com.winhands.base.util.json;    

import java.io.IOException;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;

public class MyNullSerializer extends JsonSerializer<Object> {

	@Override
	public void serialize(Object o, JsonGenerator jg, SerializerProvider s) throws IOException, JsonProcessingException {
		jg.writeString(""); 
     }

}
 