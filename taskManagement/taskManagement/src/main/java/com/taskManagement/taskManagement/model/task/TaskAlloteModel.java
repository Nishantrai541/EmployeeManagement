package com.taskManagement.taskManagement.model.task;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name="task-alloted-model")
@Entity
public class TaskAlloteModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long taskId;
	private String empEmail;
	private String taskAssign;
	private String description;
	private String priority;
	@OneToMany(mappedBy="taskAlloteModel", cascade=CascadeType.ALL)
	private List<TaskCommentModel> taskCommentList;
	private LocalDate assignDate;
	private LocalDate updateDate;
	private String status;
	
	
	public TaskAlloteModel(Long taskId, String empEmail, String taskAssign, String description, String priority,
			List<TaskCommentModel> taskCommentList, LocalDate assignDate, LocalDate updateDate, String status) {
		super();
		this.taskId = taskId;
		this.empEmail = empEmail;
		this.taskAssign = taskAssign;
		this.description = description;
		this.priority = priority;
		this.taskCommentList = taskCommentList;
		this.assignDate = assignDate;
		this.updateDate = updateDate;
		this.status = status;
	}
	public TaskAlloteModel() {
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
	
	public List<TaskCommentModel> getTaskCommentList() {
		return taskCommentList;
	}
	public void setTaskCommentList(List<TaskCommentModel> taskCommentList) {
		this.taskCommentList = taskCommentList;
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
		return "TaskAlloteModel [taskId=" + taskId + ", empEmail=" + empEmail + ", taskAssign=" + taskAssign
				+ ", description=" + description + ", priority=" + priority + ", taskCommentList=" + taskCommentList
				+ ", assignDate=" + assignDate + ", updateDate=" + updateDate + ", status=" + status + "]";
	}
	
}
