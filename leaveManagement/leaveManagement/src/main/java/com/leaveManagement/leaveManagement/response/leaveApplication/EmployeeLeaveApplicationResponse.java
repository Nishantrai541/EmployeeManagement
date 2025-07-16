package com.leaveManagement.leaveManagement.response.leaveApplication;

import java.util.Date;

public class EmployeeLeaveApplicationResponse {
	
	private Long leaveId;
	private String leaveType;
	private Date startDate;
	private Date endDate;
	private String Status;
	public EmployeeLeaveApplicationResponse(Long leaveId, String leaveType, Date startDate, Date endDate,
			String status) {
		super();
		this.leaveId = leaveId;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		Status = status;
	}
	public EmployeeLeaveApplicationResponse() {
		super();
	}
	public Long getLeaveId() {
		return leaveId;
	}
	public void setLeaveId(Long leaveId) {
		this.leaveId = leaveId;
	}
	public String getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(String leaveType) {
		this.leaveType = leaveType;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
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
		return "EmployeeLeaveApplicationResponse [leaveId=" + leaveId + ", leaveType=" + leaveType + ", startDate="
				+ startDate + ", endDate=" + endDate + ", Status=" + Status + "]";
	}
}
