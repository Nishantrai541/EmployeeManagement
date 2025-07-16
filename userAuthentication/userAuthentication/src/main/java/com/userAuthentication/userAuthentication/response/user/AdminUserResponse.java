package com.userAuthentication.userAuthentication.response.user;

import java.util.List;

import com.userAuthentication.userAuthentication.model.admin.user.AdminUserModel;
import com.userAuthentication.userAuthentication.response.page.PageDetails;



public class AdminUserResponse {

	private String message;
	private String status;
	private List<AdminUserModel> userList;
	private PageDetails pageDetails;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<AdminUserModel> getUserList() {
		return userList;
	}

	public void setUserList(List<AdminUserModel> userList) {
		this.userList = userList;
	}

	public PageDetails getPageDetails() {
		return pageDetails;
	}

	public void setPageDetails(PageDetails pageDetails) {
		this.pageDetails = pageDetails;
	}

	public AdminUserResponse() {
		super();
	}

	public AdminUserResponse(String message, String status) {
		super();
		this.message = message;
		this.status = status;
	}

	public AdminUserResponse(String message, String status, List<AdminUserModel> userList, PageDetails pageDetails) {
		super();
		this.message = message;
		this.status = status;
		this.userList = userList;
		this.pageDetails = pageDetails;
	}

	@Override
	public String toString() {
		return "UserResponse [message=" + message + ", status=" + status + ", userList=" + userList + ", pageDetails="
				+ pageDetails + "]";
	}

}

