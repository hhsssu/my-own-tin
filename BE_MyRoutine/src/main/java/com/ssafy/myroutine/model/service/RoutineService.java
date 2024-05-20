package com.ssafy.myroutine.model.service;

import java.util.List;

import com.ssafy.myroutine.model.dto.Routine;
import com.ssafy.myroutine.model.dto.SearchCondition;

public interface RoutineService {

	// 루틴 등록
	boolean registRoutine(Routine routine);

	// 루틴 수정
	boolean modifyRoutine(Routine routine);

	// 루틴 삭제
	boolean removeRoutine(int id);

	// 루틴 조회
	List<Routine> searchRoutine(SearchCondition con);

	// 루틴 상세
	Routine getRoutine(int id);
	
	// 루틴 보관함 조회
	List<Routine> searchRoutineMarked(int userId);
}
