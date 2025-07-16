package com.taskManagement.taskManagement.controller.employeeTask;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.taskManagement.taskManagement.request.employee.updateTaskStatus.UpdateTaskStatusRequest;
import com.taskManagement.taskManagement.service.employeeTask.EmployeeTaskService;

@RestController
@RequestMapping("task/getTask")
public class EmployeeTaskController {

	private EmployeeTaskService employeeTaskService;

	public EmployeeTaskController(EmployeeTaskService employeeTaskService) {
		super();
		this.employeeTaskService = employeeTaskService;
	}
	
	
	@GetMapping
	public ResponseEntity<?> getAssignTask() throws Exception{
		
		return employeeTaskService.getAssignTask();
	}
	
	@PutMapping
	public ResponseEntity<?> updateAssignTaskStatus(@RequestBody UpdateTaskStatusRequest updateTask) throws Exception{
		
		return employeeTaskService.updateAssignTaskStatus(updateTask);
	}
}
