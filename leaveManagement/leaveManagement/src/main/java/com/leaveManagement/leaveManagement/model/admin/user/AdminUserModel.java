package com.leaveManagement.leaveManagement.model.admin.user;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table
@Entity
public class AdminUserModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	private String code;
	private String name;
	private String email;
	private String mobileNo;

	@JsonIgnore
	private String otp;
	@JsonIgnore
	private Long otpExpireTime;

	private String designation;
	private String password;
	
	@OneToOne(mappedBy = "adminUserModel", cascade = CascadeType.ALL)
	private EmployeeSalaryModel employeeSalaryModel;
	
	@ManyToOne
	private RoleModel roleModel;

	public AdminUserModel(Long userId, String code, String name, String email, String mobileNo, String otp,
			Long otpExpireTime, String designation, String password, EmployeeSalaryModel employeeSalaryModel,
			RoleModel roleModel) {
		super();
		this.userId = userId;
		this.code = code;
		this.name = name;
		this.email = email;
		this.mobileNo = mobileNo;
		this.otp = otp;
		this.otpExpireTime = otpExpireTime;
		this.designation = designation;
		this.password = password;
		this.employeeSalaryModel = employeeSalaryModel;
		this.roleModel = roleModel;
	}

	public AdminUserModel() {
		super();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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

	public String getOtp() {
		return otp;
	}

	public void setOtp(String otp) {
		this.otp = otp;
	}

	public Long getOtpExpireTime() {
		return otpExpireTime;
	}

	public void setOtpExpireTime(Long otpExpireTime) {
		this.otpExpireTime = otpExpireTime;
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

	public EmployeeSalaryModel getEmployeeSalaryModel() {
		return employeeSalaryModel;
	}

	public void setEmployeeSalaryModel(EmployeeSalaryModel employeeSalaryModel) {
		this.employeeSalaryModel = employeeSalaryModel;
	}

	public RoleModel getRoleModel() {
		return roleModel;
	}

	public void setRoleModel(RoleModel roleModel) {
		this.roleModel = roleModel;
	}

	@Override
	public String toString() {
		return "AdminUserModel [userId=" + userId + ", code=" + code + ", name=" + name + ", email=" + email
				+ ", mobileNo=" + mobileNo + ", otp=" + otp + ", otpExpireTime=" + otpExpireTime + ", designation="
				+ designation + ", password=" + password + ", employeeSalaryModel=" + employeeSalaryModel
				+ ", roleModel=" + roleModel + "]";
	}
	
	

}
