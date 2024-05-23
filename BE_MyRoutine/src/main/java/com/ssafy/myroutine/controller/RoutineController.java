package com.ssafy.myroutine.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

import com.ssafy.myroutine.model.dto.Routine;
import com.ssafy.myroutine.model.dto.SearchCondition;
import com.ssafy.myroutine.model.service.RoutineService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/myroutine/routine")
@Tag(name = "RoutineController", description = "루틴 CRUD")
@CrossOrigin("http://localhost:5173")
public class RoutineController {
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";

	private final RoutineService routineService;

	@Autowired
	public RoutineController(RoutineService routineService) {
		this.routineService = routineService;
	}

	// 루틴 등록
	@PostMapping("/")
	@Transactional
	@Operation(summary = "루틴 등록", description = "루틴 등록 기능")
	public ResponseEntity<?> regist(@RequestBody Routine routine) {
		routineService.registRoutine(routine);
		return new ResponseEntity<Routine>(routine, HttpStatus.OK);
	}

	// 루틴 수정
	@PutMapping("/")
	@Transactional
	@Operation(summary = "루틴 수정", description = "루틴 수정 기능")
	public ResponseEntity<String> update(@RequestParam int id, @RequestBody Routine routine) {
		routine.setId(id);
		if (routineService.modifyRoutine(routine))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	// 루틴 삭제
	@PutMapping("/delete")
	@Transactional
	@Operation(summary = "루틴 삭제", description = "루틴 삭제 기능 / 삭제되지 않고 delete_at 에 삭제 시각 업데이트")
	public ResponseEntity<String> delete(@RequestParam int id) {
		if (routineService.removeRoutine(id))
			return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
		return new ResponseEntity<String>(FAIL, HttpStatus.BAD_REQUEST);
	}

	// 루틴 검색 조회
//	@GetMapping("/")
//	@Operation(summary = "루틴 검색 조회", description = "루틴 검색 조회")
//	public ResponseEntity<?> search(@ModelAttribute SearchCondition con) {
////		System.out.println(con.toString());
////		System.out.println(con.getKey() == "");
//		List<Routine> list = routineService.searchRoutine(con);
//		if (list == null || list.size() == 0)
//			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
//		return new ResponseEntity<List<Routine>>(list, HttpStatus.OK);
//	}

	// 루틴 검색 조회
	@GetMapping("/search")
	@Operation(summary = "루틴 검색 조회", description = "루틴 검색 조회")
	public ResponseEntity<?> search(@RequestParam("level") String level, @RequestParam("gender") String gender,
			@RequestParam("ageRange") String ageRange, @RequestParam("orderBy") String orderBy, @RequestParam("word") String word) {
		System.out.println(word);
		System.out.println(word == "");
		SearchCondition searchCondition = new SearchCondition(level, gender, ageRange, orderBy);
		if (word != "") {
			searchCondition.setKey("content");
			searchCondition.setWord(word);
		}
		List<Routine> list = routineService.searchRoutine(searchCondition);

		if (list == null || list.isEmpty())
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Routine>>(list, HttpStatus.OK);
	}

	// 루틴 상세 조회
	@GetMapping("/detail")
	@Operation(summary = "루틴 상세 조회", description = "루틴 상세 조회")
	public ResponseEntity<Routine> detail(@RequestParam("routineId") int id) {
		Routine routine = routineService.getRoutine(id);
		if (routine != null)
			return new ResponseEntity<Routine>(routine, HttpStatus.OK);
		return new ResponseEntity<Routine>(HttpStatus.NOT_FOUND);
	}

	// 루틴페이지 내 나의 루틴 불러오기
	// 만약 search 메서드로 가능하다면 사용 X
	@GetMapping("/mine")
	@Operation(summary = "나의 루틴", description = "루틴 페이지 나의 루틴 불러오기")
	public ResponseEntity<?> getMine(@RequestParam("userId") int userId) {
		List<Routine> list = routineService.getRoutineByUserId(userId);
		return new ResponseEntity<List<Routine>>(list, HttpStatus.OK);
	}

	// 마이페이지 내 나의 루틴 보관함 불러오기
	// 만약 search 메서드로 가능하다면 사용 X
	@GetMapping("/marked")
	@Operation(summary = "나의 루틴 보관함", description = "마이페이지 내 나의 보관함 불러오기")
	public ResponseEntity<?> getMarked(@RequestParam("userId") int userId) {
		List<Routine> list = routineService.searchRoutineMarked(userId);
		if (list == null || list.size() == 0)
			return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Routine>>(list, HttpStatus.OK);
	}

	// 날짜별 나의 루틴 불러오기
	@GetMapping("/byDate")
	@Operation(summary = "날짜별 나의 루틴 불러오기", description = "날짜별 나의 루틴 불러오기 기능")
	public ResponseEntity<?> getRoutineByDate(
			@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
			@RequestParam("userId") int userId) {
		List<Routine> routines = routineService.findByDate(date, userId);
		return new ResponseEntity<>(routines, HttpStatus.OK);
	}

	// 좋아요 수 증가
	@PutMapping("/like")
	@Operation(summary = "좋아요 수 증가", description = "좋아요 수 증가 기능")
	public ResponseEntity<?> like(@RequestParam("id") int id) {
		int result = routineService.updateLikeCnt(id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
