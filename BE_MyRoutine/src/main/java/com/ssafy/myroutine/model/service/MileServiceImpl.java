package com.ssafy.myroutine.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ssafy.myroutine.model.dao.MileDao;
import com.ssafy.myroutine.model.dto.Mile;

@Service
public class MileServiceImpl implements MileService {

	private final MileDao mileDao;
	
	public MileServiceImpl(MileDao mileDao) {
		this.mileDao = mileDao;
	}
	
	// 마일리지 등록
	@Override
	public boolean registMile(Mile mile) {
		return mileDao.insert(mile) == 1;
	}

	// 마일리지 삭제
	@Override
	public boolean removeMile(int id) {
		return mileDao.delete(id) == 1;
	}

	// 마일리지 전체 조회
	@Override
	public List<Mile> getList(int userId) {
		return mileDao.selectAll(userId);
	}

	// 총 마일리지 조회
	@Override
	public int getTotalMile(int userId) {
		return mileDao.getTotalMile(userId);
	}

}
