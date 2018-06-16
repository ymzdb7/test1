/*   
 * Copyright (c) {year} 南京唯恒思 www.winhands.com. All Rights Reserved.   
 *   
 */     
package com.winhands.base.util.json;    

import java.io.IOException;

import org.codehaus.jackson.JsonEncoding;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonMappingException;

public class JsonUtil {
	private static JsonGenerator jsonGenerator = null;  
	private static ObjectMapperCustomer  objectMapper = null;  
	JsonUtil(){
		objectMapper = new ObjectMapperCustomer();  
		try {  
			jsonGenerator = objectMapper.getJsonFactory().createJsonGenerator(System.out, JsonEncoding.UTF8);  
		} catch (IOException e) { 
			e.printStackTrace();  
		}  
	}
	public static String ObjectToJson(Object obj){
		objectMapper = new ObjectMapperCustomer();
		try {
			return  objectMapper.writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	public static Object readJson2Entity(Object object,String json) {
		objectMapper = new ObjectMapperCustomer();
	    Object objectNew = null;
	    try {
	    	objectNew = objectMapper.readValue(json, Object.class); 
	    } catch (JsonParseException e) {
	        e.printStackTrace();
	    } catch (JsonMappingException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    return objectNew;
	}
	public static String ObjectToMsJson(Object obj,String action){
		objectMapper = new ObjectMapperCustomer();
		try {
			String jsonItem = objectMapper.writeValueAsString(obj);
			StringBuffer jsonBuffer = new StringBuffer();
			jsonBuffer.append("{\"action\":\"").append(action).append("\",\"items\":").append(jsonItem).append("}");
			return  jsonBuffer.toString();
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
 