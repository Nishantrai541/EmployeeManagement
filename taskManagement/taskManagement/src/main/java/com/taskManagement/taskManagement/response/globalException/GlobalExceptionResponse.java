package com.taskManagement.taskManagement.response.globalException;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class GlobalExceptionResponse {
	
	private String status;
	private String message;
	public GlobalExceptionResponse(String status, String message) {
		super();
		this.status = status;
		this.message = message;
	}
	public GlobalExceptionResponse() {
		super();
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}

