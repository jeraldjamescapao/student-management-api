-- ========================================================================================================================
-- Non-Destructive Seed Data for School DB via MERGE.
-- Status Enums:
--   students.status ∈ {APPLIED, ADMITTED, ENROLLED, ON_LEAVE, SUSPENDED, WITHDRAWN, GRADUATED, INACTIVE}
--   enrollments.status ∈ {REGISTERED, ENROLLED, WAITLISTED, DROPPED, WITHDRAWN, COMPLETED, FAILED, INCOMPLETE, CANCELLED}
-- Author: Jerald James Capao
-- =======================================================================================================================

-- Students
MERGE INTO students KEY(id) VALUES
  (CAST('11111111-1111-1111-1111-111111111111' AS UUID), 'Ada', 'Lovelacey', 'ada.lovelace@example.edu', 'FEMALE', DATE '2002-05-14', 'ENROLLED',  CURRENT_TIMESTAMP, NULL, NULL),
  (CAST('22222222-2222-2222-2222-222222222222' AS UUID), 'Albert', 'Veinstein', 'albert.einstein@example.edu', 'MALE', DATE '2001-09-02', 'GRADUATED', CURRENT_TIMESTAMP, NULL, NULL),
  (CAST('33333333-3333-3333-3333-333333333333' AS UUID), 'Charles', 'Cabbage', 'charles.babbage@example.edu', 'OTHER', DATE '2003-01-28', 'ON_LEAVE',  CURRENT_TIMESTAMP, NULL, NULL);

-- Courses
MERGE INTO courses KEY(id) VALUES
  (CAST('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa' AS UUID), 'CS101', 'Intro to Programming', 'Fundamentals in Java, Control Flow, Data Structures Basics.', 4, TRUE, CURRENT_TIMESTAMP, NULL),
  (CAST('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb' AS UUID), 'MATH201', 'Discrete Mathematics', 'Logic, Sets, Combinatorics, Graphs.', 3, TRUE, CURRENT_TIMESTAMP, NULL),
  (CAST('cccccccc-cccc-cccc-cccc-cccccccccccc' AS UUID), 'HIST110', 'World History', 'Survey of World Civilizations.', 3, TRUE, CURRENT_TIMESTAMP, NULL);

-- Enrollments
MERGE INTO enrollments KEY(id) VALUES
  (CAST('d1d1d1d1-d1d1-d1d1-d1d1-d1d1d1d1d1d1' AS UUID), CAST('11111111-1111-1111-1111-111111111111' AS UUID), CAST('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa' AS UUID), '2025-FALL', 'A', 'ENROLLED',  CURRENT_TIMESTAMP, NULL),
  (CAST('d2d2d2d2-d2d2-d2d2-d2d2-d2d2d2d2d2d2' AS UUID), CAST('11111111-1111-1111-1111-111111111111' AS UUID), CAST('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb' AS UUID), '2025-FALL', 'A', 'COMPLETED', CURRENT_TIMESTAMP, NULL),
  (CAST('d3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3' AS UUID), CAST('22222222-2222-2222-2222-222222222222' AS UUID), CAST('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa' AS UUID), '2025-FALL', 'B1', 'FAILED',    CURRENT_TIMESTAMP, NULL),
  (CAST('d4d4d4d4-d4d4-d4d4-d4d4-d4d4d4d4d4d4' AS UUID), CAST('33333333-3333-3333-3333-333333333333' AS UUID), CAST('cccccccc-cccc-cccc-cccc-cccccccccccc' AS UUID), '2025-FALL', 'LEC-01', 'WAITLISTED', CURRENT_TIMESTAMP, NULL);

-- Grades
MERGE INTO grades KEY(id) VALUES
  (CAST('e1e1e1e1-e1e1-e1e1-e1e1-e1e1e1e1e1e1' AS UUID), CAST('d1d1d1d1-d1d1-d1d1-d1d1-d1d1d1d1d1d1' AS UUID), 'A',  6.00, CURRENT_TIMESTAMP, 'Excellent performance', CURRENT_TIMESTAMP, NULL),
  (CAST('e2e2e2e2-e2e2-e2e2-e2e2-e2e2e2e2e2e2' AS UUID), CAST('d2d2d2d2-d2d2-d2d2-d2d2-d2d2d2d2d2d2' AS UUID), 'B+', 3.50, CURRENT_TIMESTAMP, 'Solid understanding', CURRENT_TIMESTAMP, NULL),
  (CAST('e3e3e3e3-e3e3-e3e3-e3e3-e3e3e3e3e3e3' AS UUID), CAST('d3d3d3d3-d3d3-d3d3-d3d3-d3d3d3d3d3d3' AS UUID), 'F',  0.00, CURRENT_TIMESTAMP, 'Course failed', CURRENT_TIMESTAMP, NULL);