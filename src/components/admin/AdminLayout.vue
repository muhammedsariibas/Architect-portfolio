<script setup>
import { ref } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const router = useRouter()
const route  = useRoute()
const auth   = useAuthStore()

const sidebarOpen = ref(true)

const navItems = [
  { label: 'Dashboard',  icon: 'fa-solid fa-gauge',      to: '/admin' },
  { label: 'Medya',      icon: 'fa-solid fa-images',     to: '/admin/media' },
  { label: 'Projeler',   icon: 'fa-solid fa-building',   to: '/admin/projects' },
  { label: 'Metinler',   icon: 'fa-solid fa-language',   to: '/admin/i18n' },
  { label: 'İletişim',   icon: 'fa-solid fa-address-book', to: '/admin/contact' },
  { label: 'Kullanıcılar', icon: 'fa-solid fa-users',      to: '/admin/users' },
]

function logout() {
  auth.logout()
  router.push('/admin/login')
}
</script>

<template>
  <div class="admin-shell">
    <!-- Sidebar -->
    <aside :class="['sidebar', { collapsed: !sidebarOpen }]">
      <div class="sidebar-logo">
        <img src="/logo/kdpng.png" alt="KD Dizayn" />
        <span v-if="sidebarOpen">Admin</span>
      </div>

      <nav class="sidebar-nav">
        <router-link
          v-for="item in navItems"
          :key="item.to"
          :to="item.to"
          class="nav-item"
          :class="{ active: route.path === item.to }"
          :title="item.label"
        >
          <i :class="item.icon"></i>
          <span v-if="sidebarOpen">{{ item.label }}</span>
        </router-link>
      </nav>

      <div v-if="sidebarOpen" class="mobile-sidebar-backdrop" @click="sidebarOpen = false"></div>

      <button class="sidebar-logout" @click="logout" title="Çıkış">
        <i class="fa-solid fa-right-from-bracket"></i>
        <span v-if="sidebarOpen">Çıkış</span>
      </button>
    </aside>

    <!-- Main -->
    <div class="admin-main">
      <header class="admin-topbar">
        <button class="toggle-btn" @click="sidebarOpen = !sidebarOpen" aria-label="Toggle sidebar">
          <i class="fa-solid fa-bars"></i>
        </button>
        <span class="topbar-title">KD Dizayn — Admin Panel</span>
      </header>

      <div class="admin-content">
        <slot />
      </div>
    </div>
  </div>
</template>

<style scoped>
.admin-shell {
  display: flex;
  min-height: 100vh;
  background: #f4f5f7;
  font-family: 'Inter', sans-serif;
}

/* ── Sidebar ── */
.sidebar {
  width: 240px;
  background: #1a1a2e;
  display: flex;
  flex-direction: column;
  transition: width 0.3s ease;
  flex-shrink: 0;
  position: sticky;
  top: 0;
  height: 100vh;
  overflow: hidden;
}

.sidebar.collapsed {
  width: 64px;
}

.sidebar-logo {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 24px 16px;
  border-bottom: 1px solid rgba(255,255,255,0.08);
  white-space: nowrap;
  overflow: hidden;
}

.sidebar-logo img {
  height: 32px;
  width: auto;
  filter: brightness(0) invert(1);
  flex-shrink: 0;
}

.sidebar-logo span {
  color: #c9a84c;
  font-size: 14px;
  font-weight: 700;
  letter-spacing: 0.1em;
  text-transform: uppercase;
}

.sidebar-nav {
  flex: 1;
  padding: 16px 8px;
  display: flex;
  flex-direction: column;
  gap: 4px;
}

.nav-item {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 11px 12px;
  border-radius: 6px;
  color: rgba(255,255,255,0.6);
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  white-space: nowrap;
  overflow: hidden;
  transition: all 0.2s;
}

.nav-item i {
  width: 18px;
  text-align: center;
  flex-shrink: 0;
  font-size: 15px;
}

.nav-item:hover {
  background: rgba(255,255,255,0.07);
  color: #fff;
}

.nav-item.active {
  background: rgba(201,168,76,0.15);
  color: #c9a84c;
  border-left: 3px solid #c9a84c;
}

.sidebar-logout {
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 14px 20px;
  background: none;
  border: none;
  border-top: 1px solid rgba(255,255,255,0.08);
  color: rgba(255,255,255,0.5);
  cursor: pointer;
  font-size: 14px;
  white-space: nowrap;
  overflow: hidden;
  transition: color 0.2s;
  width: 100%;
  text-align: left;
  font-family: inherit;
}

.sidebar-logout:hover {
  color: #e74c3c;
}

.sidebar-logout i {
  width: 18px;
  text-align: center;
  flex-shrink: 0;
}

/* ── Main ── */
.admin-main {
  flex: 1;
  display: flex;
  flex-direction: column;
  min-width: 0;
}

.admin-topbar {
  background: #fff;
  padding: 0 24px;
  height: 60px;
  display: flex;
  align-items: center;
  gap: 16px;
  box-shadow: 0 1px 4px rgba(0,0,0,0.08);
  position: sticky;
  top: 0;
  z-index: 10;
}

.toggle-btn {
  background: none;
  border: none;
  font-size: 18px;
  cursor: pointer;
  color: #4a5568;
  padding: 6px;
  border-radius: 4px;
  transition: background 0.2s;
}

.toggle-btn:hover {
  background: #f0f0f5;
}

.topbar-title {
  font-size: 15px;
  font-weight: 600;
  color: #1a1a2e;
}

.admin-content {
  padding: 32px;
  flex: 1;
}

.mobile-sidebar-backdrop { display: none; }

@media (max-width: 768px) {
  .sidebar {
    position: fixed;
    z-index: 100;
    height: 100vh;
  }

  .admin-content {
    padding: 20px 16px;
  }

  .sidebar { transform: translateX(0); transition: transform .25s ease; }
  .sidebar.collapsed { width: 240px; transform: translateX(-100%); }
  .mobile-sidebar-backdrop { display: block; position: fixed; inset: 0; background: rgba(0,0,0,.35); z-index: -1; }
}
</style>
