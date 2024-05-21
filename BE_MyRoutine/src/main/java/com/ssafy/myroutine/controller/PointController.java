package com.ssafy.myroutine.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.myroutine.model.dto.Point;
import com.ssafy.myroutine.model.service.PointService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/point")
@Tag(name = "PointController", description = "포인트 CRD")
@CrossOrigin("http://localhost:5173")
public class PointController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	private final PointService pointService;
	
	@Autowired
	public PointController(PointService pointService) {
		this.pointService = pointService;
	}
	
	// 포인트 적립(등록)
	@PostMapping("/")
	@Transactional
	@Operation(summary = "포인트 적립(등록)", description = "포인트 적립(등록) 기능")
	public ResponseEntity<?> regist(@RequestBody Point point) {
		pointService.registPoint(point);
		return new ResponseEntity<Point>(point, HttpStatus.OK);
	}
	
	// 포인트 내역 조회
	@GetMapping("/")
	@Operation(summary = "포인트 내역 조회", description = "포인트 전체 내역 조회 기능")
	public ResponseEntity<?> getPointList(@RequestParam int userId) {
		List<Point> list = pointService.getList(userId);
		if(list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Point>>(list, HttpStatus.OK);
	}
	
	// 포인트 총합
	@GetMapping("/total")
	@Operation(summary = "포인트 총합 조회", description = "포인트 총 합산 조회 기능")
	public ResponseEntity<?> getTotalPoint(@RequestParam int userId) {
		int total = pointService.getTotalPoint(userId);
		return new ResponseEntity<Integer>(total, HttpStatus.OK);
	}
	
	
	// 포인트 내역 삭제 (기한 뒤 자동 삭제)
}
