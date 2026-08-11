<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router   = useRouter()
const auth     = useAuthStore()

const username = ref('')
const password = ref('')
const loading  = ref(false)
const error    = ref('')

async function handleLogin() {
  if (!username.value || !password.value) {
    error.value = 'Kullanıcı adı ve şifre zorunludur.'
    return
  }
  loading.value = true
  error.value   = ''
  try {
    await auth.login(username.value, password.value)
    router.push('/admin')
  } catch (e) {
    error.value = e.response?.data?.message || 'Kullanıcı adı veya şifre hatalı.'
  } finally {
    loading.value = false
  }
}
</script>

<template>
  <div class="login-page">
    <div class="login-card">
      <div class="login-logo">
        <img src="/logo/kdpng.png" alt="KD Dizayn" />
      </div>
      <h1>Admin Girişi</h1>
      <p class="subtitle">KD Dizayn Yönetim Paneli</p>

      <form @submit.prevent="handleLogin" class="login-form">
        <div class="field">
          <label>Kullanıcı Adı</label>
          <input
            v-model="username"
            type="text"
            placeholder="admin"
            autocomplete="username"
            :disabled="loading"
          />
        </div>

        <div class="field">
          <label>Şifre</label>
          <input
            v-model="password"
            type="password"
            placeholder="••••••••"
            autocomplete="current-password"
            :disabled="loading"
          />
        </div>

        <p v-if="error" class="error-msg">
          <i class="fa-solid fa-circle-exclamation"></i> {{ error }}
        </p>

        <button type="submit" class="btn-login" :disabled="loading">
          <i v-if="loading" class="fa-solid fa-spinner fa-spin"></i>
          <span>{{ loading ? 'Giriş yapılıyor...' : 'Giriş Yap' }}</span>
        </button>
      </form>
    </div>
  </div>
</template>

<style scoped>
.login-page {
  min-height: 100vh;
  background: #f0f0f5;
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 24px;
}

.login-card {
  background: #fff;
  width: 100%;
  max-width: 400px;
  padding: 48px 40px;
  box-shadow: 0 8px 32px rgba(26,26,46,0.12);
  text-align: center;
}

.login-logo img {
  height: 48px;
  filter: brightness(0) saturate(100%) invert(13%) sepia(49%) saturate(600%) hue-rotate(197deg);
  margin-bottom: 20px;
}

h1 {
  font-size: 24px;
  font-weight: 700;
  color: #1a1a2e;
  margin-bottom: 4px;
}

.subtitle {
  font-size: 13px;
  color: #718096;
  margin-bottom: 36px;
}

.login-form {
  display: flex;
  flex-direction: column;
  gap: 20px;
  text-align: left;
}

.field {
  display: flex;
  flex-direction: column;
  gap: 6px;
}

.field label {
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  color: #4a5568;
}

.field input {
  padding: 11px 14px;
  border: 1.5px solid #e2e8f0;
  font-size: 15px;
  outline: none;
  transition: border-color 0.2s;
  font-family: inherit;
  background: #fafafa;
  border-radius: 4px;
}

.field input:focus {
  border-color: #c9a84c;
  background: #fff;
}

.field input:disabled {
  opacity: 0.6;
}

.error-msg {
  font-size: 13px;
  color: #e74c3c;
  background: #fef2f2;
  border: 1px solid #fecaca;
  padding: 10px 12px;
  border-radius: 4px;
  display: flex;
  align-items: center;
  gap: 8px;
}

.btn-login {
  padding: 13px;
  background: #1a1a2e;
  color: #fff;
  border: none;
  font-size: 14px;
  font-weight: 700;
  letter-spacing: 0.06em;
  cursor: pointer;
  transition: background 0.2s;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  font-family: inherit;
  border-radius: 4px;
}

.btn-login:hover:not(:disabled) {
  background: #c9a84c;
}

.btn-login:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}
</style>
