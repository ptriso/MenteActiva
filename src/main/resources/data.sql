-- ==========================================
-- CREAR AUTHORITIES PRIMERO
-- ==========================================
INSERT INTO authorities (name)
SELECT 'ROLE_USER'
    WHERE NOT EXISTS (SELECT 1 FROM authorities WHERE name = 'ROLE_USER');

INSERT INTO authorities (name)
SELECT 'ROLE_CLIENT'
    WHERE NOT EXISTS (SELECT 1 FROM authorities WHERE name = 'ROLE_CLIENT');

INSERT INTO authorities (name)
SELECT 'ROLE_PROFESSIONAL'
    WHERE NOT EXISTS (SELECT 1 FROM authorities WHERE name = 'ROLE_PROFESSIONAL');

INSERT INTO authorities (name)
SELECT 'ROLE_ADMIN'
    WHERE NOT EXISTS (SELECT 1 FROM authorities WHERE name = 'ROLE_ADMIN');

-- ============================
-- USERS pro1..pro5 (idempotente)
-- ============================
INSERT INTO users (username, password, enabled)
SELECT 'pro1', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'pro1');

INSERT INTO users (username, password, enabled)
SELECT 'pro2', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'pro2');

INSERT INTO users (username, password, enabled)
SELECT 'pro3', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'pro3');

INSERT INTO users (username, password, enabled)
SELECT 'pro4', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'pro4');

INSERT INTO users (username, password, enabled)
SELECT 'pro5', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'pro5');

INSERT INTO users (username, password, enabled)
SELECT 'cli1', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'cli1');

INSERT INTO users (username, password, enabled)
SELECT 'cli2', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'cli2');

INSERT INTO users (username, password, enabled)
SELECT 'cli3', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'cli3');

INSERT INTO users (username, password, enabled)
SELECT 'cli4', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'cli4');

INSERT INTO users (username, password, enabled)
SELECT 'cli5', '$2a$12$4aM1qZdnY/BKqF/U9G3cZui0tJTMKLQ6cnAaGx.rruAcsquUkUS32', true
    WHERE NOT EXISTS (SELECT 1 FROM users WHERE username = 'cli5');

-- ============================================
-- CLIENTS (name, lastname, mail, phone, user_id)
-- ============================================
-- cli1 -> Ana Torres
INSERT INTO clients (name, lastname, mail, phone, user_id)
SELECT 'Ana', 'Torres', 'ana.torres@demo.com', '910000001', u.id
FROM users u
WHERE u.username = 'cli1'
  AND NOT EXISTS (SELECT 1 FROM clients c WHERE c.user_id = u.id);

-- cli2 -> Bruno Díaz
INSERT INTO clients (name, lastname, mail, phone, user_id)
SELECT 'Bruno', 'Diaz', 'bruno.diaz@demo.com', '910000002', u.id
FROM users u
WHERE u.username = 'cli2'
  AND NOT EXISTS (SELECT 1 FROM clients c WHERE c.user_id = u.id);

-- cli3 -> Carla Núñez
INSERT INTO clients (name, lastname, mail, phone, user_id)
SELECT 'Carla', 'Nunez', 'carla.nunez@demo.com', '910000003', u.id
FROM users u
WHERE u.username = 'cli3'
  AND NOT EXISTS (SELECT 1 FROM clients c WHERE c.user_id = u.id);

-- cli4 -> Daniel Soto
INSERT INTO clients (name, lastname, mail, phone, user_id)
SELECT 'Daniel', 'Soto', 'daniel.soto@demo.com', '910000004', u.id
FROM users u
WHERE u.username = 'cli4'
  AND NOT EXISTS (SELECT 1 FROM clients c WHERE c.user_id = u.id);

-- cli5 -> Elena Ruiz
INSERT INTO clients (name, lastname, mail, phone, user_id)
SELECT 'Elena', 'Ruiz', 'elena.ruiz@demo.com', '910000005', u.id
FROM users u
WHERE u.username = 'cli5'
  AND NOT EXISTS (SELECT 1 FROM clients c WHERE c.user_id = u.id);



-- ==========================================
-- PROFESIONALS (usa enum válido: Psiquiatra | Psicologo | Neurologo)
-- ==========================================
-- pro1 -> Psiquiatra
INSERT INTO profesionals (name, lastname, specialization, mail, phone, user_id)
SELECT 'Laura', 'Salazar', 'Psiquiatra', 'laura@demo.com', '900000001', u.id
FROM users u
WHERE u.username = 'pro1'
  AND NOT EXISTS (SELECT 1 FROM profesionals p WHERE p.user_id = u.id);

-- pro2 -> Psicologo
INSERT INTO profesionals (name, lastname, specialization, mail, phone, user_id)
SELECT 'Diego', 'Vega', 'Psicologo', 'diego@demo.com', '900000002', u.id
FROM users u
WHERE u.username = 'pro2'
  AND NOT EXISTS (SELECT 1 FROM profesionals p WHERE p.user_id = u.id);

-- pro3 -> Neurologo
INSERT INTO profesionals (name, lastname, specialization, mail, phone, user_id)
SELECT 'Camila', 'Rios', 'Neurologo', 'camila@demo.com', '900000003', u.id
FROM users u
WHERE u.username = 'pro3'
  AND NOT EXISTS (SELECT 1 FROM profesionals p WHERE p.user_id = u.id);

-- pro4 -> Psiquiatra
INSERT INTO profesionals (name, lastname, specialization, mail, phone, user_id)
SELECT 'Javier', 'Paredes', 'Psiquiatra', 'javier@demo.com', '900000004', u.id
FROM users u
WHERE u.username = 'pro4'
  AND NOT EXISTS (SELECT 1 FROM profesionals p WHERE p.user_id = u.id);

-- pro5 -> Psicologo
INSERT INTO profesionals (name, lastname, specialization, mail, phone, user_id)
SELECT 'Rocio', 'Mendoza', 'Psicologo', 'rocio@demo.com', '900000005', u.id
FROM users u
WHERE u.username = 'pro5'
  AND NOT EXISTS (SELECT 1 FROM profesionals p WHERE p.user_id = u.id);

-- ==========================================
-- USERS_AUTHORITIES -> ROLE_PROFESSIONAL
-- (no insert a authorities; solo busca por nombre)
-- ==========================================
-- pro1
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_PROFESSIONAL'
WHERE u.username = 'pro1'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- pro2
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_PROFESSIONAL'
WHERE u.username = 'pro2'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- pro3
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_PROFESSIONAL'
WHERE u.username = 'pro3'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- pro4
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_PROFESSIONAL'
WHERE u.username = 'pro4'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- pro5
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_PROFESSIONAL'
WHERE u.username = 'pro5'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- ============================================
-- USERS_AUTHORITIES -> ROLE_CLIENT (sin insertar authorities)
-- ============================================
-- cli1
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_CLIENT'
WHERE u.username = 'cli1'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- cli2
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_CLIENT'
WHERE u.username = 'cli2'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- cli3
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_CLIENT'
WHERE u.username = 'cli3'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- cli4
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_CLIENT'
WHERE u.username = 'cli4'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- cli5
INSERT INTO users_authorities (user_id, authority_id)
SELECT u.id, a.id
FROM users u
         JOIN authorities a ON a.name = 'ROLE_CLIENT'
WHERE u.username = 'cli5'
  AND NOT EXISTS (
    SELECT 1 FROM users_authorities ua
    WHERE ua.user_id = u.id AND ua.authority_id = a.id
);

-- ============================================================
-- VIDEOS (relajación/meditación) por profesional (idempotente)
-- columnas: (title, description, url, duration, profesional_id)
-- ============================================================

-- pro1 (Laura Salazar)
INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Meditación guiada 10 minutos (ansiedad)',
       'Práctica breve de respiración consciente para calmar mente y cuerpo.',
       'https://youtu.be/2OEL4P1Rz04',
       600, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro1'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Meditación guiada 10 minutos (ansiedad)' AND v.profesional_id = p.id
);

INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Respiración 4-7-8 para dormir',
       'Técnica simple para conciliar el sueño y reducir el estrés.',
       'https://youtu.be/yrJ-9f0fX8E',
       540, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro1'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Respiración 4-7-8 para dormir' AND v.profesional_id = p.id
);

-- pro2 (Diego Vega)
INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Body scan de 15 minutos',
       'Escaneo corporal guiado para soltar tensiones acumuladas.',
       'https://youtu.be/MIr3RsUWrdo',
       900, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro2'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Body scan de 15 minutos' AND v.profesional_id = p.id
);

INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Música relajante con naturaleza',
       'Sonidos ambientales y pads suaves para foco y calma.',
       'https://youtu.be/1ZYbU82GVz4',
       1800, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro2'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Música relajante con naturaleza' AND v.profesional_id = p.id
);

-- pro3 (Camila Rios)
INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Meditación mindfulness para principiantes',
       'Introducción práctica a la atención plena centrada en la respiración.',
       'https://youtu.be/inpok4MKVLM',
       600, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro3'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Meditación mindfulness para principiantes' AND v.profesional_id = p.id
);

INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Relajación progresiva de Jacobson',
       'Secuencia guiada de tensión–distensión para relajar el cuerpo.',
       'https://youtu.be/5zhnLG3GW-8',
       720, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro3'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Relajación progresiva de Jacobson' AND v.profesional_id = p.id
);

-- pro4 (Javier Paredes)
INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Meditación guiada para empezar el día',
       'Intención matutina y respiraciones para claridad y energía.',
       'https://youtu.be/sG7DBA-mgFY',
       480, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro4'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Meditación guiada para empezar el día' AND v.profesional_id = p.id
);

INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Pausa de respiración en 5 minutos',
       'Micropráctica con conteo cuadrado (box breathing).',
       'https://youtu.be/tEmt1Znux58',
       300, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro4'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Pausa de respiración en 5 minutos' AND v.profesional_id = p.id
);

-- pro5 (Rocio Mendoza)
INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Meditación para soltar el estrés',
       'Visualización y respiración diafragmática para relajar mente y cuerpo.',
       'https://youtu.be/6p_yaNFSYao',
       780, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro5'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Meditación para soltar el estrés' AND v.profesional_id = p.id
);

INSERT INTO videos (title, description, url, duration, profesional_id)
SELECT 'Música para dormir profundamente',
       'Pistas ambientales suaves para higiene del sueño.',
       'https://youtu.be/a3wvSx5bPLQ',
       3600, p.id
FROM users u
         JOIN profesionals p ON p.user_id = u.id
WHERE u.username = 'pro5'
  AND NOT EXISTS (
    SELECT 1 FROM videos v
    WHERE v.title = 'Música para dormir profundamente' AND v.profesional_id = p.id
);

-- ============================================================
-- VIDEO_PROGRESS (idempotente)
-- columnas esperadas: (client_id, video_id, completed, current_times, percentage, views_count)
-- ============================================================

-- cli1 viendo "Meditación guiada 10 minutos (ansiedad)" (600s) — 70%
INSERT INTO video_progress (client_id, video_id, current_times, percentage, completed, views_count)
SELECT c.id, v.id, 420, 70.0, FALSE, 1
FROM users u
         JOIN clients c    ON c.user_id = u.id
         JOIN videos  v    ON v.title = 'Meditación guiada 10 minutos (ansiedad)'
WHERE u.username = 'cli1'
  AND NOT EXISTS (
    SELECT 1 FROM video_progress vp
    WHERE vp.client_id = c.id AND vp.video_id = v.id
);

-- cli2 completó "Body scan de 15 minutos" (900s) — 100%
INSERT INTO video_progress (client_id, video_id, current_times, percentage, completed, views_count)
SELECT c.id, v.id, 900, 100.0, TRUE, 2
FROM users u
         JOIN clients c    ON c.user_id = u.id
         JOIN videos  v    ON v.title = 'Body scan de 15 minutos'
WHERE u.username = 'cli2'
  AND NOT EXISTS (
    SELECT 1 FROM video_progress vp
    WHERE vp.client_id = c.id AND vp.video_id = v.id
);

-- cli3 a 50% en "Meditación mindfulness para principiantes" (600s)
INSERT INTO video_progress (client_id, video_id, current_times, percentage, completed, views_count)
SELECT c.id, v.id, 300, 50.0, FALSE, 1
FROM users u
         JOIN clients c    ON c.user_id = u.id
         JOIN videos  v    ON v.title = 'Meditación mindfulness para principiantes'
WHERE u.username = 'cli3'
  AND NOT EXISTS (
    SELECT 1 FROM video_progress vp
    WHERE vp.client_id = c.id AND vp.video_id = v.id
);

-- cli4 completó "Pausa de respiración en 5 minutos" (300s)
INSERT INTO video_progress (client_id, video_id, current_times, percentage, completed, views_count)
SELECT c.id, v.id, 300, 100.0, TRUE, 3
FROM users u
         JOIN clients c    ON c.user_id = u.id
         JOIN videos  v    ON v.title = 'Pausa de respiración en 5 minutos'
WHERE u.username = 'cli4'
  AND NOT EXISTS (
    SELECT 1 FROM video_progress vp
    WHERE vp.client_id = c.id AND vp.video_id = v.id
);

-- cli5 a 33.3% en "Música para dormir profundamente" (3600s)
INSERT INTO video_progress (client_id, video_id, current_times, percentage, completed, views_count)
SELECT c.id, v.id, 1200, 33.3, FALSE, 5
FROM users u
         JOIN clients c    ON c.user_id = u.id
         JOIN videos  v    ON v.title = 'Música para dormir profundamente'
WHERE u.username = 'cli5'
  AND NOT EXISTS (
    SELECT 1 FROM video_progress vp
    WHERE vp.client_id = c.id AND vp.video_id = v.id
);

-- (Opcional) otro progreso para cli1 en "Respiración 4-7-8 para dormir" (540s) — 80%
INSERT INTO video_progress (client_id, video_id, current_times, percentage, completed, views_count)
SELECT c.id, v.id, 432, 80.0, FALSE, 2
FROM users u
         JOIN clients c    ON c.user_id = u.id
         JOIN videos  v    ON v.title = 'Respiración 4-7-8 para dormir'
WHERE u.username = 'cli1'
  AND NOT EXISTS (
    SELECT 1 FROM video_progress vp
    WHERE vp.client_id = c.id AND vp.video_id = v.id
);

-- ============================================================
-- SCHEDULES (PREDEFINIDOS PARA VARIAS SEMANAS)
-- (Crea horarios para la semana pasada, esta semana y las próximas 2)
-- ============================================================
WITH
    -- 1. Genera 4 semanas (desde -1 hasta +2)
    week_offsets AS (
        SELECT generate_series(-1, 2) AS week_offset
    ),
    -- 2. Define el Lunes de cada una de esas semanas
    week_starts AS (
        SELECT date_trunc('week', CURRENT_DATE)::date + (wo.week_offset * 7) AS monday
        FROM week_offsets wo
    ),
    -- 3. Trae los IDs de los 5 profesionales
    pros AS (
        SELECT p.id, u.username
        FROM profesionals p
                 JOIN users u ON p.user_id = u.id
        WHERE u.username IN ('pro1', 'pro2', 'pro3', 'pro4', 'pro5')
    ),
    -- 4. Define la plantilla de horarios (Lunes a Viernes, 11:00 a 18:00)
    template AS (
        -- pro1: Lunes y Miércoles (Mañana) 11:00-13:00
        SELECT 'pro1' AS username, 0 AS day_offset, '11:00:00'::time AS t_start, '12:00:00'::time AS t_end UNION ALL
        SELECT 'pro1', 0, '12:00:00'::time, '13:00:00'::time UNION ALL
        SELECT 'pro1', 2, '11:00:00'::time, '12:00:00'::time UNION ALL
        SELECT 'pro1', 2, '12:00:00'::time, '13:00:00'::time UNION ALL

        -- pro2: Lunes y Miércoles (Tarde) 14:00-18:00
        SELECT 'pro2', 0, '14:00:00'::time, '15:00:00'::time UNION ALL
        SELECT 'pro2', 0, '15:00:00'::time, '16:00:00'::time UNION ALL
        SELECT 'pro2', 2, '16:00:00'::time, '17:00:00'::time UNION ALL
        SELECT 'pro2', 2, '17:00:00'::time, '18:00:00'::time UNION ALL

        -- pro3: Martes y Jueves (11:00-16:00 con descanso)
        SELECT 'pro3', 1, '11:00:00'::time, '12:00:00'::time UNION ALL
        SELECT 'pro3', 1, '12:00:00'::time, '13:00:00'::time UNION ALL
        SELECT 'pro3', 1, '14:00:00'::time, '15:00:00'::time UNION ALL
        SELECT 'pro3', 1, '15:00:00'::time, '16:00:00'::time UNION ALL
        SELECT 'pro3', 3, '11:00:00'::time, '12:00:00'::time UNION ALL
        SELECT 'pro3', 3, '12:00:00'::time, '13:00:00'::time UNION ALL

        -- pro4: Viernes (Todo el día 11:00-16:00)
        SELECT 'pro4', 4, '11:00:00'::time, '12:00:00'::time UNION ALL
        SELECT 'pro4', 4, '12:00:00'::time, '13:00:00'::time UNION ALL
        SELECT 'pro4', 4, '14:00:00'::time, '15:00:00'::time UNION ALL
        SELECT 'pro4', 4, '15:00:00'::time, '16:00:00'::time UNION ALL

        -- pro5: Martes y Jueves (Tarde 16:00-18:00)
        SELECT 'pro5', 1, '16:00:00'::time, '17:00:00'::time UNION ALL
        SELECT 'pro5', 1, '17:00:00'::time, '18:00:00'::time UNION ALL
        SELECT 'pro5', 3, '16:00:00'::time, '17:00:00'::time UNION ALL
        SELECT 'pro5', 3, '17:00:00'::time, '18:00:00'::time
    ),
    -- 5. Combina todo para generar las filas a insertar
    to_insert AS (
        SELECT
            (w.monday + t.day_offset) AS date,
    t.t_start                 AS time_start,
    t.t_end                   AS time_ends,
    p.id                      AS profesional_id
FROM template t
    JOIN week_starts w ON TRUE
    JOIN profesionals p ON p.user_id = (SELECT id FROM users WHERE username = t.username)
    )
-- 6. Inserta los datos
INSERT INTO schedules (date, time_start, time_ends, profesional_id)
SELECT i.date, i.time_start, i.time_ends, i.profesional_id
FROM to_insert i
WHERE NOT EXISTS (
    SELECT 1 FROM schedules x
    WHERE x.profesional_id = i.profesional_id
      AND x.date           = i.date
      AND x.time_start     = i.time_start
);

-- ============================================
-- CONSENTS (client_id, age, doc_consent)
-- Solo cli1 y cli2 (menores de 18) — idempotente
-- ============================================

-- cli1 (17 años)
INSERT INTO consents (client_id, age, doc_consent)
SELECT c.id, 17, TRUE
FROM users u
         JOIN clients c ON c.user_id = u.id
WHERE u.username = 'cli1'
  AND NOT EXISTS (SELECT 1 FROM consents s WHERE s.client_id = c.id);

-- cli2 (16 años)
INSERT INTO consents (client_id, age, doc_consent)
SELECT c.id, 16, TRUE
FROM users u
         JOIN clients c ON c.user_id = u.id
WHERE u.username = 'cli2'
  AND NOT EXISTS (SELECT 1 FROM consents s WHERE s.client_id = c.id);



-- ============================================================
-- 1) STATUS (según tu entidad: id, status_ap (ENUM), description)
--    Evita duplicados si ya corriste el script antes.
-- ============================================================
INSERT INTO status (status_ap, description)
SELECT v.status_ap, v.description
FROM (VALUES
          ('PROGRAMADA',   'Cita creada; pendiente de confirmación'),
          ('CONFIRMADA',   'Sesión confirmada'),
          ('COMPLETADA',   'Sesión atendida y cerrada'),
          ('CANCELADA',    'Sesión cancelada'),
          ('INASISTENCIA', 'Cliente no asistió')
     ) AS v(status_ap, description)
WHERE NOT EXISTS (
    SELECT 1 FROM status s WHERE s.status_ap = v.status_ap
);


-- ============================================================
-- 2) APPOINTMENTS (id, client_id, status_id, schedules_id)
--    Crea 3 citas:
--      - pro1 + cli1, 13:00–14:00 -> CONFIRMADA
--      - pro2 + cli2, 09:00–10:00 -> PROGRAMADA
--      - pro3 + cli3, 19:00–20:00 -> CONFIRMADA
--    No depende de la fecha exacta: toma el horario más reciente
--    disponible para cada franja (ORDER BY s.date DESC).
--    Evita duplicados con NOT EXISTS por schedules_id.
-- ============================================================

-- pro1 + cli1, 13:00–14:00, CONFIRMADA
WITH pick AS (
    SELECT DISTINCT ON (s.time_start, s.time_ends)
    s.id AS schedules_id
FROM schedules s
    JOIN profesionals p ON p.id = s.profesional_id
    JOIN users up ON up.id = p.user_id
WHERE up.username = 'pro1'
  AND s.time_start = '13:00'::time
  AND s.time_ends  = '14:00'::time
ORDER BY s.time_start, s.time_ends, s.date DESC
    )
INSERT INTO appointments (schedules_id, client_id, status_id)
SELECT pk.schedules_id,
       c.id,
       st.id
FROM pick pk
         JOIN clients c  ON c.user_id = (SELECT id FROM users WHERE username = 'cli1')
         JOIN status  st ON st.status_ap = 'CONFIRMADA'
WHERE NOT EXISTS (
    SELECT 1 FROM appointments a WHERE a.schedules_id = pk.schedules_id
);

-- pro2 + cli2, 09:00–10:00, PROGRAMADA
WITH pick AS (
    SELECT DISTINCT ON (s.time_start, s.time_ends)
    s.id AS schedules_id
FROM schedules s
    JOIN profesionals p ON p.id = s.profesional_id
    JOIN users up ON up.id = p.user_id
WHERE up.username = 'pro2'
  AND s.time_start = '09:00'::time
  AND s.time_ends  = '10:00'::time
ORDER BY s.time_start, s.time_ends, s.date DESC
    )
INSERT INTO appointments (schedules_id, client_id, status_id)
SELECT pk.schedules_id,
       c.id,
       st.id
FROM pick pk
         JOIN clients c  ON c.user_id = (SELECT id FROM users WHERE username = 'cli2')
         JOIN status  st ON st.status_ap = 'PROGRAMADA'
WHERE NOT EXISTS (
    SELECT 1 FROM appointments a WHERE a.schedules_id = pk.schedules_id
);

-- pro3 + cli3, 19:00–20:00, CONFIRMADA
WITH pick AS (
    SELECT DISTINCT ON (s.time_start, s.time_ends)
    s.id AS schedules_id
FROM schedules s
    JOIN profesionals p ON p.id = s.profesional_id
    JOIN users up ON up.id = p.user_id
WHERE up.username = 'pro3'
  AND s.time_start = '19:00'::time
  AND s.time_ends  = '20:00'::time
ORDER BY s.time_start, s.time_ends, s.date DESC
    )
INSERT INTO appointments (schedules_id, client_id, status_id)
SELECT pk.schedules_id,
       c.id,
       st.id
FROM pick pk
         JOIN clients c  ON c.user_id = (SELECT id FROM users WHERE username = 'cli3')
         JOIN status  st ON st.status_ap = 'CONFIRMADA'
WHERE NOT EXISTS (
    SELECT 1 FROM appointments a WHERE a.schedules_id = pk.schedules_id
);

-- ==================================================================
-- SESSION_SUMMARIES: crea hasta 3 para appointments sin session aún
-- (task, progress y conclusion son NOT NULL, así que los llenamos)
-- ==================================================================
WITH pick AS (
    SELECT
        a.id AS appointment_id,
        ROW_NUMBER() OVER (ORDER BY a.id) AS rn
    FROM appointments a
             LEFT JOIN session_summaries ss ON ss.appointment_id = a.id
    WHERE ss.appointment_id IS NULL
    ORDER BY a.id
    LIMIT 3
    )
INSERT INTO session_summaries (appointment_id, task, progress, conclusion)
SELECT
    appointment_id,
    CASE rn
        WHEN 1 THEN 'Respiración diafragmática'
        WHEN 2 THEN 'Identificar disparadores'
        WHEN 3 THEN 'Rutina de higiene del sueño'
        ELSE 'Tarea'
        END AS task,
    CASE rn
        WHEN 1 THEN 'Practica 5 min diarios con conteo 4-4-6'
        WHEN 2 THEN 'Registro de detonantes por 3 días consecutivos'
        WHEN 3 THEN 'Ajuste de horario de sueño (+30 min consistentes)'
        ELSE 'En curso'
        END AS progress,
    CASE rn
        WHEN 1 THEN 'Refiere reducción leve de ansiedad'
        WHEN 2 THEN 'Mayor conciencia de patrones y señales tempranas'
        WHEN 3 THEN 'Mejor conciliación y somnolencia diurna reducida'
        ELSE 'Seguimiento pendiente'
        END AS conclusion
FROM pick;

-- ==========================================
-- DATOS DE EJEMPLO PARA CITAS PASADAS
-- ==========================================

-- 1. Un horario (Schedule) en el pasado (del pro1)
INSERT INTO schedules (date, time_start, time_ends, profesional_id)
SELECT '2025-10-20', '10:00:00', '11:00:00', p.id
FROM profesionals p WHERE p.user_id = (SELECT id FROM users WHERE username = 'pro1')
    ON CONFLICT DO NOTHING;

-- 2. Un estado "Completada" (Asumiendo ID=3, basado en tu Enum 'StatusAp')
INSERT INTO status (id, status_ap, description)
SELECT 3, 'COMPLETADA', 'La cita fue completada'
    WHERE NOT EXISTS (SELECT 1 FROM status WHERE id = 3);

-- 3. La Cita (Appointment) que conecta al cli1 con el schedule
INSERT INTO appointments (client_id, status_id, schedules_id) -- (Columna 'schedules_id' corregida)
SELECT
    (SELECT c.id FROM clients c WHERE c.user_id = (SELECT id FROM users WHERE username = 'cli1')),
    3, -- ID de "COMPLETADA"
    (SELECT s.id FROM schedules s WHERE s.date = '2025-10-20' AND s.time_start = '10:00:00')
    ON CONFLICT DO NOTHING;

-- 4. El Resumen (Session_Summary) de esa cita
INSERT INTO session_summaries (task, progress, conclusion, appointment_id)
SELECT
    'Revisión de técnicas de respiración.',
    'El cliente muestra mejora en el control de la ansiedad.',
    'Continuar con la práctica diaria. Próxima sesión en 2 semanas.',
    (SELECT a.id FROM appointments a WHERE a.schedules_id = (SELECT s.id FROM schedules s WHERE s.date = '2025-10-20')) -- (Columna 'schedules_id' corregida)
    ON CONFLICT DO NOTHING;

-- 5. Un Chat (Chats) asociado a esa cita
-- (Columnas 'appointment_id', 'mensaje', 'sender_type', 'timestamp' corregidas)
INSERT INTO chats (appointment_id, mensaje, sender_type, timestamp)
SELECT
    (SELECT a.id FROM appointments a WHERE a.schedules_id = (SELECT s.id FROM schedules s WHERE s.date = '2025-10-20')),
    'Hola Dr. Listo para nuestra sesión de hoy.',
    'CLIENTE',
    '2025-10-20 09:55:00'
    ON CONFLICT DO NOTHING;

INSERT INTO chats (appointment_id, mensaje, sender_type, timestamp)
SELECT
    (SELECT a.id FROM appointments a WHERE a.schedules_id = (SELECT s.id FROM schedules s WHERE s.date = '2025-10-20')),
    'Hola! Perfecto, te espero en la sala.',
    'PROFESIONAL',
    '2025-10-20 09:56:00'
    ON CONFLICT DO NOTHING;