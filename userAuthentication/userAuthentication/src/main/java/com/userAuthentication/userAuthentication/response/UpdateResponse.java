package com.userAuthentication.userAuthentication.response;

public class UpdateResponse {

	private String message;
	private String status;

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

	public UpdateResponse() {
		super();
	}

	public UpdateResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	@Override
	public String toString() {
		return "UpdateResponse [message=" + message + ", status=" + status + "]";
	}

}

