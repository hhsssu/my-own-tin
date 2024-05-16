package com.ssafy.myroutine.model.dao;

import com.ssafy.myroutine.model.dto.Answer;

public interface AnswerDao {
	
	// 답변 등록
	Answer insert(Answer answer);
	
	// 답변 수정
	int update(Answer answer);
	
	// 답변 삭제
	int delete(int id);
	
}
