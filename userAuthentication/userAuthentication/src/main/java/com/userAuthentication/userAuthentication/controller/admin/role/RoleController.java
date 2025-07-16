package com.userAuthentication.userAuthentication.controller.admin.role;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userAuthentication.userAuthentication.request.role.UpdateRole;
import com.userAuthentication.userAuthentication.service.role.RoleService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("api/role")
public class RoleController {
	
	private RoleService roleService;
		
	public RoleController(RoleService roleService) {
		super();
		this.roleService = roleService;
	}

	@PostMapping
	private ResponseEntity<String> addRole(HttpServletRequest request, @RequestParam(required=true)String roleName) {
		
		roleName=roleName.toUpperCase();
		return roleService.addRole(request, roleName);
	}
	
	@GetMapping
	private ResponseEntity<List<String>> getAllRoleList(HttpServletRequest request) {
		
		return roleService.getAllRoleList(request);
	}
	
	@PutMapping
	private ResponseEntity<String> updateRoleName(HttpServletRequest request, @RequestBody UpdateRole updateRole){
		
		return roleService.updateRoleName(request, updateRole);
	}

}
