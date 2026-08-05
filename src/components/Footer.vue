<script setup>
import { useI18n } from "vue-i18n";
const { t } = useI18n();
</script>

<template>
  <footer class="footer">
    <div class="container">
      <div class="footer-grid">
        <div class="footer-brand">
          <img src="/logo/kdpng.png" alt="KD Dizayn" class="footer-logo" />
          <p>{{ t('footer.tagline') }}</p>
        </div>

        <div class="footer-links">
          <h4>{{ t('footer.links_title') }}</h4>
          <ul>
            <li><a href="#offer">{{ t('nav.services') }}</a></li>
            <li><a href="#about">{{ t('nav.about') }}</a></li>
            <li><a href="#portfolio">{{ t('nav.portfolio') }}</a></li>
            <li><a href="#contact">{{ t('nav.contact') }}</a></li>
          </ul>
        </div>

        <div class="footer-contact">
          <h4>{{ t('footer.contact_title') }}</h4>
          <p><a href="tel:+905356988457">+90 535 698 84 57</a></p>
          <p><a href="mailto:info@kddizayn.com">info@kddizayn.com</a></p>
        </div>

        <div class="footer-social">
          <h4>{{ t('footer.social_title') }}</h4>
          <div class="social-links">
            <a href="https://www.instagram.com/kddizaynmimarlik/" target="_blank" rel="noopener" aria-label="Instagram">
              <i class="fa-brands fa-instagram"></i>
            </a>
            <a href="https://www.linkedin.com/company/kddi%CC%87zaynmi%CC%87marlik/" target="_blank" rel="noopener" aria-label="LinkedIn">
              <i class="fa-brands fa-linkedin"></i>
            </a>
          </div>
        </div>
      </div>

      <div class="footer-bottom">
        <p>© {{ new Date().getFullYear() }} KD Dizayn. {{ t('footer.rights') }}</p>
      </div>
    </div>
  </footer>
</template>

<style scoped>
.footer {
  background: var(--primary);
  color: rgba(255, 255, 255, 0.7);
  padding: 80px 0 0;
}

.container {
  max-width: 1200px;
  margin: 0 auto;
  padding: 0 40px;
}

.footer-grid {
  display: grid;
  grid-template-columns: 2fr 1fr 1fr 1fr;
  gap: 60px;
  padding-bottom: 60px;
  border-bottom: 1px solid rgba(255, 255, 255, 0.08);
}

.footer-logo {
  height: 40px;
  width: auto;
  filter: brightness(0) invert(1);
  margin-bottom: 20px;
  display: block;
}

.footer-brand p {
  font-size: 14px;
  line-height: 1.7;
  max-width: 260px;
}

.footer-links h4,
.footer-contact h4,
.footer-social h4 {
  font-size: 11px;
  font-weight: 700;
  letter-spacing: 0.18em;
  text-transform: uppercase;
  color: white;
  margin-bottom: 20px;
}

.footer-links ul {
  list-style: none;
  display: flex;
  flex-direction: column;
  gap: 12px;
}

.footer-links a,
.footer-contact a {
  color: rgba(255, 255, 255, 0.6);
  text-decoration: none;
  font-size: 14px;
  transition: color 0.2s;
}

.footer-links a:hover,
.footer-contact a:hover {
  color: var(--accent);
}

.footer-contact p {
  font-size: 14px;
  margin-bottom: 10px;
}

.social-links {
  display: flex;
  gap: 16px;
}

.social-links a {
  width: 40px;
  height: 40px;
  display: flex;
  align-items: center;
  justify-content: center;
  border: 1px solid rgba(255, 255, 255, 0.15);
  color: rgba(255, 255, 255, 0.65);
  font-size: 16px;
  text-decoration: none;
  transition: var(--transition);
}

.social-links a:hover {
  background: var(--accent);
  border-color: var(--accent);
  color: var(--primary);
}

.footer-bottom {
  padding: 24px 0;
  text-align: center;
}

.footer-bottom p {
  font-size: 13px;
  color: rgba(255, 255, 255, 0.35);
}

@media (max-width: 900px) {
  .footer { padding: 60px 0 0; }
  .footer-grid { grid-template-columns: 1fr 1fr; gap: 36px; padding-bottom: 48px; }
  .footer-brand { grid-column: 1 / -1; }
}

@media (max-width: 520px) {
  .container { padding: 0 20px; }
  .footer-grid { grid-template-columns: 1fr; gap: 32px; padding-bottom: 40px; }
  .footer-brand { grid-column: 1; }
  .footer-brand p { max-width: 100%; }
  .footer-links h4, .footer-contact h4, .footer-social h4 { margin-bottom: 14px; }
}
</style>
