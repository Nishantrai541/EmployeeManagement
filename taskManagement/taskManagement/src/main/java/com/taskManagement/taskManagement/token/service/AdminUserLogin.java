package com.taskManagement.taskManagement.token.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.taskManagement.taskManagement.token.adminUserDAO.AdminUserDAO;
import com.taskManagement.taskManagement.token.model.AdminUserModel;



@Service
public class AdminUserLogin implements UserDetailsService {

	private AdminUserDAO adminUserDAO;

	public AdminUserLogin(AdminUserDAO adminUserDAO) {
		super();
		this.adminUserDAO = adminUserDAO;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AdminUserModel adminUserModel = adminUserDAO.findByEmail(username);
		List<GrantedAuthority> list = new ArrayList<GrantedAuthority>();
		if (adminUserModel != null) {
			list.add(new SimpleGrantedAuthority(adminUserModel.getRoleModel().getRoleName()));
			return new User(adminUserModel.getEmail(), adminUserModel.getPassword(), list);
		} else {
			throw new UsernameNotFoundException("Invalid User Credential");
		}
	}

}
