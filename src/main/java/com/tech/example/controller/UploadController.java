package com.tech.example.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.PatternSyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.session.SqlSession;
import org.postgresql.util.PSQLException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.tech.example.dto.UserDto;
import com.tech.example.service.FileUploadService;
import com.tech.example.dao.UploadDaoType;
import com.tech.example.dto.DBFileLine;
import com.tech.example.dto.DBFileUploadResult;

@Controller
@RequestMapping("/test")
public class UploadController {

	private final FileUploadService fileUploadService;

	public UploadController(FileUploadService fileUploadService) {
		this.fileUploadService = fileUploadService;
	}

	@RequestMapping("/textupload")
	public String uploadfile(MultipartHttpServletRequest request, Model model) throws IOException {

		// 1. 파라미터 검사
		// 파일이 없으면 리턴
		if (request.getFileMap().isEmpty())
			return "test/uploadfile";

		// 2. 파일을 저장
		String uploadFileName = request.getFileNames().next();

		System.out.println(uploadFileName);

		MultipartFile multipartFile = request.getFile(uploadFileName);
		String filePath = fileUploadService.saveAndGetFilePath(multipartFile);

		if (filePath == null)
			return "test/uploadfile";

		// 3. 저장된 경로의 파일 읽기
		List<UserDto> userList = fileUploadService.getUserInfoListFromFile(filePath);

		if (userList == null)
			return "test/uploadfile";

		// 3. List<UserDTO> 를 DB에 저장
		DBFileUploadResult result = fileUploadService.insertUserInfoList(userList);

		// 최종 model 속성에 저장
		model.addAttribute("filename", uploadFileName);

		if (result.isAllSuccess()) {
			model.addAttribute("totallineCnt", result.getTotalCount());
			model.addAttribute("successCnt", result.getSuccessCount());

			return "test/userinfo";

		} else {
			model.addAttribute("failCnt", result.getFailCount());
			model.addAttribute("failLinelist", result.getFailList());
			model.addAttribute("successCnt", result.getSuccessCount());

			return "test/uploadfail";
		}

//		
//		File file1 = null;
//		String root = null;
//		String filename = null;
//		MultipartFile file = null;
//		BufferedReader bufferedreader = null;
//
//		UserDto userdto = new UserDto();
//
//		int lineCnt = 0;
//		int failCnt = 0;
//		int successCnt = 0;
//
//		file = request.getFile("file1");
//		System.out.println("file : " + file);
//
//		filename = file.getOriginalFilename(); // 파일 이름을 반환하기 위해 getOriginalFilename()함수 사용
//		root = "C:\\Users\\minku\\eclipse-workspace\\1st_test\\src\\main\\webapp\\resources\\upload" + "\\" + filename;
//
//		System.out.println("============파일명 반환 성공============\n");
//
//		try {
//			file.transferTo(new File(root));// 파일 업로드를 위해 transferTo()함수 사용
//		} catch (IOException e1) {
//			System.out.println(e1);
//
//			return "test/uploadfile";
//		}
//		System.out.println("============filename : " + filename + "============\n");
//
//		List<DBFileLine> failLineList = new ArrayList<DBFileLine>();
//
//		try {
//			// 파일 객체 생성
//			file1 = new File(root);
//			// 입력 스트림 생성
//			System.out.println("============file1 : " + file1 + "============\n");
//			// FileReader filereader = new FileReader(file1);
//			bufferedreader = new BufferedReader(new FileReader(file1));// 한줄씩 읽기
//			// String s = null; // s에 한줄씩 불러오기
//			String lineSentence = null;
//
//			while ((lineSentence = bufferedreader.readLine()) != null) {
//				lineCnt++;
//				try {
//					String split[] = lineSentence.split("/");// 구분자 /
//
//					userdto.setId(split[0]);
//					userdto.setPwd(split[1]);
//					userdto.setName(split[2]);
//					userdto.setLevel(split[3]);
//					userdto.setDesc(split[4]);
//					userdto.setReg_date(Timestamp.valueOf(split[5]));// IllegalArgumentException 발생
//
//					System.out.println(lineSentence);
//					System.out.println("성공 라인수 = " + lineCnt);
//
//					//uploaddao.userinserts(userdto);
//					
//					fileUploadService.insertUserInfo(userdto);
//					successCnt++;
//
//				} catch (Exception e) {
//					System.out.println(e);
//					System.out.println(lineSentence);
//
//					DBFileLine fileLine = new DBFileLine();
//					fileLine.setLineNumber(lineCnt);
//					fileLine.setLineText(lineSentence);
//					failLineList.add(fileLine);
//
//				}
//			}
//		} catch (Exception e) {
//		} finally {
//			bufferedreader.close();
//		}
//
//		model.addAttribute("filename", filename);
//
//		if (failLineList.size() == 0) {
//			model.addAttribute("totallineCnt", lineCnt);
//			model.addAttribute("successCnt", successCnt);
//
//			return "test/userinfo";
//
//		} else {
//			model.addAttribute("failCnt", failLineList.size());
//			model.addAttribute("failLinelist", failLineList);
//			model.addAttribute("successCnt", successCnt);
//
//			return "test/uploadfail";
//		}
	}

	@RequestMapping("/deleteinfo")
	public String deleteinfo(HttpServletRequest request, Model model) {

		fileUploadService.deleteInfoAll();
		return "test/uploadfile";
	}

	@RequestMapping("/userinfo")
	public String userinfo(HttpServletRequest request, Model model) {

		return "test/userinfo";
	}

	@RequestMapping("/test")
	public String NewFile() {

		return "test/test";
	}

	@RequestMapping("/uploadfile")
	public String test() {

		return "test/uploadfile";
	}

	@RequestMapping("/uploadfail")
	public String uploadfail() {

		return "test/uploadfail";
	}

// @ExceptionHandler(value = { NullPointerException.class })
//	public Object nullex(Exception e) {
//		System.err.println(e);// 에러 발생시 어떤 에러인지 출력
//		return "myService";
}