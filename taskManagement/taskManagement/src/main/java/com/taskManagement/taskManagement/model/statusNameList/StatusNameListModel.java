package com.taskManagement.taskManagement.model.statusNameList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name="task-status-menu-list")
@Entity
public class StatusNameListModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(unique=true)
	private String statusName;
	public StatusNameListModel(Long id, String statusName) {
		super();
		this.id = id;
		this.statusName = statusName;
	}
	public StatusNameListModel() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	@Override
	public String toString() {
		return "StatusListModel [id=" + id + ", statusName=" + statusName + "]";
	}
}
