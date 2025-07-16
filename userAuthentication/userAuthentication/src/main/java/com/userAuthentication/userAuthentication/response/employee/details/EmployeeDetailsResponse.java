package com.userAuthentication.userAuthentication.response.employee.details;

public class EmployeeDetailsResponse {
	
	private String statusCode;
	private String message;
	public EmployeeDetailsResponse(String statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}
	public EmployeeDetailsResponse() {
		super();
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "EmployeeDetailsResponse [statusCode=" + statusCode + ", message=" + message + "]";
	}
	

}
