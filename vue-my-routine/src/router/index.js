import { createRouter, createWebHistory } from 'vue-router'
import MainView from "@/views/MainView.vue";
import RoutineView from "@/views/RoutineView.vue";
import MyPageView from "@/views/MyPageView.vue";
import SearchView from "@/views/SearchView.vue";
import QnAView from "@/views/QnAView.vue";
import LoginView from "@/views/LoginView.vue";
import JoinView from "@/views/JoinView.vue";

import RoutineList from "@/components/routine/RoutineList.vue";
import RoutineCreate from "@/components/routine/RoutineCreate.vue";
import RoutineDetail from "@/components/routine/RoutineDetail.vue";
import RoutineUpdate from "@/components/routine/RoutineUpdate.vue";

import SearchList from "@/components/search/SearchList.vue";
import SearchDetail from "@/components/search/SearchDetail.vue";

import QnADetail from '@/components/qna/QnADetail.vue';
import QnAList from '@/components/qna/QnAList.vue';
import QnACreate from '@/components/qna/QnACreate.vue';
import QnAUpdate from '@/components/qna/QnAUpdate.vue';

import AnswerCreate from '@/components/qna/AnswerCreate.vue';

import { useHeaderStore } from '@/stores/header';
import { useUserStore } from '@/stores/user';


const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    { // 메인 페이지
      path: '/',
      name: 'main',
      component: MainView,
      beforeEnter: async (to, from, next) => {
        const userStore = useUserStore();
        try {
          await userStore.getLoginUser();
          if (userStore.user) {
            console.log('이미 로그인 상태입니다.');
            next({ name: 'routine' });
          } else {
            next({ name: 'login' });
          }
        } catch (error) {
          console.error(error);
          // 오류 발생 시 어떻게 처리할지 여기에 작성
        }
      }
    },
    { // 로그인
      path: '/login',
      name: 'login',
      component: LoginView,
    },
    { // 회원가입
      path: '/join',
      name: 'join',
      component: JoinView,
    },
    { // 루틴 페이지
      path: '/routine',
      name: 'routine',
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
      ],

    },
    { // 마이페이지
      path: '/mypage',
      name: 'mypage',
      component: MyPageView,
    },
    { // 질문답변 페이지
      path: '/qna',
      name: 'qna',
      component: QnAView,
      children: [ // QnAView 컴포넌트 내부의 <RouterView/>에서 렌더링됨.
        { // 질문 리스트
          path: '',
          name: 'qnaList',
          component: QnAList,
        },
        { // 질문 상세페이지
          path: ':questionId',
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
      ],
    },
    { // 검색 페이지
      path: '/search',
      name: 'search',
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
      ],
    },
  ]
})

// 헤더 안보이게 할 부분들
router.beforeEach((to, from, next) => {
  const headerStore = useHeaderStore();
  const hideHeaderRoutes = ['/', '/login', '/join']
  headerStore.toggleHeader(!hideHeaderRoutes.includes(to.path))
  next()
})

export default router
