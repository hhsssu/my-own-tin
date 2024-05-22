package com.ssafy.myroutine.model.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.ssafy.myroutine.model.dao.RoutineDao;
import com.ssafy.myroutine.model.dto.Routine;
import com.ssafy.myroutine.model.dto.SearchCondition;

@Service
public class RoutineServiceImpl implements RoutineService {

	private final RoutineDao routineDao;

	public RoutineServiceImpl(RoutineDao routineDao) {
		this.routineDao = routineDao;
	}

	// 루틴 등록
	@Override
	public boolean registRoutine(Routine routine) {
		return routineDao.insert(routine) == 1;
	}

	// 루틴 수정
	@Override
	public boolean modifyRoutine(Routine routine) {
		return routineDao.update(routine) == 1;
	}

	// 루틴 삭제
	@Override
	public boolean removeRoutine(int id) {
		return routineDao.delete(id) == 1;
	}

	// 루틴 검색 조회
	@Override
	public List<Routine> searchRoutine(SearchCondition searchCondition) {
		String queryConditions = searchCondition.toQueryConditions();
		return routineDao.search(queryConditions, searchCondition);
	}

	// 루틴 상세 조회
	@Override
	public Routine getRoutine(int id) {
		return routineDao.searchById(id);
	}

	@Override
	public List<Routine> getRoutineByUserId(int userId) {
		return routineDao.searchByUserId(userId);
	}

	// 루틴 보관함 조회
	@Override
	public List<Routine> searchRoutineMarked(int userId) {
		return routineDao.searchMarked(userId);
	}

	@Override
	public List<Routine> findByDate(LocalDate date, int userId) {
		Map<String, Object> params = new HashMap<>();
		params.put("date", date);
		params.put("userId", userId);
		return routineDao.findByDateAndUserId(params);
	}

	// 좋아요 수 증가
	@Override
	public int updateLikeCnt(int id) {
		return routineDao.updateLikeCnt(id);
	}

}
