package com.leaveManagement.leaveManagement.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestParam;

import com.leaveManagement.leaveManagement.request.microservice.EmployeeDetails;
import com.leaveManagement.leaveManagement.request.microservice.ManagerDetail;
import com.leaveManagement.leaveManagement.request.microservice.EmployeeList;

@FeignClient(name = "USERAUTHENTICATION")
public interface EmployeeDetailsExternalService {

	@GetMapping("/api/microservice")
	ManagerDetail getManager();

	@GetMapping("/api/microservice/employeeRole")
	EmployeeDetails getEmailResisterConfirmation(@RequestParam(required = true) String empEmail);

	@GetMapping("/api/microservice/get/login-user-role")
	EmployeeDetails getLoginUserRole();
	
	@GetMapping("/api/microservice/employeeList")
	EmployeeList getEmpListUnderManager();
	
	@GetMapping("/api/microservice/is-manager")
	boolean getIsManager(@RequestParam String empEmail);
	
}
