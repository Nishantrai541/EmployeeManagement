package com.userAuthentication.userAuthentication.response.manager.details;

public class ManagerTeamPersonResponse {

	private String email;
	private String name;
	private String mobileNo;
	private String designation;
	private String role;
	public ManagerTeamPersonResponse(String email, String name, String mobileNo, String designation, String role) {
		super();
		this.email = email;
		this.name = name;
		this.mobileNo = mobileNo;
		this.designation = designation;
		this.role = role;
	}
	public ManagerTeamPersonResponse() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "ManagerTeamPersonResponse [email=" + email + ", name=" + name + ", mobileNo=" + mobileNo
				+ ", designation=" + designation + ", role=" + role + "]";
	}
	
}
