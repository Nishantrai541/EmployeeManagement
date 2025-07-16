package com.taskManagement.taskManagement.request.manager.assignTask;

public class ManagerCommentRequest {

	private Long taskId;
	private String empEmail;
	private String comment;
	
	public ManagerCommentRequest(Long taskId, String empEmail, String comment) {
		super();
		this.taskId = taskId;
		this.empEmail = empEmail;
		this.comment = comment;
	}
	public ManagerCommentRequest() {
		super();
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "ManagerCommentRequest [taskId=" + taskId + ", empEmail=" + empEmail + ", comment=" + comment + "]";
	}
}
