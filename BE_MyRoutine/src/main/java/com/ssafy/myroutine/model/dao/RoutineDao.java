package com.ssafy.myroutine.model.dao;

import java.util.List;

import com.ssafy.myroutine.model.dto.Routine;
import com.ssafy.myroutine.model.dto.SearchCondition;

public interface RoutineDao {

	// 루틴 등록
	Routine insertRoutine(Routine routine);

	// 루틴 수정
	int updateRoutine(Routine routine);

	// 루틴 삭제
	int deleteRoutine(int id);

	// 루틴 조회
	List<Routine> searchRoutine(SearchCondition con);
	
	// 루틴 상세
	Routine getRoutine(int id);

}
