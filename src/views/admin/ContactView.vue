<script setup>
import AdminLayout from '@/components/admin/AdminLayout.vue'
import { ref, onMounted } from 'vue'
import { getContact, updateContact } from '@/api/contact'

const form    = ref({
  phone: '', email: '', address: '',
  googleMapsUrl: '', facebookUrl: '',
  instagramUrl: '', linkedinUrl: '', workingHours: '',
})
const loading = ref(true)
const saving  = ref(false)
const success = ref('')
const error   = ref('')

onMounted(async () => {
  try {
    const { data } = await getContact()
    Object.assign(form.value, data)
  } catch {
    error.value = 'İletişim bilgileri yüklenemedi.'
  } finally {
    loading.value = false
  }
})

async function handleSave() {
  saving.value  = true
  success.value = ''
  error.value   = ''
  try {
    await updateContact(form.value)
    success.value = 'İletişim bilgileri güncellendi.'
  } catch (e) {
    error.value = e.response?.data?.message || 'Güncelleme başarısız.'
  } finally {
    saving.value = false
  }
}
</script>

<template>
  <AdminLayout>
    <div class="contact-page">
      <h2 class="page-title">İletişim Bilgileri</h2>

      <div v-if="success" class="alert success">
        <i class="fa-solid fa-circle-check"></i> {{ success }}
        <button @click="success = ''"><i class="fa-solid fa-xmark"></i></button>
      </div>
      <div v-if="error" class="alert error">
        <i class="fa-solid fa-circle-exclamation"></i> {{ error }}
        <button @click="error = ''"><i class="fa-solid fa-xmark"></i></button>
      </div>

      <div v-if="loading" class="loading-state">
        <i class="fa-solid fa-spinner fa-spin"></i> Yükleniyor...
      </div>

      <div v-else class="card">
        <form @submit.prevent="handleSave" class="contact-form">
          <div class="form-grid">
            <div class="field">
              <label>Telefon *</label>
              <input v-model="form.phone" type="text" placeholder="+90 535 000 00 00" required />
            </div>
            <div class="field">
              <label>E-posta *</label>
              <input v-model="form.email" type="email" placeholder="info@kddizayn.com" required />
            </div>
            <div class="field full">
              <label>Adres *</label>
              <textarea v-model="form.address" rows="2" placeholder="Tam adres..." required></textarea>
            </div>
            <div class="field full">
              <label>Google Maps URL</label>
              <input v-model="form.googleMapsUrl" type="url" placeholder="https://maps.google.com/..." />
            </div>
            <div class="field">
              <label>Instagram URL</label>
              <input v-model="form.instagramUrl" type="url" placeholder="https://instagram.com/..." />
            </div>
            <div class="field">
              <label>LinkedIn URL</label>
              <input v-model="form.linkedinUrl" type="url" placeholder="https://linkedin.com/..." />
            </div>
            <div class="field">
              <label>Facebook URL</label>
              <input v-model="form.facebookUrl" type="url" placeholder="https://facebook.com/..." />
            </div>
            <div class="field">
              <label>Çalışma Saatleri</label>
              <input v-model="form.workingHours" type="text" placeholder="Pzt–Cum: 09:00–18:00" />
            </div>
          </div>

          <div class="form-footer">
            <button type="submit" class="btn-primary" :disabled="saving">
              <i v-if="saving" class="fa-solid fa-spinner fa-spin"></i>
              <i v-else class="fa-solid fa-floppy-disk"></i>
              {{ saving ? 'Kaydediliyor...' : 'Kaydet' }}
            </button>
          </div>
        </form>
      </div>
    </div>
  </AdminLayout>
</template>

<style scoped>
.contact-page { max-width: 800px; }

.page-title {
  font-size: 26px;
  font-weight: 700;
  color: #1a1a2e;
  margin-bottom: 24px;
}

.alert {
  padding: 12px 16px;
  border-radius: 6px;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 14px;
}

.alert button { margin-left: auto; background: none; border: none; cursor: pointer; opacity: 0.6; }
.alert.success { background: #f0fff4; border: 1px solid #9ae6b4; color: #276749; }
.alert.error   { background: #fff5f5; border: 1px solid #fed7d7; color: #9b2c2c; }

.loading-state {
  text-align: center;
  padding: 60px;
  color: #a0aec0;
  font-size: 15px;
}

.card {
  background: #fff;
  border-radius: 8px;
  padding: 32px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.06);
}

.form-grid {
  display: grid;
  grid-template-columns: 1fr 1fr;
  gap: 20px;
  margin-bottom: 28px;
}

.field { display: flex; flex-direction: column; gap: 6px; }
.field.full { grid-column: 1 / -1; }

.field label {
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  color: #4a5568;
}

.field input,
.field textarea {
  padding: 10px 12px;
  border: 1.5px solid #e2e8f0;
  border-radius: 4px;
  font-size: 14px;
  font-family: inherit;
  outline: none;
  transition: border-color 0.2s;
  resize: vertical;
}

.field input:focus,
.field textarea:focus { border-color: #c9a84c; }

.form-footer {
  display: flex;
  justify-content: flex-end;
}

.btn-primary {
  padding: 12px 28px;
  background: #1a1a2e;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  display: flex;
  align-items: center;
  gap: 8px;
  transition: background 0.2s;
  font-family: inherit;
}

.btn-primary:hover:not(:disabled) { background: #c9a84c; }
.btn-primary:disabled { opacity: 0.65; cursor: not-allowed; }

@media (max-width: 600px) {
  .form-grid { grid-template-columns: 1fr; }
  .field.full { grid-column: 1; }
  .card { padding: 20px; }
}
</style>
