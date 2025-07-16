package com.userAuthentication.userAuthentication.response.role;

public class EmployeeListOnRole {
	private String employeeName;
	private String employeeemail;
	private String employeeDesignation;
	private String employeeRole;
	public EmployeeListOnRole(String employeeName, String employeeemail, String employeeDesignation,
			String employeeRole) {
		super();
		this.employeeName = employeeName;
		this.employeeemail = employeeemail;
		this.employeeDesignation = employeeDesignation;
		this.employeeRole = employeeRole;
	}
	public EmployeeListOnRole() {
		super();
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeeemail() {
		return employeeemail;
	}
	public void setEmployeeemail(String employeeemail) {
		this.employeeemail = employeeemail;
	}
	public String getEmployeeDesignation() {
		return employeeDesignation;
	}
	public void setEmployeeDesignation(String employeeDesignation) {
		this.employeeDesignation = employeeDesignation;
	}
	public String getEmployeeRole() {
		return employeeRole;
	}
	public void setEmployeeRole(String employeeRole) {
		this.employeeRole = employeeRole;
	}
	@Override
	public String toString() {
		return "EmployeeListOnRole [employeeName=" + employeeName + ", employeeemail=" + employeeemail
				+ ", employeeDesignation=" + employeeDesignation + ", employeeRole=" + employeeRole + "]";
	}
	

}
