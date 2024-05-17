package com.ssafy.myroutine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myroutine.model.service.AnswerService;
import com.ssafy.myroutine.model.service.QuestionService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/qna")
@Tag(name = "QuestionController", description = "질문 및 답변 CRUD")
// @CrossOrigin 추가 예정
public class QuestionController {

	private final QuestionService queService;
	private final AnswerService ansService;
	
	@Autowired
	public QuestionController(QuestionService queService, AnswerService ansService) {
		this.queService = queService;
		this.ansService = ansService;
	}
	
	// 질문 등록

	// 질문 수정

	// 질문 삭제

	// 질문 조회

	// 답변 등록

	// 답변 수정

	// 답변 삭제

}
