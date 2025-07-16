package com.leaveManagement.leaveManagement.request.applicationResponse.manager;

public class ManagerResponseOnApplicationRequest {

	private Long id;
	private String employeeEmail;
	private String leaveType;
	private String status;
	public ManagerResponseOnApplicationRequest(Long id, String employeeEmail, String leaveType, String status) {
		super();
		this.id = id;
		this.employeeEmail = employeeEmail;
		this.leaveType = leaveType;
		this.status = status;
	}
	public ManagerResponseOnApplicationRequest() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmployeeEmail() {
		return employeeEmail;
	}
	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "ManagerResponseOnApplicationRequest [id=" + id + ", employeeEmail=" + employeeEmail + ", leaveType="
				+ leaveType + ", Status=" + status + "]";
	}
	
}
