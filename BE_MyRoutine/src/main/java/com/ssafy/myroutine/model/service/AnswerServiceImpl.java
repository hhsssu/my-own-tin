package com.ssafy.myroutine.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.myroutine.model.dao.AnswerDao;
import com.ssafy.myroutine.model.dto.Answer;

@Service
public class AnswerServiceImpl implements AnswerService {
	
	private final AnswerDao answerDao;
	
	public AnswerServiceImpl(AnswerDao answerDao) {
		this.answerDao = answerDao;
	}
	
	// 답변 등록
	@Override
	public Answer registAnswer(Answer answer) {
		return answerDao.insert(answer);
	}

	// 답변 수정
	@Override
	public int modifyAnswer(Answer answer) {
		return answerDao.update(answer);
	}

	// 답변 삭제
	@Override
	public int removeAnswer(int id) {
		return answerDao.delete(id);
	}

	// 답변 전체 조회
	@Override
	public List<Answer> selectAllAnswer(int questionId) {
		return answerDao.selectAll(questionId);
	}

}
