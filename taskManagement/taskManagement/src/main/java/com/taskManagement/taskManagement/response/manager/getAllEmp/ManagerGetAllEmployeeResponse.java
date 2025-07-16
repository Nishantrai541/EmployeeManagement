package com.taskManagement.taskManagement.response.manager.getAllEmp;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerGetAllEmployeeResponse {

	private Long taskId;
	private String empEmail;
	private String empName;
	private String empDesignation;
	private String empRole;
	private String taskAssign;
	private String description;
	private String priority;
	private List<MgrCommentResponse> commentList;
	private LocalDate assignDate;
	private LocalDate updateDate;
	private String status;
	
	public ManagerGetAllEmployeeResponse(Long taskId, String empEmail, String empName, String empDesignation,
			String empRole, String taskAssign, String description, String priority,
			List<MgrCommentResponse> commentList, LocalDate assignDate, LocalDate updateDate, String status) {
		super();
		this.taskId = taskId;
		this.empEmail = empEmail;
		this.empName = empName;
		this.empDesignation = empDesignation;
		this.empRole = empRole;
		this.taskAssign = taskAssign;
		this.description = description;
		this.priority = priority;
		this.commentList = commentList;
		this.assignDate = assignDate;
		this.updateDate = updateDate;
		this.status = status;
	}
	public ManagerGetAllEmployeeResponse() {
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
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpDesignation() {
		return empDesignation;
	}
	public void setEmpDesignation(String empDesignation) {
		this.empDesignation = empDesignation;
	}
	public String getEmpRole() {
		return empRole;
	}
	public void setEmpRole(String empRole) {
		this.empRole = empRole;
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
	public List<MgrCommentResponse> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<MgrCommentResponse> commentList) {
		this.commentList = commentList;
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
		return "ManagerGetAllEmployeeResponse [taskId=" + taskId + ", empEmail=" + empEmail + ", empName=" + empName
				+ ", empDesignation=" + empDesignation + ", empRole=" + empRole + ", taskAssign=" + taskAssign
				+ ", description=" + description + ", priority=" + priority + ", commentList=" + commentList
				+ ", assignDate=" + assignDate + ", updateDate=" + updateDate + ", status=" + status + "]";
	}
		
}
