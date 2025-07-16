package com.taskManagement.taskManagement.response.employeeTask;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmpolyeeTaskResponse {

	private Long taskId;
	private String taskAssign;
	private String description;
	private String priority;
	private List<CommentResponse> commentResponse;
	private LocalDate assignDate;
	private LocalDate updateDate;
	private String status;

	public EmpolyeeTaskResponse(Long taskId, String taskAssign, String description, String priority,
			List<CommentResponse> commentResponse, LocalDate assignDate, LocalDate updateDate, String status) {
		super();
		this.taskId = taskId;
		this.taskAssign = taskAssign;
		this.description = description;
		this.priority = priority;
		this.commentResponse = commentResponse;
		this.assignDate = assignDate;
		this.updateDate = updateDate;
		this.status = status;
	}
	public EmpolyeeTaskResponse() {
		super();
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getTaskAssign() {
		return taskAssign;
	}
	public void setTaskAssign(String taskAssign) {
		this.taskAssign = taskAssign;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public List<CommentResponse> getCommentResponse() {
		return commentResponse;
	}
	public void setCommentResponse(List<CommentResponse> commentResponse) {
		this.commentResponse = commentResponse;
	}
	public LocalDate getAssignDate() {
		return assignDate;
	}
	public void setAssignDate(LocalDate assignDate) {
		this.assignDate = assignDate;
	}
	public LocalDate getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(LocalDate updateDate) {
		this.updateDate = updateDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "EmpolyeeTaskResponse [taskId=" + taskId + ", taskAssign=" + taskAssign + ", description=" + description
				+ ", priority=" + priority + ", commentResponse=" + commentResponse + ", assignDate=" + assignDate
				+ ", updateDate=" + updateDate + ", status=" + status + "]";
	}
	
}
