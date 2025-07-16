package com.taskManagement.taskManagement.service.statuslist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.taskManagement.taskManagement.dao.statusNameList.StatusNameListDAO;
import com.taskManagement.taskManagement.external.services.EmployeeDetailsExternalService;
import com.taskManagement.taskManagement.model.statusNameList.StatusNameListModel;
import com.taskManagement.taskManagement.request.microservice.EmployeeDetails;
import com.taskManagement.taskManagement.request.statusName.StatusNameRequest;

@Service
public class StatusListService {

	@Autowired
	private EmployeeDetailsExternalService empDetailsService;
	
	@Autowired
	private StatusNameListDAO statusNameListDAO;
	
	private StatusNameListModel statusNameListModel;
	
	
	// add status name
	public ResponseEntity<?> addStatusName(StatusNameRequest request)  throws Exception{
		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if(request==null || request.getStatusName().isEmpty() || request.getStatusName()==null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("field could not be empty");
			}
			String role=loginDetail.getEmpRole().toUpperCase();
			if(role.equals("ADMIN") || role.equals("MANAGER")) {
				statusNameListModel=statusNameListDAO.findBystatusName(request.getStatusName().toUpperCase());
				if(statusNameListModel == null) {
					StatusNameListModel status=new StatusNameListModel();
					status.setStatusName(request.getStatusName().toUpperCase());
					status=statusNameListDAO.save(status);
					if(status !=null) {
						return ResponseEntity.status(HttpStatus.CREATED).body("New status name added successfully");
					}else {
						return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong");
					}
				}else {
					return ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("status name already created with :"+request.getStatusName().toUpperCase()+" Name");
				}
				
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admin or Manager can add status name");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	// get status name list
	public ResponseEntity<?> getStatusNameList() throws Exception{
		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			List<StatusNameListModel> statusNameList=new ArrayList<>();
			statusNameList=statusNameListDAO.findAll();
			if(statusNameList.isEmpty() || statusNameList==null) {
				return ResponseEntity.status(HttpStatus.OK).body("Option list is Empty");
			}else {
				List<String> response=new ArrayList<String>();
				for(StatusNameListModel status:statusNameList) {
					String statusName=status.getStatusName().toUpperCase();
					response.add(statusName);
				}
				return ResponseEntity.status(HttpStatus.OK).body(response);
			}
	
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}


	// delete status name
	public ResponseEntity<?> deleteStatusName(StatusNameRequest request) throws Exception{
		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			String role=loginDetail.getEmpRole().toUpperCase();
			if(role.equals("ADMIN")) {
				statusNameListModel=statusNameListDAO.findBystatusName(request.getStatusName().toUpperCase());
				if(statusNameListModel != null) {
					statusNameListDAO.delete(statusNameListModel);
					return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
				}else {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not have any name with this :"+request.getStatusName().toUpperCase()+" status option name");
				}
			}else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("only admin can delete this status name option");
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
