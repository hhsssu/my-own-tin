package com.ssafy.myroutine.model.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.myroutine.model.dao.UserDao;
import com.ssafy.myroutine.model.dto.User;

@Service
public class UserServiceImpl implements UserService {

	private final UserDao userDao;

	public UserServiceImpl(UserDao userDao) {
		this.userDao = userDao;
	}

	// 사용자 회원가입
	@Override
	public User registUser(User user) {
		return userDao.insert(user);
	}

	// 사용자 로그인 = 사용자 정보 조회
	@Override
	public User getUser(Map<String, String> user) {
		return userDao.selectOne(user);
	}

	// 사용자 정보 수정
	@Override
	public boolean modifyUser(User user) {
		return userDao.update(user) == 1;
	}

	// 사용자 회원 탈퇴
	@Override
	public boolean cancelUser(int id) {
		return userDao.delete(id) == 1;
	}

}
