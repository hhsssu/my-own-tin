package com.ssafy.myroutine.model.dao;

import java.util.List;

import com.ssafy.myroutine.model.dto.Mile;

public interface MileDao {
	// 마일리지 등록
	int insert(Mile mile);

	// 마일리지 삭제
	int delete(int id);

	// 마일리지 전체 조회
	List<Mile> selectAll();

	// 총 마일리지 조회
	int getTotalMile();

}
