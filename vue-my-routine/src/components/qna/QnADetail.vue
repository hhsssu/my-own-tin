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
    <!-- AnswerList 로 이동 -->
    <AnswerList />
    

    <!-- 댓글 작성 폼 -->
    <!-- AnswerCreate 로 이동 -->
    <AnswerCreate />

  </div>
</template>

<script setup>
import AnswerList from './AnswerList.vue';
import AnswerCreate from './AnswerCreate.vue';
</script>

<style scoped>
.qna-detail-container {
  width: 400px;
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
  font-size: 1em;
  width: 120px;
}

.qna-detail-profile-level {
  background-color: #fae6b1;
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
  font-size: 1em;
}

.qna-detail-profile-tag {
  background: #aaa;
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
  color: white;
  font-size: 1em;
}

.qna-detail-title {
  font-size: 1.5em;
  margin-bottom: 30px;
}

.qna-detail-content {
  font-size: 1em;
}

.qna-detail-routine {
  background-color: #fff9e8;
  padding: 10px 25px;
  /* padding-bottom: 50px; */
  height: 80px;
  box-shadow: 5px 5px 10px lightgray;
  border-radius: 5px;
}

.qna-detail-routine-tag {
  display: flex;
  align-items: center;
}

.qna-detail-routine-title {
  font-size: 1em;
  font-weight: 600;
  margin-right: 10px;
}

.qna-detail-routine-info {
  padding: 3px 8px;
  margin: 0px 5px;
  color: white;
  font-size: 1em;
  background-color: #aaa;
  border-radius: 3px;
}

.qna-detail-routine-info2 {
  padding: 3px 8px;
  margin: 0px 5px;
  color: white;
  font-size: 1em;
  background-color: #31525b;
  border-radius: 3px;
}

</style>
