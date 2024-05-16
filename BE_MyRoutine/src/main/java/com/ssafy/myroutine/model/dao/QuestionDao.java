package com.ssafy.myroutine.model.dao;

import java.util.List;

import com.ssafy.myroutine.model.dto.Question;
import com.ssafy.myroutine.model.dto.SearchCondition;

public interface QuestionDao {
	
	// 질문 등록
	Question insert(Question question);
	
	// 질문 수정
	int update(Question question);
	
	// 질문 삭제
	int delete(int id);
	
	// 질문 조회
	List<Question> select(SearchCondition con);
	
}
