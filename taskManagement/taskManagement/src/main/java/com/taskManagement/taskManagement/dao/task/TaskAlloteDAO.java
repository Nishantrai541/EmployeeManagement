package com.taskManagement.taskManagement.dao.task;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskManagement.taskManagement.model.task.TaskAlloteModel;

public interface TaskAlloteDAO extends JpaRepository<TaskAlloteModel, Long>{

	public TaskAlloteModel findBytaskId(Long taskId);
	public List<TaskAlloteModel> findByempEmail(String empEmail); 
	
	@Query
	("Select um FROM TaskAlloteModel um WHERE um.empEmail in (:taskId) AND um.taskId in (:empEmail)")
	public TaskAlloteModel findSpecificTask(Long taskId, String empEmail);
}
