package com.userAuthentication.userAuthentication.service.employee.details;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.userAuthentication.userAuthentication.dao.admin.user.AdminUserDAO;
import com.userAuthentication.userAuthentication.dao.role.RoleDAO;
import com.userAuthentication.userAuthentication.globalException.NullValueException;
import com.userAuthentication.userAuthentication.globalException.UnathorizedException;
import com.userAuthentication.userAuthentication.model.admin.user.AdminUserModel;
import com.userAuthentication.userAuthentication.model.admin.user.EmployeeSalaryModel;
import com.userAuthentication.userAuthentication.model.role.RoleModel;
import com.userAuthentication.userAuthentication.request.employee.details.EmployeeDetailsRequest;
import com.userAuthentication.userAuthentication.response.employee.details.EmployeeDetailsResponse;
import com.userAuthentication.userAuthentication.response.employee.details.EmployeeDetailsWithSalaryResponse;
import com.userAuthentication.userAuthentication.shared.utils.CommonUtils;

@Service

public class EmployeeDetailsService {

	private AdminUserDAO adminUserDAO;
	private RoleDAO roleDAO;

	public EmployeeDetailsService(AdminUserDAO adminUserDAO, RoleDAO roleDAO) {
		super();
		this.adminUserDAO = adminUserDAO;
		this.roleDAO = roleDAO;
	}

	private AdminUserModel adminUserModel;

	// Get User Details via login user(login user can get their all details only
	// they can not get any other person details)
	public EmployeeDetailsWithSalaryResponse getDetails(HttpServletRequest request) throws Exception {

		String loggedInEmail = request.getUserPrincipal().getName();
		EmployeeDetailsWithSalaryResponse response = new EmployeeDetailsWithSalaryResponse();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new Exception("User not logged in");
			}

			response.setName(adminUserModel.getName());
			response.setEmail(adminUserModel.getEmail());
			response.setMobileNo(adminUserModel.getMobileNo());
			response.setRole(adminUserModel.getRoleModel().getRoleName());

			if (adminUserModel.getEmployeeSalaryModel() != null) {
				response.setBasicSalary(adminUserModel.getEmployeeSalaryModel().getBasicSalary());
				response.setHra(adminUserModel.getEmployeeSalaryModel().getHra());
				response.setTds(adminUserModel.getEmployeeSalaryModel().getTds());
				response.setConvinienceAllowence(adminUserModel.getEmployeeSalaryModel().getConvinienceAllowence());
				response.setProvidentFund(adminUserModel.getEmployeeSalaryModel().getProvidentFund());
				response.setMedicalReimbursment(adminUserModel.getEmployeeSalaryModel().getMedicalReimbursment());
				response.setBonus(adminUserModel.getEmployeeSalaryModel().getBonus());
				response.setIncrement(adminUserModel.getEmployeeSalaryModel().getIncerement());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return response;
	}

	// Get Employee Details on the basis of Employee Email id
	public EmployeeDetailsWithSalaryResponse getEmployeeDetails(HttpServletRequest request, String userName)
			throws Exception {

		userName = userName.toLowerCase();
		String loggedInEmail = request.getUserPrincipal().getName();
		EmployeeDetailsWithSalaryResponse response = new EmployeeDetailsWithSalaryResponse();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new Exception("User not Logged In");
			} else {
				String role = adminUserModel.getRoleModel().getRoleName();

				if (role.equals("ADMIN")) {// ADMIN get employee details via Email id
					AdminUserModel adminUserModel2 = adminUserDAO.findByEmail(userName);
					if (adminUserModel2 == null) {
						throw new NullValueException("User not Register with this UserName provide User register Email");
					} else {
						response.setName(adminUserModel2.getName());
						response.setEmail(adminUserModel2.getEmail());
						response.setMobileNo(adminUserModel2.getMobileNo());
						response.setDesignation(adminUserModel2.getDesignation());
						response.setRole(adminUserModel2.getRoleModel().getRoleName());
						if (adminUserModel2.getEmployeeSalaryModel() != null) {
							response.setBasicSalary(adminUserModel2.getEmployeeSalaryModel().getBasicSalary());
							response.setHra(adminUserModel2.getEmployeeSalaryModel().getHra());
							response.setTds(adminUserModel2.getEmployeeSalaryModel().getTds());
							response.setConvinienceAllowence(
									adminUserModel2.getEmployeeSalaryModel().getConvinienceAllowence());
							response.setProvidentFund(adminUserModel2.getEmployeeSalaryModel().getProvidentFund());
							response.setMedicalReimbursment(
									adminUserModel2.getEmployeeSalaryModel().getMedicalReimbursment());
							response.setBonus(adminUserModel2.getEmployeeSalaryModel().getBonus());
							response.setIncrement(adminUserModel2.getEmployeeSalaryModel().getIncerement());
						} else {
							System.out.println("null");
						}
						return response;
					}

				} else if (role.equals("HR")) {// HR get employee details via employee email id
					AdminUserModel adminUserModel2 = adminUserDAO.findByEmail(userName);
					if (adminUserModel2 == null) {
						throw new Exception("User not Register with this UserName provide User register Email");
					} else {
						if (adminUserModel2.getRoleModel().getRoleName().equals("ADMIN")) {
							throw new UnathorizedException("you are not authorized Persion to get this Admin Details");
						} else {
							response.setName(adminUserModel2.getName());
							response.setEmail(adminUserModel2.getEmail());
							response.setMobileNo(adminUserModel2.getMobileNo());
							response.setDesignation(adminUserModel2.getDesignation());
							response.setRole(adminUserModel2.getRoleModel().getRoleName());
							if (adminUserModel2.getEmployeeSalaryModel() != null) {
								response.setBasicSalary(adminUserModel2.getEmployeeSalaryModel().getBasicSalary());
								response.setHra(adminUserModel2.getEmployeeSalaryModel().getHra());
								response.setTds(adminUserModel2.getEmployeeSalaryModel().getTds());
								response.setConvinienceAllowence(
										adminUserModel2.getEmployeeSalaryModel().getConvinienceAllowence());
								response.setProvidentFund(adminUserModel2.getEmployeeSalaryModel().getProvidentFund());
								response.setMedicalReimbursment(
										adminUserModel2.getEmployeeSalaryModel().getMedicalReimbursment());
								response.setBonus(adminUserModel2.getEmployeeSalaryModel().getBonus());
								response.setIncrement(adminUserModel2.getEmployeeSalaryModel().getIncerement());
							}
							return response;
						}
					}

				} else if (role.equals("MANAGER")) {// MANAGER get employee details via employee email id
					AdminUserModel adminUserModel2 = adminUserDAO.findByEmail(userName);
					if (adminUserModel2 == null) {
						throw new Exception("User not Register with this UserName provide User register Email");
					} else {
						if (adminUserModel2.getRoleModel().getRoleName().equals("ADMIN")) {
							throw new Exception("you are not authorized Persion to get this User Details");
						} else {
							response.setName(adminUserModel2.getName());
							response.setEmail(adminUserModel2.getEmail());
							response.setMobileNo(adminUserModel2.getMobileNo());
							response.setRole(adminUserModel2.getRoleModel().getRoleName());
							response.setDesignation(adminUserModel2.getDesignation());
							return response;
						}
					}

				} else {

					throw new UnathorizedException("Your are not authorized User");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// Add/Update Employee Details and Salary

	public EmployeeDetailsResponse updateEmployeeDetails(HttpServletRequest request,
			EmployeeDetailsRequest employeeUpdateDetails) throws Exception {

		String message = "";
		String statusCode = "";
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new Exception("User not Logged In");
			}
			if (employeeUpdateDetails == null) {
				throw new NullValueException("Upadate all value is null");
			} else {
				String role = adminUserModel.getRoleModel().getRoleName();
				String employeeEmail = employeeUpdateDetails.getEmail().toLowerCase();
				if (role.equals("ADMIN")) {// ADMIN section start
					AdminUserModel adminUserModel2 = adminUserDAO.findByEmail(employeeEmail);

					if (adminUserModel2 == null) {
						throw new Exception(
								"User not Register with this UserName provide User register Email" + employeeEmail);
					} else {

						EmployeeSalaryModel salaryModel = adminUserModel2.getEmployeeSalaryModel();// get salart table
																									// from user table
						if (salaryModel == null) {// check salry table is avilable or not with employee table
							salaryModel = new EmployeeSalaryModel();// create new object ot salary table if not get from
																	// employee table
							salaryModel.setAdminUserModel(adminUserModel2);// add empoyee table reference for salary
																			// table
							adminUserModel.setEmployeeSalaryModel(salaryModel);// add salary table reference for
																				// employee table
						}
						if (employeeUpdateDetails.getName() == null || employeeUpdateDetails.getName().isEmpty()) {

						} else {
							adminUserModel2.setName(employeeUpdateDetails.getName());
						}
						if (employeeUpdateDetails.getMobileNo() == null
								|| employeeUpdateDetails.getMobileNo().isEmpty()) {

						} else {
							validateBasicInfo(employeeUpdateDetails.getMobileNo());
							adminUserModel2.setMobileNo(employeeUpdateDetails.getMobileNo());
						}
						if (employeeUpdateDetails.getDesignation() == null
								|| employeeUpdateDetails.getDesignation().isEmpty()) {

						} else {
							adminUserModel2.setName(employeeUpdateDetails.getDesignation().toUpperCase());
						}
						if (employeeUpdateDetails.getRole() == null || employeeUpdateDetails.getRole().isEmpty()) {

						} else {
							RoleModel roleModel = roleDAO.findByName(employeeUpdateDetails.getRole().toUpperCase());
							if (roleModel == null) {

							} else {
								adminUserModel2.setRoleModel(roleModel);
							}
						}
						if (employeeUpdateDetails.getBasicSalary() == null
								|| employeeUpdateDetails.getBasicSalary().isNaN()) {

						} else {
							salaryModel.setBasicSalary(employeeUpdateDetails.getBasicSalary());
						}

						if (employeeUpdateDetails.getHra() == null || employeeUpdateDetails.getHra().isNaN()) {

						} else {
							salaryModel.setHra(employeeUpdateDetails.getHra());
						}
						if (employeeUpdateDetails.getTds() == null || employeeUpdateDetails.getTds().isNaN()) {

						} else {
							salaryModel.setTds(employeeUpdateDetails.getTds());
						}
						if (employeeUpdateDetails.getConvinienceAllowence() == null
								|| employeeUpdateDetails.getConvinienceAllowence().isNaN()) {

						} else {
							salaryModel.setConvinienceAllowence(employeeUpdateDetails.getConvinienceAllowence());
						}
						if (employeeUpdateDetails.getProvidentFund() == null
								|| employeeUpdateDetails.getProvidentFund().isNaN()) {

						} else {
							salaryModel.setProvidentFund(employeeUpdateDetails.getProvidentFund());
						}
						if (employeeUpdateDetails.getMedicalReimbursment() == null
								|| employeeUpdateDetails.getMedicalReimbursment().isNaN()) {

						} else {
							salaryModel.setMedicalReimbursment(employeeUpdateDetails.getMedicalReimbursment());
						}

						if (employeeUpdateDetails.getBonus() == null || employeeUpdateDetails.getBonus().isNaN()) {

						} else {
							salaryModel.setBonus(employeeUpdateDetails.getBonus());
						}
						if (employeeUpdateDetails.getIncrement() == null
								|| employeeUpdateDetails.getIncrement().isNaN()) {

						} else {
							salaryModel.setIncerement(employeeUpdateDetails.getIncrement());
						}

						adminUserModel2 = adminUserDAO.save(adminUserModel2);// save data into empoloyee table
						if (adminUserModel2 == null) {
							throw new Exception("something went wrong at database");
						} else {

							message = "successfully changed";
							statusCode = "200";
						}

					}

				} else if (role.equals("HR")) { // HR section start
					AdminUserModel adminUserModel2 = adminUserDAO.findByEmail(employeeEmail);
					String roleMessage="";
					if (adminUserModel2 == null) {
						throw new Exception("User not Register with this UserName provide User register Email");
					} else if ((adminUserModel.getRoleModel().getRoleName().toUpperCase()).equals("ADMIN")) {// check for Login is access admin section 
						throw new UnathorizedException("you do not have permission to access Admin section");

					} else {

						EmployeeSalaryModel salaryModel = adminUserModel2.getEmployeeSalaryModel();// Get salary table
																									// from employee
																									// table
						if (salaryModel == null) {// check for salary table is available or not
							salaryModel = new EmployeeSalaryModel();// create new object of salary table
							salaryModel.setAdminUserModel(adminUserModel2);// link employee table reference to salary
																			// table
							adminUserModel.setEmployeeSalaryModel(salaryModel);// link salary table reference to
																				// employee table
						}

						if (employeeUpdateDetails.getName() == null || employeeUpdateDetails.getName().isEmpty()) {

						} else {
							adminUserModel2.setName(employeeUpdateDetails.getName());
						}
						if (employeeUpdateDetails.getMobileNo() == null
								|| employeeUpdateDetails.getMobileNo().isEmpty()) {

						} else {
							validateBasicInfo(employeeUpdateDetails.getMobileNo());
							adminUserModel2.setMobileNo(employeeUpdateDetails.getMobileNo());
						}
						if (employeeUpdateDetails.getDesignation() == null
								|| employeeUpdateDetails.getDesignation().isEmpty()) {

						} else {
							adminUserModel2.setName(employeeUpdateDetails.getDesignation().toUpperCase());
						}
						if (employeeUpdateDetails.getRole() == null || employeeUpdateDetails.getRole().isEmpty()) {

						} else {
							RoleModel roleModel = roleDAO.findByName(employeeUpdateDetails.getRole().toUpperCase());
							if (roleModel == null) {

							} else if(roleModel.getRoleName().toUpperCase().equals("ADMIN")){
								roleMessage=" And HR cannot Assign Admin Role to any User, Other all details saved";
							}else {
								adminUserModel2.setRoleModel(roleModel);
							}
						}
						if (employeeUpdateDetails.getBasicSalary() == null
								|| employeeUpdateDetails.getBasicSalary().isNaN()) {

						} else {
							salaryModel.setBasicSalary(employeeUpdateDetails.getBasicSalary());
						}

						if (employeeUpdateDetails.getHra() == null || employeeUpdateDetails.getHra().isNaN()) {

						} else {
							salaryModel.setHra(employeeUpdateDetails.getHra());
						}
						if (employeeUpdateDetails.getTds() == null || employeeUpdateDetails.getTds().isNaN()) {

						} else {
							salaryModel.setTds(employeeUpdateDetails.getTds());
						}
						if (employeeUpdateDetails.getConvinienceAllowence() == null
								|| employeeUpdateDetails.getConvinienceAllowence().isNaN()) {

						} else {
							salaryModel.setConvinienceAllowence(employeeUpdateDetails.getConvinienceAllowence());
						}
						if (employeeUpdateDetails.getProvidentFund() == null
								|| employeeUpdateDetails.getProvidentFund().isNaN()) {

						} else {
							salaryModel.setProvidentFund(employeeUpdateDetails.getProvidentFund());
						}
						if (employeeUpdateDetails.getMedicalReimbursment() == null
								|| employeeUpdateDetails.getMedicalReimbursment().isNaN()) {

						} else {
							salaryModel.setMedicalReimbursment(employeeUpdateDetails.getMedicalReimbursment());
						}
						if (employeeUpdateDetails.getBonus() == null || employeeUpdateDetails.getBonus().isNaN()) {

						} else {
							salaryModel.setBonus(employeeUpdateDetails.getBonus());
						}
						if (employeeUpdateDetails.getIncrement() == null
								|| employeeUpdateDetails.getIncrement().isNaN()) {

						} else {
							salaryModel.setIncerement(employeeUpdateDetails.getIncrement());
						}

						adminUserModel2 = adminUserDAO.save(adminUserModel2);// save employee table
						if (adminUserModel2 == null) {
							throw new Exception("something went wrong at database");
						} else {

							message = "successfully changed"+roleMessage;
							statusCode = "200";
						}

					}

				} else {

					throw new UnathorizedException("Your are not authorized User Only Admin or HR can access this section");
				}
			}

			EmployeeDetailsResponse response = new EmployeeDetailsResponse(statusCode, message);
			return response;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	// number validate
	private void validateBasicInfo(String mobileNo) throws Exception {

		if (!CommonUtils.validateMobileNumber(mobileNo)) {
			throw new Exception("Wrong mobile no format");
		}
	}

}
