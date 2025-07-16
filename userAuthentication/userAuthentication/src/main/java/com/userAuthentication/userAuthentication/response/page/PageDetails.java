package com.userAuthentication.userAuthentication.response.page;

public class PageDetails {

	private long totalPage;
	private long totalRecords;

	private long pageNo;
	private long noOfRecords;

	public long getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public void setTotalRecords(long totalRecords) {
		this.totalRecords = totalRecords;
	}

	public long getPageNo() {
		return pageNo;
	}

	public void setPageNo(long pageNo) {
		this.pageNo = pageNo;
	}

	public long getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(long noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public PageDetails() {
		super();
	}

	public PageDetails(long pageNo, long noOfRecords, long totalPage, long totalRecords) {
		super();

		this.pageNo = pageNo;
		this.noOfRecords = noOfRecords;
		this.totalPage = totalPage;
		this.totalRecords = totalRecords;

	}

	@Override
	public String toString() {
		return "PageDetails [totalPage=" + totalPage + ", totalRecords=" + totalRecords + ", pageNo=" + pageNo
				+ ", noOfRecords=" + noOfRecords + "]";
	}

}
