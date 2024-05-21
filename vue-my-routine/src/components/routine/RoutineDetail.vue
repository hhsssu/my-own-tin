<template>
    <div>
        <h2>상세보기</h2>
    </div>
</template>

<script setup>
import { useRoutineStore } from '@/stores/routine';
import { useUserStore } from '@/stores/user';
import { onMounted } from 'vue';

const store = useRoutineStore();
const userStore = useUserStore();

const user = userStore.getLoginUser();

onMounted(() => {
    // store.getRoutine(.id);
});


// getRoutine

</script>

<style scoped>

</style>