package com.leaveManagement.leaveManagement.request.leaveSenction;

public class LeaveBreakUp {

	private String leaveName;
	private Long numberOfDays;
	public LeaveBreakUp(String leaveName, Long numberOfDays) {
		super();
		this.leaveName = leaveName;
		this.numberOfDays = numberOfDays;
	}
	public LeaveBreakUp() {
		super();
	}
	public String getLeaveName() {
		return leaveName;
	}
	public void setLeaveName(String leaveName) {
		this.leaveName = leaveName;
	}
	public Long getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(Long numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	@Override
	public String toString() {
		return "LeaveBreakUp [leaveName=" + leaveName + ", numberOfDays=" + numberOfDays + "]";
	}
	
}
