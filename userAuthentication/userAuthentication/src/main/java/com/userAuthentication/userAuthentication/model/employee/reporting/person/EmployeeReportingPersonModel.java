package com.userAuthentication.userAuthentication.model.employee.reporting.person;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="employee-reporting-person-Model")
@Entity
public class EmployeeReportingPersonModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	@Column(unique=true)
	private String employeeEmail;
	private String managerEmail;
	public EmployeeReportingPersonModel(Long id, String employeeEmail, String managerEmail) {
		super();
		this.id = id;
		this.employeeEmail = employeeEmail;
		this.managerEmail = managerEmail;
	}
	public EmployeeReportingPersonModel() {
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
	public String getManagerEmail() {
		return managerEmail;
	}
	public void setManagerEmail(String managerEmail) {
		this.managerEmail = managerEmail;
	}
	@Override
	public String toString() {
		return "EmployeeReportingPersonModel [id=" + id + ", employeeEmail=" + employeeEmail + ", managerEmail="
				+ managerEmail + "]";
	}
	
}
