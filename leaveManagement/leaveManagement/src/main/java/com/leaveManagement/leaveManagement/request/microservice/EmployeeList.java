package com.leaveManagement.leaveManagement.request.microservice;

import java.util.List;

public class EmployeeList {

	private String managerEmail;
	private List<EmployeeAllDetails> employeeDetailsList;
	public EmployeeList(String managerEmail, List<EmployeeAllDetails> employeeDetailsList) {
		super();
		this.managerEmail = managerEmail;
		this.employeeDetailsList = employeeDetailsList;
	}
	public EmployeeList() {
		super();
	}
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	public List<EmployeeAllDetails> getEmployeeDetailsList() {
		return employeeDetailsList;
	}
	public void setEmployeeDetailsList(List<EmployeeAllDetails> employeeDetailsList) {
		this.employeeDetailsList = employeeDetailsList;
	}
	@Override
	public String toString() {
		return "EmployeeList [managerEmail=" + managerEmail + ", employeeDetailsList=" + employeeDetailsList + "]";
	}
	
}
