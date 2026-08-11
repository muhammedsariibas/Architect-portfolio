<script setup>
import AdminLayout from '@/components/admin/AdminLayout.vue'
import { ref, onMounted } from 'vue'
import { getAllMedia } from '@/api/media'
import { getAllMessages } from '@/api/i18n'

const mediaCount = ref(0)
const i18nCount  = ref(0)
const loading    = ref(true)

onMounted(async () => {
  try {
    const [m, i] = await Promise.all([getAllMedia(), getAllMessages()])
    mediaCount.value = m.data.length
    i18nCount.value  = i.data.length
  } finally {
    loading.value = false
  }
})
</script>

<template>
  <AdminLayout>
    <div class="dashboard">
      <h2 class="page-title">Dashboard</h2>
      <p class="page-sub">Hoş geldiniz. Aşağıdaki modüllerden içerikleri yönetebilirsiniz.</p>

      <div class="stat-grid">
        <div class="stat-card">
          <div class="stat-icon media">
            <i class="fa-solid fa-images"></i>
          </div>
          <div>
            <p class="stat-label">Toplam Medya</p>
            <p class="stat-num">{{ loading ? '—' : mediaCount }}</p>
          </div>
        </div>

        <div class="stat-card">
          <div class="stat-icon i18n">
            <i class="fa-solid fa-language"></i>
          </div>
          <div>
            <p class="stat-label">i18n Mesaj</p>
            <p class="stat-num">{{ loading ? '—' : i18nCount }}</p>
          </div>
        </div>
      </div>

      <div class="quick-links">
        <h3>Hızlı Erişim</h3>
        <div class="link-grid">
          <router-link to="/admin/media" class="quick-card">
            <i class="fa-solid fa-images"></i>
            <span>Medya Yönetimi</span>
            <small>Fotoğraf ekle, sil, kategorile</small>
          </router-link>
          <router-link to="/admin/i18n" class="quick-card">
            <i class="fa-solid fa-language"></i>
            <span>Metin Yönetimi</span>
            <small>TR / EN içerikleri düzenle</small>
          </router-link>
          <router-link to="/admin/contact" class="quick-card">
            <i class="fa-solid fa-address-book"></i>
            <span>İletişim Bilgileri</span>
            <small>Adres, telefon, sosyal medya</small>
          </router-link>
        </div>
      </div>
    </div>
  </AdminLayout>
</template>

<style scoped>
.dashboard { max-width: 1000px; }

.page-title {
  font-size: 26px;
  font-weight: 700;
  color: #1a1a2e;
  margin-bottom: 4px;
}

.page-sub {
  color: #718096;
  font-size: 14px;
  margin-bottom: 36px;
}

.stat-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
  gap: 20px;
  margin-bottom: 48px;
}

.stat-card {
  background: #fff;
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.06);
  display: flex;
  align-items: center;
  gap: 20px;
}

.stat-icon {
  width: 52px;
  height: 52px;
  border-radius: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 22px;
  flex-shrink: 0;
}

.stat-icon.media { background: rgba(201,168,76,0.12); color: #c9a84c; }
.stat-icon.i18n  { background: rgba(66,153,225,0.12); color: #4299e1; }

.stat-label {
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  color: #718096;
  margin-bottom: 4px;
}

.stat-num {
  font-size: 32px;
  font-weight: 800;
  color: #1a1a2e;
  line-height: 1;
}

.quick-links h3 {
  font-size: 18px;
  font-weight: 700;
  color: #1a1a2e;
  margin-bottom: 16px;
}

.link-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(220px, 1fr));
  gap: 16px;
}

.quick-card {
  background: #fff;
  padding: 24px;
  border-radius: 8px;
  box-shadow: 0 2px 8px rgba(0,0,0,0.06);
  display: flex;
  flex-direction: column;
  gap: 6px;
  text-decoration: none;
  color: #1a1a2e;
  border: 2px solid transparent;
  transition: all 0.2s;
}

.quick-card:hover {
  border-color: #c9a84c;
  transform: translateY(-2px);
  box-shadow: 0 6px 20px rgba(0,0,0,0.1);
}

.quick-card i {
  font-size: 24px;
  color: #c9a84c;
  margin-bottom: 4px;
}

.quick-card span {
  font-size: 15px;
  font-weight: 700;
}

.quick-card small {
  font-size: 12px;
  color: #718096;
}
</style>
