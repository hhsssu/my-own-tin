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

import com.ssafy.myroutine.model.dto.Mile;
import com.ssafy.myroutine.model.service.MileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/mile")
@Tag(name = "MileController", description = "마일리지 CRD")
@CrossOrigin("http://localhost:5173")
public class MileController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final MileService mileService;

	@Autowired
	public MileController(MileService mileService) {
		this.mileService = mileService;
	}

	// 마일리지 적립(등록)
	@PostMapping("/")
	@Transactional
	@Operation(summary = "마일리지 적립(등록)", description = "마일리지 적립(등록) 기능")
	public ResponseEntity<?> regist(@RequestBody Mile mile) {
		mileService.registMile(mile);
		return new ResponseEntity<Mile>(mile, HttpStatus.OK);
	}

	// 마일리지 내역 조회
	@GetMapping("/")
	@Operation(summary = "마일리지 내역 조회", description = "마일리지 전체 내역 조회 기능")
	public ResponseEntity<?> getMileList(@RequestParam int userId) {
		List<Mile> list = mileService.getList(userId);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Mile>>(list, HttpStatus.OK);
	}

	// 마일리지 총합
	@GetMapping("/total")
	@Operation(summary = "마일리지 총합 조회", description = "마일리지 총 합산 조회 기능")
	public ResponseEntity<?> getTotalMile(@RequestParam int userId) {
		int total = mileService.getTotalMile(userId);
		return new ResponseEntity<Integer>(total, HttpStatus.OK);
	}

	// 마일리지 내역 삭제 (기한 뒤 자동 삭제)

}
