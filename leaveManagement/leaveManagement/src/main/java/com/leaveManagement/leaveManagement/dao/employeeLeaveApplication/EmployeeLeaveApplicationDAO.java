package com.leaveManagement.leaveManagement.dao.employeeLeaveApplication;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leaveManagement.leaveManagement.model.employeeLeaveApplication.EmployeeLeaveApplicationModel;

public interface EmployeeLeaveApplicationDAO extends JpaRepository<EmployeeLeaveApplicationModel, Long>{
	
	List<EmployeeLeaveApplicationModel> findByemployeeEmail(String empEmail);

}
