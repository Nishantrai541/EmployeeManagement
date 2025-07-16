package com.leaveManagement.leaveManagement.controller.employeeLeaveAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.leaveManagement.leaveManagement.request.leaveSenction.EmployeeLeaveSenction;
import com.leaveManagement.leaveManagement.services.employeeLeaveAllowed.EmployeeLeaveAllowedService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/leave/breakUp")
public class EmployeeLeaveBreakUpController {
	
	@Autowired
	private EmployeeLeaveAllowedService empService;
	
	
//	@GetMapping
//	public ResponseEntity<?> addLeaveBreakup(HttpServletRequest request) throws Exception{
//		
//		return empService.addLeaveBreakup();
//	}

	@GetMapping("/get")
	public ResponseEntity<?> getLeaveSenctionDetails(HttpServletRequest request) throws Exception{
		
		return empService.getLeaveSenctionDetails();
	}
	
	@PostMapping("/add")
	public ResponseEntity<?> addLeaveBreakUp(@RequestBody EmployeeLeaveSenction leaveBreakup) throws Exception{
		
		return empService.addLeaveBreakup(leaveBreakup);
	}
	
//	@GetMapping("/employee/role")
//	public ResponseEntity<?> getEmployeeRole(HttpServletRequest request, @RequestParam(required=true) String empEmail) throws Exception{
//		
//		return empService.getEmployeeRole(empEmail);
//	}

}
