package com.zach.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class JsonUtils {

    private final static Logger LOGGER = LoggerFactory.getLogger(JsonUtils.class);

    private static ObjectMapper mapper;

    /**
     * 获取ObjectMapper实例
     * 
     * @param createNew 方式：true，新实例；false,存在的mapper实例
     * @return
     */
    public static synchronized ObjectMapper getMapperInstance(boolean createNew) {
	if (createNew) {
	    mapper = new ObjectMapper();
	    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    mapper.setDateFormat(output);
	} else if (mapper == null) {
	    mapper = new ObjectMapper();
	    SimpleDateFormat output = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    mapper.setDateFormat(output);
	}
	mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	return mapper;
    }

    /**
     * 将java对象转换成json字符串
     * 
     * @param obj 准备转换的对象
     * @return json字符串
     * @throws Exception
     */
    public static String beanToJson(Object obj) {

	String json = null;
	try {
	    ObjectMapper objectMapper = getMapperInstance(false);
	    json = objectMapper.writeValueAsString(obj);
	} catch (Exception e) {
	    LOGGER.error(e.getMessage(), e);
	}
	return json;
    }

    /**
     * 将java对象转换成json字符串
     * 
     * @param obj 准备转换的对象
     * @param createNew ObjectMapper实例方式:true，新实例;false,存在的mapper实例
     * @return json字符串
     * @throws Exception
     */
    public static String beanToJson(Object obj, Boolean createNew) {

	String json = null;
	try {
	    ObjectMapper objectMapper = getMapperInstance(createNew);
	    json = objectMapper.writeValueAsString(obj);
	} catch (Exception e) {
	    LOGGER.error(e.getMessage(), e);
	}
	return json;

    }

    /**
     * 将json字符串转换成java对象
     * 
     * @param json 准备转换的json字符串
     * @param cls 准备转换的类
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String json, Class<T> cls) {

	T t = null;
	try {
	    ObjectMapper objectMapper = getMapperInstance(false);
	    objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	    t = objectMapper.readValue(json, cls);
	} catch (IOException e) {
	    LOGGER.error(e.getMessage(), e);
	}
	return t;
    }

    /**
     * 将json字符串转换成java对象
     * 
     * @param json 准备转换的json字符串
     * @param cls 准备转换的类
     * @param createNew ObjectMapper实例方式:true，新实例;false,存在的mapper实例
     * @return
     * @throws Exception
     */
    public static <T> T jsonToBean(String json, Class<T> cls, Boolean createNew) {

	T t = null;
	try {
	    ObjectMapper objectMapper = getMapperInstance(createNew);
	    t = objectMapper.readValue(json, cls);
	} catch (IOException e) {
	    LOGGER.error(e.getMessage(), e);
	}
	return t;
    }
    
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
	      getMapperInstance(false);
	    return mapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);   
     }   
    
    public static <T> Object jsonToListBean(String json, Class<T> cls) throws Exception{
	JavaType javaType = getCollectionType(ArrayList.class,cls); 
	  return mapper.readValue(json, javaType); 
    }
}
