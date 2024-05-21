<template>
  <div>
    <!-- 데이터 로드 중일 때 로딩 메시지 표시 -->
    <div v-if="isLoading">Loading...</div>
    <div v-else>
      <!-- v-for 를 통해 루틴 불러오기 -->
      <SearchListItem
        v-for="routine in store.routineList"
        :key="routine.id"
        :routine="routine"
        @selectRoutine="handleSelectRoutine"
        class="search-list-item"
      />
    </div>
  </div>
</template>

<script setup>
import { useRoutineStore } from "@/stores/routine";
import SearchListItem from "./SearchListItem.vue";
import { onMounted, ref, watch, defineEmits } from "vue";

const store = useRoutineStore();
const selectedRoutineId = ref(null);

const emits = defineEmits(["selectRoutineId"]);

const handleSelectRoutine = (routineId) => {
  selectedRoutineId.value = routineId;
  emits("selectRoutineId", selectedRoutineId.value);
};

const con = ref({
  key: "",
  word: "",
  orderBy: "",
  orderByDir: "",
});

const isLoading = ref(true);

onMounted(() => {
  store.searchRoutineList(con.value).then(() => {
    isLoading.value = false;
  });
});

// watch 사용하여 routineList 변경 감지
watch(
  () => store.routineList,
  (newVal) => {
    console.log(newVal);
  },
  { immediate: true }
);
</script>

<style scoped>
.search-list-item {
  margin-bottom: 15px;
}
</style>
