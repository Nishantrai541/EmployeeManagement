package com.leaveManagement.leaveManagement.model.employeeLeaveApplication;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="employee-leave-application")
@Entity
public class EmployeeLeaveApplicationModel {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String employeeEmail;
	private String leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private String Status;
	public EmployeeLeaveApplicationModel(Long id, String employeeEmail, String leaveType, LocalDate startDate, LocalDate endDate,
			String status) {
		super();
		this.id = id;
		this.employeeEmail = employeeEmail;
		this.leaveType = leaveType;
		this.startDate = startDate;
		this.endDate = endDate;
		Status = status;
	}
	public EmployeeLeaveApplicationModel() {
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
		return "EmployeeLeaveApplicationModel [id=" + id + ", employeeEmail=" + employeeEmail + ", leaveType="
				+ leaveType + ", startDate=" + startDate + ", endDate=" + endDate + ", Status=" + Status + "]";
	}
	
	
}
