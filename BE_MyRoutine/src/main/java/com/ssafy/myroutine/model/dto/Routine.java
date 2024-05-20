package com.ssafy.myroutine.model.dto;

import java.util.Date;

public class Routine {
	private int id;
	private int userId;
	private String writer;
	private String part1;
	private String part2;
	private String title;
	private String content;
	private int likeCnt;
	private int workoutTime;
	private Date createAt;
	private Date updateAt;
	private Date deleteAt;
	private boolean isMarked;

	public Routine() {
	}

	public Routine(int userId, String writer, String part1, String part2, String title, String content, int likeCnt,
			int workoutTime, boolean isMarked) {
		this.userId = userId;
		this.writer = writer;
		this.part1 = part1;
		this.part2 = part2;
		this.title = title;
		this.content = content;
		this.likeCnt = likeCnt;
		this.workoutTime = workoutTime;
		this.isMarked = isMarked;
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

	public String getPart1() {
		return part1;
	}

	public void setPart1(String part1) {
		this.part1 = part1;
	}

	public String getPart2() {
		return part2;
	}

	public void setPart2(String part2) {
		this.part2 = part2;
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

	public boolean isMarked() {
		return isMarked;
	}

	public void setMarked(boolean isMarked) {
		this.isMarked = isMarked;
	}

	@Override
	public String toString() {
		return "Routine [id=" + id + ", userId=" + userId + ", writer=" + writer + ", part1=" + part1 + ", part2="
				+ part2 + ", title=" + title + ", content=" + content + ", likeCnt=" + likeCnt + ", workoutTime="
				+ workoutTime + ", createAt=" + createAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt
				+ ", isMarked=" + isMarked + "]";
	}

}
