package com.ssafy.myroutine.model.dao;

import java.util.Map;

import com.ssafy.myroutine.model.dto.User;

public interface UserDao {
	
	// 사용자 회원가입
	User insertUser(User user);
	
	// 사용자 로그인 = 사용자 정보 조회
	User loginUser(Map<String, String> user);
	
	// 사용자 정보 수정
	int updateUser(User user);
	
	// 사용자 회원탈퇴
	int deleteUser(int id);
	
	// 사용자 정보 조회
	// 로그인과 기능 통일할지 논의
//	User selectOne(Map<String, String> info);
	
	
}
