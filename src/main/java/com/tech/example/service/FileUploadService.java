package com.tech.example.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.tech.example.dto.DBFileUploadResult;
import com.tech.example.dto.UserDto;

public interface FileUploadService {

	String saveAndGetFilePath(MultipartFile multipartFile);
	
	List<UserDto> getUserInfoListFromFile(String filePath);
	
	DBFileUploadResult insertUserInfoList(List<UserDto> list);
	
	void insertUserInfo(UserDto userdto);
	 
	ArrayList<UserDto> selectUserinfo();

	void deleteInfoAll();
}
