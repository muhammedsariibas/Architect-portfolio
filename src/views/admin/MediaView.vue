<script setup>
import AdminLayout from '@/components/admin/AdminLayout.vue'
import { ref, onMounted, onBeforeUnmount, computed } from 'vue'
import { getAllMedia, uploadMedia, deleteMedia } from '@/api/media'

const media      = ref([])
const loading    = ref(true)
const uploading  = ref(false)
const error      = ref('')
const success    = ref('')

// Upload form
const file       = ref(null)
const category   = ref('GALLERY')
const fileInput  = ref(null)
const preview    = ref(null)

// Filter
const filter     = ref('ALL')

const categories = ['SLIDER', 'GALLERY', 'LOGO', 'GENERAL']

const filtered = computed(() =>
  filter.value === 'ALL'
    ? media.value
    : media.value.filter(m => m.category === filter.value)
)

onMounted(fetchMedia)
onBeforeUnmount(() => { if (preview.value) URL.revokeObjectURL(preview.value) })

async function fetchMedia() {
  loading.value = true
  try {
    const { data } = await getAllMedia()
    media.value = data
  } catch {
    error.value = 'Medya yüklenemedi.'
  } finally {
    loading.value = false
  }
}

function onFileChange(e) {
  const f = e.target.files[0]
  if (!f) return
  file.value = f
  if (preview.value) URL.revokeObjectURL(preview.value)
  preview.value = URL.createObjectURL(f)
}

async function handleUpload() {
  if (!file.value) { error.value = 'Lütfen bir dosya seçin.'; return }
  uploading.value = true
  error.value = ''
  success.value = ''
  try {
    const fd = new FormData()
    fd.append('file', file.value)
    fd.append('category', category.value)
    await uploadMedia(fd)
    success.value = 'Dosya başarıyla yüklendi.'
    file.value = null
    if (preview.value) URL.revokeObjectURL(preview.value)
    preview.value = null
    if (fileInput.value) fileInput.value.value = ''
    await fetchMedia()
  } catch (e) {
    error.value = e.response?.data?.message || 'Yükleme başarısız.'
  } finally {
    uploading.value = false
  }
}

async function handleDelete(id) {
  if (!confirm('Bu görseli silmek istediğinize emin misiniz?')) return
  try {
    await deleteMedia(id)
    media.value = media.value.filter(m => m.id !== id)
    success.value = 'Görsel silindi.'
  } catch {
    error.value = 'Silme işlemi başarısız.'
  }
}
</script>

<template>
  <AdminLayout>
    <div class="media-page">
      <h2 class="page-title">Medya Yönetimi</h2>

      <!-- Alerts -->
      <div v-if="success" class="alert success" role="status" aria-live="polite">
        <i class="fa-solid fa-circle-check"></i> {{ success }}
        <button @click="success = ''"><i class="fa-solid fa-xmark"></i></button>
      </div>
      <div v-if="error" class="alert error" role="alert" aria-live="assertive">
        <i class="fa-solid fa-circle-exclamation"></i> {{ error }}
        <button @click="error = ''"><i class="fa-solid fa-xmark"></i></button>
      </div>

      <!-- Upload Card -->
      <div class="card upload-card">
        <h3><i class="fa-solid fa-cloud-arrow-up"></i> Yeni Görsel Yükle</h3>

        <div class="upload-form">
          <div class="file-drop" @click="fileInput.click()">
            <img v-if="preview" :src="preview" class="preview-img" />
            <div v-else class="drop-placeholder">
              <i class="fa-solid fa-image"></i>
              <span>Dosya seçmek için tıklayın</span>
              <small>PNG, JPG, WEBP — max 10 MB</small>
            </div>
            <input
              ref="fileInput"
              type="file"
              accept="image/png,image/jpeg,image/webp"
              @change="onFileChange"
              style="display:none"
            />
          </div>

          <div class="upload-controls">
            <div class="field">
              <label>Kategori</label>
              <select v-model="category">
                <option v-for="c in categories" :key="c" :value="c">{{ c }}</option>
              </select>
            </div>
            <button class="btn-primary" @click="handleUpload" :disabled="uploading || !file">
              <i v-if="uploading" class="fa-solid fa-spinner fa-spin"></i>
              <i v-else class="fa-solid fa-upload"></i>
              {{ uploading ? 'Yükleniyor...' : 'Yükle' }}
            </button>
          </div>
        </div>
      </div>

      <!-- Filter Tabs -->
      <div class="filter-tabs">
        <button
          v-for="tab in ['ALL', ...categories]"
          :key="tab"
          :class="['tab', { active: filter === tab }]"
          @click="filter = tab"
        >{{ tab }}</button>
      </div>

      <!-- Grid -->
      <div v-if="loading" class="loading-state">
        <i class="fa-solid fa-spinner fa-spin"></i> Yükleniyor...
      </div>

      <div v-else-if="filtered.length === 0" class="empty-state">
        <i class="fa-solid fa-images"></i>
        <p>Bu kategoride görsel bulunamadı.</p>
      </div>

      <div v-else class="media-grid">
        <div v-for="item in filtered" :key="item.id" class="media-item">
          <div class="media-img-wrap">
            <img :src="item.fileUrl" :alt="item.fileName" loading="lazy" />
            <div class="media-overlay">
              <button class="del-btn" @click="handleDelete(item.id)" title="Sil">
                <i class="fa-solid fa-trash"></i>
              </button>
            </div>
          </div>
          <div class="media-meta">
            <span class="media-name">{{ item.fileName }}</span>
            <span class="media-cat">{{ item.category }}</span>
          </div>
        </div>
      </div>
    </div>
  </AdminLayout>
</template>

<style scoped>
.media-page { max-width: 1100px; }

.page-title {
  font-size: 26px;
  font-weight: 700;
  color: #1a1a2e;
  margin-bottom: 24px;
}

/* Alerts */
.alert {
  padding: 12px 16px;
  border-radius: 6px;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 10px;
  font-size: 14px;
}

.alert button {
  margin-left: auto;
  background: none;
  border: none;
  cursor: pointer;
  opacity: 0.6;
}

.alert.success { background: #f0fff4; border: 1px solid #9ae6b4; color: #276749; }
.alert.error   { background: #fff5f5; border: 1px solid #fed7d7; color: #9b2c2c; }

/* Card */
.card {
  background: #fff;
  border-radius: 8px;
  padding: 24px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.06);
  margin-bottom: 28px;
}

.card h3 {
  font-size: 16px;
  font-weight: 700;
  color: #1a1a2e;
  margin-bottom: 20px;
  display: flex;
  align-items: center;
  gap: 10px;
}

.card h3 i { color: #c9a84c; }

/* Upload form */
.upload-form {
  display: flex;
  gap: 24px;
  align-items: flex-start;
  flex-wrap: wrap;
}

.file-drop {
  width: 200px;
  height: 160px;
  border: 2px dashed #e2e8f0;
  border-radius: 6px;
  cursor: pointer;
  overflow: hidden;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: border-color 0.2s;
  flex-shrink: 0;
}

.file-drop:hover { border-color: #c9a84c; }

.drop-placeholder {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 6px;
  color: #a0aec0;
  font-size: 13px;
  text-align: center;
  padding: 16px;
}

.drop-placeholder i { font-size: 32px; }

.preview-img {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.upload-controls {
  display: flex;
  flex-direction: column;
  gap: 16px;
  min-width: 180px;
}

.field label {
  display: block;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  color: #4a5568;
  margin-bottom: 6px;
}

.field select, .field input {
  width: 100%;
  padding: 10px 12px;
  border: 1.5px solid #e2e8f0;
  border-radius: 4px;
  font-size: 14px;
  font-family: inherit;
  outline: none;
  transition: border-color 0.2s;
}

.field select:focus, .field input:focus { border-color: #c9a84c; }

.btn-primary {
  padding: 11px 20px;
  background: #1a1a2e;
  color: #fff;
  border: none;
  border-radius: 4px;
  font-size: 14px;
  font-weight: 600;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
  transition: background 0.2s;
  font-family: inherit;
}

.btn-primary:hover:not(:disabled) { background: #c9a84c; }
.btn-primary:disabled { opacity: 0.6; cursor: not-allowed; }

/* Tabs */
.filter-tabs {
  display: flex;
  gap: 8px;
  margin-bottom: 20px;
  flex-wrap: wrap;
}

.tab {
  padding: 7px 16px;
  border: 1.5px solid #e2e8f0;
  background: #fff;
  border-radius: 20px;
  font-size: 13px;
  font-weight: 600;
  cursor: pointer;
  color: #4a5568;
  transition: all 0.2s;
  font-family: inherit;
}

.tab:hover { border-color: #c9a84c; color: #c9a84c; }
.tab.active { background: #1a1a2e; border-color: #1a1a2e; color: #fff; }

/* States */
.loading-state, .empty-state {
  text-align: center;
  padding: 60px;
  color: #a0aec0;
  font-size: 15px;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 12px;
}

.empty-state i { font-size: 48px; }

/* Grid */
.media-grid {
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(180px, 1fr));
  gap: 16px;
}

.media-item {
  background: #fff;
  border-radius: 6px;
  overflow: hidden;
  box-shadow: 0 2px 6px rgba(0,0,0,0.07);
}

.media-img-wrap {
  position: relative;
  height: 140px;
  overflow: hidden;
}

.media-img-wrap img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  transition: transform 0.3s;
}

.media-item:hover .media-img-wrap img { transform: scale(1.05); }

.media-overlay {
  position: absolute;
  inset: 0;
  background: rgba(0,0,0,0.45);
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: opacity 0.2s;
}

.media-item:hover .media-overlay { opacity: 1; }

.del-btn {
  width: 40px;
  height: 40px;
  border-radius: 50%;
  background: #e74c3c;
  border: none;
  color: #fff;
  font-size: 16px;
  cursor: pointer;
  display: flex;
  align-items: center;
  justify-content: center;
  transition: background 0.2s;
}

.del-btn:hover { background: #c0392b; }

.media-meta {
  padding: 10px 12px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 8px;
}

.media-name {
  font-size: 12px;
  color: #4a5568;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
  flex: 1;
}

.media-cat {
  font-size: 10px;
  font-weight: 700;
  letter-spacing: 0.06em;
  color: #c9a84c;
  background: rgba(201,168,76,0.1);
  padding: 2px 7px;
  border-radius: 10px;
  flex-shrink: 0;
}
</style>
