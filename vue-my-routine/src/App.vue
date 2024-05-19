<template>
  <div id="app">
    <TheHeaderNav v-if="showHeader" />
    <router-view />
  </div>
</template>

<script setup>
import { computed } from 'vue';
import { useHeaderStore } from '@/stores/header';
import TheHeaderNav from "@/components/common/TheHeaderNav.vue";

const headerStore = useHeaderStore();

const showHeader = computed(() => headerStore.showHeader);

</script>

<style scoped></style>
