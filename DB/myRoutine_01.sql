-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
-- -----------------------------------------------------
-- Schema myroutine
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`users`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`users` ;

CREATE TABLE IF NOT EXISTS `mydb`.`users` (
  `user_id` INT NOT NULL,
  `email` VARCHAR(255) NOT NULL,
  `nickname` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(4) NOT NULL,
  `age` INT NOT NULL,
  `persnality` VARCHAR(10) NULL COMMENT '성격',
  `other_routine` VARCHAR(45) NULL,
  `user_routine` VARCHAR(45) NULL,
  `public` TINYINT NOT NULL,
  `is_admin` TINYINT NOT NULL,
  `is_expert` TINYINT NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`routines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`routines` ;

CREATE TABLE IF NOT EXISTS `mydb`.`routines` (
  `routine_id` INT NOT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `reg_date` DATETIME NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `content` MEDIUMTEXT NOT NULL,
  `part` VARCHAR(10) NOT NULL,
  `likeCnt` INT NOT NULL,
  `workout_time` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`routine_id`),
  INDEX `fk_routines_users_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_routines_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`questions` ;

CREATE TABLE IF NOT EXISTS `mydb`.`questions` (
  `question_id` INT NOT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `reg_date` DATETIME NOT NULL,
  `title` VARCHAR(50) NOT NULL,
  `content` MEDIUMTEXT NOT NULL,
  `like_cnt` INT NOT NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`question_id`),
  INDEX `fk_questions_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_questions_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`personalities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`personalities` ;

CREATE TABLE IF NOT EXISTS `mydb`.`personalities` (
  `persnality_id` INT NOT NULL,
  `keyword` VARCHAR(10) NOT NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`persnality_id`),
  INDEX `fk_personalities_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_personalities_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`answers` ;

CREATE TABLE IF NOT EXISTS `mydb`.`answers` (
  `answer_id` INT NOT NULL,
  `writer` VARCHAR(20) NOT NULL,
  `content` MEDIUMTEXT NOT NULL,
  `is_picked` TINYINT NULL,
  `regDate` DATETIME NULL,
  `users_user_id` INT NOT NULL,
  PRIMARY KEY (`answer_id`),
  INDEX `fk_answers_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_answers_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`answers_has_questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `mydb`.`answers_has_questions` ;

CREATE TABLE IF NOT EXISTS `mydb`.`answers_has_questions` (
  `answers_answer_id` INT NOT NULL,
  `questions_question_id` INT NOT NULL,
  PRIMARY KEY (`answers_answer_id`, `questions_question_id`),
  INDEX `fk_answers_has_questions_questions1_idx` (`questions_question_id` ASC) VISIBLE,
  INDEX `fk_answers_has_questions_answers1_idx` (`answers_answer_id` ASC) VISIBLE,
  CONSTRAINT `fk_answers_has_questions_answers1`
    FOREIGN KEY (`answers_answer_id`)
    REFERENCES `mydb`.`answers` (`answer_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_answers_has_questions_questions1`
    FOREIGN KEY (`questions_question_id`)
    REFERENCES `mydb`.`questions` (`question_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
