package com.userAuthentication.userAuthentication.service.admin.user;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.userAuthentication.userAuthentication.dao.admin.user.AdminUserDAO;
import com.userAuthentication.userAuthentication.dao.role.RoleDAO;
import com.userAuthentication.userAuthentication.model.admin.user.AdminUserModel;
import com.userAuthentication.userAuthentication.model.role.RoleModel;
import com.userAuthentication.userAuthentication.request.admin.AddAdminUserRequest;
import com.userAuthentication.userAuthentication.request.admin.PasswordRequest;
import com.userAuthentication.userAuthentication.request.user.LoginRequest;
import com.userAuthentication.userAuthentication.response.UpdateResponse;
import com.userAuthentication.userAuthentication.response.page.PageDetails;
import com.userAuthentication.userAuthentication.response.user.AdminUserResponse;
import com.userAuthentication.userAuthentication.response.user.LoginResponse;
import com.userAuthentication.userAuthentication.service.admin.login.AdminUserLogin;
import com.userAuthentication.userAuthentication.shared.utils.CommonUtils;
import com.userAuthentication.userAuthentication.utils.jwt.JWTUtils;

@Service
public class AdminUserService {

	private JWTUtils jwtUtil;
	private AdminUserDAO adminUserDAO;
	private RoleDAO roleDAO;
	private AdminUserLogin loginService;
	private PasswordEncoder passwordEncoder;
	private AuthenticationManager authenticationManager;
	private JavaMailSender emailSender;

	public AdminUserService(JWTUtils jwtUtil, AdminUserDAO adminUserDAO, RoleDAO roleDAO, AdminUserLogin loginService,
			PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JavaMailSender emailSender) {
		super();
		this.jwtUtil = jwtUtil;
		this.adminUserDAO = adminUserDAO;
		this.roleDAO = roleDAO;
		this.loginService = loginService;
		this.passwordEncoder = passwordEncoder;
		this.authenticationManager = authenticationManager;
		this.emailSender = emailSender;
	}

	@Value("${contact_us_email}")
	private String sendMailId;

	public UpdateResponse createAdminUser(AddAdminUserRequest requestUser) throws Exception {
		if (requestUser == null) {
			throw new Exception("Request Body Must not be Empty");
		}
		if (requestUser.getEmail() == null || requestUser.getEmail().isEmpty()) {

			throw new Exception("Email id must Required");
		}
		requestUser.setEmail(requestUser.getEmail().toLowerCase());
		validateBasicInfo(requestUser);

		String message = "";
		String statusCode = "";
		try {

			String password = requestUser.getPassword();
			if (password != null && password.length() > 0) {
				password = passwordEncoder.encode(password);
			} else {
				password = generateRandomPassword();
				String mailBody = "Your account is created and password is - " + password;
				sendMail("Password", requestUser.getEmail(), mailBody);
				password = passwordEncoder.encode(password);
			}

			requestUser.setPassword(password);
			AdminUserModel adminUserModel = new AdminUserModel();
			if (requestUser.getRole() == null) {
				requestUser.setRole("EMPLOYEE");
			}
			RoleModel roleModel = roleDAO.findByName("EMPLOYEE");
			if (roleModel != null) {
				adminUserModel.setRoleModel(roleModel);
			}
			adminUserModel.setPassword(password);// set encripted password

			if (requestUser.getCode() == null || requestUser.getCode().isEmpty()) {

			} else {
				adminUserModel.setCode(requestUser.getCode());
			}
			if (requestUser.getName() == null || requestUser.getName().isEmpty()) {

			} else {
				adminUserModel.setName(requestUser.getName());
			}
			if (requestUser.getEmail() == null || requestUser.getEmail().isEmpty()) {

			} else {
				adminUserModel.setEmail(requestUser.getEmail());
			}
			if (requestUser.getMobileNo() == null || requestUser.getMobileNo().isEmpty()) {

			} else {
				adminUserModel.setMobileNo(requestUser.getMobileNo());
			}
			if (requestUser.getDesignation() == null || requestUser.getDesignation().isEmpty()) {

			} else {
				adminUserModel.setDesignation(requestUser.getDesignation().toUpperCase());
			}
			adminUserModel = adminUserDAO.save(adminUserModel);
			if (adminUserModel != null) {
				statusCode = "200 ok";
				message = "User Created Successfully";
			} else {
				statusCode = "400";
				message = "Unable to Create User";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		UpdateResponse response = new UpdateResponse(message, statusCode);
		return response;
	}

	public AdminUserResponse getUserList(HttpServletRequest requestServlet, int pageNo, int noOfRecord,
			String searchKey, String role) throws Exception {

		String loggedInUserName = requestServlet.getUserPrincipal().getName();
		if (loggedInUserName != null && loggedInUserName.length() > 0) {
			AdminUserModel userModel = adminUserDAO.findByEmail(loggedInUserName);
			if (userModel == null) {
				throw new Exception("User not logged In");
			}
		}

		String message = "";
		String statusCode = "";
		List<AdminUserModel> userList = new ArrayList<AdminUserModel>();
		AdminUserResponse response;
		try {

			Pageable pageRequest = PageRequest.of(pageNo, noOfRecord);

			Page<AdminUserModel> userListPage;
			if (searchKey != null && searchKey.length() > 0) {
				userListPage = adminUserDAO.getUserList(searchKey, pageRequest);
				userList.addAll(userListPage.getContent());
			} else {
				userListPage = adminUserDAO.getUserList(pageRequest);
				userList.addAll(userListPage.getContent());
			}

			statusCode = "200 ok";
			message = "User Details Fetch Successfully";

			PageDetails pageDetails = new PageDetails(pageNo, noOfRecord, userListPage.getTotalPages(),
					userListPage.getTotalElements());

			response = new AdminUserResponse(message, statusCode, userList, pageDetails);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		return response;
	}

	public LoginResponse adminLogin(LoginRequest request) throws Exception {

		if (request == null) {
			throw new Exception("Request Body Must not be Empty");
		}

		if (request.getEmail() == null) {
			throw new Exception("email must not be Empty");
		}

		if (request.getPassword() == null) {
			throw new Exception("Password must not be Empty");
		}

		String message = "";
		String authToken = "";
		String statusCode = "";
		String role = "";

		try {

			authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

			UserDetails userDetails = loginService.loadUserByUsername(request.getEmail());
			authToken = jwtUtil.generateToken(userDetails);
			AdminUserModel adminUserModel = adminUserDAO.findByEmail(request.getEmail());
			role = adminUserModel.getRoleModel().getRoleName();
			statusCode = "200";
			message = "Logged in Successfully";

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		LoginResponse response = new LoginResponse(authToken, message, statusCode, role);
		return response;
	}

	private void validateBasicInfo(AddAdminUserRequest request) throws Exception {
		String email = request.getEmail();

		if (email == null) {
			throw new Exception("Email Not Available");
		}

		if (adminUserDAO.findByEmail(email) != null) {
			throw new Exception("Email Already Available");
		}

		if (!CommonUtils.validateMobileNumber(request.getMobileNo())) {
			throw new Exception("Wrong mobile no format");
		}

		if (!CommonUtils.validateEmailFormat(request.getEmail())) {
			throw new Exception("Wrong Email Id Format");
		}
	}

	private String generateRandomPassword() {

		String lowerCaseText = "abcdefghijklmnopqrstuvwxyz";
		String upperCaseText = lowerCaseText.toUpperCase();
		String digit = "0123456789";

		String password = lowerCaseText + upperCaseText + digit;
		SecureRandom random = new SecureRandom();

		StringBuilder sb = new StringBuilder(15);
		for (int indexCount = 0; indexCount < 15; indexCount++) {
			int charPosition = random.nextInt(password.length());
			char passwordChar = password.charAt(charPosition);

			sb.append(passwordChar);
		}
		return sb.toString();
	}

	public void sendMail(String subject, String sendTOEmail, String mailBody) throws Exception {
		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setFrom(sendMailId);
		helper.setTo(sendTOEmail);
		helper.setSubject(subject);
		helper.setText(mailBody, true);

		emailSender.send(message);
	}

	public UpdateResponse changePassword(HttpServletRequest requestServlet, PasswordRequest requestBody)
			throws Exception {

		AdminUserModel userModel = null;
		String loggedInUserName = requestServlet.getUserPrincipal().getName();
		if (loggedInUserName != null && loggedInUserName.length() > 0) {
			userModel = adminUserDAO.findByEmail(loggedInUserName);
			if (userModel == null) {
				throw new Exception("User not logged In");
			}
		}

		if (requestBody == null || requestBody.getPassword() == null || requestBody.getPassword().length() == 0) {
			throw new Exception("Request Body Must not be Empty");
		}

		String message = "";
		String statusCode = "";
		try {

			String password = requestBody.getPassword();
			if (password != null && password.length() > 0) {
				password = passwordEncoder.encode(password);
			}

			userModel.setPassword(password);

			userModel = adminUserDAO.save(userModel);
			if (userModel != null) {
				statusCode = "200 ok";
				message = "User password Changed Successfully";
			} else {
				statusCode = "400";
				message = "Unable to Change password";
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}

		UpdateResponse response = new UpdateResponse(message, statusCode);
		return response;
	}

	// user Delete Method

	public String deleteUser(HttpServletRequest request, String email) throws Exception {

		AdminUserModel userModel = null;
		String loggedInUserName = request.getUserPrincipal().getName();
		if (loggedInUserName != null && loggedInUserName.length() > 0) {
			try {
				userModel = adminUserDAO.findByEmail(loggedInUserName);
				if (userModel == null) {
					throw new Exception("User not logged In");
				}
			} catch (Exception e) {
				throw e;
			}
		}
		String role = userModel.getRoleModel().getRoleName().toUpperCase();
		if (!(role.equals("ADMIN"))) {
			throw new Exception("Only ADMIN can delete User Details");
		}
		if (email == null || email.isEmpty()) {
			throw new Exception("Email id required");
		}
		try {
			AdminUserModel adminModel = adminUserDAO.findByEmail(email);
			if (adminModel == null) {
				throw new Exception("user not register with this Email");
			}
			adminUserDAO.delete(adminModel);
			;
			return "delete successfully";

		} catch (Exception e) {
			throw e;
		}
	}

}
