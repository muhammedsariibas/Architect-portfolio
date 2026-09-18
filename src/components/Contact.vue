<script setup>
import { ref, onMounted } from "vue";
import { useI18n } from "vue-i18n";
import { getContact, sendContactMessage } from "@/api/contact";

const { t } = useI18n();
const loading = ref(true);
const loadError = ref(false);
const sent = ref(false);
const sending = ref(false);
const sendError = ref(false);
const form = ref({ name: '', email: '', message: '' });
const contact = ref({
  phone: '+90 535 698 84 57',
  email: 'info@kddizayn.com',
  address: '',
  googleMapsUrl: '',
  instagramUrl: '',
  linkedinUrl: '',
  facebookUrl: '',
  workingHours: '',
});

onMounted(async () => {
  try {
    const { data } = await getContact();
    contact.value = { ...contact.value, ...data };
  } catch {
    loadError.value = true;
  } finally {
    loading.value = false;
  }
});

async function submitContact() {
  sent.value = false;
  sendError.value = false;
  sending.value = true;
  try {
    await sendContactMessage(form.value);
    sent.value = true;
    form.value = { name: '', email: '', message: '' };
  } catch {
    sendError.value = true;
  } finally {
    sending.value = false;
  }
}
</script>

<template>
  <div class="contact-section" data-aos="fade-up" data-aos-duration="700" aria-labelledby="contact-title">
    <div class="container">
      <div class="section-header">
        <span class="section-eyebrow">{{ t('contact.eyebrow') }}</span>
        <h2 id="contact-title" class="section-title">{{ t('contact.title') }}</h2>
      </div>

      <div class="contact-grid">
        <div class="contact-info">
          <h3>{{ t('contact.question') }}</h3>
          <p>{{ t('contact.desc') }}</p>

          <p v-if="loading" class="loading-text">{{ t('contact.loading') }}</p>
          <p v-else-if="loadError" class="inline-error" role="status">{{ t('contact.load_error') }}</p>
          <div v-else class="info-items">
            <div class="info-item">
              <div class="info-icon">
                <i class="fa-solid fa-location-dot"></i>
              </div>
              <div>
                <strong>{{ t('contact.address_label') }}</strong>
                <p>{{ contact.address }}</p>
              </div>
            </div>
            <div class="info-item">
              <div class="info-icon">
                <i class="fa-solid fa-phone"></i>
              </div>
              <div>
                <strong>{{ t('contact.phone_label') }}</strong>
                <p><a :href="`tel:${contact.phone.replace(/\s/g, '')}`">{{ contact.phone }}</a></p>
              </div>
            </div>
            <div class="info-item">
              <div class="info-icon">
                <i class="fa-solid fa-envelope"></i>
              </div>
              <div>
                <strong>{{ t('contact.email_label') }}</strong>
                <p><a :href="`mailto:${contact.email}`">{{ contact.email }}</a></p>
              </div>
            </div>
            <div v-if="contact.workingHours" class="info-item">
              <div class="info-icon"><i class="fa-solid fa-clock" aria-hidden="true"></i></div>
              <div><strong>{{ t('contact.hours_label') }}</strong><p>{{ contact.workingHours }}</p></div>
            </div>
          </div>

        </div>

        <div class="contact-form-panel">
          <h3>{{ t('contact.form_title') }}</h3>
          <p class="panel-intro">{{ t('contact.form_intro') }}</p>
          <form class="contact-form" @submit.prevent="submitContact">
            <div class="form-row">
              <label><span>{{ t('contact.name_label') }}</span><input v-model.trim="form.name" required autocomplete="name" /></label>
              <label><span>{{ t('contact.form_email_label') }}</span><input v-model.trim="form.email" required type="email" autocomplete="email" /></label>
            </div>
            <label><span>{{ t('contact.message_label') }}</span><textarea v-model.trim="form.message" required rows="7"></textarea></label>
            <button type="submit" class="form-submit" :disabled="sending">
              {{ sending ? t('contact.sending') : t('contact.send') }}
            </button>
            <p v-if="sent" class="form-note" role="status">{{ t('contact.mail_note') }}</p>
            <p v-if="sendError" class="form-error" role="alert">{{ t('contact.mail_error') }}</p>
          </form>
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

.loading-text,
.inline-error,
.form-note { color: var(--text-mid); font-size: 14px; line-height: 1.6; }
.inline-error { color: #a33b32; }
.contact-form-panel { padding: 36px; background: var(--bg-section); box-shadow: var(--shadow-md); }
.contact-form-panel h3 { font-size: 28px; color: var(--text-dark); margin-bottom: 10px; }
.panel-intro { margin: 0 0 24px; color: var(--text-mid); font-size: 14px; line-height: 1.6; }
.contact-form { margin-top: 0; }
.contact-form label { display: flex; flex-direction: column; gap: 7px; margin-bottom: 14px; color: var(--text-dark); font-size: 12px; font-weight: 700; }
.form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 14px; }
.contact-form input, .contact-form textarea { width: 100%; padding: 11px 12px; border: 1px solid var(--border); background: var(--bg-white); font: inherit; font-size: 14px; color: var(--text-dark); outline: none; border-radius: 3px; }
.contact-form input:focus, .contact-form textarea:focus { border-color: var(--accent); box-shadow: 0 0 0 3px rgba(201,168,76,.14); }
.form-submit { padding: 12px 22px; border: 0; background: var(--primary); color: white; font: inherit; font-weight: 700; cursor: pointer; }
.form-submit:hover { background: var(--accent); color: var(--primary); }
.form-submit:disabled { opacity: .65; cursor: wait; }
.form-error { color: #a33a3a; font-size: 14px; line-height: 1.6; }

.info-item a:hover {
  color: var(--accent);
}

@media (max-width: 900px) {
  .contact-section { padding: 72px 0; }
  .container { padding: 0 20px; }
  .section-header { margin-bottom: 48px; }
  .contact-grid { grid-template-columns: 1fr; gap: 40px; min-height: unset; }
  .contact-info h3 { font-size: 26px; }
  .contact-info > p { margin-bottom: 32px; }
  .contact-form-panel { padding: 28px 22px; }
}

@media (max-width: 480px) {
  .form-row { grid-template-columns: 1fr; gap: 0; }
  .info-items { gap: 24px; }
  .info-icon { width: 38px; height: 38px; font-size: 14px; }
}
</style>
