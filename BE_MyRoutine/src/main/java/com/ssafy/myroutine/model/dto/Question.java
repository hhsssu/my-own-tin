package com.ssafy.myroutine.model.dto;

import java.util.Date;

public class Question {
	private int id;
	private int userId;
	private Integer routineId;
	private String writer;
	private String title;
	private String content;
	private int likeCnt;
	private Date createAt;
	private Date updateAt;
	private Date deleteAt;

	public Question() {
	}

	public Question(int id, int userId, Integer routineId, String writer, String title, String content, int likeCnt,
			Date createAt, Date updateAt, Date deleteAt) {
		this.id = id;
		this.userId = userId;
		this.routineId = routineId;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.likeCnt = likeCnt;
		this.createAt = createAt;
		this.updateAt = updateAt;
		this.deleteAt = deleteAt;
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

	public Integer getRoutineId() {
		return routineId;
	}

	public void setRoutineId(Integer routineId) {
		this.routineId = routineId;
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
		return "Question [id=" + id + ", userId=" + userId + ", routineId=" + routineId + ", writer=" + writer
				+ ", title=" + title + ", content=" + content + ", likeCnt=" + likeCnt + ", createAt=" + createAt
				+ ", updateAt=" + updateAt + ", deleteAt=" + deleteAt + "]";
	}

}
