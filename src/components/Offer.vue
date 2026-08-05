<script setup>
import { useI18n } from "vue-i18n";
const { t } = useI18n();
</script>

<template>
  <div class="offer" data-aos="fade-up" data-aos-duration="700">
    <div class="container">
      <div class="section-header">
        <span class="section-eyebrow">{{ t('services.eyebrow') }}</span>
        <h2 class="section-title">{{ t('services.title') }}</h2>
      </div>

      <div class="offer-grid">
        <div class="offer-card" data-aos="fade-up" data-aos-delay="100">
          <div class="card-icon">
            <i class="fa-solid fa-handshake"></i>
          </div>
          <h3>{{ t('services.card1_title') }}</h3>
          <p>{{ t('services.card1_desc') }}</p>
        </div>

        <div class="offer-card" data-aos="fade-up" data-aos-delay="200">
          <div class="card-icon">
            <i class="fa-solid fa-lightbulb"></i>
          </div>
          <h3>{{ t('services.card2_title') }}</h3>
          <p>{{ t('services.card2_desc') }}</p>
        </div>

        <div class="offer-card" data-aos="fade-up" data-aos-delay="300">
          <div class="card-icon">
            <i class="fa-solid fa-hammer"></i>
          </div>
          <h3>{{ t('services.card3_title') }}</h3>
          <p>{{ t('services.card3_desc') }}</p>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.offer {
  background: var(--bg-section);
  padding: 120px 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 40px;
}

.section-header {
  text-align: center;
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

.offer-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 1fr));
  gap: 40px;
}

.offer-card {
  background: var(--bg-white);
  padding: 48px 36px;
  text-align: center;
  transition: var(--transition);
  box-shadow: var(--shadow-sm);
  border-top: 3px solid transparent;
}

.offer-card:hover {
  transform: translateY(-8px);
  box-shadow: var(--shadow-md);
  border-top-color: var(--accent);
}

.card-icon {
  width: 80px;
  height: 80px;
  margin: 0 auto 28px;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, var(--accent-light), var(--accent));
  color: var(--primary);
  font-size: 36px;
  transition: var(--transition);
}

.offer-card:hover .card-icon {
  transform: scale(1.1) rotate(-5deg);
}

.offer-card h3 {
  font-size: 22px;
  font-weight: 700;
  color: var(--text-dark);
  margin-bottom: 16px;
  letter-spacing: -0.01em;
}

.offer-card p {
  font-size: 15px;
  line-height: 1.7;
  color: var(--text-mid);
  font-weight: 400;
}

@media (max-width: 768px) {
  .offer { padding: 72px 0; }
  .container { padding: 0 20px; }
  .section-header { margin-bottom: 48px; }
  .offer-grid { grid-template-columns: 1fr; gap: 24px; }
  .offer-card { padding: 36px 24px; }
  .card-icon { width: 68px; height: 68px; font-size: 28px; margin-bottom: 20px; }
  .offer-card h3 { font-size: 20px; }
}
</style>
