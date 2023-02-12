import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Index',
    redirect: { name: "dashboard" }
  },
  {
    path: '/dashboard',
    name: 'dashboard',
    component: () => import('@/views/dashboard'),
    redirect: '/dashboard/index',
    meta: { title: '首页显示', icon: 'example' },
    children: [
      {
        path: 'index',
        name: '首页显示',
        component: () => import('@/views/index'),
        meta: { title: '首页显示', noCache: 'true' },
        hidden: true
      },
      {
        path: 'videoInfo/:id',
        name: '视频显示',
        component: () => import('@/views/video/videoInfo.vue'),
        meta: { title: '视频显示', noCache: 'true' },
        hidden: true
      },
      {
        path: 'user/:item',
        name: '用户页面',
        component: () => import('@/views/video/user'),
        meta: { title: '用户页面', noCache: 'true' },
        children: [
          {
            path: 'aboutme',
            name: '关于自己',
            component: () => import('@/views/user/aboutme'),
            meta: { title: '关于自己', noCache: 'true' },
            hidden: true
          },
          {
            path: 'videos',
            name: '个人上传视频',
            component: () => import('@/views/user/videos'),
            meta: { title: '个人上传视频', noCache: 'true' },
            hidden: true
          },
          {
            path: 'collections',
            name: '个人收藏视频',
            component: () => import('@/views/user/collections'),
            meta: { title: '个人收藏视频', noCache: 'true' },
            hidden: true
          },
          {
            path: 'follows',
            name: '关注者',
            component: () => import('@/views/user/follows'),
            meta: { title: '关注者', noCache: 'true' },
            hidden: true
          },
          {
            path: 'fans',
            name: '粉丝',
            component: () => import('@/views/user/fans'),
            meta: { title: '粉丝', noCache: 'true' },
            hidden: true
          },
          {
            path: 'comments',
            name: '回复消息',
            component: () => import('@/views/user/comments'),
            meta: { title: '回复消息', noCache: 'true' },
            hidden: true
          },
          {
            path: 'setting',
            name: '个人设置',
            component: () => import('@/views/user/setting'),
            meta: { title: '个人设置', noCache: 'true' },
            hidden: true
          },
          {
            path: 'addVideo',
            name: '增加视频',
            component: () => import('@/views/user/addVideo'),
            meta: { title: '增加视频', noCache: 'true' },
            hidden: true
          },
          {
            path: 'browseRecords',
            name: '浏览记录',
            component: () => import('@/views/user/browseRecords'),
            meta: { title: '浏览记录', noCache: 'true' },
            hidden: true
          },
          {
            path: 'privateLetter',
            name: '私信',
            component: () => import('@/views/letter/privateLetter'),
            meta: { title: '浏览记录', noCache: 'true' },
            hidden: true
          },
        ],
      },

    ]
  },
  {
    path: '/login',
    name: '登录页面',
    component: () => import('@/views/login'),
    meta: { title: '登录页面', noCache: 'true' },

  },
  {
    path: '/register',
    name: '注册页面',
    component: () => import('@/views/register'),
    meta: { title: '注册页面', noCache: 'true' },
  },
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
