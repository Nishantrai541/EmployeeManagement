package com.userAuthentication.userAuthentication.service.role;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userAuthentication.userAuthentication.dao.admin.user.AdminUserDAO;
import com.userAuthentication.userAuthentication.dao.role.RoleDAO;
import com.userAuthentication.userAuthentication.globalException.NullValueException;
import com.userAuthentication.userAuthentication.globalException.UnathorizedException;
import com.userAuthentication.userAuthentication.model.admin.user.AdminUserModel;
import com.userAuthentication.userAuthentication.model.role.RoleModel;
import com.userAuthentication.userAuthentication.response.role.EmployeeListOnRole;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class EmployeeOnRoleBasisService {

	private AdminUserDAO adminUserDAO;
	private RoleDAO roleDAO;
	private AdminUserModel adminUserModel;

	public EmployeeOnRoleBasisService(AdminUserDAO adminUserDAO, RoleDAO roleDAO) {
		super();
		this.adminUserDAO = adminUserDAO;
		this.roleDAO = roleDAO;
	}

	public ResponseEntity<List<EmployeeListOnRole>> getEmployeeListOnRole(HttpServletRequest request, String roleName)
			throws Exception{
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new Exception("User not logged in");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			if (role.equals("ADMIN") || role.equals("HR")) {
				roleName=roleName.toUpperCase();
				if (role.equals("HR") && roleName.equals("ADMIN")) {
					throw new UnathorizedException("HR cannot access Admin Details");
				}
				RoleModel roleModel = roleDAO.findByroleName(roleName);
				if (roleModel == null) {
					throw new NullValueException("Not have any role with this Role name: " + roleName);
				} else {
					List<AdminUserModel> empList = roleModel.getAdminUserModel();
					if (empList.isEmpty() || empList == null) {
						throw new NullValueException(
								"Their is no employee register within this Role Name: " + roleName);
					} else {
						List<EmployeeListOnRole> employeeDetailsList = new ArrayList<>();
						for (AdminUserModel employee : empList) {
							EmployeeListOnRole employeeDetails = new EmployeeListOnRole();
							employeeDetails.setEmployeeName(employee.getName());
							employeeDetails.setEmployeeemail(employee.getEmail());
							employeeDetails.setEmployeeDesignation(employee.getDesignation());
							employeeDetails.setEmployeeRole(roleName);
							employeeDetailsList.add(employeeDetails);
						}

						return ResponseEntity.status(HttpStatus.OK).body(employeeDetailsList);
					}
				}
			} else {

				throw new UnathorizedException("Only HR and Admin can access this section");

			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
			//throw new Exception("something went wrong");

		}
	}

}
