package com.ssafy.myroutine.model.service;

import java.util.List;

import com.ssafy.myroutine.model.dto.Routine;
import com.ssafy.myroutine.model.dto.SearchCondition;

public interface RoutineService {

	// 루틴 등록
	Routine registRoutine(Routine routine);

	// 루틴 수정
	int modifyRoutine(Routine routine);

	// 루틴 삭제
	int removeRoutine(int id);

	// 루틴 조회
	List<Routine> searchRoutine(SearchCondition con);

	// 루틴 상세
	Routine getRoutine(int id);
}
