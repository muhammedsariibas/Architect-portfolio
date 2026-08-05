<script setup>
import { useI18n } from "vue-i18n";
const { t } = useI18n();
</script>

<template>
  <div class="portfolio-section">
    <div class="container">
      <div class="section-header" data-aos="fade-up" data-aos-duration="700">
        <span class="section-eyebrow">{{ t('portfolio.eyebrow') }}</span>
        <h2 class="section-title">{{ t('portfolio.title') }}</h2>
      </div>

      <div class="portfolio-grid">
        <div
          v-for="(project, i) in projects"
          :key="project.key"
          class="portfolio-card"
          data-aos="fade-up"
          :data-aos-delay="i * 100"
          @click="openGallery(project.items)"
        >
          <div class="card-img-wrap">
            <img :src="project.cover" :alt="project.title" />
            <div class="card-overlay">
              <i class="fa-solid fa-magnifying-glass-plus"></i>
            </div>
          </div>
          <div class="card-info">
            <h3>{{ project.title }}</h3>
            <span class="card-tag">{{ t(project.tagKey) }}</span>
          </div>
        </div>
      </div>
    </div>

    <!-- Lightbox -->
    <transition name="fade">
      <div
        v-if="dialog"
        class="lightbox"
        @click.self="dialog = false"
        @touchstart="onTouchStart"
        @touchend="onTouchEnd"
      >
        <button class="lb-close" @click="dialog = false" aria-label="Kapat">
          <i class="fa fa-close"></i>
        </button>
        <button class="lb-arrow lb-prev" @click="prev" aria-label="Önceki">
          <i class="fa-solid fa-arrow-left"></i>
        </button>
        <div class="lb-img-wrap">
          <img :src="activeItems[activeIndex].img" :alt="activeItems[activeIndex].header" />
          <p class="lb-caption">{{ activeItems[activeIndex].header }} — {{ activeIndex + 1 }} / {{ activeItems.length }}</p>
        </div>
        <button class="lb-arrow lb-next" @click="next" aria-label="Sonraki">
          <i class="fa-solid fa-arrow-right"></i>
        </button>
      </div>
    </transition>
  </div>
</template>

<script>
import AOS from "aos";

export default {
  mounted() {
    AOS.init();
    window.addEventListener("keydown", this.handleKey);
  },
  beforeUnmount() {
    window.removeEventListener("keydown", this.handleKey);
  },
  data() {
    return {
      dialog: false,
      activeItems: [],
      activeIndex: 0,
      touchStartX: 0,
      projects: [
        {
          key: "bursa",
          title: "Bursa Fibabank",
          tagKey: "portfolio.tag_interior",
          cover: "/bursa-fibabank/IMG_4537.PNG",
          items: [
            { header: "Bursa Fibabank", img: "/bursa-fibabank/IMG_4537.PNG" },
            { header: "Bursa Fibabank", img: "/bursa-fibabank/IMG_4538.PNG" },
            { header: "Bursa Fibabank", img: "/bursa-fibabank/IMG_4539.PNG" },
            { header: "Bursa Fibabank", img: "/bursa-fibabank/IMG_4540.PNG" },
            { header: "Bursa Fibabank", img: "/bursa-fibabank/IMG_4541.PNG" },
            { header: "Bursa Fibabank", img: "/bursa-fibabank/IMG_4542.PNG" },
          ],
        },
        {
          key: "kayseri",
          title: "DHL Kayseri",
          tagKey: "portfolio.tag_office",
          cover: "/dhl-kayseri/IMG_4545.PNG",
          items: [
            { header: "DHL Kayseri", img: "/dhl-kayseri/IMG_4543.PNG" },
            { header: "DHL Kayseri", img: "/dhl-kayseri/IMG_4544.PNG" },
            { header: "DHL Kayseri", img: "/dhl-kayseri/IMG_4545.PNG" },
            { header: "DHL Kayseri", img: "/dhl-kayseri/IMG_4546.PNG" },
            { header: "DHL Kayseri", img: "/dhl-kayseri/IMG_4547.PNG" },
            { header: "DHL Kayseri", img: "/dhl-kayseri/IMG_4548.PNG" },
          ],
        },
        {
          key: "denizli",
          title: "DHL Denizli",
          tagKey: "portfolio.tag_office",
          cover: "/dhl-denizli/d1.jpeg",
          items: [
            { header: "DHL Denizli", img: "/dhl-denizli/d1.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d2.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d3.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d4.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d5.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d6.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d7.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d8.jpeg" },
            { header: "DHL Denizli", img: "/dhl-denizli/d9.jpeg" },
          ],
        },
        {
          key: "izmir",
          title: "DHL İzmir",
          tagKey: "portfolio.tag_office",
          cover: "/dhl-izmir/i6.jpeg",
          items: [
            { header: "DHL İzmir", img: "/dhl-izmir/i1.jpeg" },
            { header: "DHL İzmir", img: "/dhl-izmir/i2.jpeg" },
            { header: "DHL İzmir", img: "/dhl-izmir/i3.jpeg" },
            { header: "DHL İzmir", img: "/dhl-izmir/i4.jpeg" },
            { header: "DHL İzmir", img: "/dhl-izmir/i5.jpeg" },
            { header: "DHL İzmir", img: "/dhl-izmir/i6.jpeg" },
          ],
        },
      ],
    };
  },
  methods: {
    openGallery(items) {
      this.activeItems = items;
      this.activeIndex = 0;
      this.dialog = true;
    },
    next() {
      this.activeIndex = (this.activeIndex + 1) % this.activeItems.length;
    },
    prev() {
      this.activeIndex = (this.activeIndex - 1 + this.activeItems.length) % this.activeItems.length;
    },
    handleKey(e) {
      if (!this.dialog) return;
      if (e.key === "ArrowRight") this.next();
      if (e.key === "ArrowLeft") this.prev();
      if (e.key === "Escape") this.dialog = false;
    },
    onTouchStart(e) {
      this.touchStartX = e.changedTouches[0].clientX;
    },
    onTouchEnd(e) {
      const diff = this.touchStartX - e.changedTouches[0].clientX;
      if (Math.abs(diff) > 40) {
        diff > 0 ? this.next() : this.prev();
      }
    },
  },
};
</script>

<style scoped>
.portfolio-section {
  padding: 140px 0;
  background: var(--bg-section);
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 40px;
}

.section-header {
  margin-bottom: 80px;
}

.section-eyebrow {
  display: inline-block;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: var(--accent);
  margin-bottom: 12px;
}

.section-title {
  font-size: clamp(36px, 5vw, 52px);
  font-weight: 700;
  color: var(--text-dark);
  letter-spacing: -0.02em;
}

.portfolio-grid {
  display: grid;
  grid-template-columns: repeat(2, 1fr);
  gap: 32px;
}

.portfolio-card {
  cursor: pointer;
  background: var(--bg-white);
  overflow: hidden;
  box-shadow: var(--shadow-sm);
  transition: var(--transition);
}

.portfolio-card:hover {
  transform: translateY(-6px);
  box-shadow: var(--shadow-lg);
}

.portfolio-card:first-child {
  grid-column: 1 / -1;
}

.card-img-wrap {
  position: relative;
  overflow: hidden;
  height: 320px;
}

.portfolio-card:first-child .card-img-wrap {
  height: 500px;
}

.card-img-wrap img {
  width: 100%;
  height: 100%;
  object-fit: cover;
  display: block;
  transition: transform 0.6s cubic-bezier(0.4, 0, 0.2, 1);
  filter: brightness(0.88);
}

.portfolio-card:hover .card-img-wrap img {
  transform: scale(1.05);
  filter: brightness(0.7);
}

.card-overlay {
  position: absolute;
  inset: 0;
  display: flex;
  align-items: center;
  justify-content: center;
  opacity: 0;
  transition: var(--transition);
  font-size: 32px;
  color: white;
}

.portfolio-card:hover .card-overlay {
  opacity: 1;
}

.card-info {
  padding: 20px 24px;
  display: flex;
  align-items: center;
  justify-content: space-between;
  flex-wrap: wrap;
  gap: 8px;
}

.card-info h3 {
  font-size: 18px;
  font-weight: 700;
  color: var(--text-dark);
  letter-spacing: -0.01em;
}

.card-tag {
  font-size: 11px;
  font-weight: 600;
  letter-spacing: 0.12em;
  text-transform: uppercase;
  color: var(--accent);
  background: rgba(201, 168, 76, 0.1);
  padding: 4px 10px;
  border-radius: 2px;
}

/* Lightbox */
.lightbox {
  position: fixed;
  inset: 0;
  z-index: 99999;
  background: rgba(0, 0, 0, 0.93);
  display: flex;
  align-items: center;
  justify-content: center;
}

.lb-img-wrap {
  max-width: 90vw;
  max-height: 85vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 16px;
}

.lb-img-wrap img {
  max-width: 100%;
  max-height: 80vh;
  object-fit: contain;
  display: block;
}

.lb-caption {
  color: rgba(255, 255, 255, 0.6);
  font-size: 13px;
  letter-spacing: 0.05em;
}

.lb-close {
  position: absolute;
  top: 24px;
  right: 28px;
  background: none;
  border: none;
  color: rgba(255, 255, 255, 0.7);
  font-size: 24px;
  cursor: pointer;
  transition: color 0.2s;
  line-height: 1;
}

.lb-close:hover { color: white; }

.lb-arrow {
  background: rgba(255, 255, 255, 0.1);
  border: 1px solid rgba(255, 255, 255, 0.2);
  color: white;
  width: 52px;
  height: 52px;
  display: flex;
  align-items: center;
  justify-content: center;
  cursor: pointer;
  font-size: 18px;
  transition: var(--transition);
  flex-shrink: 0;
}

.lb-arrow:hover {
  background: var(--accent);
  border-color: var(--accent);
  color: var(--primary);
}

.lb-prev { margin-right: 20px; }
.lb-next { margin-left: 20px; }

.fade-enter-active, .fade-leave-active { transition: opacity 0.3s; }
.fade-enter-from, .fade-leave-to { opacity: 0; }

@media (max-width: 768px) {
  .portfolio-section { padding: 72px 0; }
  .container { padding: 0 16px; }
  .section-header { margin-bottom: 48px; }
  .portfolio-grid { grid-template-columns: 1fr; gap: 20px; }
  .portfolio-card:first-child { grid-column: 1; }
  .portfolio-card:first-child .card-img-wrap { height: 240px; }
  .card-img-wrap { height: 220px; }
  .card-info { padding: 14px 16px; }
  .card-info h3 { font-size: 16px; }

  .lightbox { flex-direction: column; justify-content: center; padding: 60px 0 20px; }
  .lb-img-wrap { max-width: 100vw; max-height: 70vh; padding: 0 16px; }
  .lb-img-wrap img { max-height: 65vh; }

  .lb-arrow {
    position: absolute;
    bottom: 32px;
    width: 48px;
    height: 48px;
    font-size: 16px;
  }

  .lb-prev { left: calc(50% - 56px); margin: 0; }
  .lb-next { left: calc(50% + 8px); margin: 0; }
  .lb-caption { margin-top: 12px; font-size: 12px; }
}
</style>
