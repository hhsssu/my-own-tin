<template>
    <div>
        <div class="view-title">회원 정보수정</div>
        <div class="mypage-modify-detail-container">
            <div class="flex-box flex-box-end">
                <div class="input-label">e-mail</div>
                <input class="input-text input-email-readonly" type="text" v-model="email" readonly>
            </div>
            <div class="flex-box flex-box-end">
                <div class="input-label">새 비밀번호</div>
                <input class="input-text" type="password" placeholder="8자 이상 15자 이하" v-model="newPassword">
            </div>
            <div class="flex-box flex-box-end">
                <div class="input-label">비밀번호 확인</div>
                <input class="input-text" type="password" placeholder="8자 이상 15자 이하" v-model="confirmPassword">
            </div>
            <div class="flex-box flex-box-end">
                <div class="input-label">나이</div>
                <input class="input-text" type="number" v-model="age">
            </div>
            <div class="flex-box flex-box-end">
                <div class="input-label">성별</div>
                <label for="gender">
                    <input class="input-radio" type="radio" name="gender" v-model="gender" value="남성" />
                    <span>남성</span>
                </label>
                <label for="gender">
                    <input class="input-radio" type="radio" name="gender" v-model="gender" value="여성"/>
                    <span>여성</span>
                </label>
            </div>
            <div class="display-block">
                <button type="button" class="modify-fin-btn" @click="updateProfile">수정 완료</button>
            </div>
        </div>
        <div class="profile-out-btn">
            <span class="span-out-btn" @click="deleteUser">회원탈퇴</span>
        </div>
    </div>
</template>

<script setup>
// 수정완료 클릭하면 제대로 작성하였는지 여부에 따라
// 수정 완료 알림창 띄워줌
// v-model로 값 보내줌

import { useUserStore } from '@/stores/user';
import { useRouter } from "vue-router";
import { ref } from 'vue';

const router = useRouter();
const userStore = useUserStore();

const user = JSON.parse(sessionStorage.getItem('user'));
const email = ref(user.email);
const newPassword = ref('');
const confirmPassword = ref('');
const age = ref(user.age);
const gender = ref(user.gender);

// 수정 완료 버튼
const updateProfile = () => {
    if(!age.value || !gender.value) {
        alert("필수 항목을 작성해주세요.");
        return;
    }
    // 비밀번호 작성란 모두 공백
    // 비밀번호 업데이트 X
    if(!newPassword.value && !confirmPassword.value) { 
        const updateUserInfo = {
            id: user.id,
            email: user.email,
            password: user.password,
            age: age.value,
            gender: gender.value,
        }
        console.log(updateUserInfo);
        userStore.updateLoginUser(updateUserInfo);
    } else { // 새 비밀번호로 변경 O
        // 둘 중 하나라도 공백
        if(!newPassword.value || !confirmPassword.value) { 
            alert("비밀번호를 변경하시려면 빈칸을 모두 작성해주세요.");
            return;
        }// 둘 다 채워짐
        if(newPassword.value !== confirmPassword.value){
            alert('새 비밀번호와 비밀번호 확인이 일치하지 않습니다.');
            return;
        }
        const updateUserInfo = {
            id: user.id,
            email: user.email,
            password: newPassword.value,
            age: age.value,
            gender: gender.value,
        }
        userStore.updateLoginUser(updateUserInfo);
    }
    
}

const deleteUser = () => {
    userStore.deleteUser(user.id);
}

</script>

<style scoped>
.mypage-modify-detail-container {
    margin: 0 auto;
}

.profile-out-btn {
    color: #777;
    width: 50%;
    text-decoration: none;
    font-size: 0.9em;
    text-align: center;
    margin: 170px auto;
}

.span-out-btn {
    cursor: pointer;
}

/* input의 label 부분 */
.input-label {
    font-size: 1em;
    font-weight: 600;
    margin: 0 30px 50px 0;
}

/* input 부분 */
.input-text {
    margin: 0 50px 50px 0;
    padding: 10px 45px;
    border: none;
    border-bottom: 1px solid #999;
    font-size: 1em;
}

/* 이메일 input 부분(readonly) */
.input-email-readonly {
    background-color: #ddd;
    font-style: italic;
    font-weight: 600;
    color: #555;
}

/* 셀렉트 라벨 */
label {
    font-weight: 600;
    font-size: 1em;
    margin: 0 120px 50px 0;
}

/* 라디오 버튼 가운데 정렬 */
[type="radi0"], span {
    vertical-align: middle;
}

/* 수정 완료 버튼 */
.modify-fin-btn {
    border: none;
    font-size: 1.1em;
    font-weight: 600;
    background-color: #31525B;
    color: #fff;
    border-radius: 3px;
    padding: 10px 30px;
    cursor: pointer;
}

</style>