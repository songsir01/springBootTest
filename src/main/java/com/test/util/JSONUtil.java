package com.test.util;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class JSONUtil {

    public static String getJSON(Object obj) throws JsonGenerationException, JsonMappingException, IOException{
        if(null==obj){
            return "";
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.getSerializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        String jsonStr=mapper.writeValueAsString(obj);
        return jsonStr;
    }

    @SuppressWarnings({"unchecked"})
    public static Object getObj(String json, @SuppressWarnings("rawtypes") Class clazz) throws JsonParseException, JsonMappingException, IOException{
        if(null==json||json.length()==0){
            return null;
        }
        ObjectMapper mapper=new ObjectMapper();
        mapper.getDeserializationConfig().with(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
//        if(json.startsWith("[")){
//            List result=mapper.readValue(json, TypeFactory.rawClass(ArrayList.class));
//            return result;
//        }
        return mapper.readValue(json, clazz);
    }
}
