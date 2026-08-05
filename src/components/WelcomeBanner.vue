<script setup>
import { useI18n } from "vue-i18n";
const { t } = useI18n();
</script>

<template>
  <div class="banner">
    <div class="overlay">
      <div class="hero-content">
        <p class="hero-eyebrow">{{ t('hero.eyebrow') }}</p>
        <h1 class="hero-title">{{ t('hero.title') }}</h1>
        <p class="hero-subtitle">{{ t('hero.subtitle') }}</p>
        <div class="hero-actions">
          <a href="#portfolio" class="btn-primary">{{ t('hero.cta_projects') }}</a>
          <a href="#contact" class="btn-ghost">{{ t('hero.cta_contact') }}</a>
        </div>
      </div>

      <div class="scroll-indicator">
        <span class="scroll-line"></span>
        <span class="scroll-text">{{ t('hero.scroll') }}</span>
      </div>
    </div>
  </div>
</template>

<style scoped>
.banner {
  position: relative;
  background-image: url("../assets/banner3.jpg");
  background-repeat: no-repeat;
  background-size: cover;
  background-position: center 70%;
  height: 100vh;
  min-height: 600px;
}

.overlay {
  height: 100%;
  background: linear-gradient(
    135deg,
    rgba(10, 10, 26, 0.75) 0%,
    rgba(10, 10, 26, 0.45) 60%,
    rgba(10, 10, 26, 0.2) 100%
  );
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: flex-start;
  padding: 0 10%;
  animation: fadeIn 1.2s ease forwards;
}

@keyframes fadeIn {
  from { opacity: 0; }
  to { opacity: 1; }
}

.hero-content {
  max-width: 680px;
  animation: slideUp 1s cubic-bezier(0.4, 0, 0.2, 1) 0.4s both;
}

@keyframes slideUp {
  from { opacity: 0; transform: translateY(40px); }
  to { opacity: 1; transform: translateY(0); }
}

.hero-eyebrow {
  font-size: 13px;
  font-weight: 600;
  letter-spacing: 0.25em;
  text-transform: uppercase;
  color: var(--accent);
  margin-bottom: 20px;
}

.hero-title {
  font-family: "Playfair Display", Georgia, serif;
  font-size: clamp(56px, 9vw, 110px);
  font-weight: 800;
  color: #ffffff;
  line-height: 1;
  margin-bottom: 20px;
  letter-spacing: -0.02em;
}

.hero-subtitle {
  font-size: clamp(16px, 2vw, 22px);
  color: rgba(255, 255, 255, 0.75);
  font-style: italic;
  font-weight: 300;
  margin-bottom: 48px;
  letter-spacing: 0.02em;
}

.hero-actions {
  display: flex;
  gap: 16px;
  flex-wrap: wrap;
}

.btn-primary {
  display: inline-block;
  padding: 14px 36px;
  background: var(--accent);
  color: var(--primary);
  text-decoration: none;
  font-size: 14px;
  font-weight: 700;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  transition: var(--transition);
  border: 2px solid var(--accent);
}

.btn-primary:hover {
  background: transparent;
  color: var(--accent);
}

.btn-ghost {
  display: inline-block;
  padding: 14px 36px;
  background: transparent;
  color: rgba(255, 255, 255, 0.85);
  text-decoration: none;
  font-size: 14px;
  font-weight: 600;
  letter-spacing: 0.08em;
  text-transform: uppercase;
  border: 2px solid rgba(255, 255, 255, 0.4);
  transition: var(--transition);
}

.btn-ghost:hover {
  border-color: white;
  color: white;
}

.scroll-indicator {
  position: absolute;
  bottom: 48px;
  left: 50%;
  transform: translateX(-50%);
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 8px;
  animation: slideUp 1s cubic-bezier(0.4, 0, 0.2, 1) 1.2s both;
}

.scroll-line {
  display: block;
  width: 1px;
  height: 50px;
  background: linear-gradient(to bottom, transparent, rgba(255, 255, 255, 0.6));
  animation: scrollPulse 2s ease-in-out infinite;
}

@keyframes scrollPulse {
  0%, 100% { opacity: 0.4; transform: scaleY(1); }
  50% { opacity: 1; transform: scaleY(1.15); }
}

.scroll-text {
  font-size: 10px;
  letter-spacing: 0.2em;
  text-transform: uppercase;
  color: rgba(255, 255, 255, 0.5);
  font-weight: 500;
}

@media (max-width: 768px) {
  .overlay {
    padding: 0 6%;
    padding-top: 80px;
    align-items: flex-start;
    justify-content: center;
    text-align: center;
  }

  .hero-content {
    max-width: 100%;
  }

  .hero-eyebrow {
    font-size: 11px;
    margin-bottom: 14px;
  }

  .hero-subtitle {
    margin-bottom: 36px;
  }

  .hero-actions {
    justify-content: center;
    flex-direction: column;
    align-items: center;
  }

  .btn-primary,
  .btn-ghost {
    width: 100%;
    max-width: 280px;
    text-align: center;
    padding: 14px 24px;
    font-size: 13px;
  }

  .scroll-indicator {
    bottom: 24px;
  }
}

@media (max-width: 480px) {
  .banner {
    min-height: 100svh;
  }

  .overlay {
    padding: 0 5%;
    padding-top: 90px;
  }
}
</style>
