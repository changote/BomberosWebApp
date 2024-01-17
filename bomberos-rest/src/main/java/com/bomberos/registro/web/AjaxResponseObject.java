package com.bomberos.registro.web;

public class AjaxResponseObject {

	private AjaxResponseState state;
	private String message;
	private Object data;
	private String url;
	private int code;

	public AjaxResponseObject() {
	}

	public AjaxResponseObject(AjaxResponseState state, String message) {
		this.state = state;
		this.message = message;
	}
	
	public AjaxResponseObject(AjaxResponseState state, String message, int code) {
		this.state = state;
		this.message = message;
		this.code = code;
	}

	public AjaxResponseObject(AjaxResponseState state, String message, Object data) {
		this(state, message);
		this.data = data;
	}

	public AjaxResponseObject(AjaxResponseState state, String message, Object data, String url) {
		this(state, message, data);
		this.url = url;
	}
	
	public static AjaxResponseObject createSimpleResponseOK(Object okMessageData){
		return new AjaxResponseObject(AjaxResponseState.OK, "", okMessageData);
	}

	public AjaxResponseState getState() {
		return state;
	}

	public void setState(AjaxResponseState state) {
		this.state = state;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}	
}