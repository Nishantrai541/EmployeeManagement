package com.userAuthentication.userAuthentication.service.employee.reporting.person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userAuthentication.userAuthentication.dao.admin.user.AdminUserDAO;
import com.userAuthentication.userAuthentication.dao.employee.reporting.person.EmployeeReportingPersonDAO;
import com.userAuthentication.userAuthentication.model.admin.user.AdminUserModel;
import com.userAuthentication.userAuthentication.model.employee.reporting.person.EmployeeReportingPersonModel;
import com.userAuthentication.userAuthentication.request.employee.reporting.person.EmployeeReportingRequest;
import com.userAuthentication.userAuthentication.response.manager.details.ManagerDetailsResponse;
import com.userAuthentication.userAuthentication.response.manager.details.ManagerTeamListResponse;
import com.userAuthentication.userAuthentication.response.manager.details.ManagerTeamPersonResponse;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeReportingPersonService {

	private AdminUserDAO adminUserDAO;
	private EmployeeReportingPersonDAO employeeReportingPersonDAO;
	private AdminUserModel adminUserModel;

	public EmployeeReportingPersonService(AdminUserDAO adminUserDAO,
			EmployeeReportingPersonDAO employeeReportingPersonDAO) {
		super();
		this.adminUserDAO = adminUserDAO;
		this.employeeReportingPersonDAO = employeeReportingPersonDAO;
	}

	// Assign Employee Manager only by Admin and HR
	public ResponseEntity<String> assignEmployeeManager(HttpServletRequest request,
			EmployeeReportingRequest reportingPerson) throws Exception {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not logged in");
			}
			if (reportingPerson == null || reportingPerson.getEmployeeEmail() == null
					|| reportingPerson.getManagerEmail() == null) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Details not provided properly");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			// check logged in user role is it ADMIN or HR
			if (role.equals("ADMIN") || role.equals("HR")) {
				if (reportingPerson.getEmployeeEmail().toLowerCase()
						.equals(reportingPerson.getManagerEmail().toLowerCase())) {
					return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("both Email can't be same");
				}
				AdminUserModel employeeDetails = adminUserDAO
						.findByEmail(reportingPerson.getEmployeeEmail().toLowerCase());
				if (employeeDetails == null) {// check Employee register with this Email or Not
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body("No one register with this Email, Provide a valid Email");
				}
				AdminUserModel managerDetails = adminUserDAO
						.findByEmail(reportingPerson.getManagerEmail().toLowerCase());
				if (managerDetails == null) {// check Manager register with this Email or Not
					return ResponseEntity.status(HttpStatus.NOT_FOUND)
							.body("No one register with provided Manager Email id");
				}
				String empRole = employeeDetails.getRoleModel().getRoleName().toUpperCase();// employee Role
				String managerRole = managerDetails.getRoleModel().getRoleName().toUpperCase();// manager Role

				if (empRole.equals("ADMIN")) {// checking for we want to assign Admin manager
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Admin's manager cannot assign");
					// checking for We are not assigning Manager for the Role of MANAGER or HR to
					// Employee
				} else if ((empRole.equals("MANAGER") && managerRole.equals("EMPLOYEE"))
						|| (empRole.equals("HR") && managerRole.equals("EMPLOYEE"))) {
					return ResponseEntity.status(HttpStatus.FORBIDDEN)
							.body("EMPLOYEE role cannot be Manager for MANAGER/HR role");
				} else {
					EmployeeReportingPersonModel managerAssignModel = employeeReportingPersonDAO
							.findByemployeeEmail(employeeDetails.getEmail().toLowerCase());
					if (managerAssignModel == null) {
						EmployeeReportingPersonModel managerAssignModel1 = new EmployeeReportingPersonModel();
						managerAssignModel1.setEmployeeEmail(employeeDetails.getEmail().toLowerCase());
						managerAssignModel1.setManagerEmail(managerDetails.getEmail().toLowerCase());
						managerAssignModel1 = employeeReportingPersonDAO.save(managerAssignModel1);
						if (managerAssignModel1 == null) {
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
						} else {
							return ResponseEntity.status(HttpStatus.ACCEPTED).body("Success Manager assigned");
						}
					} else {
						managerAssignModel.setEmployeeEmail(employeeDetails.getEmail().toLowerCase());
						managerAssignModel.setManagerEmail(managerDetails.getEmail().toLowerCase());
						managerAssignModel = employeeReportingPersonDAO.save(managerAssignModel);
						if (managerAssignModel == null) {
							return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("something went wrong");
						} else {
							return ResponseEntity.status(HttpStatus.ACCEPTED)
									.body("Employee Manager Update Successfully");
						}
					}
				}

			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN)
						.body("only ADMIN and HR can assign employee manager");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// get employee manager Details by employeeLogin
	public ResponseEntity<?> getEmployeeManager(HttpServletRequest request) {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not logged in");
			}
			EmployeeReportingPersonModel managerAssignModel = employeeReportingPersonDAO
					.findByemployeeEmail(adminUserModel.getEmail().toLowerCase());
			if (managerAssignModel == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Their is no manager assign for You");
			} else {
				String managerEmail = managerAssignModel.getManagerEmail().toLowerCase();
				if (managerEmail == null) {
					return ResponseEntity.status(HttpStatus.OK).body("Their is no manager assign for You");
				}
				AdminUserModel managerDetails = adminUserDAO.findByEmail(managerEmail);
				if (managerDetails == null) {
					return ResponseEntity.status(HttpStatus.OK)
							.body("Manager Details Not avilable not but have manager Email: " + managerEmail);
				} else {
					ManagerDetailsResponse manager = new ManagerDetailsResponse();
					manager.setManagerName(managerDetails.getName());
					manager.setManagerEmail(managerDetails.getEmail());
					manager.setManagerDesignation(managerDetails.getDesignation().toUpperCase());
					manager.setManagerRole(managerDetails.getRoleModel().getRoleName().toUpperCase());
					return ResponseEntity.status(HttpStatus.OK).body(manager);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Get Team Member List
	public ResponseEntity<?> getTeamList(HttpServletRequest request) {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not logged in");
			}
			List<EmployeeReportingPersonModel> managerTeamList = employeeReportingPersonDAO
					.findBymanagerEmail(adminUserModel.getEmail().toLowerCase());
			if (managerTeamList.isEmpty() || managerTeamList == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Not assign any User under you till now");
			} else {
				List<ManagerTeamPersonResponse> response = new ArrayList<ManagerTeamPersonResponse>();
				int count = 0;
				for (EmployeeReportingPersonModel empEmail : managerTeamList) {
					String employeeEmail = empEmail.getEmployeeEmail();
					if (employeeEmail == null) {

					} else {
						employeeEmail = employeeEmail.toLowerCase();
						AdminUserModel userDetails = adminUserDAO.findByEmail(employeeEmail);
						ManagerTeamPersonResponse teamMemberDetail = new ManagerTeamPersonResponse();
						teamMemberDetail.setName(userDetails.getName());
						teamMemberDetail.setEmail(userDetails.getEmail());
						teamMemberDetail.setMobileNo(userDetails.getMobileNo());
						teamMemberDetail.setDesignation(userDetails.getDesignation());
						teamMemberDetail.setRole(userDetails.getRoleModel().getRoleName());
						response.add(teamMemberDetail);
						count++;
					}
				}
				if (count == 0) {
					return ResponseEntity.status(HttpStatus.OK).body("Not have any team member list");
				} else {
					return ResponseEntity.status(HttpStatus.OK).body(response);
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// get Manager Team List on Manager Email id only access by ADMIN or HR
	public ResponseEntity<?> getTeamListOnManagerEmail(HttpServletRequest request, String managerEmail) {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not logged in");
			}
			if (managerEmail == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Email Must be provide");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			if (role.equals("ADMIN") || role.equals("HR")) {//
				List<EmployeeReportingPersonModel> managerTeamList = employeeReportingPersonDAO
						.findBymanagerEmail(managerEmail.toLowerCase());
				if (managerTeamList.isEmpty() || managerTeamList == null) {
					return ResponseEntity.status(HttpStatus.OK).body("Not assign any User under you till now");
				} else {
					ManagerTeamListResponse response = new ManagerTeamListResponse();
					ManagerTeamPersonResponse managerDetails = new ManagerTeamPersonResponse();
					AdminUserModel managerData = adminUserDAO.findByEmail(managerEmail);

					if (managerData == null) {

					} else {
						managerDetails.setName(managerData.getName());
						managerDetails.setEmail(managerData.getEmail());
						managerDetails.setMobileNo(managerData.getMobileNo());
						managerDetails.setDesignation(managerData.getDesignation());
						managerDetails.setRole(managerData.getRoleModel().getRoleName().toUpperCase());
						response.setManagerDetails(managerDetails);
					}
					List<ManagerTeamPersonResponse> teamList = new ArrayList<ManagerTeamPersonResponse>();

					for (EmployeeReportingPersonModel empEmail : managerTeamList) {
						String employeeEmail = empEmail.getEmployeeEmail();
						if (employeeEmail == null) {

						} else {
							employeeEmail = employeeEmail.toLowerCase();
							AdminUserModel userDetails = adminUserDAO.findByEmail(employeeEmail);
							if (userDetails == null) {

							} else {
								ManagerTeamPersonResponse teamMemberDetail = new ManagerTeamPersonResponse();
								teamMemberDetail.setName(userDetails.getName());
								teamMemberDetail.setEmail(userDetails.getEmail());
								teamMemberDetail.setMobileNo(userDetails.getMobileNo());
								teamMemberDetail.setDesignation(userDetails.getDesignation());
								teamMemberDetail.setRole(userDetails.getRoleModel().getRoleName());
								teamList.add(teamMemberDetail);
							}
						}
					}
					if (teamList != null && managerDetails != null) {
						response.setListOfTeam(teamList);
						return ResponseEntity.status(HttpStatus.OK).body(response);
					} else if (teamList != null && managerDetails == null) {
						response.setListOfTeam(teamList);
						return ResponseEntity.status(HttpStatus.OK).body(response);
					} else {
						return ResponseEntity.status(HttpStatus.OK).body(response);
					}

				} //

			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only HR and Admin can access this section");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

	}

	public ResponseEntity<?> deleteEmployeeManager(HttpServletRequest request, String employeeEmail) {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User not logged in");
			}
			if (employeeEmail == null) {
				return ResponseEntity.status(HttpStatus.OK).body("Email Must be provide");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			if (role.equals("ADMIN") || role.equals("HR")) {
				EmployeeReportingPersonModel managerAssignModel = employeeReportingPersonDAO
						.findByemployeeEmail(employeeEmail);
				if (managerAssignModel == null) {
					return ResponseEntity.status(HttpStatus.FORBIDDEN).body("No Manager Assign to this user");
				} else {
					employeeReportingPersonDAO.delete(managerAssignModel);
					return ResponseEntity.status(HttpStatus.OK).body("Manager Deleted Successfully");
				}

			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only HR and Admin can access this section");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
