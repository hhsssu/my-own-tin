<template>
  <!-- 마이페이지 전체 컨테이너 -->
  <div class="mypage-container">
    <!-- 마이페이지 왼쪽 프로필 부분 -->
    <div class="mypage-profile-container">
      <div class="flex-box flex-space-between">

      <div class="flex-box" v-if="loginUser">
        <!-- 프로필 이미지 -->
        <img
          src="@/assets/img/profile_female.png"
          alt="프로필 이미지"
          class="img-mypage-profile"
        />
        <!-- 프로필 닉네임, 레벨 표시 부분 -->
        <div>
          <div class="flex-box">
            <div class="profile-name">{{ loginUser.nickname }}</div>
            <div class="profile-level">Lv.{{ loginUser.level }}</div>
          </div>
          <!-- 프로필 해시태그(성격) 표시 부분 -->
          <div class="flex-box">
            <div class="routine-tag profile-hashtag" v-if="loginUser.character1 !== null">#{{ loginUser.character1 }}</div>
            <div class="routine-tag profile-hashtag" v-if="loginUser.character2 !== null">#{{ loginUser.character2 }}</div>
            <div class="routine-tag profile-hashtag" v-if="loginUser.character3 !== null">#{{ loginUser.character3 }}</div>
          </div>
        </div>
        <!-- 루틴 등록하기 버튼 -->
      </div>
        <div>
          <button @click="createRoutine" class="create-btn"><img src="@/assets/img/plus.png"></button>
        </div>
      </div>
      <!-- 루틴 리스트 -->
      <RoutineList
        v-if="loginUser"
        :userId="loginUser.id"
        @selectRoutineId="handleSelectRoutine"
      />
    </div>
    <div class="right-container">
      <!-- 루틴 상세보기 -->
      <div v-if="selectedRoutineId != null && !isUpdateRoutine">
        <RoutineDetail :routineId="selectedRoutineId" @isUpdate="handleUpdateRoutine" />
      </div>
      <!-- 루틴 수정하기 -->
      <div v-else-if="selectedRoutineId == null && isUpdateRoutine">
        <RoutineUpdate :routineId="selectedRoutineId" />
      </div>
      <!-- 루틴 등록하기 -->
      <div v-else>
        <RoutineCreate />
      </div>
    </div>
  </div>
</template>

<script setup>
import RoutineDetail from "@/components/routine/RoutineDetail.vue";
import RoutineList from "@/components/routine/RoutineList.vue";
import RoutineCreate from "@/components/routine/RoutineCreate.vue";
import RoutineUpdate from "@/components/routine/RoutineUpdate.vue";

import { useRoutineStore } from "@/stores/routine";
import { useUserStore } from "@/stores/user";
import { ref, onMounted, computed, watch } from "vue";
import { useRouter } from "vue-router";

const store = useRoutineStore();
const userStore = useUserStore();
const router = useRouter();

const selectedRoutineId = ref(null);
const isUpdateRoutine = ref(false);

onMounted(() => {
  userStore.getLoginUser();
});

const loginUser = computed(() => userStore.loginUser);

const handleSelectRoutine = (routineId) => {
  // console.log('Selected Routine ID:', routineId);
  selectedRoutineId.value = routineId;
};

const handleUpdateRoutine = (isUpdate) => {
  if (isUpdate == true) {
    selectedRoutineId.value = null;
    isUpdateRoutine.value = true;
    router.push({ name: 'routineUpdate' });
  }
}

// 루틴 등록하는 컴포넌트로 이동
const createRoutine = function () {
  selectedRoutineId.value = null;
  router.push({ name: 'routineCreate'});
}
</script>

<style scoped>
/* 마이페이지 전체 */
.mypage-container {
  width: 1000px;
  height: 700px;
  margin: 0 auto;
  display: flex;
  font-family: "Pretendard";
}

/* 마이페이지 프로필 부분 */
.mypage-profile-container {
  width: 50%;
  /* background-color: rgb(255, 235, 251); */
  border-right: 1px solid #ccc;
  padding-top: 30px;
}

.create-btn {
  background-color: transparent;
  border: none;
  margin-right: 20px;
}

.create-btn img {
  width: 20px;
}

/* 프로필 이미지 */
.img-mypage-profile {
  width: 100px;
  margin-right: 15px;
}

/* 프로필 닉네임 스타일 */
.profile-name {
  color: #555;
  font-weight: 800;
  font-size: 1.5em;
  margin: 0 5px 20px 0;
}

/* 레벨, 성격키워드, 루틴 태그 스타일 */
.profile-level,
.profile-hashtag,
.routine-tag {
  background-color: #fae6b1;
  font-size: 0.8em;
  color: #333;
  font-weight: 500;
  padding: 4px 5px;
  margin-bottom: 20px;
  border-radius: 3px;
  margin-right: 7px;
}

/* 프로필 성격 키워드 스타일 */
.profile-hashtag:first-child {
  background-color: #e6b3cb;
}

.profile-hashtag:nth-child(2) {
  background-color: #b3dee5;
}

.profile-hashtag:last-child {
  background-color: #b3e6d0;
}


</style>
