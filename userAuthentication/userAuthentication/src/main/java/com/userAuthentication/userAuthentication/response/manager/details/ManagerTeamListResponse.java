package com.userAuthentication.userAuthentication.response.manager.details;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ManagerTeamListResponse {

	private ManagerTeamPersonResponse managerDetails;
	private List<ManagerTeamPersonResponse> listOfTeam;
	public ManagerTeamListResponse(ManagerTeamPersonResponse managerDetails,
			List<ManagerTeamPersonResponse> listOfTeam) {
		super();
		this.managerDetails = managerDetails;
		this.listOfTeam = listOfTeam;
	}
	public ManagerTeamListResponse() {
		super();
	}
	public ManagerTeamPersonResponse getManagerDetails() {
		return managerDetails;
	}
	public void setManagerDetails(ManagerTeamPersonResponse managerDetails) {
		this.managerDetails = managerDetails;
	}
	public List<ManagerTeamPersonResponse> getListOfTeam() {
		return listOfTeam;
	}
	public void setListOfTeam(List<ManagerTeamPersonResponse> listOfTeam) {
		this.listOfTeam = listOfTeam;
	}
	@Override
	public String toString() {
		return "ManagerTeamListResponse [managerDetails=" + managerDetails + ", listOfTeam=" + listOfTeam + "]";
	} 
	
}
