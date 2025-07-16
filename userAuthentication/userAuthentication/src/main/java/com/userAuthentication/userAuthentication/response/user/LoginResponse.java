package com.userAuthentication.userAuthentication.response.user;

public class LoginResponse {

	private String authToken;
	private String message;
	private String status;
	private String role;

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public LoginResponse() {
		super();
	}

	public LoginResponse(String authToken, String message, String status, String role) {
		super();
		this.authToken = authToken;
		this.message = message;
		this.status = status;
		this.role = role;
	}

	@Override
	public String toString() {
		return "LoginResponse [authToken=" + authToken + ", message=" + message + ", status=" + status + ", role="
				+ role + "]";
	}

}

