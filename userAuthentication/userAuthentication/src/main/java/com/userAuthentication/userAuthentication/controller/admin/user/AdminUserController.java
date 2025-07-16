package com.userAuthentication.userAuthentication.controller.admin.user;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userAuthentication.userAuthentication.request.admin.AddAdminUserRequest;
import com.userAuthentication.userAuthentication.request.admin.PasswordRequest;
import com.userAuthentication.userAuthentication.request.user.LoginRequest;
import com.userAuthentication.userAuthentication.response.UpdateResponse;
import com.userAuthentication.userAuthentication.response.user.AdminUserResponse;
import com.userAuthentication.userAuthentication.response.user.LoginResponse;
import com.userAuthentication.userAuthentication.service.admin.user.AdminUserService;



@RestController
@CrossOrigin
@RequestMapping("/api/admin/user")
public class AdminUserController {

	private AdminUserService service;
	
	public AdminUserController(AdminUserService service) {
		super();
		this.service = service;
	}

	@PostMapping("/test")
	public String apiTest() throws Exception {
		return "Api call";
	}

	@PostMapping
	public UpdateResponse createUser(@RequestBody AddAdminUserRequest request) throws Exception {
		return service.createAdminUser(request);
	}

	@GetMapping
	public AdminUserResponse getUserList(HttpServletRequest requestServlet, @RequestParam int pageNo,
			@RequestParam int noOfRecord, @RequestParam(required = false) String searchKey,
			@RequestParam(required = false) String role) throws Exception {
		return service.getUserList(requestServlet, pageNo, noOfRecord, searchKey, role);
	}

	@PostMapping("/login")
	public LoginResponse adminLogin(@RequestBody LoginRequest request) throws Exception {
		return service.adminLogin(request);
	}

	@PostMapping("/password")
	public UpdateResponse changePassword(HttpServletRequest requestServlet, @RequestBody PasswordRequest requestBody)
			throws Exception {
		return service.changePassword(requestServlet, requestBody);
	}
	
	@PostMapping("/delete")
	public String deleteUser(HttpServletRequest request, @RequestParam(required=true) String email) throws Exception{
		
		return service.deleteUser(request, email);
	}

}
