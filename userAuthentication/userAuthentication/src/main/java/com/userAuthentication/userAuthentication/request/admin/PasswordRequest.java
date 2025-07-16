package com.userAuthentication.userAuthentication.request.admin;

public class PasswordRequest {

	private String password;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public PasswordRequest() {
		super();
	}

	public PasswordRequest(String password) {
		super();
		this.password = password;
	}

	@Override
	public String toString() {
		return "PasswordRequest [password=" + password + "]";
	}

}

