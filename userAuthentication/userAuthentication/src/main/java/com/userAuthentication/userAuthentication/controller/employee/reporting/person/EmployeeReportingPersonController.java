package com.userAuthentication.userAuthentication.controller.employee.reporting.person;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userAuthentication.userAuthentication.request.employee.reporting.person.EmployeeReportingRequest;
import com.userAuthentication.userAuthentication.service.employee.reporting.person.EmployeeReportingPersonService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/employee-reporting-person")
public class EmployeeReportingPersonController {
	
	private EmployeeReportingPersonService employeeReportingPerson;
	
	public EmployeeReportingPersonController(EmployeeReportingPersonService employeeReportingPerson) {
		super();
		this.employeeReportingPerson = employeeReportingPerson;
	}


	@GetMapping
	public ResponseEntity<?> getEmployeeManager(HttpServletRequest request){
		
		return employeeReportingPerson.getEmployeeManager(request);
	}
	
	@GetMapping("/manager")
	public ResponseEntity<?> getTeamList(HttpServletRequest request){
		
		return employeeReportingPerson.getTeamList(request);
	}
	
	@GetMapping("/manager/email")
	public ResponseEntity<?>getTeamListOnManagerEmail(HttpServletRequest request,@RequestParam(required=true)String managerEmail){
		
		return employeeReportingPerson.getTeamListOnManagerEmail(request, managerEmail);
	}
	
	@PostMapping
	public ResponseEntity<String> assignEmployeeManager(HttpServletRequest request,@RequestBody EmployeeReportingRequest reportingPerson) throws Exception{
	
		return employeeReportingPerson.assignEmployeeManager(request,reportingPerson);
	}
	
	@DeleteMapping("/manager/email")
	public ResponseEntity<?> deleteEmployeeManager(HttpServletRequest request,@RequestParam(required=true)String employeeEmail){
		
		return employeeReportingPerson.deleteEmployeeManager(request, employeeEmail);
	}

}
