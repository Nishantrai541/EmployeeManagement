package com.leaveManagement.leaveManagement.response.employeeApplication.manager;

import java.time.LocalDate;

public class EmployeeApplicationManagerResponse {

	private Long id;
	private String empName;
	private String empEmail;
	private String empNumber;
	private String empDesignation;
	private String empRole;
	private String leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private String Status;
	public EmployeeApplicationManagerResponse(Long id, String empName, String empEmail, String empNumber,
			String empDesignation, String empRole, String leaveType, LocalDate startDate, LocalDate endDate,
			String status) {
		super();
		this.id = id;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empNumber = empNumber;
		this.empDesignation = empDesignation;
		this.empRole = empRole;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		Status = status;
	}
	public EmployeeApplicationManagerResponse() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
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
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	@Override
	public String toString() {
		return "EmployeeApplicationResponse [id=" + id + ", empName=" + empName + ", empEmail=" + empEmail
				+ ", empNumber=" + empNumber + ", empDesignation=" + empDesignation + ", empRole=" + empRole
				+ ", leaveType=" + leaveType + ", startDate=" + startDate + ", endDate=" + endDate + ", Status="
				+ Status + "]";
	}
	
	
}
