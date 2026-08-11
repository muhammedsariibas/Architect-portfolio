import { defineStore } from 'pinia'
import { ref, computed } from 'vue'
import { login as apiLogin } from '@/api/auth'

export const useAuthStore = defineStore('auth', () => {
  const token = ref(localStorage.getItem('admin_token') || null)

  const isLoggedIn = computed(() => !!token.value)

  async function login(username, password) {
    const { data } = await apiLogin(username, password)
    token.value = data.accessToken
    localStorage.setItem('admin_token', data.accessToken)
  }

  function logout() {
    token.value = null
    localStorage.removeItem('admin_token')
  }

  return { token, isLoggedIn, login, logout }
})
