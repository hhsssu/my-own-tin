<template>
  <div>
    <!-- v-for 를 통해 루틴 불러오기 -->
    <SearchListItem class="search-list-item"/>
    <SearchListItem />
  </div>
</template>

<script setup>
import SearchListItem from "./SearchListItem.vue";
</script>

<style scoped>
.search-list-item {
  margin-bottom: 15px;
}
</style>
