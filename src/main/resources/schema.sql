-- ===================================================================
-- The Non-destructive (idempotent) Schema of School DB for Demos.
-- Author: Jerald James Capao
-- ===================================================================

-- Students
CREATE TABLE IF NOT EXISTS students (
    id UUID     PRIMARY KEY,
    first_name  VARCHAR(100) NOT NULL,
    last_name   VARCHAR(100) NOT NULL,
    email       VARCHAR(320) NOT NULL, -- RFC-compliant max
    gender      VARCHAR(10) NOT NULL, -- enum via CHECK
    birth_date  DATE NOT NULL,
    status      VARCHAR(20) NOT NULL, -- enum via CHECK
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NULL,
    deleted_at  TIMESTAMP WITH TIME ZONE NULL, -- soft delete
    CONSTRAINT  uq_students_email UNIQUE (email),
    CONSTRAINT  ck_students_status CHECK (status IN (
        'APPLIED','ADMITTED','ENROLLED','ON_LEAVE',
        'SUSPENDED','WITHDRAWN','GRADUATED','INACTIVE'
    )),
    CONSTRAINT ck_students_gender CHECK (gender IN ('MALE','FEMALE','OTHER'))
);

CREATE INDEX IF NOT EXISTS ix_students_last_first ON students(last_name, first_name);
CREATE INDEX IF NOT EXISTS ix_students_status ON students(status);

-- Courses
CREATE TABLE IF NOT EXISTS courses (
    id UUID     PRIMARY KEY,
    code        VARCHAR(32) NOT NULL,
    title       VARCHAR(200) NOT NULL,
    description VARCHAR(2000),
    credits     INT NOT NULL CHECK (credits BETWEEN 0 AND 60),
    active      BOOLEAN NOT NULL DEFAULT TRUE,
    created_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP WITH TIME ZONE NULL,
    deleted_at  TIMESTAMP WITH TIME ZONE NULL, -- soft delete
    CONSTRAINT  uq_courses_code UNIQUE (code)
);

CREATE INDEX IF NOT EXISTS ix_courses_active ON courses(active);

-- Enrollments
CREATE TABLE IF NOT EXISTS enrollments (
    id UUID     PRIMARY KEY,
    student_id UUID NOT NULL,
    course_id  UUID NOT NULL,
    term       VARCHAR(20) NOT NULL, -- e.g., 2025-FALL
    section    VARCHAR(10) NOT NULL, -- e.g., A, B1, LEC-01
    status     VARCHAR(20) NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL,
    deleted_at  TIMESTAMP WITH TIME ZONE NULL, -- soft delete
    CONSTRAINT fk_enrollments_student
      FOREIGN KEY (student_id) REFERENCES students(id),
    CONSTRAINT fk_enrollments_course
      FOREIGN KEY (course_id)  REFERENCES courses(id),
    CONSTRAINT uq_enrollments UNIQUE (student_id, course_id, term, section),
    CONSTRAINT ck_enrollments_status CHECK (status IN (
        'REGISTERED','ENROLLED','WAITLISTED','DROPPED',
        'WITHDRAWN','COMPLETED','FAILED','INCOMPLETE','CANCELLED'
    ))
);

CREATE INDEX IF NOT EXISTS ix_enrollments_student ON enrollments(student_id);
CREATE INDEX IF NOT EXISTS ix_enrollments_course  ON enrollments(course_id);
CREATE INDEX IF NOT EXISTS ix_enrollments_status  ON enrollments(status);

-- Grades
CREATE TABLE IF NOT EXISTS grades (
    id UUID PRIMARY KEY,
    enrollment_id UUID NOT NULL,
    letter     VARCHAR(2) NOT NULL, -- A, B+, C-, I, W...
    points     DECIMAL(3,2) NOT NULL CHECK (points BETWEEN 0 AND 6.0),
    graded_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    notes      CLOB,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP WITH TIME ZONE NULL,
    deleted_at  TIMESTAMP WITH TIME ZONE NULL, -- soft delete
    CONSTRAINT fk_grades_enrollment
      FOREIGN KEY (enrollment_id) REFERENCES enrollments(id)
);

CREATE INDEX IF NOT EXISTS ix_grades_enrollment ON grades(enrollment_id);
CREATE INDEX IF NOT EXISTS ix_grades_letter     ON grades(letter);