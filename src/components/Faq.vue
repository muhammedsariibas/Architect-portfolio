<script setup>
import { ref } from 'vue'
import { useI18n } from 'vue-i18n'

const { t } = useI18n()
const activeIndex = ref(null)

const questions = [
  { question: 'faq.q1', answer: 'faq.a1' },
  { question: 'faq.q2', answer: 'faq.a2' },
  { question: 'faq.q3', answer: 'faq.a3' },
  { question: 'faq.q4', answer: 'faq.a4' },
  { question: 'faq.q5', answer: 'faq.a5' },
  { question: 'faq.q6', answer: 'faq.a6' },
]

function toggle(index) {
  activeIndex.value = activeIndex.value === index ? null : index
}
</script>

<template>
  <section class="faq-section" id="faq" aria-labelledby="faq-title">
    <div class="faq-container">
      <div class="faq-heading">
        <span class="section-eyebrow">{{ t('faq.eyebrow') }}</span>
        <h2 id="faq-title" class="section-title">{{ t('faq.title') }}</h2>
        <p>{{ t('faq.description') }}</p>
      </div>

      <div class="faq-list">
        <article v-for="(item, index) in questions" :key="item.question" class="faq-item" :class="{ open: activeIndex === index }">
          <h3>
            <button
              class="faq-question"
              type="button"
              :aria-expanded="activeIndex === index"
              :aria-controls="`faq-answer-${index}`"
              @click="toggle(index)"
            >
              <span>{{ t(item.question) }}</span>
              <i class="fa-solid fa-plus" aria-hidden="true"></i>
            </button>
          </h3>
          <div v-show="activeIndex === index" :id="`faq-answer-${index}`" class="faq-answer">
            <p>{{ t(item.answer) }}</p>
          </div>
        </article>
      </div>

      <div class="faq-cta">
        <p>{{ t('faq.cta_text') }}</p>
        <a href="#contact" class="faq-cta-link">{{ t('faq.cta_button') }}</a>
      </div>
    </div>
  </section>
</template>

<style scoped>
.faq-section { padding: 120px 0; background: var(--bg-section); }
.faq-container { max-width: 1000px; margin: 0 auto; padding: 0 40px; }
.faq-heading { max-width: 650px; margin-bottom: 52px; }
.section-eyebrow { display: inline-block; margin-bottom: 12px; color: var(--accent); font-size: 12px; font-weight: 700; letter-spacing: .2em; text-transform: uppercase; }
.section-title { margin-bottom: 18px; color: var(--text-dark); font-size: clamp(36px, 5vw, 52px); letter-spacing: -.02em; }
.faq-heading p { color: var(--text-mid); font-size: 16px; line-height: 1.75; }
.faq-list { border-top: 1px solid var(--border); }
.faq-item { border-bottom: 1px solid var(--border); }
.faq-question { width: 100%; display: flex; align-items: center; justify-content: space-between; gap: 24px; padding: 24px 4px; border: 0; background: transparent; color: var(--text-dark); cursor: pointer; text-align: left; font: inherit; font-size: 17px; font-weight: 600; }
.faq-question i { flex-shrink: 0; color: var(--accent); font-size: 14px; transition: transform .25s ease; }
.faq-item.open .faq-question i { transform: rotate(45deg); }
.faq-question:hover { color: var(--accent); }
.faq-answer { padding: 0 44px 24px 4px; }
.faq-answer p { margin: 0; color: var(--text-mid); font-size: 15px; line-height: 1.8; }
.faq-cta { display: flex; align-items: center; justify-content: space-between; gap: 24px; margin-top: 48px; padding: 24px 28px; background: var(--bg-white); box-shadow: var(--shadow-sm); }
.faq-cta p { margin: 0; color: var(--text-dark); font-size: 15px; font-weight: 600; }
.faq-cta-link { flex-shrink: 0; padding: 12px 20px; background: var(--primary); color: #fff; text-decoration: none; font-size: 13px; font-weight: 700; }
.faq-cta-link:hover { background: var(--accent); color: var(--primary); }
@media (max-width: 768px) {
  .faq-section { padding: 72px 0; }
  .faq-container { padding: 0 20px; }
  .faq-heading { margin-bottom: 36px; }
  .faq-question { padding: 20px 0; font-size: 15px; }
  .faq-answer { padding: 0 28px 20px 0; }
  .faq-cta { align-items: flex-start; flex-direction: column; margin-top: 32px; padding: 20px; }
}
</style>
