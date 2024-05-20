package com.ssafy.myroutine.model.dto;

import java.util.Date;

public class Point {
	private int id;
	private int userId;
	private int amount;
	private String record;
	private Date createAt;

	public Point() {
	}

	public Point(int userId, int amount, String record, Date createAt) {
		this.userId = userId;
		this.amount = amount;
		this.record = record;
		this.createAt = createAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	@Override
	public String toString() {
		return "Point [id=" + id + ", userId=" + userId + ", amount=" + amount + ", record=" + record + ", createAt="
				+ createAt + "]";
	}

}
