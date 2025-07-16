package com.taskManagement.taskManagement.request.manager.assignTask;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerAssignTaskRequest {

	private String empEmail;
	private String taskName;
	private String taskDescription;
	private String priority;
	private String comment;
	public ManagerAssignTaskRequest(String empEmail, String taskName, String taskDescription, String priority,
			String comment) {
		super();
		this.empEmail = empEmail;
		this.taskName = taskName;
		this.taskDescription = taskDescription;
		this.priority = priority;
		this.comment = comment;
	}
	public ManagerAssignTaskRequest() {
		super();
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "ManagerAssignTaskRequest [empEmail=" + empEmail + ", taskName=" + taskName + ", taskDescription="
				+ taskDescription + ", priority=" + priority + ", comment=" + comment + "]";
	}
	
}
