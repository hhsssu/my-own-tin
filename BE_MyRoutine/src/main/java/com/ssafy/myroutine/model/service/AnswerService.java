package com.ssafy.myroutine.model.service;

import java.util.List;

import com.ssafy.myroutine.model.dto.Answer;

public interface AnswerService {

	// 답변 등록
	Answer registAnswer(Answer answer);

	// 답변 수정
	int modifyAnswer(Answer answer);
	
	// 답변 삭제
	int removeAnswer(int id);
	
	// 답변 전체 조회
	List<Answer> selectAllAnswer(int questionId);
}
