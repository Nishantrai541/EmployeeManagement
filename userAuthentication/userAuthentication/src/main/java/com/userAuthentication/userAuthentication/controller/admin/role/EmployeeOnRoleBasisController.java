package com.userAuthentication.userAuthentication.controller.admin.role;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userAuthentication.userAuthentication.response.role.EmployeeListOnRole;
import com.userAuthentication.userAuthentication.service.role.EmployeeOnRoleBasisService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/getemployeelistonrole")
public class EmployeeOnRoleBasisController {
	
	private EmployeeOnRoleBasisService employeeOnRoleService;

	public EmployeeOnRoleBasisController(EmployeeOnRoleBasisService employeeOnRoleService) {
		super();
		this.employeeOnRoleService = employeeOnRoleService;
	}
	
	@GetMapping
	public ResponseEntity<List<EmployeeListOnRole>> getEmployeeListOnRole(HttpServletRequest request, @RequestParam(required=true) String roleName) throws Exception{
				
		return employeeOnRoleService.getEmployeeListOnRole(request, roleName);
	}

}
