package com.tech.example.controller;

import java.util.ArrayList;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tech.example.dao.UploadDaoType;
import com.tech.example.dto.UserDto;
import com.tech.example.service.FileUploadServiceImpl;

@RestController
public class UploadRestController {

	@Autowired
	private SqlSession session;
	
	@Autowired
	private FileUploadServiceImpl fileuploadService;

	@PostMapping("/selectinfo")
	public ArrayList<UserDto> selectinfo(Model model) {

		System.out.println("왔음");

		UploadDaoType uploaddao = session.getMapper(UploadDaoType.class);
		ArrayList<UserDto> userinfo = uploaddao.userinfo();

		return userinfo;
	}
}
