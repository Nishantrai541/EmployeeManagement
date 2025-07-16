package com.userAuthentication.userAuthentication.dao.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.userAuthentication.userAuthentication.model.role.RoleModel;

public interface RoleDAO extends JpaRepository<RoleModel, Long>{

	@Query("select um from RoleModel um where um.roleName in (:roleName)")
	public RoleModel findByName(String roleName);
	
	@Query("select um.roleName from RoleModel um")
	public List<String> findRoleList();
	
	public RoleModel findByroleName(String roleName);
}
