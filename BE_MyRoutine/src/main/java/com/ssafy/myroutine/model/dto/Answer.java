package com.ssafy.myroutine.model.dto;

import java.util.Date;

public class Answer {
	private int id;
	private int userId;
	private int questionId;
	private String writer;
	private String content;
	private int isPicked;
	private Date createAt;
	private Date updateAt;
	private Date deleteAt;

	public Answer() {
	}

	public Answer(int userId, int questionId, String writer, String content, int isPicked) {
		this.userId = userId;
		this.questionId = questionId;
		this.writer = writer;
		this.content = content;
		this.isPicked = isPicked;
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

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getIsPicked() {
		return isPicked;
	}

	public void setIsPicked(int isPicked) {
		this.isPicked = isPicked;
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
		return "Answer [id=" + id + ", writer=" + writer + ", content=" + content + ", isPicked=" + isPicked
				+ ", createAt=" + createAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt + "]";
	}

}
