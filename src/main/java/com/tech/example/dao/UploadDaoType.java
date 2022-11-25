package com.tech.example.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import com.tech.example.dto.UserDto;

@Mapper
public interface UploadDaoType {

	public void userinserts(UserDto userdto);

	public ArrayList<UserDto> userinfo();

	public void deleteinfo();

}
