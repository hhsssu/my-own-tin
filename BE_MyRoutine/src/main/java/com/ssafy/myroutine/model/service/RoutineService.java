package com.ssafy.myroutine.model.service;

import java.time.LocalDate;
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
	List<Routine> searchRoutine(SearchCondition searchCondition);

	// 루틴 상세
	Routine getRoutine(int id);

	// 사용자 루틴 조회
	List<Routine> getRoutineByUserId(int userId);
	
	// 루틴 보관함 조회
	List<Routine> searchRoutineMarked(int userId);
	
	// 날짜별 나의 루틴 조회
	List<Routine> findByDate(LocalDate date, int userId);
}
