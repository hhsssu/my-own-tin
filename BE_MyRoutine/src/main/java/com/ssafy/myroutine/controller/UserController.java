package com.ssafy.myroutine.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myroutine.model.dto.User;
import com.ssafy.myroutine.model.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/myroutine/user")
@Tag(name = "UserController", description = "사용자 CRUD")
@CrossOrigin("http://localhost:5173")
public class UserController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	// 사용자 회원가입
	@PostMapping("/")
	@Transactional
	@Operation(summary = "사용자 회원가입", description = "사용자 회원가입 기능")
	public ResponseEntity<?> insert(@RequestBody User user) {
		userService.registUser(user);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	// 사용자 로그인
	@PostMapping("/login")
	@Operation(summary = "사용자 로그인", description = "사용자 로그인 기능")
	public ResponseEntity<?> login(@RequestBody User user, HttpSession session) {
		Map<String, String> info = new HashMap<>();
		info.put("email", user.getEmail());
		info.put("password", user.getPassword());
		
		System.out.println(info.toString());
		User loginUser = userService.getUser(info);

		
		if (session.getAttribute("loginUser") == null) {
			if (loginUser != null) {
				session.setAttribute("loginUser", loginUser);
				return new ResponseEntity<User>(loginUser, HttpStatus.OK);
			} else {
				String msg = "아이디(이메일) 또는 비밀번호를 확인하세요.";
				return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
			}
		} else {
			String msg = "이미 로그인이 되어있습니다.";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	// 사용자 로그아웃
	@DeleteMapping("/logout")
	@Operation(summary = "사용자 로그아웃", description = "사용자 로그아웃 기능")
	public ResponseEntity<String> logout(HttpSession session) {
		// 세션에서 사용자 정보 삭제
		if (session.getAttribute("loginUser") != null) {
			session.removeAttribute("loginUser");
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		} else {
			String msg = "로그인이 되어있지 않습니다.";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
	}

	// 사용자 정보 수정
	@PutMapping("/")
	@Operation(summary = "사용자 정보 수정", description = "사용자 정보 수정 기능")
	public ResponseEntity<String> update(@RequestParam int id, @RequestBody User user) {
		user.setId(id);
		if (userService.modifyUser(user)) 
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	// 사용자 회원탈퇴
	@PutMapping("/delete")
	@Operation(summary = "사용자 회원탈퇴", description = "사용자 회원탈퇴 기능")
	public ResponseEntity<String> delete(@RequestParam int id) {
		if (userService.cancelUser(id))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	// 사용자 정보 조회
	@GetMapping("/")
	@Operation(summary = "사용자 정보 조회", description = "사용자 정보 조회 기능")
	public ResponseEntity<?> detail(HttpSession session) {
		// 로그인한 유저가 있는 경우 정보 조회 (마이 페이지)
		User user = (User) session.getAttribute("loginUser");
		if (user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} else {
			String msg = "로그인이 되어있지 않습니다.";
			return new ResponseEntity<String>(msg, HttpStatus.BAD_REQUEST);
		}
	}
	

}
