package com.ssafy.myroutine.model.service;

import java.util.List;

import com.ssafy.myroutine.model.dto.Mile;

public interface MileService {
	// 마일리지 등록
	boolean registMile(Mile mile);
	
	// 마일리지 삭제
	boolean removeMile(int id);
	
	// 마일리지 전체 조회
	List<Mile> getList(int userId);
	
	// 총 마일리지 조회
	int getTotalMile(int userId);
	
}
