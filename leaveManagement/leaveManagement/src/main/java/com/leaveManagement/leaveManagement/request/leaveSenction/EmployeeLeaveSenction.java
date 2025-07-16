package com.leaveManagement.leaveManagement.request.leaveSenction;

import java.util.List;

public class EmployeeLeaveSenction {

	private String empEmail;
	private List<LeaveBreakUp> leaveBreakUp;
	public EmployeeLeaveSenction(String empEmail, List<LeaveBreakUp> leaveBreakUp) {
		super();
		this.empEmail = empEmail;
		this.leaveBreakUp = leaveBreakUp;
	}
	public EmployeeLeaveSenction() {
		super();
	}
	public String getEmpEmail() {
		return empEmail;
	}
	public void setEmpEmail(String empEmail) {
		this.empEmail = empEmail;
	}
	public List<LeaveBreakUp> getLeaveBreakUp() {
		return leaveBreakUp;
	}
	public void setLeaveBreakUp(List<LeaveBreakUp> leaveBreakUp) {
		this.leaveBreakUp = leaveBreakUp;
	}
	@Override
	public String toString() {
		return "EmployeeLeaveSenction [empEmail=" + empEmail + ", leaveBreakUp=" + leaveBreakUp + "]";
	}
	
	
}
