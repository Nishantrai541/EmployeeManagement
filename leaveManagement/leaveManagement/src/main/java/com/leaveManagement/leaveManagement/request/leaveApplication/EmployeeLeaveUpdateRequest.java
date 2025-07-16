package com.leaveManagement.leaveManagement.request.leaveApplication;

import java.time.LocalDate;

public class EmployeeLeaveUpdateRequest {

	private Long id;
	private LocalDate startDate;
	private LocalDate endDate;
	public EmployeeLeaveUpdateRequest(Long id, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	public EmployeeLeaveUpdateRequest() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
		return "EmployeeLeaveUpdateRequest [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}
	
}
