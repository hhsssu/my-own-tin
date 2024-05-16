import { createRouter, createWebHistory } from 'vue-router'
import MainView from "@/views/MainView.vue";
import RoutineView from "@/views/RoutineView.vue";
import MyPageView from "@/views/MyPageView.vue";
import SearchView from "@/views/SearchView.vue";
import QnAView from "@/views/QnAView.vue";
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";

import SearchList from "@/components/search/SearchList.vue";
import SearchDetail from "@/components/search/SearchDetail.vue";

import QnADetail from '@/components/qna/QnADetail.vue';
import QnAList from '@/components/qna/QnAList.vue';
import QnACreate from '@/components/qna/QnACreate.vue';
import QnAUpdate from '@/components/qna/QnAUpdate.vue';

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { // 메인 페이지
      path: '/',
      name: 'main',
      component: MainView,
    },
    { // 루틴 페이지
      path: '/routine',
      name: 'routineView',
      component: RoutineView,
      children: [ // RoutineView 컴포넌트 내부의 <RouterView/>에서 렌더링됨.
        { // 루틴 리스트
          path: '',
          name: 'routineList',
          component: RoutineList,
        },
        { // 루틴 상세
          path: ':routineId',
          name: 'routineDetail',
          component: RoutineDetail,
        },
        { // 루틴 등록
          path: 'create',
          name: 'routineCreate',
          component: RoutineCreate,
        },
        { // 루틴 수정
          path: 'modify',
          name: 'routineUpdate',
          component: RoutineUpdate,
        },
      ]
    },
    { // 마이페이지
      path: '/mypage',
      name: 'mypageView',
      component: MyPageView,
    },
    { // 질문답변 페이지
      path: '/qna',
      name: 'qnaView',
      component: QnAView,
      children: [ // QnAView 컴포넌트 내부의 <RouterView/>에서 렌더링됨.
        { // 질문 리스트
          path: '',
          name: 'qnaList',
          component: QnAList,
        },
        { // 질문 상세페이지
          path: ':qustionId',
          name: 'qnaDetail',
          component: QnADetail,
          children: [ // QnADetail 컴포넌트 내부의 <RouterView/>에서 렌더링됨.
          { // 댓글 등록
            path: 'create',
            name: 'answerCreate',
            component: AnswerCreate,
          },
          ]
        },
        { // 질문 등록
          path: 'create',
          name: 'qnaCreate',
          component: QnACreate,
        },
        { // 질문 수정
          path: 'modify',
          name: 'qnaUpdate',
          component: QnAUpdate,
        },
      ]
    },
    { // 검색 페이지
      path: '/search',
      name: 'searchView',
      component: SearchView,
      children: [ // SearchView 컴포넌트 내부의 <RouterView/>에서 렌더링됨.
        { // 루틴 검색 리스트
          path: '',
          name: 'searchList',
          component: SearchView,
        },
        { // 루틴 검색 상세
          path: ':routineId',
          name: 'searchDetail',
          component: SearchDetail,
        },
      ]
    },
    { // 로그인
      path: '/login',
      name: 'loginView',
      component: LoginView,
    },
    { // 회원가입
      path: '/join',
      name: 'joinView',
      component: JoinView,
    },
  ]
})

export default router
