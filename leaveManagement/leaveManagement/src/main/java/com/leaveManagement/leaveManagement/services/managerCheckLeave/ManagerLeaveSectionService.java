package com.leaveManagement.leaveManagement.services.managerCheckLeave;

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
import com.leaveManagement.leaveManagement.request.applicationResponse.manager.ManagerResponseOnApplicationRequest;
import com.leaveManagement.leaveManagement.request.microservice.EmployeeAllDetails;
import com.leaveManagement.leaveManagement.request.microservice.EmployeeDetails;
import com.leaveManagement.leaveManagement.request.microservice.EmployeeList;
import com.leaveManagement.leaveManagement.response.employeeApplication.manager.EmployeeApplicationManagerResponse;

@Service
public class ManagerLeaveSectionService {

	@Autowired
	private EmployeeDetailsExternalService empDetailsService;

	@Autowired
	private EmployeeLeaveAllowedDetailsDAO leaveDAO;

	@Autowired
	private EmployeeLeaveApplicationDAO empApplicationDAO;

	private EmployeeLeaveAllowedDetailsModel leaveModel;

	// Get List of All Employee Leave Application In sort by Leave Starting Date
	public ResponseEntity<?> getLeaveList() throws Exception {
		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			EmployeeList employeeList = empDetailsService.getEmpListUnderManager();
			if (employeeList == null || employeeList.getEmployeeDetailsList() == null) {
				return ResponseEntity.status(HttpStatus.OK).body("No Employee register under  you");
			}
			List<EmployeeAllDetails> employeeDetailsList = employeeList.getEmployeeDetailsList();
			List<EmployeeApplicationManagerResponse> applicationDetailsList = new ArrayList<EmployeeApplicationManagerResponse>();
			for (EmployeeAllDetails emp : employeeDetailsList) {
				List<EmployeeLeaveApplicationModel> listOfLeave = empApplicationDAO
						.findByemployeeEmail(emp.getEmpEmail());
				if (listOfLeave == null || listOfLeave.isEmpty()) {

				} else {

					for (EmployeeLeaveApplicationModel application : listOfLeave) {
						EmployeeApplicationManagerResponse empApplication = new EmployeeApplicationManagerResponse();
						empApplication.setId(application.getId());
						empApplication.setEmpName(emp.getEmpName());
						empApplication.setEmpEmail(emp.getEmpEmail());
						empApplication.setEmpNumber(emp.getEmpNumber());
						empApplication.setEmpDesignation(emp.getEmpDesignation());
						empApplication.setEmpRole(emp.getEmpRole());
						empApplication.setLeaveType(application.getLeaveType());
						empApplication.setStartDate(application.getStartDate());
						empApplication.setEndDate(application.getEndDate());
						empApplication.setStatus(application.getStatus());
						applicationDetailsList.add(empApplication);

					}

				}

			}
			applicationDetailsList.sort((e1, e2) -> e1.getStartDate().compareTo(e2.getStartDate()));
			return ResponseEntity.status(HttpStatus.OK).body(applicationDetailsList);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Application Status approve or Delete from Manager
	public ResponseEntity<?> responseApplicationStatus(ManagerResponseOnApplicationRequest applicationResponse)
			throws Exception {

		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if (applicationResponse == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Application Response form could not be  empty");
			}
			if (applicationResponse.getId() == null || applicationResponse.getEmployeeEmail() == null
					|| applicationResponse.getLeaveType() == null || applicationResponse.getStatus() == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("All fields are required ");
			}
			if(!empDetailsService.getIsManager(applicationResponse.getEmployeeEmail().toLowerCase())) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not a manager of this employee");
			}
			// get application from database on the basis of application id
			Optional<EmployeeLeaveApplicationModel> leaveApplicationOptional = empApplicationDAO
					.findById(applicationResponse.getId());
			if (leaveApplicationOptional == null || leaveApplicationOptional.isEmpty()) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("no leave application is requested with this application id");
			}
			EmployeeLeaveApplicationModel application = leaveApplicationOptional.get();// get application
			if (application.getStartDate().isBefore(LocalDate.now())) {//check date exceed or not
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Starting Date of this application is exceed to today date you can not approve this one");
			}
			// check status if Reject or approved only they can go inside
			if (applicationResponse.getStatus().toUpperCase().equals("APPROVED")
					|| applicationResponse.getStatus().toUpperCase().equals("REJECT")) {

				if (application.getStatus().toUpperCase().equals("APPROVED")) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST)
							.body("Application is already Approved you can't do anything into this Application form");
				} else {
					if (!applicationResponse.getEmployeeEmail().toLowerCase()
							.equals(application.getEmployeeEmail().toLowerCase())) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body("Provided Email id for particular form id :" + applicationResponse.getId()
										+ " , is not matching with form Email id. Means this form is not belong to this particular person with Email id :"
										+ applicationResponse.getEmployeeEmail().toLowerCase());
					} else if (!applicationResponse.getLeaveType().toUpperCase()
							.equals(application.getLeaveType().toUpperCase())) {
						return ResponseEntity.status(HttpStatus.BAD_REQUEST)
								.body("Application Leave Type not Matching with previous Leave Type");
					} else {// check if we need to change status into "APPROVED"
						if (applicationResponse.getStatus().toUpperCase().equals("APPROVED")) {
							// get LeaveModel to increase number of Leave taken by this employee
							leaveModel = leaveDAO.findLeaveType(application.getEmployeeEmail().toLowerCase(),
									application.getLeaveType().toUpperCase());
							if (leaveModel == null) {// get user allowed Leave Type Details
								return ResponseEntity.status(HttpStatus.BAD_REQUEST)
										.body("this user not assign any Leave for : "
												+ application.getLeaveType().toUpperCase());
							} else {// is user have this type of leaveType
								Long daysTaken = leaveModel.getNumberOfDaysTaken();// get leave taken till now
								Long leavePeriod = ChronoUnit.DAYS.between(application.getStartDate(),
										application.getEndDate())+1L;// calculate leave taken days
								// total leave taken days after approved this application
								Long totalDaysTaken = daysTaken + leavePeriod;
								// set application Status into APPROVED
								application.setStatus(applicationResponse.getStatus().toUpperCase());
								application = empApplicationDAO.save(application);// save application
								if (application != null) {
									leaveModel.setNumberOfDaysTaken(totalDaysTaken);// set leave taken by user till now
									leaveModel = leaveDAO.save(leaveModel);// save user leave allowed model after set
																			// leave taken
									if (leaveModel != null) {
										return ResponseEntity.status(HttpStatus.OK).body("Leave Assign succesfully");
									} else {// if leave allowed table not save
										return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(
												"Leave Assign succesfully but Leave not Count something Went Wrong in databses");
									}
								} else {// if Status id need to change into "REJECT"
									return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
											.body("Leave Application not Saved, something went wrong");

								}
							}

						} else {
							application.setStatus(applicationResponse.getStatus().toUpperCase());
							application = empApplicationDAO.save(application);// save application
							if (application != null) {
								return ResponseEntity.status(HttpStatus.OK).body("Leave Rejected succesfully");
							} else {
								return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
										.body("Leave Rejected not save, something went wrong");
							}

						}
					}

				}

			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST)
						.body("Status Only 'APPROVED' or 'REJECT' is permitted");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
