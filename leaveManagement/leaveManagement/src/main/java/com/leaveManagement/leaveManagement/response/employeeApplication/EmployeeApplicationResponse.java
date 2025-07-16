package com.leaveManagement.leaveManagement.response.employeeApplication;

import java.time.LocalDate;

public class EmployeeApplicationResponse {

	private Long id;
	private String leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private String Status;
	public EmployeeApplicationResponse(Long id, String leaveType, LocalDate startDate, LocalDate endDate,
			String status) {
		super();
		this.id = id;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		Status = status;
	}
	public EmployeeApplicationResponse() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		return "EmployeeApplicationResponse [id=" + id + ", leaveType=" + leaveType + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", Status=" + Status + "]";
	}
	
}
