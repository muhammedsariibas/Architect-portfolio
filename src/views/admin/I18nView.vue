<script setup>
import AdminLayout from '@/components/admin/AdminLayout.vue'
import { ref, onMounted, computed } from 'vue'
import { getAllMessages, updateMessage } from '@/api/i18n'

const messages   = ref([])
const loading    = ref(true)
const saving     = ref(null)   // id of the row being saved
const success    = ref('')
const error      = ref('')

const locale     = ref('tr')
const search     = ref('')

const filtered = computed(() => {
  const q = search.value.toLowerCase()
  return messages.value
    .filter(m => m.locale === locale.value)
    .filter(m => !q || m.key.toLowerCase().includes(q) || m.content.toLowerCase().includes(q))
})

onMounted(fetchAll)

async function fetchAll() {
  loading.value = true
  try {
    const { data } = await getAllMessages()
    messages.value = data.map(m => ({ ...m, draft: m.content }))
  } catch {
    error.value = 'Mesajlar yüklenemedi.'
  } finally {
    loading.value = false
  }
}

async function save(msg) {
  if (msg.draft === msg.content) return
  saving.value = msg.id
  success.value = ''
  error.value   = ''
  try {
    const { data } = await updateMessage(msg.id, {
      key: msg.key,
      locale: msg.locale,
      content: msg.draft,
    })
    msg.content = data.content
    success.value = `"${msg.key}" güncellendi.`
  } catch (e) {
    error.value = e.response?.data?.message || 'Güncelleme başarısız.'
  } finally {
    saving.value = null
  }
}
</script>

<template>
  <AdminLayout>
    <div class="i18n-page">
      <h2 class="page-title">Metin Yönetimi (i18n)</h2>

      <div v-if="success" class="alert success">
        <i class="fa-solid fa-circle-check"></i> {{ success }}
        <button @click="success = ''"><i class="fa-solid fa-xmark"></i></button>
      </div>
      <div v-if="error" class="alert error">
        <i class="fa-solid fa-circle-exclamation"></i> {{ error }}
        <button @click="error = ''"><i class="fa-solid fa-xmark"></i></button>
      </div>

      <!-- Toolbar -->
      <div class="toolbar">
        <div class="locale-tabs">
          <button :class="['tab', { active: locale === 'tr' }]" @click="locale = 'tr'">🇹🇷 Türkçe</button>
          <button :class="['tab', { active: locale === 'en' }]" @click="locale = 'en'">🇬🇧 English</button>
        </div>
        <div class="search-wrap">
          <i class="fa-solid fa-magnifying-glass"></i>
          <input v-model="search" type="text" placeholder="Anahtar veya içerik ara..." />
        </div>
      </div>

      <!-- Table -->
      <div v-if="loading" class="loading-state">
        <i class="fa-solid fa-spinner fa-spin"></i> Yükleniyor...
      </div>

      <div v-else class="table-wrap">
        <table class="msg-table">
          <thead>
            <tr>
              <th style="width:32%">Anahtar (Key)</th>
              <th>İçerik</th>
              <th style="width:80px"></th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="msg in filtered" :key="msg.id" :class="{ dirty: msg.draft !== msg.content }">
              <td class="key-cell">
                <code>{{ msg.key }}</code>
              </td>
              <td>
                <textarea
                  v-model="msg.draft"
                  rows="2"
                  class="content-input"
                  @keydown.ctrl.enter="save(msg)"
                ></textarea>
              </td>
              <td>
                <button
                  class="save-btn"
                  :disabled="msg.draft === msg.content || saving === msg.id"
                  @click="save(msg)"
                  title="Kaydet (Ctrl+Enter)"
                >
                  <i v-if="saving === msg.id" class="fa-solid fa-spinner fa-spin"></i>
                  <i v-else class="fa-solid fa-floppy-disk"></i>
                </button>
              </td>
            </tr>
            <tr v-if="filtered.length === 0">
              <td colspan="3" class="empty-row">Sonuç bulunamadı.</td>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  </AdminLayout>
</template>

<style scoped>
.i18n-page { max-width: 1000px; }

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

/* Toolbar */
.toolbar {
  display: flex;
  align-items: center;
  justify-content: space-between;
  gap: 16px;
  margin-bottom: 20px;
  flex-wrap: wrap;
}

.locale-tabs {
  display: flex;
  gap: 8px;
}

.tab {
  padding: 8px 20px;
  border: 1.5px solid #e2e8f0;
  background: #fff;
  border-radius: 6px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  color: #4a5568;
  transition: all 0.2s;
  font-family: inherit;
}

.tab:hover { border-color: #c9a84c; }
.tab.active { background: #1a1a2e; border-color: #1a1a2e; color: #fff; }

.search-wrap {
  display: flex;
  align-items: center;
  gap: 10px;
  background: #fff;
  border: 1.5px solid #e2e8f0;
  border-radius: 6px;
  padding: 8px 14px;
  flex: 1;
  max-width: 340px;
  color: #a0aec0;
}

.search-wrap input {
  border: none;
  outline: none;
  font-size: 14px;
  font-family: inherit;
  width: 100%;
  color: #1a1a2e;
}

/* Table */
.table-wrap {
  background: #fff;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.06);
  overflow: auto;
}

.msg-table {
  width: 100%;
  border-collapse: collapse;
}

.msg-table th {
  padding: 12px 16px;
  text-align: left;
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  color: #718096;
  background: #f8f9fa;
  border-bottom: 1px solid #e2e8f0;
}

.msg-table td {
  padding: 10px 16px;
  border-bottom: 1px solid #f0f0f5;
  vertical-align: middle;
}

.msg-table tr:last-child td { border-bottom: none; }

.msg-table tr.dirty {
  background: #fffbf0;
}

.key-cell code {
  font-size: 12px;
  background: #f0f0f5;
  padding: 3px 8px;
  border-radius: 4px;
  color: #4a5568;
  font-family: 'Courier New', monospace;
  word-break: break-all;
}

.content-input {
  width: 100%;
  border: 1.5px solid #e2e8f0;
  border-radius: 4px;
  padding: 7px 10px;
  font-size: 13px;
  font-family: inherit;
  resize: vertical;
  outline: none;
  transition: border-color 0.2s;
  line-height: 1.5;
}

.content-input:focus { border-color: #c9a84c; }

.save-btn {
  width: 36px;
  height: 36px;
  background: #1a1a2e;
  border: none;
  border-radius: 6px;
  color: #fff;
  font-size: 15px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background 0.2s;
}

.save-btn:hover:not(:disabled) { background: #c9a84c; }
.save-btn:disabled { opacity: 0.35; cursor: not-allowed; }

.loading-state, .empty-row {
  text-align: center;
  padding: 60px;
  color: #a0aec0;
  font-size: 15px;
}
</style>
