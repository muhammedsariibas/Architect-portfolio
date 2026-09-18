import { createI18n } from "vue-i18n";
import tr from "./locales/tr.js";
import en from "./locales/en.js";
import axios from "axios";

const apiBase = import.meta.env.VITE_API_URL || "http://localhost:8085";

function applyFlatMessages(target, messages) {
  messages.forEach(([key, value]) => {
    const parts = key.split('.');
    let cursor = target;
    parts.slice(0, -1).forEach((part) => { cursor = cursor[part] || (cursor[part] = {}); });
    cursor[parts[parts.length - 1]] = value;
  });
}

const i18n = createI18n({
  legacy: false,
  locale: localStorage.getItem("site_locale") || "tr",
  fallbackLocale: "en",
  messages: { tr, en },
});

export default i18n;

Promise.all(['tr', 'en'].map(async (locale) => {
  try {
    const { data } = await axios.get(`${apiBase}/api/v1/i18n/public/${locale}`, { timeout: 5000 });
    applyFlatMessages(i18n.global.getLocaleMessage(locale), Object.entries(data));
  } catch {
    // Local translations are the offline fallback.
  }
}));
