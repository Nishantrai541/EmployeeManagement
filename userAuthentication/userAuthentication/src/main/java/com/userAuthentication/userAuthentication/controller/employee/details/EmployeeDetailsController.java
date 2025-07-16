package com.userAuthentication.userAuthentication.controller.employee.details;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userAuthentication.userAuthentication.request.employee.details.EmployeeDetailsRequest;
import com.userAuthentication.userAuthentication.response.employee.details.EmployeeDetailsResponse;
import com.userAuthentication.userAuthentication.response.employee.details.EmployeeDetailsWithSalaryResponse;
import com.userAuthentication.userAuthentication.service.employee.details.EmployeeDetailsService;



@RestController
@RequestMapping("/api/user-details")
public class EmployeeDetailsController {

	private EmployeeDetailsService employeeDetailsService;
		
	public EmployeeDetailsController(EmployeeDetailsService employeeDetailsService) {
		super();
		this.employeeDetailsService = employeeDetailsService;
	}


	@GetMapping()
	public EmployeeDetailsWithSalaryResponse getDetails(HttpServletRequest request) throws Exception{
		
		return employeeDetailsService.getDetails(request);
	}
	
	
	@GetMapping(path="/employee")
	public EmployeeDetailsWithSalaryResponse getEmployeeDetails(HttpServletRequest request, @RequestParam(required=true)String userName) throws Exception{
		
		return employeeDetailsService.getEmployeeDetails(request, userName);
	}
	
	@PostMapping()
	public EmployeeDetailsResponse updateEmployeeDetails(HttpServletRequest request,@RequestBody() EmployeeDetailsRequest employeeUpdateDetails) throws Exception {
	
		
		return employeeDetailsService.updateEmployeeDetails(request, employeeUpdateDetails);
	}
	
}
