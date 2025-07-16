package com.taskManagement.taskManagement.request.statusName;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StatusNameRequest {

	private String statusName;

	public StatusNameRequest(String statusName) {
		super();
		this.statusName = statusName;
	}

	public StatusNameRequest() {
		super();
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Override
	public String toString() {
		return "StatusNameRequest [statusName=" + statusName + "]";
	}
	
}
