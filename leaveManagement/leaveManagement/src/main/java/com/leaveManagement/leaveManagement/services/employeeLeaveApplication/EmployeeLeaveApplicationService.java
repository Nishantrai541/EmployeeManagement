package com.leaveManagement.leaveManagement.services.employeeLeaveApplication;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.leaveManagement.leaveManagement.dao.employeeLeaveAllotted.EmployeeLeaveAllowedDetailsDAO;
import com.leaveManagement.leaveManagement.dao.employeeLeaveApplication.EmployeeLeaveApplicationDAO;
import com.leaveManagement.leaveManagement.external.services.EmployeeDetailsExternalService;
import com.leaveManagement.leaveManagement.model.employeeLeaveAlloted.EmployeeLeaveAllowedDetailsModel;
import com.leaveManagement.leaveManagement.model.employeeLeaveApplication.EmployeeLeaveApplicationModel;
import com.leaveManagement.leaveManagement.request.leaveApplication.EmployeeLeaveApplicationRequest;
import com.leaveManagement.leaveManagement.request.leaveApplication.EmployeeLeaveUpdateRequest;
import com.leaveManagement.leaveManagement.request.microservice.EmployeeDetails;
import com.leaveManagement.leaveManagement.response.employeeApplication.EmployeeApplicationResponse;

@Service
public class EmployeeLeaveApplicationService {
	@Autowired
	private EmployeeDetailsExternalService empDetailsService;

	@Autowired
	private EmployeeLeaveAllowedDetailsDAO leaveDAO;

	@Autowired
	private EmployeeLeaveApplicationDAO empApplicationDAO;

	private EmployeeLeaveAllowedDetailsModel leaveModel;

	// Add application
	public ResponseEntity<?> addApplication(EmployeeLeaveApplicationRequest applicationRequest) throws Exception {

		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if (applicationRequest == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("application request is Empty");
			}
			if (applicationRequest.getLeaveType() == null || applicationRequest.getLeaveType().isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Leave type is required");
			}
			if (applicationRequest.getStartDate() == null
					|| applicationRequest.getStartDate().isBefore(LocalDate.now())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Starting Date is empty or Before today Date");
			}
			if (applicationRequest.getEndDate() == null
					|| applicationRequest.getEndDate().isBefore(applicationRequest.getStartDate())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("End Date is Empty or before the Starting Date");
			}
			if (applicationRequest.getStartDate().isEqual(applicationRequest.getEndDate())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Starting and End date both are not be same");
			}

			leaveModel = leaveDAO.findLeaveType(loginDetail.getEmpEmail().toLowerCase(),
					applicationRequest.getLeaveType().toUpperCase());
			if (leaveModel == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						"you do not alloted" + applicationRequest.getLeaveType().toUpperCase() + " this Type Leave");
			}
			Long balanceDays = (leaveModel.getNumberOfDayAllowed() - leaveModel.getNumberOfDaysTaken());
			if ((leaveModel.getNumberOfDayAllowed() - leaveModel.getNumberOfDaysTaken()) <= 0) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						"you do not alloted (" + applicationRequest.getLeaveType().toUpperCase() + ") Type Leave");
			}
			Long leavePeriod = ChronoUnit.DAYS.between(applicationRequest.getStartDate(),
					applicationRequest.getEndDate())+1L;
			if (leavePeriod > balanceDays) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("you do not have that much of days left in particular leave section, only have :"
								+ balanceDays + " left");
			} else {

				EmployeeLeaveApplicationModel leaveApplication = new EmployeeLeaveApplicationModel();
				leaveApplication.setEmployeeEmail(loginDetail.getEmpEmail().toLowerCase());
				leaveApplication.setLeaveType(applicationRequest.getLeaveType().toUpperCase());
				leaveApplication.setStartDate(applicationRequest.getStartDate());
				leaveApplication.setEndDate(applicationRequest.getEndDate());
				leaveApplication.setStatus("PENDING");
				leaveApplication = empApplicationDAO.save(leaveApplication);
				if (leaveApplication == null) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went Wrong");
				} else {
					return ResponseEntity.status(HttpStatus.CREATED).body("created successfully");

				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Get application
	public ResponseEntity<?> getApplication() throws Exception {
		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			List<EmployeeLeaveApplicationModel> listOfLeave = empApplicationDAO
					.findByemployeeEmail(loginDetail.getEmpEmail().toLowerCase());
			if (listOfLeave == null || listOfLeave.isEmpty()) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not apply for any Leave Till now");
			} else {
				List<EmployeeApplicationResponse> listOfLeaveResponse = new ArrayList<EmployeeApplicationResponse>();
				for (EmployeeLeaveApplicationModel application : listOfLeave) {
					EmployeeApplicationResponse empApplication = new EmployeeApplicationResponse();
					empApplication.setId(application.getId());
					empApplication.setLeaveType(application.getLeaveType());
					empApplication.setStartDate(application.getStartDate());
					empApplication.setEndDate(application.getEndDate());
					empApplication.setStatus(application.getStatus());
					listOfLeaveResponse.add(empApplication);

				}
				listOfLeaveResponse.sort((e1, e2) -> e1.getStartDate().compareTo(e2.getStartDate()));
				return ResponseEntity.status(HttpStatus.OK).body(listOfLeaveResponse);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	
	// Update Employee Application
	public ResponseEntity<?> updateApplication(EmployeeLeaveUpdateRequest updateRequest) {

		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if (updateRequest == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("application request is Empty");
			}
			if (updateRequest.getStartDate() == null
					|| updateRequest.getStartDate().isBefore(LocalDate.now())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Starting Date is empty or Before today Date");
			}
			if (updateRequest.getEndDate() == null
					|| updateRequest.getEndDate().isBefore(updateRequest.getStartDate())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("End Date is Empty or before the Starting Date");
			}
			if (updateRequest.getStartDate().isEqual(updateRequest.getEndDate())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Starting and End date both are not be same");
			}
			Optional<EmployeeLeaveApplicationModel> applicationOption=empApplicationDAO.findById(updateRequest.getId());
			if(applicationOption==null || applicationOption.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not have any application with this application id :"+updateRequest.getId());
			}else{
				EmployeeLeaveApplicationModel applicationModel=applicationOption.get();
				if(!applicationModel.getEmployeeEmail().equals(loginDetail.getEmpEmail().toLowerCase())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This application id is not belong to you");
				}else if(applicationModel.getStatus().equals("APPROVED")) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This application is approve you can not do anything on it");
				}
			

			leaveModel = leaveDAO.findLeaveType(loginDetail.getEmpEmail().toLowerCase(),
					applicationModel.getLeaveType().toUpperCase());
			if (leaveModel == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						"you do not alloted " + applicationModel.getLeaveType().toUpperCase() + " this Type Leave");
			}
			Long balanceDays = (leaveModel.getNumberOfDayAllowed() - leaveModel.getNumberOfDaysTaken());
			if (balanceDays <= 0) {

				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
						"you do not alloted (" + applicationModel.getLeaveType().toUpperCase() + ") Type Leave");
			}
			Long leavePeriod = ChronoUnit.DAYS.between(updateRequest.getStartDate(),
					updateRequest.getEndDate())+1L;
			if (leavePeriod > balanceDays) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("you do not have that much of days left in particular leave section, only have :"
								+ balanceDays + " left");
			} else { 

				applicationModel.setStartDate(updateRequest.getStartDate());
				applicationModel.setEndDate(updateRequest.getEndDate());				
				applicationModel=empApplicationDAO.save(applicationModel);
				if(applicationModel==null) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
							.body("Something went wrong");
				}else {
					return ResponseEntity.status(HttpStatus.OK)
							.body("Updated successfully");
				}
			}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}



	
	//Delete Pending Application
	public ResponseEntity<?> deleteApplication(Long applicationId) throws Exception{
		
		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			Optional<EmployeeLeaveApplicationModel> applicationOption=empApplicationDAO.findById(applicationId);
			if(applicationOption==null || applicationOption.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not have any application with this application id :"+applicationId);
			}else{
				EmployeeLeaveApplicationModel applicationModel=applicationOption.get();
				if(!applicationModel.getEmployeeEmail().equals(loginDetail.getEmpEmail().toLowerCase())) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("This application id is not belong to you");
				}else if(applicationModel.getStatus().equals("APPROVED")) {
					return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("This application is approve you can not delete it now");
				}else {
					empApplicationDAO.delete(applicationModel);
					return ResponseEntity.status(HttpStatus.OK).body("Application Deleted SuccessFully with applicationId: "+applicationId);
					
				}
			}
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}

