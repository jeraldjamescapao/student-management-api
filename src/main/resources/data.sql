-- ========================================================================================================================
-- Non-Destructive Seed Data for School DB via MERGE.
-- Status Enums:
--   students.status ∈ {APPLIED, ADMITTED, ENROLLED, ON_LEAVE, SUSPENDED, WITHDRAWN, GRADUATED, INACTIVE}
--   enrollments.status ∈ {REGISTERED, ENROLLED, WAITLISTED, DROPPED, WITHDRAWN, COMPLETED, FAILED, INCOMPLETE, CANCELLED}
-- Author: Jerald James Capao
-- =======================================================================================================================

-- Students
MERGE INTO students KEY(id) VALUES
  (RANDOM_UUID(), 'Ada', 'Lovelacey', 'ada.lovelace@example.edu', 'FEMALE', DATE '2002-05-14', 'ENROLLED',  CURRENT_TIMESTAMP, NULL, NULL),
  (RANDOM_UUID(), 'Albert', 'Veinstein', 'albert.einstein@example.edu', 'MALE', DATE '2001-09-02', 'GRADUATED', CURRENT_TIMESTAMP, NULL, NULL),
  (RANDOM_UUID(), 'Charles', 'Cabbage', 'charles.babbage@example.edu', 'OTHER', DATE '2003-01-28', 'ON_LEAVE',  CURRENT_TIMESTAMP, NULL, NULL);

-- Courses
MERGE INTO courses KEY(id) VALUES
  (RANDOM_UUID(), 'CS101', 'Intro to Programming', 'Fundamentals in Java, Control Flow, Data Structures Basics.', 4, TRUE, CURRENT_TIMESTAMP, NULL),
  (RANDOM_UUID(), 'MATH201', 'Discrete Mathematics', 'Logic, Sets, Combinatorics, Graphs.', 3, TRUE, CURRENT_TIMESTAMP, NULL),
  (RANDOM_UUID(), 'HIST110', 'World History', 'Survey of World Civilizations.', 3, TRUE, CURRENT_TIMESTAMP, NULL);

-- Enrollments
MERGE INTO enrollments KEY(id)
SELECT RANDOM_UUID(), s.id, c.id, '2025-FALL', 'A', 'ENROLLED', CURRENT_TIMESTAMP, NULL
FROM students s, courses c
WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'CS101'
UNION ALL
SELECT RANDOM_UUID(), s.id, c.id, '2025-FALL', 'A', 'COMPLETED', CURRENT_TIMESTAMP, NULL
FROM students s, courses c
WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'MATH201'
UNION ALL
SELECT RANDOM_UUID(), s.id, c.id, '2025-FALL', 'B1', 'FAILED', CURRENT_TIMESTAMP, NULL
FROM students s, courses c
WHERE s.email = 'albert.einstein@example.edu' AND c.code = 'CS101'
UNION ALL
SELECT RANDOM_UUID(), s.id, c.id, '2025-FALL', 'LEC-01', 'WAITLISTED', CURRENT_TIMESTAMP, NULL
FROM students s, courses c
WHERE s.email = 'charles.babbage@example.edu' AND c.code = 'HIST110';

-- Grades
MERGE INTO grades KEY(id)
SELECT RANDOM_UUID(), e.id, 'A', 6.00, CURRENT_TIMESTAMP, 'Excellent performance', CURRENT_TIMESTAMP, NULL
FROM enrollments e
JOIN students s ON e.student_id = s.id
JOIN courses c ON e.course_id = c.id
WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'CS101'
UNION ALL
SELECT RANDOM_UUID(), e.id, 'B+', 3.50, CURRENT_TIMESTAMP, 'Solid understanding', CURRENT_TIMESTAMP, NULL
FROM enrollments e
JOIN students s ON e.student_id = s.id
JOIN courses c ON e.course_id = c.id
WHERE s.email = 'ada.lovelace@example.edu' AND c.code = 'MATH201'
UNION ALL
SELECT RANDOM_UUID(), e.id, 'F', 0.00, CURRENT_TIMESTAMP, 'Course failed', CURRENT_TIMESTAMP, NULL
FROM enrollments e
JOIN students s ON e.student_id = s.id
JOIN courses c ON e.course_id = c.id
WHERE s.email = 'albert.einstein@example.edu' AND c.code = 'CS101';