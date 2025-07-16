package com.userAuthentication.userAuthentication.request.employee.details;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EmployeeDetailsRequest {

	private String email;
	private String name;
	private String mobileNo;
	private String designation;
	private String role;
	private Double hra;
	private Double convinienceAllowence;
	private Double medicalReimbursment;
	private Double tds;
	private Double providentFund;
	private Double basicSalary;
	private Double bonus;
	private Double increment;

	public EmployeeDetailsRequest(String email, String name, String mobileNo, String designation, String role,
			Double hra, Double convinienceAllowence, Double medicalReimbursment, Double tds, Double providentFund,
			Double basicSalary, Double bonus, Double increment) {
		super();
		this.email = email;
		this.name = name;
		this.mobileNo = mobileNo;
		this.designation = designation;
		this.role = role;
		this.hra = hra;
		this.convinienceAllowence = convinienceAllowence;
		this.medicalReimbursment = medicalReimbursment;
		this.tds = tds;
		this.providentFund = providentFund;
		this.basicSalary = basicSalary;
		this.bonus = bonus;
		this.increment = increment;
	}
	public EmployeeDetailsRequest() {
		super();
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public Double getHra() {
		return hra;
	}
	public void setHra(Double hra) {
		this.hra = hra;
	}
	public Double getConvinienceAllowence() {
		return convinienceAllowence;
	}
	public void setConvinienceAllowence(Double convinienceAllowence) {
		this.convinienceAllowence = convinienceAllowence;
	}
	public Double getMedicalReimbursment() {
		return medicalReimbursment;
	}
	public void setMedicalReimbursment(Double medicalReimbursment) {
		this.medicalReimbursment = medicalReimbursment;
	}
	public Double getTds() {
		return tds;
	}
	public void setTds(Double tds) {
		this.tds = tds;
	}
	public Double getProvidentFund() {
		return providentFund;
	}
	public void setProvidentFund(Double providentFund) {
		this.providentFund = providentFund;
	}
	public Double getBasicSalary() {
		return basicSalary;
	}
	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
	}
	public Double getBonus() {
		return bonus;
	}
	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}
	public Double getIncrement() {
		return increment;
	}
	public void setIncrement(Double increment) {
		this.increment = increment;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	@Override
	public String toString() {
		return "EmployeeDetailsRequest [email=" + email + ", name=" + name + ", mobileNo=" + mobileNo + ", designation="
				+ designation + ", role=" + role + ", hra=" + hra + ", convinienceAllowence=" + convinienceAllowence
				+ ", medicalReimbursment=" + medicalReimbursment + ", tds=" + tds + ", providentFund=" + providentFund
				+ ", basicSalary=" + basicSalary + ", bonus=" + bonus + ", increment=" + increment + "]";
	}
	
}

