package com.userAuthentication.userAuthentication.response.microservice;

public class EmployeeDetails {

	private String empEmail;
	private String empRole;
	public EmployeeDetails(String empEmail, String empRole) {
		super();
		this.empEmail = empEmail;
		this.empRole = empRole;
	}
	public EmployeeDetails() {
		super();
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	@Override
	public String toString() {
		return "EmployeeDetails [empEmail=" + empEmail + ", empRole=" + empRole + "]";
	}
	
	
}
