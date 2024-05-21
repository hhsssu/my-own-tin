<template>
    <!-- 나의 루틴 태그 영역 -->
    <!-- <RoutineListItem /> -->
    <RoutineListItem 
        v-for="routine in store.routineList"
        :key="routine.id"
        :routine="routine"
        @selectRoutine="handleSelectRoutine" />
    <!-- </RoutineListItem> -->
</template>

<script setup>
import RoutineListItem from '@/components/routine/RoutineListItem.vue';
import { useRoutineStore } from '@/stores/routine';
import { ref, defineProps, defineEmits, onMounted } from 'vue';

const store = useRoutineStore();
const selectedRoutineId = ref(null);

const props = defineProps({
  userId: {
    type: Number,
    required: true
  }
});

const emits = defineEmits(['selectRoutineId']);

const handleSelectRoutine = (routineId) => {
  selectedRoutineId.value = routineId;
  emits('selectRoutineId', selectedRoutineId.value);
};

onMounted(() => {
    store.getRoutineList(props.userId);
})

</script>

<style scoped>

</style>