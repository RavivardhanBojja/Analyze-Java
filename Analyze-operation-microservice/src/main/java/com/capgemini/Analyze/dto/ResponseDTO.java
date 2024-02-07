package com.capgemini.Analyze.dto;

public class ResponseDTO {

	private Object response;
	private String message;
	private String exception;

	public ResponseDTO() {

	}

	public ResponseDTO(Object response, String message, String exception) {
		super();
		this.response = response;
		this.message = message;
		this.exception = exception;
	}

	public ResponseDTO(Object response, String message) {
		super();
		this.response = response;
		this.message = message;
	}

	public ResponseDTO(String exception) {
		super();
		this.exception = exception;
	}

	public ResponseDTO(Object response) {
		super();
		this.response = response;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public Object getResponse() {
		return response;
	}

	public void setResponse(Object response) {
		this.response = response;
	}

}
