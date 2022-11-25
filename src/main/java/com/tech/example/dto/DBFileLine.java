package com.tech.example.dto;

public class DBFileLine {
	
	private int lineNumber;
	private String lineText;

	public void setLineNumber(int lineNumber) {
		this.lineNumber = lineNumber;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	public void setLineText(String lineText) {
		this.lineText = lineText;
	}

	public String getLineText() {
		return lineText;
	}
}