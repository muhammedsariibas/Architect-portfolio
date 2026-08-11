-- ============================================================
-- Seed script — run only on first setup.
-- In application.yml set: spring.sql.init.mode: always
-- After first run revert to: spring.sql.init.mode: never
-- ============================================================

-- Admin user (password: Admin@2024!)
INSERT INTO users (username, password, role)
SELECT 'admin',
       '$2a$10$7QJ8zV3k1mN5pL9wX2dOuOQvYrT6sA4hBcDeFgHiJkLmNoPqRsTuV',
       'ROLE_ADMIN'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'admin');

-- Default contact info
INSERT INTO contact_info (id, phone, email, address, google_maps_url, instagram_url, linkedin_url, working_hours)
SELECT 1,
       '+90 535 698 84 57',
       'info@kddizayn.com',
       'G Plus, Bağlar mah. Mimarsinan Cad. NO:37/1, A Blok, 34209 Bağcılar/İstanbul',
       'https://maps.app.goo.gl/example',
       'https://www.instagram.com/kddizaynmimarlik/',
       'https://www.linkedin.com/company/kddizaynmimarlik/',
       'Pzt–Cum: 09:00–18:00'
WHERE NOT EXISTS (SELECT 1 FROM contact_info WHERE id = 1);
