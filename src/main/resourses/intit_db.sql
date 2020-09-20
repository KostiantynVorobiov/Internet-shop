CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products`
(
    `product_id` BIGINT(11)      NOT NULL AUTO_INCREMENT,
    `name`       VARCHAR(255)    NOT NULL,
    `price`      DOUBLE ZEROFILL NOT NULL,
    `deleted`    TINYINT         NOT NULL DEFAULT 0,
    PRIMARY KEY (`product_id`),
    UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE
);
