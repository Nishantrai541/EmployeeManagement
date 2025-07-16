package com.leaveManagement.leaveManagement.model.admin.user;

import java.util.List;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Table(name="role_table")
@Entity
public class RoleModel {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	private String roleName;
	
	@OneToMany(mappedBy="roleModel")
	private List<AdminUserModel> adminUserModel;

	public RoleModel(Long id, String roleName, List<AdminUserModel> adminUserModel) {
		super();
		this.id = id;
		this.roleName = roleName;
		this.adminUserModel = adminUserModel;
	}

	public RoleModel() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<AdminUserModel> getAdminUserModel() {
		return adminUserModel;
	}

	public void setAdminUserModel(List<AdminUserModel> adminUserModel) {
		this.adminUserModel = adminUserModel;
	}

	@Override
	public String toString() {
		return "RoleModel [id=" + id + ", roleName=" + roleName + ", adminUserModel=" + adminUserModel + "]";
	}

	

}

