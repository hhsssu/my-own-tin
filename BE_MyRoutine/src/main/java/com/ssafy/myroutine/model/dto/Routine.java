package com.ssafy.myroutine.model.dto;

import java.util.Date;

public class Routine {
	private int id;
	private int userId;
	private String writer;
	private String title;
	private String content;
	private int likeCnt;
	private int workoutTime;
	private Date createAt;
	private Date updateAt;
	private Date deleteAt;

	public Routine() {
	}

	public Routine(int userId, String writer, String title, String content, int likeCnt, int workoutTime) {
		this.userId = userId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.likeCnt = likeCnt;
		this.workoutTime = workoutTime;
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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLikeCnt() {
		return likeCnt;
	}

	public void setLikeCnt(int likeCnt) {
		this.likeCnt = likeCnt;
	}

	public int getWorkoutTime() {
		return workoutTime;
	}

	public void setWorkoutTime(int workoutTime) {
		this.workoutTime = workoutTime;
	}

	public Date getCreateAt() {
		return createAt;
	}

	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public Date getUpdateAt() {
		return updateAt;
	}

	public void setUpdateAt(Date updateAt) {
		this.updateAt = updateAt;
	}

	public Date getDeleteAt() {
		return deleteAt;
	}

	public void setDeleteAt(Date deleteAt) {
		this.deleteAt = deleteAt;
	}

	@Override
	public String toString() {
		return "Routine [id=" + id + ", writer=" + writer + ", title=" + title + ", content=" + content + ", likeCnt="
				+ likeCnt + ", workoutTime=" + workoutTime + ", createAt=" + createAt + ", updateAt=" + updateAt
				+ ", deleteAt=" + deleteAt + "]";
	}

}
