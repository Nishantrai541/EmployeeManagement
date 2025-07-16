package com.taskManagement.taskManagement.dao.statusNameList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.taskManagement.taskManagement.model.statusNameList.StatusNameListModel;

public interface StatusNameListDAO extends JpaRepository<StatusNameListModel, Long> {

	public StatusNameListModel findBystatusName(String statusName);
}
