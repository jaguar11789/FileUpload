package com.tech.example.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tech.example.dao.UploadDaoType;
import com.tech.example.dto.DBFileLine;
import com.tech.example.dto.DBFileUploadResult;
import com.tech.example.dto.UserDto;

@Service
public class FileUploadServiceImpl implements FileUploadService{
	
	private final static String SAVE_DIR_PATH = 
			"C:\\Users\\minku\\eclipse-workspace\\"
			+ "1st_test\\src\\main\\webapp\\"
			+ "resources\\upload\\";
	
	private final SqlSession sqlsession;
	
	public String saveAndGetFilePath(MultipartFile multipartFile) {
		
		String savePath = SAVE_DIR_PATH + multipartFile.getOriginalFilename();
		try {
			multipartFile.transferTo(new File(savePath));
			return savePath;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<UserDto> getUserInfoListFromFile(String filePath) {
		
		try(BufferedReader reader = new BufferedReader(new FileReader(filePath));) {
			List<UserDto> list = new ArrayList<>();
			String line = null;
			
			while( (line = reader.readLine()) != null ) {
				
				UserDto userDto = new UserDto();
				userDto.setOriginText(line);
				try {
					String split[] = line.split("/");// 구분자 /
					
					userDto.setId(split[0]);
					userDto.setPwd(split[1]);
					userDto.setName(split[2]);
					userDto.setLevel(split[3]);
					userDto.setDesc(split[4]);
					userDto.setReg_date(Timestamp.valueOf(split[5]));// IllegalArgumentException 발생
					
				} catch (Exception e) { }
				
				list.add(userDto);
			}
			
			return list;
		} catch (Exception e) {
			return null;
		} 
		
	}
	
	
	public DBFileUploadResult insertUserInfoList(List<UserDto> list) {
		
		DBFileUploadResult result = new DBFileUploadResult();
		result.setSuccessCount(list.size());
		
		for(int i = 0; i < list.size(); i++) {
			UserDto dto = list.get(i);
			try {
				insertUserInfo(dto);
				result.increaseSuccessCount();
				// 성공 카운트 ++
			} catch (Exception e) {
				// 실패 라인,텍스트 저장
				result.increaseFailCount();
				DBFileLine line = new DBFileLine();
				line.setLineNumber(i);
				line.setLineText(dto.getOriginText());
				
			}
		}
		
		return result;
	}
	
	public FileUploadServiceImpl(SqlSession sqlsession) {
		this.sqlsession = sqlsession;
	}

	@Override
	public void insertUserInfo(UserDto userdto) {
		UploadDaoType uploadDaoType = sqlsession.getMapper(UploadDaoType.class);
		uploadDaoType.userinserts(userdto);
	}

	@Override
	public ArrayList<UserDto> selectUserinfo() {
		UploadDaoType uploadDaoType = sqlsession.getMapper(UploadDaoType.class);
		return uploadDaoType.userinfo();
	}

	@Override
	public void deleteInfoAll() {
		UploadDaoType uploadDaoType = sqlsession.getMapper(UploadDaoType.class);
		uploadDaoType.deleteinfo();
	}
	
	
}
