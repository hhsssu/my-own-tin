package com.ssafy.myroutine.model.service;

import java.util.List;

import com.ssafy.myroutine.model.dao.PointDao;
import com.ssafy.myroutine.model.dto.Point;

public class PointServiceImpl implements PointService {

	private final PointDao pointDao;
	
	public PointServiceImpl(PointDao pointDao) {
		this.pointDao = pointDao;
	}
	
	// 포인트 등록
	@Override
	public boolean registPoint(Point point) {
		return pointDao.insert(point) == 1;
	}

	// 포인트 삭제
	@Override
	public boolean removePoint(int id) {
		return pointDao.delete(id) == 1;
	}

	// 포인트 전체 조회
	@Override
	public List<Point> getList(int userId) {
		return pointDao.selectAll(userId);
	}

	// 총 포인트 조회
	@Override
	public int getTotalPoint(int userId) {
		return pointDao.getTotalPoint(userId);
	}

}
