package com.leaveManagement.leaveManagement.controller.managerCheckLeave;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leaveManagement.leaveManagement.request.applicationResponse.manager.ManagerResponseOnApplicationRequest;
import com.leaveManagement.leaveManagement.services.managerCheckLeave.ManagerLeaveSectionService;

@RestController
@RequestMapping("/leave/manager")
public class ManagerLeaveSectionController {

	private ManagerLeaveSectionService managerLeaveSectionService;

	public ManagerLeaveSectionController(ManagerLeaveSectionService managerLeaveSectionService) {
		super();
		this.managerLeaveSectionService = managerLeaveSectionService;
	}
	
	@GetMapping
	public ResponseEntity<?> getLeaveList() throws Exception{
		
		return managerLeaveSectionService.getLeaveList();
	}
	
	@PostMapping
	public ResponseEntity<?> responseApplicationStatus(@RequestBody ManagerResponseOnApplicationRequest applicationResponse) throws Exception{
		
		return managerLeaveSectionService.responseApplicationStatus(applicationResponse);
	}
}
