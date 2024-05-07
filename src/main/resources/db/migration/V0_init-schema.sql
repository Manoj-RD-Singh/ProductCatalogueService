CREATE TABLE j_instructor
(
    user_id BIGINT NOT NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_j_instructor PRIMARY KEY (user_id)
);

CREATE TABLE j_mentor
(
    user_id        BIGINT NOT NULL,
    num_of_session BIGINT NULL,
    CONSTRAINT pk_j_mentor PRIMARY KEY (user_id)
);

CREATE TABLE j_ta
(
    user_id            BIGINT NOT NULL,
    num_doubt_resolved BIGINT NULL,
    CONSTRAINT pk_j_ta PRIMARY KEY (user_id)
);

CREATE TABLE j_user
(
    id    BIGINT AUTO_INCREMENT NOT NULL,
    name  VARCHAR(255) NULL,
    email VARCHAR(255) NULL,
    CONSTRAINT pk_j_user PRIMARY KEY (id)
);

CREATE TABLE msc_instructor
(
    id      BIGINT AUTO_INCREMENT NOT NULL,
    name    VARCHAR(255) NULL,
    email   VARCHAR(255) NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_msc_instructor PRIMARY KEY (id)
);

CREATE TABLE msc_mentor
(
    id             BIGINT AUTO_INCREMENT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    num_of_session BIGINT NULL,
    CONSTRAINT pk_msc_mentor PRIMARY KEY (id)
);

CREATE TABLE msc_ta
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    num_doubt_resolved BIGINT NULL,
    CONSTRAINT pk_msc_ta PRIMARY KEY (id)
);

CREATE TABLE product
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    last_modified_at datetime NULL,
    status           SMALLINT NULL,
    name             VARCHAR(255) NULL,
    `description`    VARCHAR(255) NULL,
    imageurl         VARCHAR(255) NULL,
    price DOUBLE NULL,
    is_prime_product BIT(1) NULL,
    category_id      BIGINT NULL,
    CONSTRAINT pk_product PRIMARY KEY (id)
);

CREATE TABLE product_category
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    last_modified_at datetime NULL,
    status           SMALLINT NULL,
    name             VARCHAR(255) NULL,
    `description`    VARCHAR(255) NULL,
    CONSTRAINT pk_productcategory PRIMARY KEY (id)
);

CREATE TABLE schema_versioning_test_model
(
    id               BIGINT AUTO_INCREMENT NOT NULL,
    created_at       datetime NULL,
    last_modified_at datetime NULL,
    status           SMALLINT NULL,
    CONSTRAINT pk_schemaversioningtestmodel PRIMARY KEY (id)
);

CREATE TABLE st_user
(
    id                 BIGINT AUTO_INCREMENT NOT NULL,
    user_type          INT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    num_of_session     BIGINT NULL,
    company            VARCHAR(255) NULL,
    num_doubt_resolved BIGINT NULL,
    CONSTRAINT pk_st_user PRIMARY KEY (id)
);

CREATE TABLE tpc_instructor
(
    id      BIGINT NOT NULL,
    name    VARCHAR(255) NULL,
    email   VARCHAR(255) NULL,
    company VARCHAR(255) NULL,
    CONSTRAINT pk_tpc_instructor PRIMARY KEY (id)
);

CREATE TABLE tpc_mentor
(
    id             BIGINT NOT NULL,
    name           VARCHAR(255) NULL,
    email          VARCHAR(255) NULL,
    num_of_session BIGINT NULL,
    CONSTRAINT pk_tpc_mentor PRIMARY KEY (id)
);

CREATE TABLE tpc_ta
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255) NULL,
    email              VARCHAR(255) NULL,
    num_doubt_resolved BIGINT NULL,
    CONSTRAINT pk_tpc_ta PRIMARY KEY (id)
);

ALTER TABLE j_instructor
    ADD CONSTRAINT FK_J_INSTRUCTOR_ON_USER FOREIGN KEY (user_id) REFERENCES j_user (id);

ALTER TABLE j_mentor
    ADD CONSTRAINT FK_J_MENTOR_ON_USER FOREIGN KEY (user_id) REFERENCES j_user (id);

ALTER TABLE j_ta
    ADD CONSTRAINT FK_J_TA_ON_USER FOREIGN KEY (user_id) REFERENCES j_user (id);

ALTER TABLE product
    ADD CONSTRAINT FK_PRODUCT_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES product_category (id);