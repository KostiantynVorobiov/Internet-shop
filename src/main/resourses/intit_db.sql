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
    `name`     VARCHAR(255) NOT NULL,
    `login`    VARCHAR(255) NOT NULL,
    `password` VARCHAR(255) NULL,
    `deleted`  TINYINT      NOT NULL DEFAULT 0,
    PRIMARY KEY (`user_id`),
    UNIQUE INDEX `login_UNIQUE` (`login` ASC) VISIBLE
);

ALTER TABLE `internet_shop`.`users`
    ADD COLUMN `salt` VARBINARY(16) NOT NULL AFTER `password`;

CREATE TABLE `internet_shop`.`roles`
(
    `role_id`   BIGINT       NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(256) NOT NULL,
    PRIMARY KEY (`role_id`)
);

INSERT INTO roles (role_name)
VALUES ('USER');
INSERT INTO roles (role_name)
VALUES ('ADMIN');

CREATE TABLE `internet_shop`.`users_roles`
(
    `id`      BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `role_id` BIGINT(11) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `user_id_idx` (`user_id` ASC) VISIBLE,
    INDEX `role_id_idx` (`role_id` ASC) VISIBLE,
    CONSTRAINT `user_id_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `role_id_fk`
        FOREIGN KEY (`role_id`)
            REFERENCES `internet_shop`.`roles` (`role_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`shopping_carts`
(
    `cart_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `deleted` TINYINT    NOT NULL DEFAULT 0,
    PRIMARY KEY (`cart_id`),
    INDEX `cart_id_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `user_cart_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`shopping_carts_products`
(
    `id`         BIGINT(11) NOT NULL AUTO_INCREMENT,
    `cart_id`    BIGINT(11) NOT NULL,
    `product_id` BIGINT(11) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `cart_cart_id_fk_idx` (`cart_id` ASC) VISIBLE,
    INDEX `products_product_id_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `carts_cart_id_fk`
        FOREIGN KEY (`cart_id`)
            REFERENCES `internet_shop`.`shopping_carts` (`cart_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `products_product_id_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`orders`
(
    `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id`  BIGINT(11) NOT NULL,
    `deleted`  TINYINT    NOT NULL DEFAULT 0,
    PRIMARY KEY (`order_id`),
    INDEX `user_order_id_fk_idx` (`user_id` ASC) VISIBLE,
    CONSTRAINT `user_order_id_fk`
        FOREIGN KEY (`user_id`)
            REFERENCES `internet_shop`.`users` (`user_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`orders_products`
(
    `id`         BIGINT(11) NOT NULL AUTO_INCREMENT,
    `order_id`   BIGINT(11) NOT NULL,
    `product_id` BIGINT(11) NOT NULL,
    PRIMARY KEY (`id`),
    INDEX `orders_order_id_fk_idx` (`order_id` ASC) VISIBLE,
    INDEX `products_product_id_fk_idx` (`product_id` ASC) VISIBLE,
    CONSTRAINT `orders_order_id_fk`
        FOREIGN KEY (`order_id`)
            REFERENCES `internet_shop`.`orders` (`order_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
    CONSTRAINT `products_to_product_id_fk`
        FOREIGN KEY (`product_id`)
            REFERENCES `internet_shop`.`products` (`product_id`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
);
