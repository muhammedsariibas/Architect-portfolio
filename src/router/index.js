import { createRouter, createWebHistory } from 'vue-router'
import HomeView from '../views/HomeView.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    // ── Public ──────────────────────────────────────────────────────────────
    {
      path: '/',
      name: 'home',
      component: HomeView,
    },

    // ── Admin ────────────────────────────────────────────────────────────────
    {
      path: '/admin/login',
      name: 'admin-login',
      component: () => import('@/views/admin/LoginView.vue'),
      meta: { guestOnly: true },
    },
    {
      path: '/admin',
      name: 'admin-dashboard',
      component: () => import('@/views/admin/DashboardView.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/admin/media',
      name: 'admin-media',
      component: () => import('@/views/admin/MediaView.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/admin/i18n',
      name: 'admin-i18n',
      component: () => import('@/views/admin/I18nView.vue'),
      meta: { requiresAuth: true },
    },
    {
      path: '/admin/contact',
      name: 'admin-contact',
      component: () => import('@/views/admin/ContactView.vue'),
      meta: { requiresAuth: true },
    },

    // ── 404 ──────────────────────────────────────────────────────────────────
    {
      path: '/:pathMatch(.*)*',
      redirect: '/',
    },
  ],

  scrollBehavior(to, from, savedPosition) {
    if (savedPosition) return savedPosition
    if (to.hash) return { el: to.hash, behavior: 'smooth' }
    return { top: 0 }
  },
})

// ── Navigation Guard ──────────────────────────────────────────────────────────
router.beforeEach((to, from, next) => {
  const token = localStorage.getItem('admin_token')

  if (to.meta.requiresAuth && !token) {
    return next({ name: 'admin-login', query: { redirect: to.fullPath } })
  }

  if (to.meta.guestOnly && token) {
    return next({ name: 'admin-dashboard' })
  }

  next()
})

export default router
