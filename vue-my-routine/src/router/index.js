import { createRouter, createWebHistory } from 'vue-router'
import MainView from "@/views/MainView.vue";
import RoutineView from "@/views/RoutineView.vue";
import MyPageView from "@/views/MyPageView.vue";
import QnAView from "@/views/QnAView.vue";
import SearchView from "@/views/SearchView.vue";
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";

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
      component: () => import('../views/RoutineView.vue'),
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
    },
    { // 검색 페이지
      path: '/search',
      name: 'searchView',
      component: SearchView,
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
