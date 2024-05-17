package com.ssafy.myroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myroutine.model.dto.Answer;
import com.ssafy.myroutine.model.dto.SearchCondition;
import com.ssafy.myroutine.model.service.AnswerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/ans")
@Tag(name = "QuestionController", description = "질문 및 답변 CRUD")
@CrossOrigin("http://localhost:5173")
public class AnswerController {
	// 응답을 편하게 하기 위해 상수로 지정
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	

	private final AnswerService ansService;

	@Autowired
	public AnswerController(AnswerService ansService) {
		this.ansService = ansService;
	}

	// 답변 등록
	@Transactional
	@PostMapping("/")
	@Operation(summary = "답변 등록", description = "답변 등록 기능")
	public ResponseEntity<?> write(@RequestBody Answer ans) {
		ansService.registAnswer(ans);
		return new ResponseEntity<Answer>(ans, HttpStatus.CREATED);
	}

	// 답변 수정
	@Transactional
	@PutMapping("/")
	@Operation(summary = "답변 수정", description = "답변 수정 기능")
	public ResponseEntity<?> update(@RequestBody Answer ans) {
		if(ansService.modifyAnswer(ans))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
		
	}

	// 답변 삭제
	@Transactional
	@PutMapping("/delete")
	@Operation(summary = "답변 삭제", description = "답변 삭제 기능")
	public ResponseEntity<?> delete(@RequestParam int id) {
		if(ansService.removeAnswer(id))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}
	
	// 답변 전체 조회
	@GetMapping("/")
	public ResponseEntity<?> list(@ModelAttribute SearchCondition con) {
		List<Answer> list = ansService.selectAllAnswer();
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Answer>>(list, HttpStatus.OK);
	}

}
