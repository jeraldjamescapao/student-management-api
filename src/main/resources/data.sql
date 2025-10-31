-- ========================================================================================================================
-- Non-Destructive Seed Data for School DB via MERGE.
-- Status Enums:
--   students.status ∈ {APPLIED, ADMITTED, ENROLLED, ON_LEAVE, SUSPENDED, WITHDRAWN, GRADUATED, INACTIVE}
--   enrollments.status ∈ {REGISTERED, ENROLLED, WAITLISTED, DROPPED, WITHDRAWN, COMPLETED, FAILED, INCOMPLETE, CANCELLED}
-- Author: Jerald James Capao
-- =======================================================================================================================

-- Students (unique by email)
INSERT INTO students (id, first_name, last_name, email, gender, birth_date, status, created_at, updated_at)
SELECT RANDOM_UUID(), 'Ada',     'Lovelacey', 'ada.lovelace@example.edu',    'FEMALE', DATE '2002-05-14', 'ENROLLED',  CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'ada.lovelace@example.edu');

INSERT INTO students (id, first_name, last_name, email, gender, birth_date, status, created_at, updated_at)
SELECT RANDOM_UUID(), 'Albert',  'Veinstein', 'albert.einstein@example.edu', 'MALE',   DATE '2001-09-02', 'GRADUATED', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'albert.einstein@example.edu');

INSERT INTO students (id, first_name, last_name, email, gender, birth_date, status, created_at, updated_at)
SELECT RANDOM_UUID(), 'Charles', 'Cabbage',   'charles.babbage@example.edu', 'OTHER',  DATE '2003-01-28', 'ON_LEAVE',  CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM students WHERE email = 'charles.babbage@example.edu');

-- Courses (unique by code)
INSERT INTO courses (id, code, title, description, credits, active, created_at, updated_at)
SELECT RANDOM_UUID(), 'CS101',   'Intro to Programming', 'Fundamentals in Java, Control Flow, Data Structures Basics.', 4, TRUE, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE code = 'CS101');

INSERT INTO courses (id, code, title, description, credits, active, created_at, updated_at)
SELECT RANDOM_UUID(), 'MATH201', 'Discrete Mathematics', 'Logic, Sets, Combinatorics, Graphs.',                         3, TRUE, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE code = 'MATH201');

INSERT INTO courses (id, code, title, description, credits, active, created_at, updated_at)
SELECT RANDOM_UUID(), 'HIST110', 'World History',        'Survey of World Civilizations.',                               3, TRUE, CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (SELECT 1 FROM courses WHERE code = 'HIST110');

-- Enrollments (unique by student+course+term+section)
-- Ada x CS101 (2025-FALL, A, ENROLLED)
INSERT INTO enrollments (id, student_id, course_id, term, section, status, created_at, updated_at)
SELECT RANDOM_UUID(),
       (SELECT id FROM students WHERE email = 'ada.lovelace@example.edu'),
       (SELECT id FROM courses  WHERE code  = 'CS101'),
       '2025-FALL', 'A', 'ENROLLED', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (
  SELECT 1 FROM enrollments e
  JOIN students s ON s.id = e.student_id
  JOIN courses  c ON c.id = e.course_id
  WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'CS101' AND e.term = '2025-FALL' AND e.section = 'A'
);

-- Ada x MATH201 (2025-FALL, A, COMPLETED)
INSERT INTO enrollments (id, student_id, course_id, term, section, status, created_at, updated_at)
SELECT RANDOM_UUID(),
       (SELECT id FROM students WHERE email = 'ada.lovelace@example.edu'),
       (SELECT id FROM courses  WHERE code  = 'MATH201'),
       '2025-FALL', 'A', 'COMPLETED', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (
  SELECT 1 FROM enrollments e
  JOIN students s ON s.id = e.student_id
  JOIN courses  c ON c.id = e.course_id
  WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'MATH201' AND e.term = '2025-FALL' AND e.section = 'A'
);

-- Albert x CS101 (2025-FALL, B1, FAILED)
INSERT INTO enrollments (id, student_id, course_id, term, section, status, created_at, updated_at)
SELECT RANDOM_UUID(),
       (SELECT id FROM students WHERE email = 'albert.einstein@example.edu'),
       (SELECT id FROM courses  WHERE code  = 'CS101'),
       '2025-FALL', 'B1', 'FAILED', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (
  SELECT 1 FROM enrollments e
  JOIN students s ON s.id = e.student_id
  JOIN courses  c ON c.id = e.course_id
  WHERE s.email = 'albert.einstein@example.edu' AND c.code = 'CS101' AND e.term = '2025-FALL' AND e.section = 'B1'
);

-- Charles x HIST110 (2025-FALL, LEC-01, WAITLISTED)
INSERT INTO enrollments (id, student_id, course_id, term, section, status, created_at, updated_at)
SELECT RANDOM_UUID(),
       (SELECT id FROM students WHERE email = 'charles.babbage@example.edu'),
       (SELECT id FROM courses  WHERE code  = 'HIST110'),
       '2025-FALL', 'LEC-01', 'WAITLISTED', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (
  SELECT 1 FROM enrollments e
  JOIN students s ON s.id = e.student_id
  JOIN courses  c ON c.id = e.course_id
  WHERE s.email = 'charles.babbage@example.edu' AND c.code = 'HIST110' AND e.term = '2025-FALL' AND e.section = 'LEC-01'
);

-- Grades (one per enrollment_id) ----------
-- Ada CS101 -> A
INSERT INTO grades (id, enrollment_id, letter, points, graded_at, notes, created_at, updated_at)
SELECT RANDOM_UUID(),
       (SELECT e.id FROM enrollments e
        JOIN students s ON s.id = e.student_id
        JOIN courses  c ON c.id = e.course_id
        WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'CS101' AND e.term = '2025-FALL' AND e.section = 'A'),
       'A', 6.00, CURRENT_TIMESTAMP, 'Excellent performance', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (
  SELECT 1 FROM grades g
  WHERE g.enrollment_id = (
    SELECT e.id FROM enrollments e
    JOIN students s ON s.id = e.student_id
    JOIN courses  c ON c.id = e.course_id
    WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'CS101' AND e.term = '2025-FALL' AND e.section = 'A'
  )
);

-- Ada MATH201 -> B+
INSERT INTO grades (id, enrollment_id, letter, points, graded_at, notes, created_at, updated_at)
SELECT RANDOM_UUID(),
       (SELECT e.id FROM enrollments e
        JOIN students s ON s.id = e.student_id
        JOIN courses  c ON c.id = e.course_id
        WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'MATH201' AND e.term = '2025-FALL' AND e.section = 'A'),
       'B+', 3.50, CURRENT_TIMESTAMP, 'Solid understanding', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (
  SELECT 1 FROM grades g
  WHERE g.enrollment_id = (
    SELECT e.id FROM enrollments e
    JOIN students s ON s.id = e.student_id
    JOIN courses  c ON c.id = e.course_id
    WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'MATH201' AND e.term = '2025-FALL' AND e.section = 'A'
  )
);

-- Albert CS101 -> F
INSERT INTO grades (id, enrollment_id, letter, points, graded_at, notes, created_at, updated_at)
SELECT RANDOM_UUID(),
       (SELECT e.id FROM enrollments e
        JOIN students s ON s.id = e.student_id
        JOIN courses  c ON c.id = e.course_id
        WHERE s.email = 'albert.einstein@example.edu' AND c.code = 'CS101' AND e.term = '2025-FALL' AND e.section = 'B1'),
       'F', 0.00, CURRENT_TIMESTAMP, 'Course failed', CURRENT_TIMESTAMP, NULL
WHERE NOT EXISTS (
  SELECT 1 FROM grades g
  WHERE g.enrollment_id = (
    SELECT e.id FROM enrollments e
    JOIN students s ON s.id = e.student_id
    JOIN courses  c ON c.id = e.course_id
    WHERE s.email = 'albert.einstein@example.edu' AND c.code = 'CS101' AND e.term = '2025-FALL' AND e.section = 'B1'
  )
);