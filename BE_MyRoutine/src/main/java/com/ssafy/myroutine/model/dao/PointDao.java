package com.ssafy.myroutine.model.dao;

import java.util.List;

import com.ssafy.myroutine.model.dto.Point;

public interface PointDao {
	// 포인트 등록
	int insert(Point point);

	// 포인트 삭제
	int delete(int id);

	// 포인트 전체 조회
	List<Point> selectAll(int userId);

	// 총 포인트 조회
	int getTotalPoint(int userId);

}
