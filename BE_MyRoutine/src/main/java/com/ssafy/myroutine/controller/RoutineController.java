package com.ssafy.myroutine.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myroutine.model.service.RoutineService;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/routine")
@Tag(name = "RoutineController", description = "루틴 CRUD")
// @CrossOrigin 추가 예정
public class RoutineController {

	private final RoutineService routineService;
	
	@Autowired
	public RoutineController(RoutineService routineService) {
		this.routineService = routineService;
	}
	
	// 루틴 등록

	// 루틴 수정

	// 루틴 삭제

	// 루틴 조회

}
