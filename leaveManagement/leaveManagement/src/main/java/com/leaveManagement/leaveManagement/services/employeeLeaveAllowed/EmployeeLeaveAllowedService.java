package com.leaveManagement.leaveManagement.services.employeeLeaveAllowed;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.leaveManagement.leaveManagement.dao.employeeLeaveAllotted.EmployeeLeaveAllowedDetailsDAO;
import com.leaveManagement.leaveManagement.external.services.EmployeeDetailsExternalService;
import com.leaveManagement.leaveManagement.model.employeeLeaveAlloted.EmployeeLeaveAllowedDetailsModel;
import com.leaveManagement.leaveManagement.request.leaveSenction.EmployeeLeaveSenction;
import com.leaveManagement.leaveManagement.request.leaveSenction.LeaveBreakUp;
import com.leaveManagement.leaveManagement.request.microservice.EmployeeDetails;

@Service
public class EmployeeLeaveAllowedService {

	@Autowired
	private EmployeeDetailsExternalService empDetailsService;

	@Autowired
	private EmployeeLeaveAllowedDetailsDAO leaveDAO;

	private EmployeeLeaveAllowedDetailsModel leaveModel;



	// add Employee Leave Break Up by ADMIN or HR
	public ResponseEntity<?> addLeaveBreakup(EmployeeLeaveSenction leaveBreakup) throws Exception {
		try {
			EmployeeDetails loginDetail = empDetailsService.getLoginUserRole();
			if (loginDetail == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("you are not registered user");
			}
			if (leaveBreakup == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("LeaveBreakUp should not be Empty");
			}

			String loginRole = loginDetail.getEmpRole().toUpperCase();
			if (loginRole.equals("ADMIN") || loginRole.equals("HR")) {
				String empEmail = leaveBreakup.getEmpEmail().toLowerCase();
				if (empEmail == null) {
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not Provided");
				}
				EmployeeDetails employeeDetails = empDetailsService.getEmailResisterConfirmation(empEmail);
				if (employeeDetails == null) {
					return ResponseEntity.status(HttpStatus.OK).body("user not regiater");
				} else {
					int count = 0;
					for (LeaveBreakUp leave : leaveBreakup.getLeaveBreakUp()) {
						leaveModel = leaveDAO.findLeaveType(empEmail, leave.getLeaveName().toUpperCase());
						if (leaveModel == null) {
							EmployeeLeaveAllowedDetailsModel leaveDetails = new EmployeeLeaveAllowedDetailsModel();
							leaveDetails.setEmployeeEmail(empEmail);
							leaveDetails.setLeaveAllowedType(leave.getLeaveName().toUpperCase());
							leaveDetails.setNumberOfDayAllowed(leave.getNumberOfDays());
							leaveDetails.setNumberOfDaysTaken(0L);
							leaveDetails = leaveDAO.save(leaveDetails);
							if (leaveDetails == null) {

							} else {
								count++;
							}
						} else {

							leaveModel.setNumberOfDayAllowed(leave.getNumberOfDays());

							leaveModel = leaveDAO.save(leaveModel);
							if (leaveModel == null) {

							} else {
								count++;
							}
						}

					}
					if (count > 0) {
						return ResponseEntity.status(HttpStatus.OK).body("Added Successfully");
					} else {
						return ResponseEntity.status(HttpStatus.OK).body("No Data Changes required all are Same");
					}

				}

			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body("only admin can access this section" + loginRole);

			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Return Employee Leave Alloted Table
	public ResponseEntity<?> getLeaveSenctionDetails() throws Exception {

		try {
			EmployeeDetails manager = empDetailsService.getLoginUserRole();

			if (manager == null) {
				return ResponseEntity.status(HttpStatus.OK).body("not register");
			} else {
				List<EmployeeLeaveAllowedDetailsModel> listLeaveModel = leaveDAO
						.findByemployeeEmail(manager.getEmpEmail().toLowerCase());
				if (listLeaveModel == null) {
					return ResponseEntity.status(HttpStatus.OK).body("Not have any leave allowed Senction");
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(listLeaveModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
}
