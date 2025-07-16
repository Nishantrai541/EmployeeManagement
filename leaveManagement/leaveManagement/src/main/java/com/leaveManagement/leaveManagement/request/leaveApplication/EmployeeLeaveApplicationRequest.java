package com.leaveManagement.leaveManagement.request.leaveApplication;

import java.time.LocalDate;

public class EmployeeLeaveApplicationRequest {

	private String leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	public EmployeeLeaveApplicationRequest(String leaveType, LocalDate startDate, LocalDate endDate) {
		super();
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public EmployeeLeaveApplicationRequest() {
		super();
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
	@Override
	public String toString() {
		return "EmployeeLeaveApplication [leaveType=" + leaveType + ", startDate=" + startDate + ", endDate=" + endDate
				+ "]";
	}
	
}
