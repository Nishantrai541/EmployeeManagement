package com.userAuthentication.userAuthentication.controller.microservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userAuthentication.userAuthentication.response.microservice.EmployeeDetails;
import com.userAuthentication.userAuthentication.response.microservice.EmployeeList;
import com.userAuthentication.userAuthentication.response.microservice.ManagerDetail;
import com.userAuthentication.userAuthentication.service.microservice.MicroserviceProviderService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/microservice")
public class MicroserviceProviderController {
	
	private MicroserviceProviderService microService;

	public MicroserviceProviderController(MicroserviceProviderService microService) {
		super();
		this.microService = microService;
	}
	

	@GetMapping
	public ManagerDetail getManagerEmail(HttpServletRequest request) throws Exception {
		
		return microService.getManagerEmail(request);
	}
	
	@GetMapping("/employeeRole")
	public EmployeeDetails getEmailResisterConfirmation(HttpServletRequest request, @RequestParam(required=true) String empEmail) throws Exception {
		
		return microService.getEmailResisterConfirmation(request, empEmail);
	}
	
	@GetMapping("/get/login-user-role")
	public EmployeeDetails getLoginUserRole(HttpServletRequest request) throws Exception {
		
		return microService.getLoginUserRole(request);
	}
	
	@GetMapping("/employeeList")
	public EmployeeList getEmployeeEmail(HttpServletRequest request) throws Exception {
		
		return microService.getEmployeeEmail(request);
	}
	
	@GetMapping("/is-manager")
	public boolean getIsManager(HttpServletRequest request,@RequestParam String empEmail) throws Exception{
		
		return microService.getIsManager(request, empEmail);
	}
	
}
