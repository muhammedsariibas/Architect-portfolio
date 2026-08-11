package com.kddizayn.api.config;

import com.kddizayn.api.module.contact.ContactInfo;
import com.kddizayn.api.module.contact.ContactRepository;
import com.kddizayn.api.module.i18n.I18nMessage;
import com.kddizayn.api.module.i18n.I18nRepository;
import com.kddizayn.api.module.user.User;
import com.kddizayn.api.module.user.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository      userRepository;
    private final ContactRepository   contactRepository;
    private final I18nRepository      i18nRepository;
    private final PasswordEncoder     passwordEncoder;

    @Override
    public void run(String... args) {
        seedAdmin();
        seedContact();
        seedI18n();
    }

    // ── Admin user ────────────────────────────────────────────────────────────

    private void seedAdmin() {
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("Admin@2024!"))  // change on first login
                    .role("ROLE_ADMIN")
                    .build();
            userRepository.save(admin);
            log.info("Default admin user created (username: admin)");
        }
    }

    // ── Default contact info ──────────────────────────────────────────────────

    private void seedContact() {
        if (contactRepository.count() == 0) {
            ContactInfo info = ContactInfo.builder()
                    .phone("+90 535 698 84 57")
                    .email("info@kddizayn.com")
                    .address("G Plus, Bağlar mah. Mimarsinan Cad. NO:37/1, A Blok, 34209 Bağcılar/İstanbul")
                    .googleMapsUrl("https://maps.app.goo.gl/example")
                    .instagramUrl("https://www.instagram.com/kddizaynmimarlik/")
                    .linkedinUrl("https://www.linkedin.com/company/kddizaynmimarlik/")
                    .workingHours("Pzt–Cum: 09:00–18:00")
                    .build();
            contactRepository.save(info);
            log.info("Default contact info seeded");
        }
    }

    // ── i18n seed data ────────────────────────────────────────────────────────

    private void seedI18n() {
        if (i18nRepository.count() > 0) return;

        List<I18nMessage> messages = List.of(
            // ── Turkish ──
            msg("nav.services",        "tr", "Hizmetler"),
            msg("nav.about",           "tr", "Hakkımızda"),
            msg("nav.portfolio",       "tr", "Projeler"),
            msg("nav.contact",         "tr", "İletişim"),
            msg("hero.eyebrow",        "tr", "Mimarlık & Tasarım"),
            msg("hero.title",          "tr", "KD Dizayn"),
            msg("hero.subtitle",       "tr", "Geleceği inşa ediyoruz"),
            msg("hero.cta_projects",   "tr", "Projelerimizi Keşfedin"),
            msg("hero.cta_contact",    "tr", "İletişime Geçin"),
            msg("services.eyebrow",    "tr", "Neler Yapıyoruz"),
            msg("services.title",      "tr", "Hizmetlerimiz"),
            msg("services.card1_title","tr", "Müşteri Odaklı"),
            msg("services.card1_desc", "tr", "Müşterilerimizin ihtiyaçlarını en iyi şekilde karşılamak için çalışıyoruz."),
            msg("services.card2_title","tr", "İnovasyon"),
            msg("services.card2_desc", "tr", "Sıra dışı tasarımlar, benzersiz mekânlar. İnovasyonla geleceği inşa ediyoruz."),
            msg("services.card3_title","tr", "Kaliteli İşçilik"),
            msg("services.card3_desc", "tr", "İşçiliğimizle projeleri kusursuzca hayata geçiriyor, her detaya özen gösteriyoruz."),
            msg("about.eyebrow",       "tr", "Biz Kimiz"),
            msg("about.title",         "tr", "Hakkımızda"),
            msg("about.p1",            "tr", "Firmamız, 2023 yılında 3 ortak tarafından kurulmuş olup teknoloji, mühendislik, üretim, lojistik ve hizmet sektörlerinde faaliyet göstermektedir."),
            msg("about.p2",            "tr", "İnovasyon süreçlerine büyük önem veren firmamız, aile şirketi olmanın esnekliğini profesyonel iş anlayışıyla birleştirerek uzman ekibiyle kaliteli hizmet sunmayı hedeflemektedir."),
            msg("about.stat1_label",   "tr", "Kuruluş Yılı"),
            msg("about.stat2_label",   "tr", "Tamamlanan Proje"),
            msg("about.stat3_label",   "tr", "Kurucu Ortak"),
            msg("about.chart1",        "tr", "TUTKU"),
            msg("about.chart2",        "tr", "DENEYİM"),
            msg("about.chart3",        "tr", "BAŞARI"),
            msg("portfolio.eyebrow",   "tr", "Referanslarımız"),
            msg("portfolio.title",     "tr", "Projeler"),
            msg("portfolio.tag_interior","tr","İç Mimari"),
            msg("portfolio.tag_office","tr", "Ofis Tasarımı"),
            msg("contact.eyebrow",     "tr", "Ulaşın"),
            msg("contact.title",       "tr", "İletişim"),
            msg("contact.question",    "tr", "Sorunuz mu var?"),
            msg("contact.desc",        "tr", "Bize sorun, her zaman cevaplamaktan mutluluk duyarız."),
            msg("contact.address_label","tr","Adres"),
            msg("contact.phone_label", "tr", "Telefon"),
            msg("contact.email_label", "tr", "E-posta"),
            msg("footer.tagline",      "tr", "Mimarlık, tasarım ve mühendislik alanında anahtar teslim çözümler."),
            msg("footer.links_title",  "tr", "Bağlantılar"),
            msg("footer.contact_title","tr", "İletişim"),
            msg("footer.social_title", "tr", "Sosyal Medya"),
            msg("footer.rights",       "tr", "Tüm hakları saklıdır."),

            // ── English ──
            msg("nav.services",        "en", "Services"),
            msg("nav.about",           "en", "About"),
            msg("nav.portfolio",       "en", "Projects"),
            msg("nav.contact",         "en", "Contact"),
            msg("hero.eyebrow",        "en", "Architecture & Design"),
            msg("hero.title",          "en", "KD Design"),
            msg("hero.subtitle",       "en", "Building the future"),
            msg("hero.cta_projects",   "en", "Explore Our Projects"),
            msg("hero.cta_contact",    "en", "Get in Touch"),
            msg("services.eyebrow",    "en", "What We Do"),
            msg("services.title",      "en", "Our Services"),
            msg("services.card1_title","en", "Client Focused"),
            msg("services.card1_desc", "en", "We work to meet our clients' needs in the best possible way."),
            msg("services.card2_title","en", "Innovation"),
            msg("services.card2_desc", "en", "Extraordinary designs, unique spaces. We build the future through innovation."),
            msg("services.card3_title","en", "Quality Craftsmanship"),
            msg("services.card3_desc", "en", "We bring projects to life flawlessly, paying close attention to every detail."),
            msg("about.eyebrow",       "en", "Who We Are"),
            msg("about.title",         "en", "About Us"),
            msg("about.p1",            "en", "Our company was founded in 2023 by 3 partners and operates in the technology, engineering, manufacturing, logistics and services sectors."),
            msg("about.p2",            "en", "Our company, which places great importance on innovation processes, aims to provide quality services with its expert team by combining the flexibility of a family business with a professional approach."),
            msg("about.stat1_label",   "en", "Founded"),
            msg("about.stat2_label",   "en", "Completed Projects"),
            msg("about.stat3_label",   "en", "Founding Partners"),
            msg("about.chart1",        "en", "PASSION"),
            msg("about.chart2",        "en", "EXPERIENCE"),
            msg("about.chart3",        "en", "SUCCESS"),
            msg("portfolio.eyebrow",   "en", "Our References"),
            msg("portfolio.title",     "en", "Projects"),
            msg("portfolio.tag_interior","en","Interior Design"),
            msg("portfolio.tag_office","en", "Office Design"),
            msg("contact.eyebrow",     "en", "Reach Us"),
            msg("contact.title",       "en", "Contact"),
            msg("contact.question",    "en", "Have a question?"),
            msg("contact.desc",        "en", "Ask us anything, we are always happy to answer."),
            msg("contact.address_label","en","Address"),
            msg("contact.phone_label", "en", "Phone"),
            msg("contact.email_label", "en", "Email"),
            msg("footer.tagline",      "en", "Turnkey solutions in architecture, design and engineering."),
            msg("footer.links_title",  "en", "Links"),
            msg("footer.contact_title","en", "Contact"),
            msg("footer.social_title", "en", "Social Media"),
            msg("footer.rights",       "en", "All rights reserved.")
        );

        i18nRepository.saveAll(messages);
        log.info("i18n seed data inserted ({} messages)", messages.size());
    }

    private I18nMessage msg(String key, String locale, String content) {
        return I18nMessage.builder()
                .key(key)
                .locale(locale)
                .content(content)
                .build();
    }
}
