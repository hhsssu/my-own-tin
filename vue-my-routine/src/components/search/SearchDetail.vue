<template>
  <div v-if="routine" class="search-detail">
    <!-- 루틴 말머리 (정보) -->
    <div class="search-detail-head">
      <div class="search-detail-title">{{ routine.title }}</div>
      <div class="search-detail-tag">{{ routine.userAge }}</div>
      <div class="search-detail-tag">{{ routine.userGender }}</div>
      <div class="search-detail-tag">{{ routine.part1 }}</div>
      <div class="search-detail-tag" v-if="routine.part2 != null">
        {{ routine.part2 }}
      </div>
      <div class="search-detail-tag">{{ routine.workoutTime }}분</div>
    </div>
    <!-- 루틴 본문 -->
    <div class="search-detail-content">{{ routine.content }}</div>
    <!-- 버튼 -->
    <div class="search-detail-buttons">
      <button @click="createBookmark">담기</button>
      <button @click="clickForLike">좋아요</button>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, defineProps, isMemoSame } from "vue";
import { useRoutineStore } from "@/stores/routine";

const props = defineProps({
  routineId: {
    type: Number,
    required: true,
  },
});

const store = useRoutineStore();
const routine = ref(null);

const fetchRoutineDetail = async (id) => {
  const routineData = await store.getRoutine(id);
  routine.value = routineData;
};

watch(
  () => props.routineId,
  async (newId) => {
    if (newId) {
      await fetchRoutineDetail(newId);
    }
  },
  { immediate: true }
);

const createBookmark = function () {
  store.getRoutine(props.routineId);
  const loginUser = JSON.parse(sessionStorage.getItem('user'));
  // console.log(loginUserId);
  const newRoutine = {...store.routine, isMarked: 1, userId: loginUser.id, writer: loginUser.nickname };
  store.createRoutine(newRoutine);
  alert('북마크에 추가되었습니다!');
}

const clickForLike = function () {

}

</script>

<style scoped>
.search-detail {
  margin-left: 4%;
  padding: 15px;
  border-radius: 5px;
  box-shadow: 5px 5px 10px lightgray;
  width: 90%;
  height: 300px;
  display: flex;
  flex-direction: column;
}

.search-detail-head {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.search-detail-title {
  font-size: 1.2em;
  font-weight: 600;
  margin-right: 10px;
}

.search-detail-tag {
  font-size: 0.8em;
  color: white;
  background-color: #aaa;
  border-radius: 3px;
  padding: 3px 7px;
  margin: 0px 5px;
}

.search-detail-content {
  font-size: 1em;
  padding: 5pdetail;
}

.search-detail-buttons {
  margin-top: auto;
  margin-left: auto;
  margin-right: auto;
}

.search-detail-buttons button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 1.3em;
  width: 100px;
  border-radius: 5px;
  border: 1px solid black;
}
</style>
