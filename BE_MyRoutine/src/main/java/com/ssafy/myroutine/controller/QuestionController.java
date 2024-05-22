package com.ssafy.myroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myroutine.model.dto.Answer;
import com.ssafy.myroutine.model.dto.Question;
import com.ssafy.myroutine.model.dto.SearchCondition;
import com.ssafy.myroutine.model.service.AnswerService;
import com.ssafy.myroutine.model.service.QuestionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/que")
@Tag(name = "QuestionController", description = "질문 및 답변 CRUD")
@CrossOrigin("http://localhost:5173")
public class QuestionController {
	// 응답을 편하게 하기 위해 상수로 지정
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final QuestionService queService;

	@Autowired
	public QuestionController(QuestionService queService) {
		this.queService = queService;
	}

	// 질문 등록
	@Transactional
	@PostMapping("/")
	@Operation(summary = "질문 등록", description = "질문 등록 기능")
	public ResponseEntity<?> write(@RequestBody Question que) {
		System.out.println(que.getRoutineId());
		queService.registQuestion(que);
		return new ResponseEntity<Question>(que, HttpStatus.CREATED);
	}

	// 질문 수정
	@Transactional
//	@PutMapping("/{id}") --> PathVariable 처리와 ID 추가 작업 필요
	@PutMapping("/")
	@Operation(summary = "질문 수정", description = "질문 수정 기능")
	public ResponseEntity<?> update(@RequestParam int id, @RequestBody Question que) {
		que.setId(id);
		if(queService.modifyQuestion(que)) // true
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	// 질문 삭제
	@Transactional
	@PutMapping("/delete")
	@Operation(summary = "질문 삭제", description = "질문 삭제 기능")
	public ResponseEntity<?> delete(@RequestParam int id) {
		if(queService.removeQuestion(id)) // true
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}
	
	// 질문 검색 조회
	@GetMapping("/")
	@Operation(summary = "질문 검색", description = "질문 검색 기능")
	public ResponseEntity<?> list(@RequestParam("level") String level, @RequestParam("gender") String gender, @RequestParam("ageRange") String ageRange, @RequestParam("orderBy") String orderBy) {
        SearchCondition searchCondition = new SearchCondition(level, gender, ageRange, orderBy);
        List<Question> list = queService.searchQuestions(searchCondition);
		
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Question>>(list, HttpStatus.OK);
	}
	
	// 질문 상세 조회
	@GetMapping("/{queId}")
	@Operation(summary = "질문 상세 조회", description = "질문 상세 조회 기능")
	public ResponseEntity<?> detail(@PathVariable("queId") int id) {
		Question question = queService.getQuestion(id);
		if(question != null)
			return new ResponseEntity<Question>(question, HttpStatus.OK);
		return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
	}


}
