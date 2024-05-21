package com.ssafy.myroutine.model.dao;

import java.util.Map;

import com.ssafy.myroutine.model.dto.User;

public interface UserDao {
	
	// 사용자 회원가입
	int insert(User user);
	
	// 사용자 로그인 = 사용자 정보 조회
	User selectOne(Map<String, String> user);
	
	// 사용자 정보 수정
	int update(User user);
	
	// 사용자 회원탈퇴
	int delete(int id);
	
	// id를 통한 사용자 조회
	User selectById(int id);
	
}
