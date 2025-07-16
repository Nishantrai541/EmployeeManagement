package com.taskManagement.taskManagement.external.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.taskManagement.taskManagement.request.microservice.EmployeeDetails;
import com.taskManagement.taskManagement.request.microservice.EmployeeList;
import com.taskManagement.taskManagement.request.microservice.ManagerDetail;

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
