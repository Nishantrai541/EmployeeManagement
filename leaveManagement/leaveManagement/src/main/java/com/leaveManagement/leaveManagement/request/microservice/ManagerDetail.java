package com.leaveManagement.leaveManagement.request.microservice;

public class ManagerDetail {

	private String employeeEmail;
	private String managerEmail;
	public ManagerDetail(String employeeEmail, String managerEmail) {
		super();
		this.employeeEmail = employeeEmail;
		this.managerEmail = managerEmail;
	}
	public ManagerDetail() {
		super();
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	@Override
	public String toString() {
		return "ManagerDetail [employeeEmail=" + employeeEmail + ", managerEmail=" + managerEmail + "]";
	}
	

}
