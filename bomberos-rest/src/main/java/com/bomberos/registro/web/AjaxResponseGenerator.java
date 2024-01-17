package com.bomberos.registro.web;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AjaxResponseGenerator {
	
	private static Logger logger = LoggerFactory.getLogger(JsonGenerator.class);
	public static ObjectMapper objectMapper = new ObjectMapper();

	public static synchronized AjaxResponseObject createSimpleResponseOK(Object okMessageData){
		return new AjaxResponseObject(AjaxResponseState.OK, "", okMessageData);
	}
	
	public static synchronized AjaxResponseObject createSimpleResponseError(String errorMessage){
		return new AjaxResponseObject(AjaxResponseState.ERROR, errorMessage);
	}
	
	public static synchronized String objectToJson(Object obj) {
		try {
			return objectMapper.writeValueAsString(obj);
		} catch (IOException e) {
			logger.error("Ocurrio un error al convertir el objeto a json", e);
			throw new RuntimeException("Ocurri� un error inesperado.", e);
		}
	}
}
