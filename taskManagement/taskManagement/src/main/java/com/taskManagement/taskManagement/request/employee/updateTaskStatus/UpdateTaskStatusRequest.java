package com.taskManagement.taskManagement.request.employee.updateTaskStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateTaskStatusRequest {

	private Long taskId;
	private String comment;
	private String status;
	public UpdateTaskStatusRequest(Long taskId, String comment, String status) {
		super();
		this.taskId = taskId;
		this.comment = comment;
		this.status = status;
	}
	public UpdateTaskStatusRequest() {
		super();
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "UpdateTaskStatusRequest [taskId=" + taskId + ", comment=" + comment + ", status=" + status + "]";
	}
	
	
}
