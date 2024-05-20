package com.ssafy.myroutine.model.service;

import java.util.List;

import com.ssafy.myroutine.model.dto.Point;

public interface PointService {
	// 포인트 등록
	boolean registPoint(Point point);
	
	// 포인트 삭제
	boolean removePoint(int id);
	
	// 포인트 전체 조회
	List<Point> getList(int userId);
	
	// 총 포인트 조회
	int getTotalPoint(int userId);
	
}
