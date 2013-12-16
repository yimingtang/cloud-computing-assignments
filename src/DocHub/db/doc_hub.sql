SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `doc_hub` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `doc_hub` ;

-- -----------------------------------------------------
-- Table `doc_hub`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`user` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `username` VARCHAR(32) NOT NULL,
  `password` VARCHAR(32) NOT NULL,
  `salt` VARCHAR(12) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NULL,
  `permission_level` TINYINT NOT NULL DEFAULT 1,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `active` TINYINT(1) NOT NULL DEFAULT TRUE,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`document_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`document_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `type_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`document` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(256) NOT NULL,
  `author` VARCHAR(256) NOT NULL,
  `year` YEAR NOT NULL,
  `pages` VARCHAR(45) NOT NULL,
  `abstract` TEXT NOT NULL,
  `keywords` VARCHAR(64) NOT NULL,
  `publisher` VARCHAR(128) NOT NULL,
  `url` VARCHAR(256) NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `document_type_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `published` TINYINT(1) NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX `fk_document_document_type1_idx` (`document_type_id` ASC),
  INDEX `fk_document_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_document_document_type1`
    FOREIGN KEY (`document_type_id`)
    REFERENCES `doc_hub`.`document_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `doc_hub`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`document_property_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`document_property_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`document_property`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`document_property` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `value` VARCHAR(64) NULL,
  `document_property_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_document_property_document_property_type1_idx` (`document_property_type_id` ASC),
  CONSTRAINT `fk_document_property_document_property_type1`
    FOREIGN KEY (`document_property_type_id`)
    REFERENCES `doc_hub`.`document_property_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`tag` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`document_has_document_property`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`document_has_document_property` (
  `document_id` INT UNSIGNED NOT NULL,
  `document_property_id` INT UNSIGNED NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  INDEX `fk_document_has_document_property_document_property1_idx` (`document_property_id` ASC),
  INDEX `fk_document_has_document_property_document1_idx` (`document_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_document_has_document_property_document1`
    FOREIGN KEY (`document_id`)
    REFERENCES `doc_hub`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_has_document_property_document_property1`
    FOREIGN KEY (`document_property_id`)
    REFERENCES `doc_hub`.`document_property` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`tagging`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`tagging` (
  `document_id` INT UNSIGNED NOT NULL,
  `tag_id` INT UNSIGNED NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `tagged_by` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_document_has_tag_tag1_idx` (`tag_id` ASC),
  INDEX `fk_document_has_tag_document1_idx` (`document_id` ASC),
  INDEX `fk_tagging_user1_idx` (`tagged_by` ASC),
  CONSTRAINT `fk_document_has_tag_document1`
    FOREIGN KEY (`document_id`)
    REFERENCES `doc_hub`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_has_tag_tag1`
    FOREIGN KEY (`tag_id`)
    REFERENCES `doc_hub`.`tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_tagging_user1`
    FOREIGN KEY (`tagged_by`)
    REFERENCES `doc_hub`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`comment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`comment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `content` TEXT NOT NULL,
  `rank` TINYINT NULL,
  `document_id` INT UNSIGNED NOT NULL,
  `user_id` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  `published` TINYINT(1) NOT NULL DEFAULT FALSE,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_document1_idx` (`document_id` ASC),
  INDEX `fk_comment_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_comment_document1`
    FOREIGN KEY (`document_id`)
    REFERENCES `doc_hub`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `doc_hub`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`reference_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`reference_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(64) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`reference`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`reference` (
  `source` INT UNSIGNED NOT NULL,
  `destination` INT UNSIGNED NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `reference_type_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_document_has_document_document2_idx` (`destination` ASC),
  INDEX `fk_document_has_document_document1_idx` (`source` ASC),
  INDEX `fk_reference_reference_type1_idx` (`reference_type_id` ASC),
  CONSTRAINT `fk_document_has_document_document1`
    FOREIGN KEY (`source`)
    REFERENCES `doc_hub`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_has_document_document2`
    FOREIGN KEY (`destination`)
    REFERENCES `doc_hub`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_reference_reference_type1`
    FOREIGN KEY (`reference_type_id`)
    REFERENCES `doc_hub`.`reference_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`attachment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`attachment` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `url` VARCHAR(256) NOT NULL,
  `type` TINYINT NOT NULL,
  `document_id` INT UNSIGNED NOT NULL,
  `created_by` INT UNSIGNED NOT NULL,
  `created_at` DATETIME NOT NULL,
  `updated_at` DATETIME NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_attachment_document1_idx` (`document_id` ASC),
  INDEX `fk_attachment_user1_idx` (`created_by` ASC),
  CONSTRAINT `fk_attachment_document1`
    FOREIGN KEY (`document_id`)
    REFERENCES `doc_hub`.`document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_attachment_user1`
    FOREIGN KEY (`created_by`)
    REFERENCES `doc_hub`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`comment_property_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`comment_property_type` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`comment_property`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`comment_property` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `value` TEXT NULL,
  `comment_property_type_id` INT UNSIGNED NOT NULL,
  `comment_id` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_comment_property_comment_property_type1_idx` (`comment_property_type_id` ASC),
  INDEX `fk_comment_property_comment1_idx` (`comment_id` ASC),
  CONSTRAINT `fk_comment_property_comment_property_type1`
    FOREIGN KEY (`comment_property_type_id`)
    REFERENCES `doc_hub`.`comment_property_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_comment_property_comment1`
    FOREIGN KEY (`comment_id`)
    REFERENCES `doc_hub`.`comment` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`document_type_has_document_property_type`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`document_type_has_document_property_type` (
  `document_type_id` INT UNSIGNED NOT NULL,
  `document_property_type_id` INT UNSIGNED NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  INDEX `fk_document_type_has_document_property_type_document_proper_idx` (`document_property_type_id` ASC),
  INDEX `fk_document_type_has_document_property_type_document_type1_idx` (`document_type_id` ASC),
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_document_type_has_document_property_type_document_type1`
    FOREIGN KEY (`document_type_id`)
    REFERENCES `doc_hub`.`document_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_document_type_has_document_property_type_document_property1`
    FOREIGN KEY (`document_property_type_id`)
    REFERENCES `doc_hub`.`document_property_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `doc_hub`.`user_log`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `doc_hub`.`user_log` (
  `user_id` INT UNSIGNED NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `type` SMALLINT NOT NULL,
  `description` VARCHAR(45) NULL,
  `created_at` DATETIME NOT NULL,
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_log_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `doc_hub`.`user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
