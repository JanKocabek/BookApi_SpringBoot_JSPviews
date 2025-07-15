CREATE DATABASE bookApi COLLATE utf8mb4_unicode_ci;
CREATE TABLE books
(
    id        INT(11)                                 NOT NULL AUTO_INCREMENT,
    isbn      VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    title     VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    author    VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    publisher VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    type      VARCHAR(255) COLLATE utf8mb4_unicode_ci NOT NULL,
    PRIMARY KEY (id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;