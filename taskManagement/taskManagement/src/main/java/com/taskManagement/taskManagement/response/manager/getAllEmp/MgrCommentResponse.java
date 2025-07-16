package com.taskManagement.taskManagement.response.manager.getAllEmp;

public class MgrCommentResponse {

	private String comment;

	public MgrCommentResponse(String comment) {
		super();
		this.comment = comment;
	}

	public MgrCommentResponse() {
		super();
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String toString() {
		return "MgrCommentResponse [comment=" + comment + "]";
	}
	
}
