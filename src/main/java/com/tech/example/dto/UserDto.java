package com.tech.example.dto;

import java.sql.Timestamp;

import org.apache.ibatis.session.SqlSession;

public class UserDto {

	private String id;
	private String pwd;
	private String name;
	private String level;
	private String desc;
	private Timestamp reg_date;

	private String originText;
	
	public UserDto() {
		
	}

	public UserDto(String id, String pwd, String name, String level, String desc, Timestamp reg_date) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.level = level;
		this.desc = desc;
		this.reg_date = reg_date;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Timestamp getReg_date() {
		return reg_date;
	}

	public void setReg_date(Timestamp reg_date) {
		this.reg_date = reg_date;
	}

	public String getOriginText() {
		return originText;
	}

	public void setOriginText(String originText) {
		this.originText = originText;
	}

	
}
