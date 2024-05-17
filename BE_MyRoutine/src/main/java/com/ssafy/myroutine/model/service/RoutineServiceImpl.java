package com.ssafy.myroutine.model.service;

import java.util.List;

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
	public Routine registRoutine(Routine routine) {
		return routineDao.insert(routine);
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
	public List<Routine> searchRoutine(SearchCondition con) {
		return routineDao.search(con);
	}
	
	// 루틴 상세 조회
	@Override
	public Routine getRoutine(int id) {
		return routineDao.searchById(id);
	}
	
}
