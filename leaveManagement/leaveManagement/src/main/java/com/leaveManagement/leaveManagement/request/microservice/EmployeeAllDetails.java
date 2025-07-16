package com.leaveManagement.leaveManagement.request.microservice;

public class EmployeeAllDetails {

	private String empEmail;
	private String empName;
	private String empNumber;
	private String empDesignation;
	private String empRole;
	public EmployeeAllDetails(String empEmail, String empName, String empNumber, String empDesignation,
			String empRole) {
		super();
		this.empEmail = empEmail;
		this.empName = empName;
		this.empNumber = empNumber;
		this.empDesignation = empDesignation;
		this.empRole = empRole;
	}
	public EmployeeAllDetails() {
		super();
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpNumber() {
		return empNumber;
	}
	public void setEmpNumber(String empNumber) {
		this.empNumber = empNumber;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
	}
	@Override
	public String toString() {
		return "EmployeeAllDetails [empEmail=" + empEmail + ", empName=" + empName + ", empNumber=" + empNumber
				+ ", empDesignation=" + empDesignation + ", empRole=" + empRole + "]";
	}
	
}
