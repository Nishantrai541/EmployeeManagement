package com.userAuthentication.userAuthentication.request.admin;

import com.fasterxml.jackson.annotation.JsonInclude;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class AddAdminUserRequest {

	private String code;
	private String name;
	private String email;
	private String mobileNo;
	private String designation;
	private String password;
	private String role;
	public AddAdminUserRequest(String code, String name, String email, String mobileNo, String designation,
			String password, String role) {
		super();
		this.code = code;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.designation = designation;
		this.password = password;
		this.role = role;
	}
	public AddAdminUserRequest() {
		super();
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "AddAdminUserRequest [code=" + code + ", name=" + name + ", email=" + email + ", mobileNo=" + mobileNo
				+ ", designation=" + designation + ", password=" + password + ", role=" + role + "]";
	}
	
}
