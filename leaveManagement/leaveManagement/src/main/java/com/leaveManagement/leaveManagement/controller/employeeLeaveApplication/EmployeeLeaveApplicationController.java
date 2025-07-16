package com.leaveManagement.leaveManagement.controller.employeeLeaveApplication;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaveManagement.leaveManagement.request.leaveApplication.EmployeeLeaveApplicationRequest;
import com.leaveManagement.leaveManagement.request.leaveApplication.EmployeeLeaveUpdateRequest;
import com.leaveManagement.leaveManagement.services.employeeLeaveApplication.EmployeeLeaveApplicationService;

@RestController
@RequestMapping("/leave/application")
public class EmployeeLeaveApplicationController {

	private EmployeeLeaveApplicationService empApplicationService;

	public EmployeeLeaveApplicationController(EmployeeLeaveApplicationService empApplicationService) {
		super();
		this.empApplicationService = empApplicationService;
	}

	@GetMapping
	public ResponseEntity<?> getApplication()
			throws Exception {

		return empApplicationService.getApplication();
	}

	@PostMapping
	public ResponseEntity<?> addApplication(@RequestBody EmployeeLeaveApplicationRequest applicationRequest)
			throws Exception {

		return empApplicationService.addApplication(applicationRequest);
	}
	
	@PutMapping
	public ResponseEntity<?> UpdateApplication(@RequestBody  EmployeeLeaveUpdateRequest updateRequest)
			throws Exception {

		return empApplicationService.updateApplication(updateRequest);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteApplication(@RequestBody Long applicationId)
			throws Exception {

		return empApplicationService.deleteApplication(applicationId);
	}

}
