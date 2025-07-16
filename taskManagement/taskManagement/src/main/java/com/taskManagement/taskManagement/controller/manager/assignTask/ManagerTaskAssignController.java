package com.taskManagement.taskManagement.controller.manager.assignTask;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManagement.taskManagement.request.manager.assignTask.ManagerAssignTaskRequest;
import com.taskManagement.taskManagement.request.manager.assignTask.ManagerCommentRequest;
import com.taskManagement.taskManagement.service.manager.assignTask.ManagerTaskAssignService;

@RestController
@RequestMapping("task/employeeTask")
public class ManagerTaskAssignController {

	private ManagerTaskAssignService managerTaskAssignservice;

	public ManagerTaskAssignController(ManagerTaskAssignService managerTaskAssignservice) {
		super();
		this.managerTaskAssignservice = managerTaskAssignservice;
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAllEmployeeTaskList() throws Exception{
		
		return managerTaskAssignservice.getAllEmployeeTaskList();
	}
	
	@PostMapping
	public ResponseEntity<?> addEmployeeTask(@RequestBody ManagerAssignTaskRequest addRequest) throws Exception{
		
		return managerTaskAssignservice.addEmployeeTask(addRequest);
	}
	
	
	@PutMapping
	public ResponseEntity<?> addTaskComment(@RequestBody ManagerCommentRequest addComment) throws Exception{
		
		return managerTaskAssignservice.addTaskComment(addComment);
	}
	
}
