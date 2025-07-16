package com.taskManagement.taskManagement.response.employeeTask;

public class CommentResponse {

	private String comment;

	public CommentResponse(String comment) {
		super();
		this.comment = comment;
	}

	public CommentResponse() {
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
		return "Comment [comment=" + comment + "]";
	}
	
}
