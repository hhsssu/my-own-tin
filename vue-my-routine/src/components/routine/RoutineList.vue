<template>
    <!-- 나의 루틴 태그 영역 -->
    <!-- <RoutineListItem /> -->
    <RoutineListItem 
        v-for="routine in store.routineList"
        :key="routine.id"
        :routine="routine" />
    <!-- </RoutineListItem> -->
</template>

<script setup>
import RoutineListItem from '@/components/routine/RoutineListItem.vue';
import { useRoutineStore } from '@/stores/routine';
import { defineProps, onMounted } from 'vue';

const store = useRoutineStore();

const props = defineProps({
  userId: {
    type: Number,
    required: true
  }
});

onMounted(() => {
    store.getRoutineList(props.userId);
})

</script>

<style scoped>

</style>