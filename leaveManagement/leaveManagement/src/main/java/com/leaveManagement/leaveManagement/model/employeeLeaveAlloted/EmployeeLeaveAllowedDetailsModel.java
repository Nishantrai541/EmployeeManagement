package com.leaveManagement.leaveManagement.model.employeeLeaveAlloted;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="leave-allowed-for-employee")
@Entity
public class EmployeeLeaveAllowedDetailsModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private Long id;
	private String employeeEmail;
	private String leaveAllowedType;
	private Long numberOfDayAllowed;
	private Long numberOfDaysTaken;
	public EmployeeLeaveAllowedDetailsModel(Long id, String employeeEmail, String leaveAllowedType,
			Long numberOfDayAllowed, Long numberOfDaysTaken) {
		super();
		this.id = id;
		this.employeeEmail = employeeEmail;
		this.leaveAllowedType = leaveAllowedType;
		this.numberOfDayAllowed = numberOfDayAllowed;
		this.numberOfDaysTaken = numberOfDaysTaken;
	}
	public EmployeeLeaveAllowedDetailsModel() {
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
	public String getLeaveAllowedType() {
		return leaveAllowedType;
	}
	public void setLeaveAllowedType(String leaveAllowedType) {
		this.leaveAllowedType = leaveAllowedType;
	}
	public Long getNumberOfDayAllowed() {
		return numberOfDayAllowed;
	}
	public void setNumberOfDayAllowed(Long numberOfDayAllowed) {
		this.numberOfDayAllowed = numberOfDayAllowed;
	}
	public Long getNumberOfDaysTaken() {
		return numberOfDaysTaken;
	}
	public void setNumberOfDaysTaken(Long numberOfDaysTaken) {
		this.numberOfDaysTaken = numberOfDaysTaken;
	}
	@Override
	public String toString() {
		return "EmployeeLeaveAllowedDetailsModel [id=" + id + ", employeeEmail=" + employeeEmail + ", leaveAllowedType="
				+ leaveAllowedType + ", numberOfDayAllowed=" + numberOfDayAllowed + ", numberOfDaysTaken="
				+ numberOfDaysTaken + "]";
	}
	
}
