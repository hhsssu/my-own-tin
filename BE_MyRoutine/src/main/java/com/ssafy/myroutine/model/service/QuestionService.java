package com.ssafy.myroutine.model.service;

import java.util.List;

import com.ssafy.myroutine.model.dto.Question;
import com.ssafy.myroutine.model.dto.SearchCondition;

public interface QuestionService {

	// 질문 등록
	boolean registQuestion(Question question);

	// 질문 수정
	boolean modifyQuestion(Question question);

	// 질문 삭제
	boolean removeQuestion(int id);

	// 질문 조회
	List<Question> searchQuestions(String queryConditions);

	// 질문 상세
	Question getQuestion(int id);
	
}
