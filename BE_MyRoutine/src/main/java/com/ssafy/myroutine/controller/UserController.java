package com.ssafy.myroutine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myroutine.model.service.UserService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/user")
@Tag(name = "UserController", description = "사용자 CRUD")
// @CrossOrigin 추가 예정
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 사용자 회원가입

	// 사용자 로그인

	// 사용자 로그아웃

	// 사용자 정보 수정

	// 사용자 회원탈퇴

	// 사용자 정보 조회

}
