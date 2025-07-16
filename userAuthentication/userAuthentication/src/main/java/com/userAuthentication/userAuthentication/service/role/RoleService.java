package com.userAuthentication.userAuthentication.service.role;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.userAuthentication.userAuthentication.dao.admin.user.AdminUserDAO;
import com.userAuthentication.userAuthentication.dao.role.RoleDAO;
import com.userAuthentication.userAuthentication.model.admin.user.AdminUserModel;
import com.userAuthentication.userAuthentication.model.role.RoleModel;
import com.userAuthentication.userAuthentication.request.role.UpdateRole;

import jakarta.servlet.http.HttpServletRequest;

@Service
public class RoleService {

	private AdminUserDAO adminUserDAO;
	private RoleDAO roleDAO;
	
	public RoleService(AdminUserDAO adminUserDAO, RoleDAO roleDAO) {
		super();
		this.adminUserDAO = adminUserDAO;
		this.roleDAO = roleDAO;
	}


	private AdminUserModel adminUserModel;

	//Add Roles only admin can add role
	public ResponseEntity<String> addRole(HttpServletRequest request, String roleName) {
		// TODO Auto-generated method stub
		String loggedInEmail = request.getUserPrincipal().getName();
		String message = "";
		ResponseEntity<String> response = null;
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not logged in");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			if (role.equals("ADMIN")) {

				RoleModel roleModel = roleDAO.findByName(roleName);
				if (roleModel == null) {
					RoleModel addRole = new RoleModel();
					addRole.setRoleName(roleName);

					addRole = roleDAO.save(addRole);

					if (addRole != null) {
						message = "Role added successfully";
						response = ResponseEntity.status(HttpStatus.CREATED).body(message);
					} else {

					}
				} else {

					response = ResponseEntity.status(HttpStatus.ALREADY_REPORTED).body("Duplicate Role Name");
				}
			} else {

				response = ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admin can add Role");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred: " + e.getMessage());

		}
		return response;
	}
	

	//Admin or HR Get all Role name list
	public ResponseEntity<List<String>> getAllRoleList(HttpServletRequest request){
		String loggedInEmail = request.getUserPrincipal().getName();
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				throw new Exception("User not logged in");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			if (role.equals("ADMIN") || role.equals("HR")) {
				List<String> list = roleDAO.findRoleList();
				if(list==null || list.isEmpty()) {
					return ResponseEntity.status(HttpStatus.NO_CONTENT).body(list);
				}
				return ResponseEntity.status(HttpStatus.OK).body(list);
			} else {
				return ResponseEntity.status(HttpStatus.FORBIDDEN).body(List.of("Only Admin or HR can access this section"));
			}

		} catch (Exception e) {
			e.printStackTrace();
			 return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                     .body(List.of("Something went wrong"));

		}
	}
	
	
	//Update Role Name only admin can change
	public ResponseEntity<String> updateRoleName(HttpServletRequest request, UpdateRole updateRole) {
		
		String loggedInEmail = request.getUserPrincipal().getName();
		String message = "";
		ResponseEntity<String> response = null;
		try {
			adminUserModel = adminUserDAO.findByEmail(loggedInEmail);
			if (adminUserModel == null) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not logged in");
			}
			String role = adminUserModel.getRoleModel().getRoleName().toUpperCase();
			if (role.equals("ADMIN")) {

				String roleName=updateRole.getOldRoleName().toUpperCase();
				RoleModel roleModel = roleDAO.findByName(roleName);
				if (roleModel != null) {
					roleModel.setRoleName(updateRole.getNewRoleName().toUpperCase());
					roleModel = roleDAO.save(roleModel);
					if (roleModel != null) {
						message = "Role "+roleName+ " successfully update with "+updateRole.getNewRoleName().toUpperCase();
						response = ResponseEntity.status(HttpStatus.CREATED).body(message);
					} else {
						response = ResponseEntity.status(HttpStatus.CREATED).body("Not Update something wrong at databse");
					}
				} else {

					response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not have any Role with this name: "+roleName);
				}
			} else {

				response = ResponseEntity.status(HttpStatus.FORBIDDEN).body("Only Admin can add Role");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred: " + e.getMessage());

		}
		return response;
	}

}
