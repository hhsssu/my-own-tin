package com.ssafy.myroutine.model.dao;

import java.util.List;

import com.ssafy.myroutine.model.dto.Answer;

public interface AnswerDao {
	
	// 답변 등록
	Answer insertAnswer(Answer answer);
	
	// 답변 수정
	int updateAnswer(Answer answer);
	
	// 답변 삭제
	int deleteAnswer(int id);
	
	// 답변 전체 조회
	List<Answer> selectAllAnswer(int questionId);
}
