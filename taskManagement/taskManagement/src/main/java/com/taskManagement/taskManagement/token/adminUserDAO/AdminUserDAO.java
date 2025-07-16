package com.taskManagement.taskManagement.token.adminUserDAO;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.taskManagement.taskManagement.token.model.AdminUserModel;


public interface AdminUserDAO extends JpaRepository<AdminUserModel, Long> {

	@Query("select um from AdminUserModel um where um.email in (:email)")
	public AdminUserModel findByEmail(String email);
	
	@Query("select um from AdminUserModel um where (um.name like concat('%', :searchKey, '%') or "
			+ "um.email like concat('%', :searchKey, '%') or um.mobileNo like concat('%', :searchKey, '%'))")
	public Page<AdminUserModel> getUserList(String searchKey, Pageable pageRequest);

	@Query("select um from AdminUserModel um")
	public Page<AdminUserModel> getUserList(Pageable pageRequest);

	@Query("select um from AdminUserModel um where um.userId not in (:userId)")
	public List<AdminUserModel> getOtherUserList(Long userId);

}
