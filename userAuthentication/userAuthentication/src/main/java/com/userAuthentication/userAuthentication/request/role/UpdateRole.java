package com.userAuthentication.userAuthentication.request.role;

public class UpdateRole {
	
	private String oldRoleName;
	private String newRoleName;
	public UpdateRole(String oldRoleName, String newRoleName) {
		super();
		this.oldRoleName = oldRoleName;
		this.newRoleName = newRoleName;
	}
	public UpdateRole() {
		super();
	}
	public String getOldRoleName() {
		return oldRoleName;
	}
	public void setOldRoleName(String oldRoleName) {
		this.oldRoleName = oldRoleName;
	}
	public String getNewRoleName() {
		return newRoleName;
	}
	public void setNewRoleName(String newRoleName) {
		this.newRoleName = newRoleName;
	}
	@Override
	public String toString() {
		return "UpdateRole [oldRoleName=" + oldRoleName + ", newRoleName=" + newRoleName + "]";
	}
}
