<script setup>
import AdminLayout from '@/components/admin/AdminLayout.vue'
import { onMounted, ref } from 'vue'
import { createUser, getUsers } from '@/api/users'

const users = ref([])
const loading = ref(true)
const saving = ref(false)
const error = ref('')
const success = ref('')
const form = ref({ username: '', password: '', passwordConfirmation: '' })

onMounted(loadUsers)

async function loadUsers() {
  loading.value = true
  try {
    users.value = (await getUsers()).data
  } catch {
    error.value = 'Kullanıcılar yüklenemedi.'
  } finally {
    loading.value = false
  }
}

async function saveUser() {
  error.value = ''
  success.value = ''
  if (form.value.password !== form.value.passwordConfirmation) {
    error.value = 'Şifreler eşleşmiyor.'
    return
  }

  saving.value = true
  try {
    await createUser({ username: form.value.username, password: form.value.password })
    form.value = { username: '', password: '', passwordConfirmation: '' }
    success.value = 'Yeni kullanıcı oluşturuldu.'
    await loadUsers()
  } catch (e) {
    error.value = e.response?.data?.message || 'Kullanıcı oluşturulamadı.'
  } finally {
    saving.value = false
  }
}
</script>

<template>
  <AdminLayout>
    <div class="users-page">
      <div class="page-heading">
        <div>
          <p class="eyebrow">Yetki yönetimi</p>
          <h1>Kullanıcılar</h1>
          <p class="intro">Admin paneline erişebilecek yeni kullanıcılar oluşturun.</p>
        </div>
      </div>

      <div v-if="success" class="alert success" role="status">{{ success }}</div>
      <div v-if="error" class="alert error" role="alert">{{ error }}</div>

      <form class="editor-card" @submit.prevent="saveUser">
        <h2>Yeni kullanıcı oluştur</h2>
        <div class="form-grid">
          <label>Kullanıcı adı *
            <input v-model.trim="form.username" required minlength="3" maxlength="50" autocomplete="username" />
          </label>
          <label>Şifre *
            <input v-model="form.password" required minlength="8" maxlength="100" type="password" autocomplete="new-password" />
          </label>
          <label>Şifre tekrar *
            <input v-model="form.passwordConfirmation" required minlength="8" maxlength="100" type="password" autocomplete="new-password" />
          </label>
        </div>
        <div class="editor-actions">
          <button class="primary" type="submit" :disabled="saving">{{ saving ? 'Oluşturuluyor…' : 'Kullanıcı oluştur' }}</button>
        </div>
      </form>

      <section class="list-card">
        <h2>Mevcut kullanıcılar</h2>
        <div v-if="loading" class="state"><i class="fa-solid fa-spinner fa-spin"></i> Kullanıcılar yükleniyor…</div>
        <div v-else class="user-list">
          <div v-for="user in users" :key="user.id" class="user-row">
            <i class="fa-solid fa-user-shield"></i>
            <strong>{{ user.username }}</strong>
            <span>Admin</span>
          </div>
        </div>
      </section>
    </div>
  </AdminLayout>
</template>

<style scoped>
.users-page { max-width: 900px; }
.page-heading { margin-bottom: 28px; }
.eyebrow { margin: 0 0 6px; color: #c9a84c; font-size: 11px; font-weight: 700; letter-spacing: .14em; text-transform: uppercase; }
h1 { margin: 0 0 8px; color: #1a1a2e; font-size: 30px; }
.intro { margin: 0; color: #718096; font-size: 14px; }
.alert { margin-bottom: 18px; padding: 12px 16px; border-radius: 6px; font-size: 14px; }
.success { background: #f0fff4; color: #276749; }.error { background: #fff5f5; color: #9b2c2c; }
.editor-card,.list-card { margin-bottom: 24px; padding: 24px; background: #fff; border-radius: 8px; box-shadow: 0 2px 8px rgba(0,0,0,.06); }
h2 { margin: 0 0 20px; color: #1a1a2e; font-size: 18px; }
.form-grid { display: grid; grid-template-columns: repeat(3, 1fr); gap: 16px; margin-bottom: 22px; }
.form-grid label { display: flex; flex-direction: column; gap: 7px; color: #4a5568; font-size: 12px; font-weight: 700; }
.form-grid input { padding: 10px 12px; border: 1px solid #e2e8f0; border-radius: 4px; font: inherit; font-size: 14px; }
.form-grid input:focus { outline: 2px solid #c9a84c; outline-offset: 1px; }
.editor-actions { display: flex; justify-content: flex-end; }
.primary { border: 0; border-radius: 5px; padding: 11px 16px; background: #1a1a2e; color: white; cursor: pointer; font: inherit; font-weight: 700; }
.primary:hover { background: #c9a84c; color: #1a1a2e; }.primary:disabled { opacity: .6; cursor: wait; }
.user-list { display: grid; gap: 10px; }.user-row { display: flex; align-items: center; gap: 12px; padding: 13px 14px; border: 1px solid #edf2f7; border-radius: 5px; color: #1a1a2e; }.user-row i { color: #c9a84c; }.user-row span { margin-left: auto; color: #718096; font-size: 12px; }.state { padding: 25px 0; color: #a0aec0; }
@media (max-width: 700px) { .form-grid { grid-template-columns: 1fr; } .editor-card,.list-card { padding: 18px; } }
</style>
