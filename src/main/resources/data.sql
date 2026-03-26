INSERT INTO users (name, surname, age, email, password, phone_number, avatar, account_type)
VALUES
    ('Mega', 'Soft', 10, 'company@mail.com', '1234', '+996700000001', 'default-avatar.png', 'EMPLOYER'),
    ('Ivan', 'Ivanov', 24, 'ivan@mail.com', '1234', '+996700000002', 'default-avatar.png', 'APPLICANT'),
    ('Aigerim', 'Asanova', 22, 'aigerim@mail.com', '1234', '+996700000003', 'default-avatar.png', 'APPLICANT');

INSERT INTO categories (name, parent_id)
VALUES
    ('IT', NULL),
    ('Backend', 1),
    ('Frontend', 1),
    ('Design', NULL);

INSERT INTO contact_types (type)
VALUES
    ('EMAIL'),
    ('PHONE'),
    ('TELEGRAM'),
    ('LINKEDIN');

INSERT INTO resumes (applicant_id, name, category_id, salary, is_active, created_date, update_time)
VALUES
    (2, 'Java Developer', 2, 80000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    (3, 'Frontend Developer', 3, 70000, TRUE, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO contacts_info (type_id, resume_id, value)
VALUES
    (1, 1, 'ivan@mail.com'),
    (2, 1, '+996700000002'),
    (3, 1, '@ivan_java'),
    (1, 2, 'aigerim@mail.com'),
    (2, 2, '+996700000003');

INSERT INTO education_info (resume_id, institution, program, start_date, end_date, degree)
VALUES
    (1, 'AUCA', 'Computer Science', '2019-09-01', '2023-06-30', 'Bachelor'),
    (2, 'KNU', 'Software Engineering', '2020-09-01', '2024-06-30', 'Bachelor');

INSERT INTO work_experience_info (resume_id, years, company_name, position, responsibilities)
VALUES
    (1, 2, 'Tech Soft', 'Junior Java Developer', 'Backend development'),
    (2, 1, 'Web Studio', 'Frontend Developer', 'Frontend development');

INSERT INTO vacancies (name, description, category_id, salary, exp_from, exp_to, is_active, author_id, created_date, update_time)
VALUES
    ('Java Developer', 'Spring Boot developer needed', 2, 100000, 1, 3, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
    ('Frontend Developer', 'JavaScript developer needed', 3, 90000, 1, 2, TRUE, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO responded_applicants (resume_id, vacancy_id, confirmation)
VALUES
    (1, 1, FALSE),
    (2, 2, TRUE);

INSERT INTO messages (responded_applicants, content, timestamp)
VALUES
    (1, 'Здравствуйте, хотел бы обсудить вакансию', CURRENT_TIMESTAMP),
    (1, 'Добрый день, напишите подробнее о своем опыте', CURRENT_TIMESTAMP),
    (2, 'Спасибо, ждем вас на собеседовании', CURRENT_TIMESTAMP);