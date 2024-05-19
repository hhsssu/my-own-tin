<template>
    <div>
        <h2 class="view-title">나의 루틴 태그</h2>
        <div class="profile-click-box content-box">
            <div class="flex-box">
                <div class="profile-title">기본 정보 태그 설정</div>
                <div class="routinetag-explain">회원정보 수정에서 변경 가능합니다.</div>
            </div>
            <div class="flex-box">
                <div class="routine-tag">20대</div>
                <div class="routine-tag">여성</div>
            </div>
        </div>
        <div class="profile-click-box content-box">
            <div class="profile-title">운동 정보 태그 설정</div>
            <div class="flex-box">
                <div>
                    <!-- 운동 부위 1 -->
                    <div>운동부위1</div>
                    <select name="part1" id="part1" size="3">
                        <option value="유산소" selected>유산소</option>
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
                    <select name="part2" id="part2" size="3" disabled>
                        <option value="none">선택안함</option>
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
                    <select name="time" id="time" size="3">
                        <option value="10m">10분</option>
                        <option value="20m">20분</option>
                        <option value="30m">30분</option>
                        <option value="40m">40분</option>
                        <option value="50m">50분</option>
                        <option value="1h" selected>1시간</option>
                        <option value="1h">1시간30분</option>
                        <option value="2h">2시간</option>
                        <option value="2h">2시간30분</option>
                        <option value="3h">3시간</option>
                        <option value="4h">4시간</option>
                        <option value="5h">5시간 이상</option>
                    </select>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
// 기본 루틴 태그 설정하기
// select 창으로 설정하도록 함..!
// 회원 나이(age)가 27세면 20세에 자동으로 선택되어있도록 함.
// 성별도 마찬가지.

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