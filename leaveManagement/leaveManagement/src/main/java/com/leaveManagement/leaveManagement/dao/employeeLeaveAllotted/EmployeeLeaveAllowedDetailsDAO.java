package com.leaveManagement.leaveManagement.dao.employeeLeaveAllotted;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.leaveManagement.leaveManagement.model.employeeLeaveAlloted.EmployeeLeaveAllowedDetailsModel;

public interface EmployeeLeaveAllowedDetailsDAO extends JpaRepository<EmployeeLeaveAllowedDetailsModel, Long> {

	List<EmployeeLeaveAllowedDetailsModel> findByemployeeEmail(String empEmail);

	List<EmployeeLeaveAllowedDetailsModel> findByleaveAllowedType(String leaveType);

	@Query("SELECT um FROM EmployeeLeaveAllowedDetailsModel um WHERE um.employeeEmail in (:email) AND um.leaveAllowedType in (:leaveType)")
	public EmployeeLeaveAllowedDetailsModel findLeaveType(String email, String leaveType);

}
