package com.userAuthentication.userAuthentication.dao.employee.reporting.person;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.userAuthentication.userAuthentication.model.employee.reporting.person.EmployeeReportingPersonModel;

public interface EmployeeReportingPersonDAO extends JpaRepository<EmployeeReportingPersonModel, Long>{

	public EmployeeReportingPersonModel findByemployeeEmail(String email);
	
	public List<EmployeeReportingPersonModel> findBymanagerEmail(String email);
}
