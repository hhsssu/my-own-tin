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
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '회원고유번호',
  `email` VARCHAR(255) NOT NULL COMMENT '이메일(아이디)',
  `nickname` VARCHAR(20) NOT NULL COMMENT '별명',
  `password` VARCHAR(20) NOT NULL COMMENT '비밀번호',
  `gender` VARCHAR(4) NOT NULL COMMENT '성별',
  `age` INT NOT NULL COMMENT '나이',
  `level` INT NOT NULL DEFAULT 1 COMMENT '레벨',
  `is_public` TINYINT NOT NULL DEFAULT 1 COMMENT '공개여부',
  `is_admin` TINYINT NOT NULL DEFAULT 0 COMMENT '관리자여부',
  `is_expert` TINYINT NOT NULL DEFAULT 0 COMMENT '전문가여부',
  `join_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '가입일',
  `update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '회원정보수정일',
  `delete_at` TIMESTAMP NULL DEFAULT NULL COMMENT '회원탈퇴일',
  `part1` VARCHAR(20) NULL COMMENT '운동부위1',
  `part2` VARCHAR(20) NULL COMMENT '운동부위2',
  `workout_time` INT NULL COMMENT '운동시간',
  PRIMARY KEY (`id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;

ALTER TABLE `myroutine`.`users` AUTO_INCREMENT = 11;

-- -----------------------------------------------------
-- Table `myroutine`.`routines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`routines` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`routines` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '루틴고유번호',
  `writer` VARCHAR(20) NOT NULL COMMENT '작성자(닉네임)',
  `title` VARCHAR(50) NOT NULL COMMENT '루틴제목',
  `content` TEXT NOT NULL COMMENT '루틴내용',
  `likeCnt` INT NOT NULL DEFAULT 0 COMMENT '좋아요수',
  `part1` VARCHAR(20) NOT NULL COMMENT '운동부위1',
  `part2` VARCHAR(20) NULL COMMENT '운동부위2',
  `workout_time` INT NOT NULL COMMENT '운동시간',
  `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
  `delete_at` TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일',
  `is_marked` TINYINT NULL DEFAULT 0 COMMENT '북마크 표시',
  `user_id` INT NOT NULL COMMENT '현재작성회원',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_routines_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myroutine`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`questions` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`questions` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '질문고유번호',
  `writer` VARCHAR(20) NOT NULL COMMENT '작성자',
  `title` VARCHAR(50) NOT NULL COMMENT '제목',
  `content` TEXT NOT NULL COMMENT '내용',
  `like_cnt` INT NOT NULL DEFAULT 0 COMMENT '좋아요수',
  `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
  `delete_at` TIMESTAMP NULL COMMENT '삭제일',
  `user_id` INT NOT NULL COMMENT '회원고유번호',
  `routine_id` INT NULL COMMENT '루틴고유번호',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_questions_users`
    FOREIGN KEY (`user_id`)
    REFERENCES `myroutine`.`users` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_questions_routines1`
    FOREIGN KEY (`routine_id`)
    REFERENCES `myroutine`.`routines` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`answers` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`answers` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '답변고유번호',
  `writer` VARCHAR(20) NOT NULL COMMENT '작성자',
  `content` TEXT NOT NULL COMMENT '내용',
  `is_picked` TINYINT NULL DEFAULT 0 COMMENT '답변채택여부',
  `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '등록일',
  `update_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일',
  `delete_at` TIMESTAMP NULL DEFAULT NULL COMMENT '삭제일',
  `question_id` INT NOT NULL COMMENT '질문고유번호',
  `user_id` INT NOT NULL COMMENT '회원고유번호',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_answers_questions1`
    FOREIGN KEY (`question_id`)
    REFERENCES `myroutine`.`questions` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_answers_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myroutine`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`user_keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`user_keyword` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`user_keyword` (
  `id` INT NOT NULL COMMENT '회원키워드고유번호',
  `keyword` VARCHAR(10) NOT NULL COMMENT '키워드명',
  `user_id` INT NOT NULL COMMENT '회원고유번호',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_user_keyword_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myroutine`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`point`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`point` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`point` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '포인트 고유번호',
  `record` VARCHAR(255) NOT NULL COMMENT '포인트 내역',
  `amount` INT NOT NULL COMMENT '포인트 값',
  `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '포인트 내역 일자',
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_point_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myroutine`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`mile`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`mile` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`mile` (
  `id` INT NOT NULL AUTO_INCREMENT COMMENT '마일리지 고유번호',
  `record` VARCHAR(255) NOT NULL COMMENT '마일리지 내역',
  `amount` INT NOT NULL COMMENT '마일리지 값',
  `create_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '마일리지 내역 일자',
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_mile_users1`
    FOREIGN KEY (`user_id`)
    REFERENCES `myroutine`.`users` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
