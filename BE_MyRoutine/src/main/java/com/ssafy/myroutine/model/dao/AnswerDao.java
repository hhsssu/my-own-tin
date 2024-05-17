package com.ssafy.myroutine.model.dao;

import java.util.List;

import com.ssafy.myroutine.model.dto.Answer;

public interface AnswerDao {
	
	// 답변 등록
	int insert(Answer answer);
	
	// 답변 수정
	int update(Answer answer);
	
	// 답변 삭제
	int delete(int id);
	
	// 답변 전체 조회
	List<Answer> selectAll(int questionId);
}
