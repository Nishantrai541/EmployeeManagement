package com.userAuthentication.userAuthentication.model.admin.user;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Table(name = "salary_table")
@Entity
public class EmployeeSalaryModel {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	private Double basicSalary;
	private Double hra;
	private Double convinienceAllowence;
	private Double medicalReimbursment;
	private Double tds;
	private Double providentFund;
	private Double bonus;
	private Double incerement;

	@OneToOne
	@JoinColumn(name = "employee_table_id")
	private AdminUserModel adminUserModel;

	public EmployeeSalaryModel(Long id, Double basicSalary, Double hra, Double convinienceAllowence,
			Double medicalReimbursment, Double tds, Double providentFund, Double bonus, Double incerement,
			AdminUserModel adminUserModel) {
		super();
		this.id = id;
		this.basicSalary = basicSalary;
		this.hra = hra;
		this.convinienceAllowence = convinienceAllowence;
		this.medicalReimbursment = medicalReimbursment;
		this.tds = tds;
		this.providentFund = providentFund;
		this.bonus = bonus;
		this.incerement = incerement;
		this.adminUserModel = adminUserModel;
	}

	public EmployeeSalaryModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getBasicSalary() {
		return basicSalary;
	}

	public void setBasicSalary(Double basicSalary) {
		this.basicSalary = basicSalary;
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

	public Double getBonus() {
		return bonus;
	}

	public void setBonus(Double bonus) {
		this.bonus = bonus;
	}

	public Double getIncerement() {
		return incerement;
	}

	public void setIncerement(Double incerement) {
		this.incerement = incerement;
	}

	public AdminUserModel getAdminUserModel() {
		return adminUserModel;
	}

	public void setAdminUserModel(AdminUserModel adminUserModel) {
		this.adminUserModel = adminUserModel;
	}

	@Override
	public String toString() {
		return "EmployeeSalaryModel [id=" + id + ", basicSalary=" + basicSalary + ", hra=" + hra
				+ ", convinienceAllowence=" + convinienceAllowence + ", medicalReimbursment=" + medicalReimbursment
				+ ", tds=" + tds + ", providentFund=" + providentFund + ", bonus=" + bonus + ", incerement="
				+ incerement + ", adminUserModel=" + adminUserModel + "]";
	}
	
	
}
