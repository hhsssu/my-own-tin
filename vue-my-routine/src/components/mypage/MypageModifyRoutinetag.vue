<template>
    <div>
        <h2 class="view-title">나의 루틴 태그</h2>
        <div class="profile-click-box content-box">
            <div class="flex-box">
                <div class="profile-title">기본 정보 태그 설정</div>
                <div class="routinetag-explain">회원정보 수정에서 변경 가능합니다.</div>
            </div>
            <div class="flex-box">
                <div class="routine-tag" v-if="user.age < 20">10대</div>
                    <div class="routine-tag" v-else-if="user.age > 19">20대</div>
                    <div class="routine-tag" v-else-if="user.age > 29">30대</div>
                    <div class="routine-tag" v-else-if="user.age > 39">40대</div>
                    <div class="routine-tag" v-else-if="user.age > 49">50대</div>
                    <div class="routine-tag" v-else-if="user.age > 59">60대</div>
                    <div class="routine-tag" v-else-if="user.age > 69">70대</div>
                    <div class="routine-tag" v-else-if="user.age > 79">80대⬆</div>
                    <div class="routine-tag">{{ user.gender }}</div>
            </div>
        </div>
        <div class="profile-click-box content-box">
            <div class="profile-title">운동 정보 태그 설정</div>
            <div class="flex-box">
                <div>
                    <!-- 운동 부위 1 -->
                    <div>운동부위1</div>
                    <select name="part1" id="part1" size="5" v-model="selectedOption1">
                        <option value="null">선택안함</option>
                        <option value="유산소">유산소</option>
                        <option value="하체">하체</option>
                        <option value="상체">상체</option>
                        <option value="어깨">어깨</option>
                        <option value="팔">팔</option>
                        <option value="허벅지">허벅지</option>
                    </select>
                </div>
                <div>
                    <!-- 운동 부위 2 -->
                    <!-- 운동 부위 1을 선택해야 선택할 수 있도록 함(value != null) -->
                    <div>운동부위2</div>
                    <select name="part2" id="part2" size="5" :disabled="isPart2Disabled" v-model="selectedOption2">
                        <option value="null">선택안함</option>
                        <option value="유산소">유산소</option>
                        <option value="하체">하체</option>
                        <option value="상체">상체</option>
                        <option value="어깨">어깨</option>
                        <option value="팔">팔</option>
                        <option value="허벅지">허벅지</option>
                    </select>
                </div>
                <div>
                    <!-- 운동 시간 -->
                    <div>총 운동 시간</div>
                    <select name="time" id="time" size="5" v-model="timeOption">
                        <option value="0">선택안함</option>
                        <option value="10">10분</option>
                        <option value="20">20분</option>
                        <option value="30">30분</option>
                        <option value="40">40분</option>
                        <option value="50">50분</option>
                        <option value="60">1시간</option>
                        <option value="90">1시간30분</option>
                        <option value="120">2시간</option>
                        <option value="150">2시간30분</option>
                        <option value="180">3시간</option>
                        <option value="240">4시간</option>
                        <option value="300">5시간 이상</option>
                    </select>
                </div>
            </div>
        </div>
        <button @click="modifyExTag">수정 완료</button>
    </div>
</template>

<script setup>
// 기본 루틴 태그 설정하기
// select 창으로 설정하도록 함..!
// 회원 나이(age)가 27세면 20세에 자동으로 선택되어있도록 함.
// 성별도 마찬가지.
import { ref, watch, computed } from 'vue';
import { useUserStore } from '@/stores/user';
import { useRoutineStore } from '@/stores/routine';

const userStore = useUserStore();
const routineStore = useRoutineStore();

const user = JSON.parse(sessionStorage.getItem('user'));

const selectedOption1 = ref(user.part1);
const selectedOption2 = ref(user.part2);
const timeOption = ref(user.workoutTime);

const isPart2Disabled = computed(() => selectedOption1.value === 'null');


watch(selectedOption1, (newValue) => {
    if (newValue === 'null') {
        selectedOption2.value = 'null';
    }
});


// 운동 부위, 시간 정보 수정
const modifyExTag = () => {
    const updatedUserInfo = {
        id: user.id,
        part1: selectedOption1.value === 'null' ? null : selectedOption1.value,
        part2: selectedOption2.value === 'null' ? null : selectedOption2.value,
        workoutTime: timeOption.value === '0' ? null : parseInt(timeOption.value, 10),
    };
    userStore.updateLoginUser(updatedUserInfo);
}

</script>

<style scoped>
/* 나의 루틴 태그 스타일 */
.routine-tag {
    font-size: 0.8em;
    padding: 4px 5px;
    margin-bottom: 20px;
    border-radius: 3px;
    margin-right: 7px;
    background-color: #aaa;
    color: white;
    font-weight: 300;
}

/* 프로필 클릭 박스의 타이틀 스타일 */
.profile-title {
    font-weight: 700;
    margin-bottom: 15px;
    margin-right: 15px;
}

.routinetag-explain {
    font-weight: 300;
    font-style: italic;
    font-size: 0.8em;
    color: #777;
    margin-bottom: 15px;
}
</style>