<template>
    <!-- 마이페이지 전체 컨테이너 -->
    <div class="mypage-container">
        <!-- 마이페이지 왼쪽 프로필 부분 -->
        <div class="left-container">
            <div class="flex-box">
                <!-- 프로필 이미지 -->
                <img src="@/assets/img/profile_female.png" alt="프로필 이미지" class="img-mypage-profile">
                <!-- 프로필 닉네임, 레벨 표시 부분 -->
                <div>
                    <div class="flex-box-mb">
                        <div class="profile-name">{{ user.nickname }}</div>
                        <div class="routine-tag profile-level">Lv.{{ user.level }}</div>
                        <img src="@/assets/img/icon/compose2.png" alt="프로필 수정 버튼" class="img-modify-btn" 
                            @click="showComponent('MypageModifyProfile')">
                    </div>
                    <!-- 프로필 해시태그(성격) 표시 부분 -->
                    <div class="flex-box">
                        <div class="routine-tag profile-hashtag" v-if="user.character1 !== null">#{{ user.character1 }}</div>
                        <div style="font-size: 0.8rem; color: #999;" v-else>태그를 추가해보세요!</div>
                        <div class="routine-tag profile-hashtag" v-if="user.character2 !== null">#{{ user.character2 }}</div>
                        <div class="routine-tag profile-hashtag" v-if="user.character3 !== null">#{{ user.character3 }}</div>
                    </div>
                </div>
            </div>
            <!-- 나의 루틴 태그 영역 -->
            <section class="profile-click-box content-box"
                @click="showComponent('MypageModifyRoutinetag')">
                <div class="profile-title">나의 루틴 태그</div>
                <div class="flex-box">
                    <div class="routine-user-tag" v-if="user.age < 20">10대</div>
                    <div class="routine-user-tag" v-else-if="user.age > 19 && user.age < 30">20대</div>
                    <div class="routine-user-tag" v-else-if="user.age > 29 && user.age < 40">30대</div>
                    <div class="routine-user-tag" v-else-if="user.age > 39 && user.age < 50">40대</div>
                    <div class="routine-user-tag" v-else-if="user.age > 49 && user.age < 60">50대</div>
                    <div class="routine-user-tag" v-else-if="user.age > 59 && user.age < 70">60대</div>
                    <div class="routine-user-tag" v-else-if="user.age > 69 && user.age < 80">70대</div>
                    <div class="routine-user-tag" v-else-if="user.age > 79">80대⬆</div>
                    <div class="routine-user-tag">{{ user.gender }}</div>
                    <div class="routine-tag" v-if="user.part1 !== null">{{ user.part1 }}</div>
                    <div class="routine-tag" v-if="user.part2 !== null">{{ user.part2 }}</div>
                    <div class="routine-tag" v-if="user.workoutTime !== 0">{{ workoutTimeFormat(user.workoutTime) }}</div>
                </div>
            </section>
            <!-- 포인트 마일리지 표기 영역 -->
            <section class="profile-click-box flex-box content-box">
                <div class="profile-point-box" @click="showComponent('MypagePoint')">
                    <div class="profile-title">포인트</div>
                    <div class="profile-point-amount num" v-if="pointTotal > 0">{{ pointTotal }}</div>
                    <div class="profile-point-amount num" v-else-if="pointTotal === 0 || pointTotal === null">0</div>
                </div>
                <div class="profile-mile-box" @click="showComponent('MypageMile')">
                    <div class="profile-title">마일리지</div>
                    <div class="profile-mile-amount num" v-if="mileTotal > 0">{{ mileTotal }}</div>
                    <div class="profile-mile-amount num" v-else-if="mileTotal === 0 || mileTotal === null">0</div>
                </div>
            </section>
            <!-- 루틴 보관함 영역 -->
            <section class="profile-click-box content-box"
                @click="showComponent('MypageRoutineBox')">
                <div class="profile-title">루틴 보관함</div>
                <div class="profile-tinbox-amount num">{{ routineTotal }}</div>
            </section>
            <!-- 회원정보 수정 영역 -->
            <section class="profile-click-box content-box"
                @click="showComponent('MypageModifyUser')">
                <div class="profile-title">회원정보 수정</div>
                <div class="profile-modify-user">바로가기</div>
            </section>
        </div>
        
        <!-- 마이페이지 오른쪽 메뉴 선택 시 나올 부분 -->
        <div class="right-container mypage-click-container">
            <!-- 프로필 페이지에 있는 메뉴들 클릭 시 띄울 화면 구현 -->
            <component :is="activeComponent" :mile-total="mileTotal" :point-total="pointTotal"/>
        </div>
    </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import MypageModifyProfile from '@/components/mypage/MypageModifyProfile.vue';
import MypagePoint from '@/components/mypage/MypagePointDetail.vue';
import MypageMile from '@/components/mypage/MypageMileDetail.vue';
import MypageRoutineBox from '@/components/mypage/MypageRoutineBox.vue';
import MypageModifyUser from '@/components/mypage/MypageModifyUser.vue';
import MypageModifyRoutinetag from '@/components/mypage/MypageModifyRoutinetag.vue';
import TheCommonComponent from '@/components/common/TheCommonComponent.vue';

import { useUserStore } from '@/stores/user';
import { useRoutineStore } from '@/stores/routine';
import { usePointmileStore } from '@/stores/pointmile';
import { computed, ref, shallowRef, onMounted } from 'vue';

const userStore = useUserStore();
const routineStore = useRoutineStore();
const pointmileStore = usePointmileStore();

const user = JSON.parse(sessionStorage.getItem('user'));


// 포인트 총합 조회
const pointTotal = computed(() => pointmileStore.pointTotal);

// 마일리지 총합 조회
const mileTotal = computed(() => pointmileStore.mileTotal);

// 나의 루틴 개수 조회
const routineTotal = computed(() => routineStore.routineList.length);

// 클릭 시 컴포넌트 변경
const activeComponent = shallowRef(TheCommonComponent);
const components = {
    MypageModifyProfile,
    MypagePoint,
    MypageMile,
    MypageRoutineBox,
    MypageModifyUser,
    MypageModifyRoutinetag,
}
const showComponent = (componentName) => {
    activeComponent.value = components[componentName];
    console.log(activeComponent.value);
}

// 운동 시간 태그 (30 -> 30분, 60 -> 1시간, 90 -> 1시간30분)
const workoutTimeFormat = (workoutTime) => {
    const hours = Math.floor(workoutTime / 60); // 시간
    const minuties = workoutTime % 60; // 분
    
    let workout = '';
    if(hours > 0) {
        workout += `${hours}시간`;
    }
    if(minuties > 0) {
        workout += `${minuties}분`;
    }

    return workout.trim();
}

const loadData = async () => {
    await routineStore.getMarkedList(user.id);
    await pointmileStore.getPointTotal(user.id);
    await pointmileStore.getMileTotal(user.id);
}

onMounted(async () => {
    await loadData();
});

</script>

<style scoped>





/* 각 프로필 박스 클릭 커서 변경 */
.profile-click-box {
    cursor: pointer;
}

/* 프로필 클릭 박스의 타이틀 스타일 */
.profile-title {
    font-weight: 700;
    margin-bottom: 15px;
}

/* 포인트-마일리지 박스의 포인트쪽 스타일 */
.profile-point-box {
    width: 50%;
    border-right: 1px solid #555;
}

.profile-mile-box {
    width: 50%;
    padding: 0 10px 0;
}

.profile-point-amount {
    color: #FFA101;
}

.profile-mile-amount {
    color: #38ABBE;
}

.profile-tinbox-amount {
    color: #777;
}

/* 숫자 스타일 */
.num {
    font-weight: 700;
    font-size: 2em;
    text-align: center;
}

/* 회원정보 수정 바로가기 스타일 */
.profile-modify-user {
    color: #777;
    text-align: center;
    font-weight: 700;
}

/* 프로필 수정 버튼 스타일 */
.img-modify-btn {
    width: 20px;
    margin-left: 10px;
    cursor: pointer;
}



.mypage-click-container {
    height: 700px;
    margin: 0 auto;
}
</style>