package com.ssafy.myroutine.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.myroutine.model.dao.QuestionDao;
import com.ssafy.myroutine.model.dto.Question;
import com.ssafy.myroutine.model.dto.SearchCondition;

@Service
public class QuestionServiceImpl implements QuestionService {
	
	private final QuestionDao questionDao;
	
	public QuestionServiceImpl(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}
	
	// 질문 등록
	@Override
	public boolean registQuestion(Question question) {
		return questionDao.insert(question) == 1;
	}

	// 질문 수정
	@Override
	public boolean modifyQuestion(Question question) {
		return questionDao.update(question) == 1;
	}

	// 질문 삭제
	@Override
	public boolean removeQuestion(int id) {
		return questionDao.delete(id) == 1;
	}

	// 질문 검색 조회
	@Override
    public List<Question> searchQuestions(SearchCondition searchCondition) {
		String queryConditions = searchCondition.toQueryConditions();
        // 검색 조건에 따라 적절한 쿼리를 생성하여 DAO에 전달하여 검색
        return questionDao.search(queryConditions, searchCondition);
    }

	// 질문 상세
	@Override
	public Question getQuestion(int id) {
//		questionDao.updateViewCnt(id);
		return questionDao.searchById(id);
	}

}
