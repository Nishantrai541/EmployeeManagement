package com.taskManagement.taskManagement.controller.statuslist;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManagement.taskManagement.request.statusName.StatusNameRequest;
import com.taskManagement.taskManagement.service.statuslist.StatusListService;

@RestController
@RequestMapping("/task/statusName")
public class StatusListController {

	private StatusListService statusListService;

	public StatusListController(StatusListService statusListService) {
		super();
		this.statusListService = statusListService;
	}
	
	@GetMapping
	public ResponseEntity<?> getStatusNameList()throws Exception{
		
		return statusListService.getStatusNameList();
	}
	
	@PostMapping
	public ResponseEntity<?> addStatusName(@RequestBody StatusNameRequest request) throws Exception{
		
		return statusListService.addStatusName(request);
	}
	
	@DeleteMapping
	public ResponseEntity<?> deleteStatusName(@RequestBody StatusNameRequest request) throws Exception{
		
		return statusListService.deleteStatusName(request);
	}
}
