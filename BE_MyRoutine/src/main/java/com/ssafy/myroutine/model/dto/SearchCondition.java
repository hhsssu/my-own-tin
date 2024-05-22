package com.ssafy.myroutine.model.dto;

public class SearchCondition {
	private String key;
	private String word;
	private String orderBy;
	private String orderByDir;
	private String level;
	private String gender;
	private String ageRange;

	public SearchCondition() {
	}
	
	

	public SearchCondition(String level, String gender, String ageRange, String orderBy) {
		this.level = level;
		this.gender = gender;
		this.ageRange = ageRange;
		this.orderBy = orderBy;
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

	// 선택된 값들을 적절한 쿼리 조건으로 변환하는 메서드
    public String toQueryConditions() {
        StringBuilder conditions = new StringBuilder();

        if (level != null && !level.isEmpty()) {
            conditions.append("u.level = '").append(level).append("' AND ");
        }
        if (gender != null && !gender.isEmpty()) {
            conditions.append("u.gender = '").append(gender).append("' AND ");
        }
        if (ageRange != null && !ageRange.isEmpty()) {
            int startAge = Integer.parseInt(ageRange);
            int endAge = startAge + 10;
            conditions.append("u.age >= ").append(startAge).append(" AND age < ").append(endAge).append(" AND ");
        }
        if (conditions.length() > 0) {
            conditions.delete(conditions.length() - 5, conditions.length());
        }
        return conditions.toString();
    }

    @Override
    public String toString() {
        return "SearchCondition [key=" + key + ", word=" + word + ", orderBy=" + orderBy + ", orderByDir=" + orderByDir
                + ", level=" + level + ", age=" + ageRange + ", gender=" + gender + "]";
    }

}
