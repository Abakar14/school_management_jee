-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema bytmasoft
-- -----------------------------------------------------
-- Datamodel for user management
DROP SCHEMA IF EXISTS `bytmasoft` ;

-- -----------------------------------------------------
-- Schema bytmasoft
--
-- Datamodel for user management
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bytmasoft` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
SHOW WARNINGS;
USE `bytmasoft` ;

-- -----------------------------------------------------
-- Table `role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `role` (
  `role_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NOT NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(1) NULL DEFAULT '1',
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
COMMENT = 'The role table contain all roles of the System (Rolle | funktion)';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `country` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `country` (
  `country_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `abbreviations` VARCHAR(15) NULL COMMENT 'Länderabkurzungen (DE, FR)',
  `status` VARCHAR(1) NULL DEFAULT '1',
  PRIMARY KEY (`country_id`))
ENGINE = InnoDB;

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `state`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `state` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `state` (
  `state_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `substate_name` VARCHAR(45) NULL,
  `country_id_fk` INT NOT NULL,
  `status` VARCHAR(1) NULL DEFAULT '1',
  PRIMARY KEY (`state_id`),
  CONSTRAINT `country_id_fk`
    FOREIGN KEY (`country_id_fk`)
    REFERENCES `country` (`country_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `country_id_fk_idx` ON `state` (`country_id_fk` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `address`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `address` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `address` (
  `address_id` INT NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NULL,
  `street` VARCHAR(45) NULL,
  `postcode` VARCHAR(45) NULL,
  `number` VARCHAR(45) NULL,
  `state_id_fk` INT NULL,
  `status` VARCHAR(1) NULL DEFAULT '1',
  PRIMARY KEY (`address_id`),
  CONSTRAINT `state_id_fk`
    FOREIGN KEY (`state_id_fk`)
    REFERENCES `state` (`state_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `state_id_fk_idx` ON `address` (`state_id_fk` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user` (
  `user_id` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `address_id_fk` INT NULL,
  `password` VARCHAR(100) NOT NULL,
  `birthday` DATE NULL,
  `status` VARCHAR(1) NULL DEFAULT '1',
  `foto` BLOB NULL,
  PRIMARY KEY (`user_id`),
  CONSTRAINT `address_id_fk`
    FOREIGN KEY (`address_id_fk`)
    REFERENCES `address` (`address_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf32
COLLATE = utf32_bin
COMMENT = 'Table of all users in our user management system';

SHOW WARNINGS;
CREATE UNIQUE INDEX `user_id_UNIQUE` ON `user` (`user_id` ASC);

SHOW WARNINGS;
CREATE INDEX `address_id_fk_idx` ON `user` (`address_id_fk` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `permission` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `permission` (
  `permission_id` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(45) NULL,
  `description` VARCHAR(45) NULL,
  `status` VARCHAR(1) NULL DEFAULT '1',
  PRIMARY KEY (`permission_id`))
ENGINE = InnoDB
COMMENT = 'is used for give a user  permission (Erlaubnis / Berechtigung / Rights )';

SHOW WARNINGS;
CREATE UNIQUE INDEX `permission_id_UNIQUE` ON `permission` (`permission_id` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_role` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user_role` (
  `user_role_id` INT NOT NULL AUTO_INCREMENT,
  `user_id_fk` INT NOT NULL,
  `role_id_fk` INT NOT NULL,
  PRIMARY KEY (`user_role_id`),
  CONSTRAINT `user_id_fk`
    FOREIGN KEY (`user_id_fk`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `role_id_fk`
    FOREIGN KEY (`role_id_fk`)
    REFERENCES `role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `user_role_id_UNIQUE` ON `user_role` (`user_role_id` ASC);

SHOW WARNINGS;
CREATE INDEX `user_id_fk_idx` ON `user_role` (`user_id_fk` ASC);

SHOW WARNINGS;
CREATE INDEX `role_id_fk_idx` ON `user_role` (`role_id_fk` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `role_permission`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `role_permission` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `role_permission` (
  `role_permission_id` INT NOT NULL AUTO_INCREMENT,
  `role_id_fk` INT NOT NULL,
  `permission_id_fk` INT NOT NULL,
  PRIMARY KEY (`role_permission_id`),
  CONSTRAINT `user_role_id_fk`
    FOREIGN KEY (`role_id_fk`)
    REFERENCES `role` (`role_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `permission_id_fk`
    FOREIGN KEY (`permission_id_fk`)
    REFERENCES `permission` (`permission_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE UNIQUE INDEX `role_permission_id_UNIQUE` ON `role_permission` (`role_permission_id` ASC);

SHOW WARNINGS;
CREATE INDEX `role_id_fk_idx` ON `role_permission` (`role_id_fk` ASC);

SHOW WARNINGS;
CREATE INDEX `permission_id_fk_idx` ON `role_permission` (`permission_id_fk` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `group` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `group` (
  `group_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `status` VARCHAR(1) NULL DEFAULT '1',
  PRIMARY KEY (`group_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_bin
COMMENT = 'contain all groups for users';

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `user_group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user_group` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `user_group` (
  `user_group_id` INT NOT NULL AUTO_INCREMENT,
  `group_id_fk` INT NOT NULL,
  `user_id_fk` INT NOT NULL,
  PRIMARY KEY (`user_group_id`),
  CONSTRAINT `group_id_fk`
    FOREIGN KEY (`group_id_fk`)
    REFERENCES `group` (`group_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `user_group_id_fk`
    FOREIGN KEY (`user_id_fk`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `group_id_fk_idx` ON `user_group` (`group_id_fk` ASC);

SHOW WARNINGS;
CREATE INDEX `user_group_id_fk_idx` ON `user_group` (`user_id_fk` ASC);

SHOW WARNINGS;

-- -----------------------------------------------------
-- Table `login`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `login` ;

SHOW WARNINGS;
CREATE TABLE IF NOT EXISTS `login` (
  `login_id` INT NOT NULL AUTO_INCREMENT,
  `user_id_fk` INT NOT NULL,
  `last_login` DATETIME NULL,
  `mistrial` INT NULL DEFAULT 0 COMMENT 'Fehlversuche',
  `old_password` VARCHAR(100) NULL,
  `status` VARCHAR(1) NULL DEFAULT '1',
  PRIMARY KEY (`login_id`),
  CONSTRAINT `login_user_id_fk`
    FOREIGN KEY (`user_id_fk`)
    REFERENCES `user` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SHOW WARNINGS;
CREATE INDEX `login_user_id_fk_idx` ON `login` (`user_id_fk` ASC);

SHOW WARNINGS;
USE `bytmasoft`;

DELIMITER $$

USE `bytmasoft`$$
DROP TRIGGER IF EXISTS `user_AFTER_INSERT` $$
SHOW WARNINGS$$
USE `bytmasoft`$$
CREATE DEFINER = CURRENT_USER TRIGGER `bytmasoft`.`user_AFTER_INSERT` AFTER INSERT ON `user` FOR EACH ROW
BEGIN
  insert into login(user_id_fk) values (new.user_id);
END$$

SHOW WARNINGS$$

DELIMITER ;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
