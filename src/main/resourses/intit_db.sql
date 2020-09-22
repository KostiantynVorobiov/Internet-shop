CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products`
(
    `product_id` BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255) NOT NULL,
    `price`      DOUBLE       NOT NULL,
    `deleted`    TINYINT      NOT NULL DEFAULT 0,
    PRIMARY KEY (`product_id`)
);

CREATE TABLE `internet_shop`.`users`
(
    `user_id`  BIGINT(11)   NOT NULL AUTO_INCREMENT,
    `name`     VARCHAR(256) NOT NULL,
    `login`    VARCHAR(256) NOT NULL,
    `password` VARCHAR(256) NOT NULL,
    `deleted`  TINYINT      NOT NULL DEFAULT 0,
    PRIMARY KEY (`user_id`)
);

CREATE TABLE `internet_shop`.`roles`
(
    `role_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`role_id`)
);