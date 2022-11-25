package com.tech.example.dto;

import java.util.List;

public class DBFileUploadResult {
	
	private int totalCount = 0;
	
	private int successCount = 0;
	
	private int failCount = 0;
	
	private List<DBFileLine> failList;
	
//	private boolean;

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
	
	public void increaseSuccessCount() {
		this.successCount++;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}
	
	public void increaseFailCount() {
		this.failCount++;
	}

	public List<DBFileLine> getFailList() {
		return failList;
	}

	public void setFailList(List<DBFileLine> failList) {
		this.failList = failList;
	}

	public boolean isAllSuccess() {
		return this.failCount == 0;
	}
	
}
