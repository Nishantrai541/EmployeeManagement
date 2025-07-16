package com.userAuthentication.userAuthentication.response.manager.details;

public class ManagerDetailsResponse {

	private String managerName;
	private String managerEmail;
	private String managerDesignation;
	private String managerRole;
	public ManagerDetailsResponse(String managerName, String managerEmail, String managerDesignation,
			String managerRole) {
		super();
		this.managerName = managerName;
		this.managerEmail = managerEmail;
		this.managerDesignation = managerDesignation;
		this.managerRole = managerRole;
	}
	public ManagerDetailsResponse() {
		super();
	}
	public String getManagerName() {
		return managerName;
	}
	public void setManagerName(String managerName) {
		this.managerName = managerName;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public String getManagerDesignation() {
		return managerDesignation;
	}
	public void setManagerDesignation(String managerDesignation) {
		this.managerDesignation = managerDesignation;
	}
	public String getManagerRole() {
		return managerRole;
	}
	public void setManagerRole(String managerRole) {
		this.managerRole = managerRole;
	}
	@Override
	public String toString() {
		return "ManagerDetailsResponse [managerName=" + managerName + ", managerEmail=" + managerEmail
				+ ", managerDesignation=" + managerDesignation + ", managerRole=" + managerRole + "]";
	}
	
}
