package com.ssafy.myroutine.model.dto;

public class SearchCondition {
	private String key;
	private String word;
	private String orderBy;
	private String orderByDir;
	private String level;
	private String age;
	private String gender;

	public SearchCondition() {
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public String getOrderByDir() {
		return orderByDir;
	}

	public void setOrderByDir(String orderByDir) {
		this.orderByDir = orderByDir;
	}

	// 선택된 값들을 설정하는 메서드
	public void setFilters(String level, String gender, String age) {
		this.level = level;
		this.gender = gender;
		this.age = age;
	}

	// 선택된 값들을 적절한 쿼리 조건으로 변환하는 메서드
	public String toQueryConditions() {
		StringBuilder conditions = new StringBuilder();

		// 레벨이 선택된 경우
		if (level != null && !level.isEmpty()) {
			conditions.append("level = '").append(level).append("' AND ");
		}

		// 성별이 선택된 경우
		if (gender != null && !gender.isEmpty()) {
			conditions.append("gender = '").append(gender).append("' AND ");
		}

		// 연령대가 선택된 경우
		if (age != null && !age.isEmpty()) {
			conditions.append("age = '").append(age).append("' AND ");
		}

		// 필요에 따라 다른 조건들도 추가할 수 있습니다.

		// 마지막 AND를 제거하고 반환
		if (conditions.length() > 0) {
			conditions.delete(conditions.length() - 5, conditions.length());
		}

		return conditions.toString();
	}

	@Override
	public String toString() {
		return "SearchCondition [key=" + key + ", word=" + word + ", orderBy=" + orderBy + ", orderByDir=" + orderByDir
				+ "]";
	}

}
