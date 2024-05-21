package com.ssafy.myroutine.model.dto;

import java.util.Date;

public class User {
	private int id;
	private String email;
	private String nickname;
	private String password;
	private String gender;
	private int age;
	private int level;
	private boolean isPublic;
	private boolean isAdmin;
	private boolean isExpert;
	private Date joinAt;
	private Date updateAt;
	private Date deleteAt;
	private String part1;
	private String part2;
	private int workoutTime;

	public User() {
	}

	public User(String email, String nickname, String password, String gender, int age, boolean isPublic,
			boolean isAdmin, boolean isExpert, String part1, String part2, int workoutTime) {
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.isPublic = isPublic;
		this.isAdmin = isAdmin;
		this.isExpert = isExpert;
		this.part1 = part1;
		this.part2 = part2;
		this.workoutTime = workoutTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public boolean isExpert() {
		return isExpert;
	}

	public void setExpert(boolean isExpert) {
		this.isExpert = isExpert;
	}

	public Date getJoinAt() {
		return joinAt;
	}

	public void setJoinAt(Date joinAt) {
		this.joinAt = joinAt;
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

	public int getWorkoutTime() {
		return workoutTime;
	}

	public void setWorkoutTime(int workoutTime) {
		this.workoutTime = workoutTime;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", email=" + email + ", nickname=" + nickname + ", password=" + password + ", gender="
				+ gender + ", age=" + age + ", level=" + level + ", isPublic=" + isPublic + ", isAdmin=" + isAdmin
				+ ", isExpert=" + isExpert + ", joinAt=" + joinAt + ", updateAt=" + updateAt + ", deleteAt=" + deleteAt
				+ ", part1=" + part1 + ", part2=" + part2 + ", workoutTime=" + workoutTime + "]";
	}

}
