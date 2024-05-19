<template>
  <div class="qna-detail-container">
    <!-- 작성자 프로필 -->
    <div class="qna-detail-profile">
      <img src="/src/assets/img/profile_male.png" class="qna-detail-profile-pic" />
      <div class="qna-detail-profile-name">힘세진</div>
      <div class="qna-detail-profile-level">Lv. 2</div>
      <div class="qna-detail-profile-tag">20대</div>
      <div class="qna-detail-profile-tag">남성</div>
    </div>
    <!-- 작성글 -->
    <div class="qna-detail-card">
      <div class="qna-detail-title">추가로 할 운동 추천해주세요</div>
      <pre class="qna-detail-content">
매일 하는 운동만 해서 아무래도 자극이 잘 오지 않습니다
제 루틴에서 다른 운동 좀 추가하고 싶은데, 
혹시 추천해주실 분 있으신가요?</pre
      >
      <!-- 작성자 루틴 -->
      <div class="qna-detail-routine">
        <div class="qna-detail-routine-tag">
          <div class="qna-detail-routine-title">평일 헬스장</div>
          <div class="qna-detail-routine-info">20대</div>
          <div class="qna-detail-routine-info">남성</div>
          <div class="qna-detail-routine-info2">근력</div>
          <div class="qna-detail-routine-info2">1시간</div>
        </div>
      </div>
    </div>

    <!-- 댓글 -->
    <div class="qna-detail-comment">
      <div class="count">댓글 2개</div>
      <div class="qna-detail-comment-card">
        <!-- 댓글 작성자 프로필 -->
        <div class="qna-detail-comment-profile">
          <img
            src="/src/assets/img/profile_gentleman.png"
            class="qna-detail-comment-profile-pic"
          />
          <div class="qna-detail-comment-profile-name">독고수</div>
          <div class="qna-detail-comment-profile-level">Lv.5</div>
          <div class="qna-detail-comment-profile-tag">40대</div>
          <div class="qna-detail-comment-profile-tag">남성</div>
        </div>
        <!-- 댓글 내용 -->
        <div>
          <div class="qna-detail-comment-content">
            충분히 잘 하고 계신 것 같습니다. 세트만 추가하세요^^
          </div>
        </div>
      </div>

      <div class="qna-detail-comment-card">
        <!-- 댓글 작성자 프로필 -->
        <div class="qna-detail-comment-profile">
          <img
            src="/src/assets/img/profile_gentlelady.png"
            class="qna-detail-comment-profile-pic"
          />
          <div class="qna-detail-comment-profile-name">요가영</div>
          <div class="qna-detail-comment-profile-level">Lv.4</div>
          <div class="qna-detail-comment-profile-tag">30대</div>
          <div class="qna-detail-comment-profile-tag">여성</div>
        </div>
        <!-- 댓글 내용 -->
        <div>
          <div class="qna-detail-comment-content">
            추가로 요가수업은 어떠신지요?ㅎㅎ 코어도 중요합니다.
          </div>
        </div>
      </div>
    </div>

    <!-- 댓글 작성 폼 -->
    <div class="qna-detail-comment-input">
      <textarea
        name=""
        id=""
        cols="50"
        rows="4"
        placeholder="댓글을 작성하세요..."
      ></textarea>
      <button class="qna-detail-comment-button">등록</button>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.qna-detail-container {
  width: 600px;
  margin-left: 15px;
  padding: 20px 30px;
}

.qna-detail-profile {
  display: flex;
  align-items: center; /* 수직 가운데 정렬 */
  margin-bottom: 30px;
}

.qna-detail-profile-pic {
  width: 50px;
  height: 50px;
}

.qna-detail-profile-name {
  padding: 3px 10px;
  margin: 0px 5px;
  font-size: 18px;
  width: 300px;
}

.qna-detail-profile-level {
  background-color: #fae6b1;
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
}

.qna-detail-profile-tag {
  background: #aaa;
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
  color: white;
}

.qna-detail-title {
  font-size: 30px;
  margin-bottom: 30px;
}

.qna-detail-content {
  font-size: 20px;
}

.qna-detail-routine {
  background-color: #fff9e8;
  padding: 10px 25px;
  /* padding-bottom: 50px; */
  height: 100px;
  box-shadow: 5px 5px 10px lightgray;
  border-radius: 5px;
}

.qna-detail-routine-tag {
  display: flex;
  align-items: center;
}

.qna-detail-routine-title {
  font-size: 25px;
  font-weight: 600;
  margin-right: 10px;
}

.qna-detail-routine-info {
  padding: 3px 8px;
  margin: 0px 5px;
  color: white;
  font-size: 13px;
  background-color: #aaa;
  border-radius: 3px;
}

.qna-detail-routine-info2 {
  padding: 3px 8px;
  margin: 0px 5px;
  color: white;
  font-size: 13px;
  background-color: #31525b;
  border-radius: 3px;
}

.qna-detail-comment {
  /* border-top: 2px solid #777; */
  margin-top: 20px;
  margin-bottom: 10px;
}
.qna-detail-comment .count {
  border-bottom: 2px solid #777;
  padding-bottom: 10px;
  padding-left: 10px;
  font-size: 17px;
  color: #31525B;
}

.qna-detail-comment-card {
  padding: 10px 15px;
  border-top: 1px solid #999;
}

.qna-detail-comment-profile {
  display: flex;
  align-items: center;
  /* padding: 10px 15px; */
}

.qna-detail-comment-profile-pic {
  width: 50px;
  height: 50px;
}

.qna-detail-comment-profile-name {
  font-size: 18px;
  /* margin: 0px 5px; */
  padding: 0px 5px;
}

.qna-detail-comment-profile-level {
  font-size: 13px;
  padding: 3px 10px;
  margin: 0px 5px;
  background-color: #fae6b1;
  border-radius: 3px;
}

.qna-detail-comment-profile-tag {
  color: white;
  font-size: 13px;
  padding: 3px 10px;
  margin: 0px 5px;
  background-color: #aaa;
  border-radius: 3px;
}

.qna-detail-comment-content {
  font-size: 17px;
  margin-top: 15px;
  margin-bottom: 15px;
}

.qna-detail-comment-input {
  display: flex;
  flex-direction: column;
}

.qna-detail-comment-input textarea {
  padding: 20px;
  margin: 20px 0px 10px 0px;
  border: 1px solid #777;
  border-radius: 5px;
  font-size: 17px;
  width: 550px;
}

.qna-detail-comment-button {
  background-color: #FFA101;
  color: white;
  font-size: 15px;
  padding: 5px 12px;
  border: none;
  border-radius: 5px;
  margin-left: auto;
  margin-right: 10px;
  /* margin-left: 10px; */
}
</style>
