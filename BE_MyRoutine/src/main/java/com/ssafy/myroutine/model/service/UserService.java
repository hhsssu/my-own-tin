package com.ssafy.myroutine.model.service;

import java.util.Map;

import com.ssafy.myroutine.model.dto.User;

public interface UserService {

	// 사용자 회원가입
	boolean registUser(User user);

	// 사용자 로그인 = 사용자 정보 조회
	User getUser(Map<String, String> user);

	// 사용자 정보 수정
	boolean modifyUser(User user);

	// 사용자 회원 탈퇴
	boolean cancelUser(int id);

	// id를 통한 사용자 조회
	User getUserById(int id);
	
	// 이메일 중복 조회
	boolean checkEmail(String email);
	
	// 닉네임 중복 조회
	boolean checkNickname(String nickname);
}
