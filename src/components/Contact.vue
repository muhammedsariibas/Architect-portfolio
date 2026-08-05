<script setup>
import { useI18n } from "vue-i18n";
const { t } = useI18n();
</script>

<template>
  <div class="contact-section" data-aos="fade-up" data-aos-duration="700">
    <div class="container">
      <div class="section-header">
        <span class="section-eyebrow">{{ t('contact.eyebrow') }}</span>
        <h2 class="section-title">{{ t('contact.title') }}</h2>
      </div>

      <div class="contact-grid">
        <div class="contact-info">
          <h3>{{ t('contact.question') }}</h3>
          <p>{{ t('contact.desc') }}</p>

          <div class="info-items">
            <div class="info-item">
              <div class="info-icon">
                <i class="fa-solid fa-location-dot"></i>
              </div>
              <div>
                <strong>{{ t('contact.address_label') }}</strong>
                <p>{{ t('contact.address_value') }}</p>
              </div>
            </div>
            <div class="info-item">
              <div class="info-icon">
                <i class="fa-solid fa-phone"></i>
              </div>
              <div>
                <strong>{{ t('contact.phone_label') }}</strong>
                <p><a href="tel:+905356988457">+90 535 698 84 57</a></p>
              </div>
            </div>
            <div class="info-item">
              <div class="info-icon">
                <i class="fa-solid fa-envelope"></i>
              </div>
              <div>
                <strong>{{ t('contact.email_label') }}</strong>
                <p><a href="mailto:info@kddizayn.com">info@kddizayn.com</a></p>
              </div>
            </div>
          </div>
        </div>

        <div class="map-wrap">
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3010.0082149122422!2d28.812431576221336!3d41.02507621838999!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x14caa5b3eb863a73%3A0xd315a7ae7499525c!2sKD%20Dizayn%20ve%20Mimarl%C4%B1k!5e0!3m2!1str!2str!4v1692175725697!5m2!1str!2str"
            allowfullscreen=""
            loading="lazy"
            referrerpolicy="no-referrer-when-downgrade"
            title="KD Dizayn Konum"
          ></iframe>
        </div>
      </div>
    </div>
  </div>
</template>

<style scoped>
.contact-section {
  padding: 140px 0;
  background: var(--bg-white);
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

.contact-grid {
  display: grid;
  grid-template-columns: 1fr 1.4fr;
  gap: 64px;
  align-items: stretch;
  min-height: 480px;
}

.contact-info h3 {
  font-size: 32px;
  font-weight: 700;
  color: var(--text-dark);
  margin-bottom: 16px;
  letter-spacing: -0.02em;
}

.contact-info > p {
  font-size: 15px;
  color: var(--text-mid);
  margin-bottom: 48px;
  line-height: 1.7;
}

.info-items {
  display: flex;
  flex-direction: column;
  gap: 32px;
}

.info-item {
  display: flex;
  gap: 20px;
  align-items: flex-start;
}

.info-icon {
  width: 44px;
  height: 44px;
  flex-shrink: 0;
  background: rgba(201, 168, 76, 0.12);
  display: flex;
  align-items: center;
  justify-content: center;
  color: var(--accent);
  font-size: 16px;
}

.info-item strong {
  display: block;
  font-size: 12px;
  font-weight: 700;
  letter-spacing: 0.1em;
  text-transform: uppercase;
  color: var(--text-dark);
  margin-bottom: 4px;
}

.info-item p {
  font-size: 14px;
  color: var(--text-mid);
  line-height: 1.6;
}

.info-item a {
  color: var(--text-mid);
  text-decoration: none;
  transition: color 0.2s;
}

.info-item a:hover {
  color: var(--accent);
}

.map-wrap {
  overflow: hidden;
  box-shadow: var(--shadow-md);
  min-height: 480px;
  height: 100%;
}

.map-wrap iframe {
  width: 100%;
  height: 100%;
  min-height: 480px;
  border: 0;
  display: block;
}

@media (max-width: 900px) {
  .contact-section { padding: 72px 0; }
  .container { padding: 0 20px; }
  .section-header { margin-bottom: 48px; }
  .contact-grid { grid-template-columns: 1fr; gap: 40px; min-height: unset; }
  .contact-info h3 { font-size: 26px; }
  .contact-info > p { margin-bottom: 32px; }
  .map-wrap { min-height: 300px; height: 300px; }
  .map-wrap iframe { min-height: 300px; }
}

@media (max-width: 480px) {
  .info-items { gap: 24px; }
  .info-icon { width: 38px; height: 38px; font-size: 14px; }
  .map-wrap { min-height: 260px; height: 260px; }
  .map-wrap iframe { min-height: 260px; }
}
</style>
