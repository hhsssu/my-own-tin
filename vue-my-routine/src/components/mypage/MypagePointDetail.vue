<template>
    <div class="mypage-point-detail-container">
        <h2 class="view-title">포인트 적립 내역</h2>
        <div class="point-detail-total-box">
            <div class="point-detail-total" v-if="pointTotal > 0">잔여 포인트 : {{ pointTotal }}P</div>
            <div class="point-detail-total" v-if="pointTotal === 0 || pointTotal === null">잔여 포인트 : 0 P</div>
        </div>

        <table class="point-detail-table" style="table-layout: fixed;">
            <thead>
                <tr class="point-detail-table-header">
                    <th>거래일</th>
                    <th>이용 내역</th>
                    <th>적립/사용</th>
                </tr>
            </thead>
            <tbody>
                <!-- <tr v-for="item in items"> -->
                <tr v-for="item in pointmileStore.pointList" :key="item.id" class="point-detail-table-content">
                    <td class="point-detail-table-date">{{ formatDate(item.createAt) }}</td>
                    <td class="point-detail-table-detail">{{ item.record }}</td>
                    <td class="point-detail-table-saveuse" v-if="item.amount > 0">+{{ item.amount }}</td>
                    <td class="point-detail-table-saveuse" v-else-if="item.amount < 0">-{{ item.amount }}</td>
                </tr>
            </tbody>
        </table>
    </div>
</template>

<script setup>
import { usePointmileStore } from '@/stores/pointmile';
import { ref, onMounted, defineProps } from 'vue';

const props = defineProps({
    pointTotal:{
        type: Number,
        required: true
    },
})
const pointmileStore = usePointmileStore();

const user = JSON.parse(sessionStorage.getItem('user'));

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더해줍니다.
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}/${month}/${day}`;
}
// 해당하는 회원의 포인트 내역 (날짜, 설명, 쌓인 금액) 불러오기
// -값은 색상 변경 (if 음수면 색상변경 class 추가)

onMounted(() => {
    pointmileStore.getPointList(user.id);
    console.log(pointmileStore.pointList);

});
</script>

<style scoped>
.mypage-point-detail-container {
    padding-top: 10px;
}



/* 포인트, 마일리지 총 금액 레이아웃 정렬 */
.point-detail-total-box {
    display: flex;
    align-items: center;
    justify-content: center;
}

/* 포인트, 마일리지 총 금액 */
.point-detail-total {
    margin: 0 15px 30px;
    font-weight: 600;
    color: #555;
    font-size: 1.1rem;
}

/* 포인트 상세 내역 테이블 */
.point-detail-table {
    text-align: center;
    margin: 20px auto;
}

/* 테이블 헤더 */
.point-detail-table-header {
    font-weight: 600;
    font-size: 1.1rem;
    border-bottom: 1px solid #999;
}

th {
    padding-bottom: 7px;
    padding-right: 50px;
    padding-left: 50px;
}

/* 테이블 각 한줄 컬럼 */
.point-detail-table-content {
}

/* 테이블 콘텐츠 */
td {
    padding: 15px 0px;
}

/* 거래일 */
.point-detail-table-date {
    color: #777;
    font-weight: 400;

}

/* 이용내역 */
.point-detail-table-detail {
    font-weight: 400;
}

/* 적립/사용 포인트 */
.point-detail-table-saveuse {
    color: #FFA101;
    font-weight: 500;
}

</style>