package com.ssafy.myroutine.model.dao;

import java.util.List;

import com.ssafy.myroutine.model.dto.Question;
import com.ssafy.myroutine.model.dto.SearchCondition;

public interface QuestionDao {
	
	// 질문 등록
	Question insertQuestion(Question question);
	
	// 질문 수정
	int updateQuestion(Question question);
	
	// 질문 삭제
	int deleteQuestion(int id);
	
	// 질문 검색 조회
	List<Question> searchQuestion(SearchCondition con);
	
	// 질문 상세
	Question getQuestion(int id);
	
}
