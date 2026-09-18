<script setup>
import { RouterView, useRoute } from "vue-router";
import { ref, onMounted, onUnmounted, computed } from "vue";
import { useI18n } from "vue-i18n";
import { getAllMedia } from '@/api/media';

const { t, locale } = useI18n();
const route = useRoute();

// Navbar'ı admin sayfalarında gösterme
const isAdminRoute = computed(() => route.path.startsWith('/admin'))

const scrolled  = ref(false);
const menuOpen  = ref(false);
const logoUrl = ref('/logo/kdpng.png');

function handleScroll() {
  scrolled.value = window.scrollY > 60;
}

function toggleMenu() {
  menuOpen.value = !menuOpen.value;
  document.body.style.overflow = menuOpen.value ? "hidden" : "";
}

function closeMenu() {
  menuOpen.value = false;
  document.body.style.overflow = "";
}

function handleKeydown(event) {
  if (event.key === 'Escape' && menuOpen.value) closeMenu()
}

function toggleLang() {
  locale.value = locale.value === "tr" ? "en" : "tr";
  localStorage.setItem('site_locale', locale.value)
  document.documentElement.lang = locale.value
}

onMounted(() => {
  document.documentElement.lang = locale.value
  window.addEventListener("scroll", handleScroll)
  window.addEventListener("keydown", handleKeydown)
  getAllMedia().then(({ data }) => {
    const logo = data.find((item) => item.category === 'LOGO')
    if (logo?.fileUrl) logoUrl.value = logo.fileUrl
  }).catch(() => {})
})
onUnmounted(() => {
  window.removeEventListener("scroll", handleScroll)
  window.removeEventListener("keydown", handleKeydown)
  document.body.style.overflow = ''
})
</script>

<template>
  <!-- Navbar — public site only -->
  <header v-if="!isAdminRoute" :class="['navbar', { scrolled: scrolled }]">
    <div class="navbar-inner">
      <a href="#welcome" class="navbar-logo">
        <img :src="logoUrl" alt="KD Dizayn" />
      </a>

      <nav id="site-navigation" class="navbar-links" :class="{ open: menuOpen }" :aria-hidden="!menuOpen">
        <!-- Close button -->
        <button class="drawer-close" @click="closeMenu" aria-label="Menüyü kapat" type="button">
          <span></span>
          <span></span>
        </button>

        <div class="drawer-links">
          <a href="#offer" @click="closeMenu">{{ t('nav.services') }}</a>
          <a href="#about" @click="closeMenu">{{ t('nav.about') }}</a>
          <a href="#portfolio" @click="closeMenu">{{ t('nav.portfolio') }}</a>
          <a href="#faq" @click="closeMenu">{{ t('nav.faq') }}</a>
          <a href="#contact" @click="closeMenu">{{ t('nav.contact') }}</a>
        </div>

        <!-- Lang toggle inside mobile menu -->
        <button class="lang-toggle mobile-lang" @click="toggleLang" type="button" :aria-label="locale === 'tr' ? 'Switch to English' : 'Türkçeye geç'">
          <span :class="{ active: locale === 'tr' }">TR</span>
          <span class="lang-sep">/</span>
          <span :class="{ active: locale === 'en' }">EN</span>
        </button>
      </nav>

      <!-- Overlay backdrop -->
      <div v-if="menuOpen" class="drawer-backdrop" @click="closeMenu"></div>

      <div class="navbar-right">
        <!-- Lang toggle desktop -->
        <button class="lang-toggle desktop-lang" @click="toggleLang" type="button" :aria-label="locale === 'tr' ? 'Switch to English' : 'Türkçeye geç'">
          <span :class="{ active: locale === 'tr' }">TR</span>
          <span class="lang-sep">/</span>
          <span :class="{ active: locale === 'en' }">EN</span>
        </button>

        <button class="hamburger" @click="toggleMenu" :class="{ active: menuOpen }" :aria-expanded="menuOpen" aria-controls="site-navigation" aria-label="Menü" type="button">
          <span></span>
          <span></span>
          <span></span>
        </button>
      </div>
    </div>
  </header>

  <RouterView />

  <!-- WhatsApp FAB — public site only -->
  <a
    v-if="!isAdminRoute"
    href="https://api.whatsapp.com/send?phone=905356988457"
    class="whatsapp-fab"
    target="_blank"
    rel="noopener"
    aria-label="WhatsApp ile iletişime geç"
  >
    <svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 448 512" fill="white" width="28" height="28">
      <path d="M380.9 97.1C339 55.1 283.2 32 223.9 32c-122.4 0-222 99.6-222 222 0 39.1 10.2 77.3 29.6 111L0 480l117.7-30.9c32.4 17.7 68.9 27 106.1 27h.1c122.3 0 224.1-99.6 224.1-222 0-59.3-25.2-115-67.1-157zm-157 341.6c-33.2 0-65.7-8.9-94-25.7l-6.7-4-69.8 18.3L72 359.2l-4.4-7c-18.5-29.4-28.2-63.3-28.2-98.2 0-101.7 82.8-184.5 184.6-184.5 49.3 0 95.6 19.2 130.4 54.1 34.8 34.9 56.2 81.2 56.1 130.5 0 101.8-84.9 184.6-186.6 184.6zm101.2-138.2c-5.5-2.8-32.8-16.2-37.9-18-5.1-1.9-8.8-2.8-12.5 2.8-3.7 5.6-14.3 18-17.6 21.8-3.2 3.7-6.5 4.2-12 1.4-32.6-16.3-54-29.1-75.5-66-5.7-9.8 5.7-9.1 16.3-30.3 1.8-3.7.9-6.9-.5-9.7-1.4-2.8-12.5-30.1-17.1-41.2-4.5-10.8-9.1-9.3-12.5-9.5-3.2-.2-6.9-.2-10.6-.2-3.7 0-9.7 1.4-14.8 6.9-5.1 5.6-19.4 19-19.4 46.3 0 27.3 19.9 53.7 22.6 57.4 2.8 3.7 39.1 59.7 94.8 83.8 35.2 15.2 49 16.5 66.6 13.9 10.7-1.6 32.8-13.4 37.4-26.4 4.6-13 4.6-24.1 3.2-26.4-1.3-2.5-5-3.9-10.5-6.6z"/>
    </svg>
  </a>
</template>

<style>
@import "@/assets/base.css";

/* ── Navbar ── */
.navbar {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  z-index: 9999;
  transition: var(--transition);
  padding: 0;
}

.navbar.scrolled {
  background: rgba(26, 26, 46, 0.92);
  backdrop-filter: blur(16px);
  -webkit-backdrop-filter: blur(16px);
  box-shadow: 0 2px 24px rgba(0, 0, 0, 0.25);
}

.navbar-inner {
  max-width: 1280px;
  margin: 0 auto;
  padding: 20px 40px;
  display: flex;
  align-items: center;
  justify-content: space-between;
}

.navbar-logo img {
  height: 44px;
  width: auto;
  display: block;
  filter: brightness(0) invert(1);
}

.navbar-links {
  display: flex;
  align-items: center;
}

.drawer-close {
  display: none;
}

.drawer-links {
  display: flex;
  align-items: center;
  gap: 8px;
  padding: 6px;
  border: 1px solid rgba(255, 255, 255, 0.12);
  border-radius: 999px;
  background: rgba(255, 255, 255, 0.05);
}

.navbar-links a {
  display: inline-flex;
  align-items: center;
  color: rgba(255, 255, 255, 0.85);
  text-decoration: none;
  font-size: 14px;
  font-weight: 500;
  letter-spacing: 0.06em;
  text-transform: uppercase;
  padding: 10px 15px;
  border-radius: 999px;
  transition: var(--transition);
  position: relative;
}

.navbar-links a::after {
  content: "";
  position: absolute;
  bottom: -4px;
  left: 0;
  width: 0;
  height: 1.5px;
  background: var(--accent);
  transition: var(--transition);
}

.navbar-links a:hover {
  color: var(--accent);
  background: rgba(255, 255, 255, 0.08);
}

.navbar-links a:hover::after {
  width: 100%;
}

/* Right group: lang + hamburger */
.navbar-right {
  display: flex;
  align-items: center;
  gap: 16px;
}

/* Lang toggle */
.lang-toggle {
  background: none;
  border: 1px solid rgba(255, 255, 255, 0.3);
  border-radius: 2px;
  color: rgba(255, 255, 255, 0.7);
  font-size: 12px;
  font-weight: 600;
  letter-spacing: 0.08em;
  cursor: pointer;
  padding: 5px 10px;
  display: flex;
  align-items: center;
  gap: 4px;
  transition: var(--transition);
  font-family: inherit;
}

.lang-toggle:hover {
  border-color: var(--accent);
  color: var(--accent);
}

.lang-toggle span.active {
  color: var(--accent);
}

.lang-sep {
  opacity: 0.4;
}

/* Desktop lang visible, mobile hidden by default */
.mobile-lang {
  display: none;
}

/* Hamburger */
.hamburger {
  display: none;
  flex-direction: column;
  gap: 5px;
  background: none;
  border: none;
  cursor: pointer;
  padding: 4px;
}

.hamburger span {
  display: block;
  width: 24px;
  height: 2px;
  background: white;
  transition: var(--transition);
  transform-origin: center;
}

.hamburger.active span:nth-child(1) {
  transform: translateY(7px) rotate(45deg);
}

.hamburger.active span:nth-child(2) {
  opacity: 0;
}

.hamburger.active span:nth-child(3) {
  transform: translateY(-7px) rotate(-45deg);
}

/* WhatsApp FAB */
.whatsapp-fab {
  position: fixed;
  bottom: 36px;
  right: 36px;
  z-index: 9998;
  width: 56px;
  height: 56px;
  background: #25d366;
  border-radius: 50%;
  display: flex;
  align-items: center;
  justify-content: center;
  box-shadow: 0 4px 20px rgba(37, 211, 102, 0.45);
  transition: var(--transition);
}

.whatsapp-fab:hover {
  transform: translateY(-3px) scale(1.05);
  box-shadow: 0 8px 28px rgba(37, 211, 102, 0.55);
}

/* ── Mobile ── */
@media (max-width: 768px) {
  .hamburger {
    display: flex;
  }

  .desktop-lang {
    display: none;
  }

  .navbar-inner {
    padding: 16px 20px;
  }

  .navbar-links {
    position: fixed;
    top: 0;
    right: -100%;
    width: 100%;
    height: 100vh;
    height: 100dvh;
    background: var(--primary);
    flex-direction: column;
    justify-content: center;
    align-items: center;
    gap: 0;
    transition: right 0.4s cubic-bezier(0.4, 0, 0.2, 1);
    padding: 0;
    z-index: 10000;
  }

  .navbar-links.open {
    right: 0;
  }

  .drawer-close {
    position: absolute;
    top: 24px;
    right: 24px;
    width: 44px;
    height: 44px;
    background: rgba(255, 255, 255, 0.08);
    border: 1px solid rgba(255, 255, 255, 0.15);
    border-radius: 50%;
    cursor: pointer;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    gap: 0;
    transition: var(--transition);
    padding: 0;
  }

  .drawer-close:hover {
    background: rgba(255, 255, 255, 0.15);
    border-color: var(--accent);
  }

  .drawer-close span {
    display: block;
    width: 18px;
    height: 2px;
    background: rgba(255, 255, 255, 0.85);
    position: absolute;
    transition: var(--transition);
  }

  .drawer-close span:nth-child(1) {
    transform: rotate(45deg);
  }

  .drawer-close span:nth-child(2) {
    transform: rotate(-45deg);
  }

  .drawer-links {
    display: flex;
    flex-direction: column;
    align-items: center;
    gap: 8px;
    width: 100%;
    padding: 0;
    border: 0;
    border-radius: 0;
    background: none;
  }

  .drawer-links a {
    display: block;
    width: 100%;
    text-align: center;
    padding: 18px 40px;
    font-size: 22px;
    font-weight: 600;
    letter-spacing: 0.1em;
    text-transform: uppercase;
    color: rgba(255, 255, 255, 0.75);
    text-decoration: none;
    transition: var(--transition);
    border-bottom: 1px solid rgba(255, 255, 255, 0.06);
  }

  .drawer-links a:first-child {
    border-top: 1px solid rgba(255, 255, 255, 0.06);
  }

  .drawer-links a:hover {
    color: var(--accent);
    background: rgba(255, 255, 255, 0.04);
    padding-left: 56px;
  }

  /* override the ::after underline for full-width links */
  .navbar-links a::after {
    display: none;
  }

  .mobile-lang {
    display: flex;
    font-size: 14px;
    padding: 8px 20px;
    border-color: rgba(255, 255, 255, 0.2);
    margin-top: 32px;
    width: fit-content;
  }

  .whatsapp-fab {
    bottom: 20px;
    right: 20px;
    width: 48px;
    height: 48px;
  }
}

/* Backdrop overlay */
.drawer-backdrop {
  display: none;
}

@media (max-width: 768px) {
  .drawer-backdrop {
    display: block;
    position: fixed;
    inset: 0;
    background: rgba(0, 0, 0, 0.6);
    z-index: 9999;
    backdrop-filter: blur(2px);
    -webkit-backdrop-filter: blur(2px);
  }
}

/* Remove old pseudo-element overlay */
.navbar-links.open::before {
  display: none;
}
</style>
