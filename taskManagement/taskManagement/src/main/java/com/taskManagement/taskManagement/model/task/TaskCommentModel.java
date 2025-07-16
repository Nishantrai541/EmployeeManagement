package com.taskManagement.taskManagement.model.task;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Table(name="task-comment")
@Entity
public class TaskCommentModel {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String comment;
	@ManyToOne
	private TaskAlloteModel taskAlloteModel;
	public TaskCommentModel(Long id, String comment, TaskAlloteModel taskAlloteModel) {
		super();
		this.id = id;
		this.comment = comment;
		this.taskAlloteModel = taskAlloteModel;
	}
	public TaskCommentModel() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public TaskAlloteModel getTaskAlloteModel() {
		return taskAlloteModel;
	}
	public void setTaskAlloteModel(TaskAlloteModel taskAlloteModel) {
		this.taskAlloteModel = taskAlloteModel;
	}
	@Override
	public String toString() {
		return "TaskCommentModel [id=" + id + ", comment=" + comment + ", taskAlloteModel=" + taskAlloteModel + "]";
	}
}
