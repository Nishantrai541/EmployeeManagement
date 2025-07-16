package com.userAuthentication.userAuthentication.service.microservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.userAuthentication.userAuthentication.dao.admin.user.AdminUserDAO;
import com.userAuthentication.userAuthentication.dao.employee.reporting.person.EmployeeReportingPersonDAO;
import com.userAuthentication.userAuthentication.globalException.NullValueException;
import com.userAuthentication.userAuthentication.globalException.UnathorizedException;
import com.userAuthentication.userAuthentication.model.admin.user.AdminUserModel;
import com.userAuthentication.userAuthentication.model.employee.reporting.person.EmployeeReportingPersonModel;
import com.userAuthentication.userAuthentication.response.microservice.EmployeeAllDetails;
import com.userAuthentication.userAuthentication.response.microservice.EmployeeDetails;
import com.userAuthentication.userAuthentication.response.microservice.EmployeeList;
import com.userAuthentication.userAuthentication.response.microservice.ManagerDetail;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class MicroserviceProviderService {

	private AdminUserDAO adminUserDAO;
	private EmployeeReportingPersonDAO managerDetailsDAO;
	private AdminUserModel adminUserModel;
	private EmployeeReportingPersonModel managerDetailsModel;

	public MicroserviceProviderService(AdminUserDAO adminUserDAO, EmployeeReportingPersonDAO managerDetailsDAO) {
		super();
		this.adminUserDAO = adminUserDAO;
		this.managerDetailsDAO = managerDetailsDAO;
	}

	public ManagerDetail getManagerEmail(HttpServletRequest request) throws Exception {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new UnathorizedException("User not logged in");
			}
			managerDetailsModel = managerDetailsDAO.findByemployeeEmail(loggedInEmail);
			if (managerDetailsModel == null) {
				return null;
			} else {
				ManagerDetail managerDetail = new ManagerDetail();
				managerDetail.setEmployeeEmail(loggedInEmail);
				managerDetail.setManagerEmail(managerDetailsModel.getManagerEmail());
				return managerDetail;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public EmployeeDetails getEmailResisterConfirmation(HttpServletRequest request, String empEmail) throws Exception {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new UnathorizedException("User not logged in");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			if (role.equals("ADMIN") || role.equals("HR")) {
				AdminUserModel empDetails = adminUserDAO.findByEmail(empEmail);
				if (empDetails == null) {
					throw new NullValueException("Employee Email not register");
				} else {
					EmployeeDetails employeeDetails = new EmployeeDetails();
					employeeDetails.setEmpEmail(empDetails.getEmail());
					employeeDetails.setEmpRole(empDetails.getRoleModel().getRoleName().toUpperCase());
					return employeeDetails;
				}
			} else {
				throw new UnathorizedException("Only admin or HR can access this section");
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public EmployeeDetails getLoginUserRole(HttpServletRequest request) throws Exception {
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new UnathorizedException("User not logged in");
			} else {
				EmployeeDetails employeeDetails = new EmployeeDetails();
				employeeDetails.setEmpEmail(adminUserModel.getEmail());
				employeeDetails.setEmpRole(adminUserModel.getRoleModel().getRoleName().toUpperCase());
				return employeeDetails;
			}
			}catch(Exception e) {
				e.printStackTrace();
				throw e;
				}
	}
	
	
	

	public EmployeeList getEmployeeEmail(HttpServletRequest request) throws Exception{
		
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new UnathorizedException("User not logged in");
			}
				List<EmployeeReportingPersonModel> managerTeamList = managerDetailsDAO
						.findBymanagerEmail(loggedInEmail);
				if (managerTeamList.isEmpty() || managerTeamList == null) {
					throw new NullValueException("No Employee assign to you");
				} else {
					EmployeeList response = new EmployeeList();
					response.setManagerEmail(loggedInEmail);
					List<EmployeeAllDetails> teamList = new ArrayList<EmployeeAllDetails>();

					for (EmployeeReportingPersonModel empEmail : managerTeamList) {
						String employeeEmail = empEmail.getEmployeeEmail();
						if (employeeEmail == null) {

						} else {
							employeeEmail = employeeEmail.toLowerCase();
							AdminUserModel userDetails = adminUserDAO.findByEmail(employeeEmail);
							if (userDetails == null) {

							} else {
								EmployeeAllDetails teamMemberDetail = new EmployeeAllDetails();
								teamMemberDetail.setEmpName(userDetails.getName());
								teamMemberDetail.setEmpEmail(userDetails.getEmail());
								teamMemberDetail.setEmpNumber(userDetails.getMobileNo());
								teamMemberDetail.setEmpDesignation(userDetails.getDesignation());
								teamMemberDetail.setEmpRole(userDetails.getRoleModel().getRoleName());
								teamList.add(teamMemberDetail);
							}
						}
					}
					response.setEmployeeDetailsList(teamList);
					return response;
				}//
			
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	
	
	// get is manager of specific email of employee
	public boolean getIsManager(HttpServletRequest request, String empEmail) throws Exception{
		
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new UnathorizedException("User not logged in");
			}
			managerDetailsModel=managerDetailsDAO.findByemployeeEmail(empEmail);
			if(managerDetailsModel==null) {
				return false;
			}
			String managerEmail=managerDetailsModel.getManagerEmail().toLowerCase();
			if(managerEmail.equals(loggedInEmail.toLowerCase())) {
				return true;
			}else {
				return false;
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw e;
		}
	}
	

}
