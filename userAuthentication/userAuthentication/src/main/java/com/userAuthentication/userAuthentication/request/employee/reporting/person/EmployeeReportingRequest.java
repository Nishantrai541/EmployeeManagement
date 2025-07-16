package com.userAuthentication.userAuthentication.request.employee.reporting.person;

public class EmployeeReportingRequest {

	private String employeeEmail;
	private String managerEmail;
	public EmployeeReportingRequest(String employeeEmail, String managerEmail) {
		super();
		this.employeeEmail = employeeEmail;
		this.managerEmail = managerEmail;
	}
	public EmployeeReportingRequest() {
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
		return "EmployeeReportingRequest [employeeEmail=" + employeeEmail + ", managerEmail=" + managerEmail + "]";
	}
		
}
