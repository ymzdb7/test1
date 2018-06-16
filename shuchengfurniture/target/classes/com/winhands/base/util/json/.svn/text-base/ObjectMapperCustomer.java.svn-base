/*   
 * Copyright (c) 2014 南京唯恒思 www.winhands.com. All Rights Reserved.   
 */     
package com.winhands.base.util.json;    

import org.codehaus.jackson.map.ObjectMapper;

public class ObjectMapperCustomer extends ObjectMapper {
	public ObjectMapperCustomer(){
        super();
//        // 允许单引号
//        this.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
//        // 字段和值都加引号
//        this.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
//        // 数字也加引号
//        this.configure(JsonGenerator.Feature.WRITE_NUMBERS_AS_STRINGS, true);
//        this.configure(JsonGenerator.Feature.QUOTE_NON_NUMERIC_NUMBERS, true);
          // 空值处理为空串  
          this.getSerializerProvider().setNullValueSerializer(new MyNullSerializer());
    }
}