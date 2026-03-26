CREATE TABLE IF NOT EXISTS users
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    surname VARCHAR(255),
    age INT,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone_number VARCHAR(55) NOT NULL,
    avatar VARCHAR(255),
    account_type VARCHAR(50) NOT NULL
    );

CREATE TABLE IF NOT EXISTS categories
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id INT,
    CONSTRAINT fk_categories_parent FOREIGN KEY (parent_id) REFERENCES categories(id)
    );

CREATE TABLE IF NOT EXISTS resumes
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    applicant_id INT NOT NULL,
    name VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    salary DECIMAL(12,2),
    is_active BOOLEAN NOT NULL,
    created_date TIMESTAMP NOT NULL,
    update_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_resumes_applicant FOREIGN KEY (applicant_id) REFERENCES users(id),
    CONSTRAINT fk_resumes_category FOREIGN KEY (category_id) REFERENCES categories(id)
    );

CREATE TABLE IF NOT EXISTS contact_types
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    type VARCHAR(255) NOT NULL
    );

CREATE TABLE IF NOT EXISTS contacts_info
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    type_id INT NOT NULL,
    resume_id INT NOT NULL,
    value VARCHAR(255) NOT NULL,
    CONSTRAINT fk_contacts_type FOREIGN KEY (type_id) REFERENCES contact_types(id),
    CONSTRAINT fk_contacts_resume FOREIGN KEY (resume_id) REFERENCES resumes(id)
    );

CREATE TABLE IF NOT EXISTS education_info
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    resume_id INT NOT NULL,
    institution VARCHAR(255) NOT NULL,
    program VARCHAR(255) NOT NULL,
    start_date DATE,
    end_date DATE,
    degree VARCHAR(255),
    CONSTRAINT fk_education_resume FOREIGN KEY (resume_id) REFERENCES resumes(id)
    );

CREATE TABLE IF NOT EXISTS work_experience_info
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    resume_id INT NOT NULL,
    years INT,
    company_name VARCHAR(255) NOT NULL,
    position VARCHAR(255) NOT NULL,
    responsibilities VARCHAR(1000),
    CONSTRAINT fk_work_resume FOREIGN KEY (resume_id) REFERENCES resumes(id)
    );

CREATE TABLE IF NOT EXISTS vacancies
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description VARCHAR(2000),
    category_id INT NOT NULL,
    salary DECIMAL(12,2),
    exp_from INT,
    exp_to INT,
    is_active BOOLEAN NOT NULL,
    author_id INT NOT NULL,
    created_date TIMESTAMP NOT NULL,
    update_time TIMESTAMP NOT NULL,
    CONSTRAINT fk_vacancies_category FOREIGN KEY (category_id) REFERENCES categories(id),
    CONSTRAINT fk_vacancies_author FOREIGN KEY (author_id) REFERENCES users(id)
    );

CREATE TABLE IF NOT EXISTS responded_applicants
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    resume_id INT NOT NULL,
    vacancy_id INT NOT NULL,
    confirmation BOOLEAN,
    CONSTRAINT fk_response_resume FOREIGN KEY (resume_id) REFERENCES resumes(id),
    CONSTRAINT fk_response_vacancy FOREIGN KEY (vacancy_id) REFERENCES vacancies(id)
    );

CREATE TABLE IF NOT EXISTS messages
(
    id INT AUTO_INCREMENT PRIMARY KEY,
    responded_applicants INT NOT NULL,
    content VARCHAR(2000) NOT NULL,
    timestamp TIMESTAMP NOT NULL,
    CONSTRAINT fk_messages_response FOREIGN KEY (responded_applicants) REFERENCES responded_applicants(id)
    );