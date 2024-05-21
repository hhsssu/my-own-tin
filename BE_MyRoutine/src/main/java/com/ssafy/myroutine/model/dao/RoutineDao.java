package com.ssafy.myroutine.model.dao;

import java.util.List;
import java.util.Map;

import com.ssafy.myroutine.model.dto.Routine;
import com.ssafy.myroutine.model.dto.SearchCondition;

public interface RoutineDao {

	// 루틴 등록
	int insert(Routine routine);

	// 루틴 수정
	int update(Routine routine);

	// 루틴 삭제
	int delete(int id);

	// 루틴 조회
	List<Routine> search(SearchCondition con);
	
	// 루틴 상세 조회
	Routine searchById(int id);
	
	// 루틴 보관함 조회
	List<Routine> searchMarked(int id);

	// 조회수 증가
//	public int updateViewCnt(int id);
	
	// 날짜와 사용자 ID에 따른 루틴 조회
    List<Routine> findByDateAndUserId(Map<String, Object> params);
}
