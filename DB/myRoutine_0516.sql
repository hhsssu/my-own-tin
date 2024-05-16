-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema myroutine
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `myroutine` ;

-- -----------------------------------------------------
-- Schema myroutine
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `myroutine` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `myroutine` ;

-- -----------------------------------------------------
-- Table `myroutine`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`users` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`users` (
  `user_id` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(4) NOT NULL,
  `age` INT NOT NULL,
  `is_public` TINYINT NOT NULL,
  `is_admin` TINYINT NOT NULL,
  `is_expert` TINYINT NOT NULL,
  `join_at` TIMESTAMP NOT NULL,
  `update_at` TIMESTAMP NOT NULL,
  `delete_at` TIMESTAMP NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`questions` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`questions` (
  `question_id` INT NOT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `content` TEXT NOT NULL,
  `like_cnt` INT NOT NULL,
  `create_at` TIMESTAMP NOT NULL,
  `update_at` TIMESTAMP NOT NULL,
  `delete_at` TIMESTAMP NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`question_id`),
  INDEX `fk_questions_users_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_questions_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `myroutine`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`answers` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`answers` (
  `answer_id` INT NOT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `content` TEXT NOT NULL,
  `is_picked` TINYINT NULL,
  `create_at` TIMESTAMP NOT NULL,
  `update_at` TIMESTAMP NOT NULL,
  `delete_at` TIMESTAMP NULL,
  `questions_question_id` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`answer_id`),
  INDEX `fk_answers_questions1_idx` (`questions_question_id` ASC) VISIBLE,
  INDEX `fk_answers_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`questions_question_id`)
    REFERENCES `myroutine`.`questions` (`question_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answers_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `myroutine`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`routines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`routines` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`routines` (
  `routine_id` INT NOT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `content` TEXT NOT NULL,
  `likeCnt` INT NOT NULL,
  `workout_time` INT NOT NULL,
  `create_at` TIMESTAMP NOT NULL,
  `update_at` TIMESTAMP NOT NULL,
  `delete_at` TIMESTAMP NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`routine_id`),
  INDEX `fk_routines_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_routines_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `myroutine`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`user_keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`user_keyword` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`user_keyword` (
  `user_keyword_id` INT NOT NULL,
  `keyword` VARCHAR(10) NOT NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`user_keyword_id`, `users_user_id`),
  INDEX `fk_user_keyword_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_user_keyword_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `myroutine`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`part_keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`part_keyword` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`part_keyword` (
  `part_keyword_id` INT NOT NULL,
  `keyword` VARCHAR(10) NOT NULL,
  `routines_routine_id` INT NOT NULL,
  PRIMARY KEY (`part_keyword_id`, `routines_routine_id`),
  INDEX `fk_part_keyword_routines1_idx` (`routines_routine_id` ASC) VISIBLE,
  CONSTRAINT `fk_part_keyword_routines1`
    FOREIGN KEY (`routines_routine_id`)
    REFERENCES `myroutine`.`routines` (`routine_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`user_routine_box`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`user_routine_box` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`user_routine_box` (
  `mybox_id` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  `routines_routine_id` INT NOT NULL,
  PRIMARY KEY (`mybox_id`),
  INDEX `fk_mybox_users1_idx` (`users_user_id` ASC) VISIBLE,
  INDEX `fk_mybox_routines1_idx` (`routines_routine_id` ASC) VISIBLE,
  CONSTRAINT `fk_mybox_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `myroutine`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_mybox_routines1`
    FOREIGN KEY (`routines_routine_id`)
    REFERENCES `myroutine`.`routines` (`routine_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
