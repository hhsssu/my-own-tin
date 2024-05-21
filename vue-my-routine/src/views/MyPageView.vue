<template>
    <!-- 마이페이지 전체 컨테이너 -->
    <div class="mypage-container">
        <!-- 마이페이지 왼쪽 프로필 부분 -->
        <div class="mypage-profile-container">
            <div class="flex-box">
                <!-- 프로필 이미지 -->
                <img src="@/assets/img/profile_female.png" alt="프로필 이미지" class="img-mypage-profile">
                <!-- 프로필 닉네임, 레벨 표시 부분 -->
                <div>
                    <div class="flex-box">
                        <div class="profile-name">{{ user.nickname }}</div>
                        <div class="profile-level">Lv.{{ user.level }}</div>
                    </div>
                    <!-- 프로필 해시태그(성격) 표시 부분 -->
                    <div class="flex-box">
                        <div class="profile-hashtag">#체력UP</div>
                        <div class="profile-hashtag">#활발한</div>
                        <div class="profile-hashtag">#헬스장</div>
                    </div>
                </div>
            </div>
            <!-- 나의 루틴 태그 영역 -->
            <section class="profile-click-box content-box"
                @click="showComponent('MypageModifyRoutinetag')">
                <div class="profile-title">나의 루틴 태그</div>
                <div class="flex-box">
                    <div class="routine-tag">20대</div>
                    <div class="routine-tag">여성</div>
                    <div class="routine-tag">유산소</div>
                    <div class="routine-tag">1시간</div>
                </div>
            </section>
            <!-- 포인트 마일리지 표기 영역 -->
            <section class="profile-click-box flex-box content-box"
                @click="showComponent('MypagePoint')">
                <div class="profile-point-box">
                    <div class="profile-title">포인트</div>
                    <div class="profile-point-amount num">1900</div>
                </div>
                <div class="profile-mile-box">
                    <div class="profile-title">마일리지</div>
                    <div class="profile-mile-amount num">500</div>
                </div>
            </section>
            <!-- 루틴 보관함 영역 -->
            <section class="profile-click-box content-box"
                @click="showComponent('MypageRoutineBox')">
                <div class="profile-title">루틴 보관함</div>
                <div class="profile-tinbox-amount num">{{ routineCnt }}</div>
            </section>
            <!-- 회원정보 수정 영역 -->
            <section class="profile-click-box content-box"
                @click="showComponent('MypageModifyUser')">
                <div class="profile-title">회원정보 수정</div>
                <div class="profile-modify-user">바로가기</div>
            </section>
        </div>
        
        <!-- 마이페이지 오른쪽 메뉴 선택 시 나올 부분 -->
        <div class="mypage-click-container">
            <!-- 프로필 페이지에 있는 메뉴들 클릭 시 띄울 화면 구현 -->
            <component :is="activeComponent"/>
        </div>
    </div>
</template>

<script setup>
import TheHeaderNav from '@/components/common/TheHeaderNav.vue';
import MypageModifyProfile from '@/components/mypage/MypageModifyProfile.vue';
import MypagePoint from '@/components/mypage/MypagePointDetail.vue';
import MypageRoutineBox from '@/components/mypage/MypageRoutineBox.vue';
import MypageModifyUser from '@/components/mypage/MypageModifyUser.vue';
import MypageModifyRoutinetag from '@/components/mypage/MypageModifyRoutinetag.vue';

import { useUserStore } from '@/stores/user';
import { useRoutineStore } from '@/stores/routine';

const userStore = useUserStore();

const user = userStore.getLoginUser();

import { ref } from 'vue';

const routineCnt = ref(17);

const activeComponent = ref('');

const components = {
    MypageModifyProfile,
    MypagePoint,
    MypageRoutineBox,
    MypageModifyUser,
    MypageModifyRoutinetag,
}

const showComponent = (componentName) => {
    activeComponent.value = components[componentName];
    console.log(activeComponent.value);
}

</script>

<style scoped>
/* 마이페이지 전체 */
.mypage-container {
    width: 1000px;
    margin: 0 auto;
    display: flex;
    font-family: 'Pretendard';
}

/* 마이페이지 프로필 부분 */
.mypage-profile-container {
    width: 50%;
    /* background-color: rgb(255, 235, 251); */
    border-right: 1.5px solid #ccc;
    padding-top: 30px;
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
.profile-level, .profile-hashtag, .routine-tag {
    background-color: #FAE6B1;
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
    background-color: #E6B3CB;
}

.profile-hashtag:nth-child(2) {
    background-color: #B3DEE5;
}

.profile-hashtag:last-child {
    background-color: #B3E6D0;
}

/* 나의 루틴 태그 스타일 */
.routine-tag {
    background-color: #aaa;
    color: white;
    font-weight: 300;
}

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





.mypage-click-container {
    width: 50%;
    height: 500px;
    padding-top: 30px;
    margin: 0 auto;
}
</style>