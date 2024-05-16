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
  `user_id` INT NOT NULL COMMENT '회원고유번호',
  `email` VARCHAR(255) NOT NULL COMMENT '이메일(아이디)',
  `nickname` VARCHAR(20) NOT NULL COMMENT '별명',
  `password` VARCHAR(20) NOT NULL COMMENT '비밀번호',
  `gender` VARCHAR(4) NOT NULL COMMENT '성별',
  `age` INT NOT NULL COMMENT '나이',
  `is_public` TINYINT NOT NULL COMMENT '공개여부',
  `is_admin` TINYINT NOT NULL COMMENT '관리자여부',
  `is_expert` TINYINT NOT NULL COMMENT '전문가여부',
  `join_at` TIMESTAMP NOT NULL COMMENT '가입일',
  `update_at` TIMESTAMP NOT NULL COMMENT '회원정보수정일',
  `delete_at` TIMESTAMP NULL COMMENT '회원탈퇴일',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`questions` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`questions` (
  `question_id` INT NOT NULL COMMENT '질문고유번호',
  `writer` VARCHAR(20) NOT NULL COMMENT '작성자',
  `title` VARCHAR(50) NOT NULL COMMENT '제목',
  `content` TEXT NOT NULL COMMENT '내용',
  `like_cnt` INT NOT NULL COMMENT '좋아요수',
  `create_at` TIMESTAMP NOT NULL COMMENT '등록일',
  `update_at` TIMESTAMP NOT NULL COMMENT '수정일',
  `delete_at` TIMESTAMP NULL COMMENT '삭제일',
  `users_user_id` INT NOT NULL COMMENT '회원고유번호',
  PRIMARY KEY (`question_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`answers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`answers` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`answers` (
  `answer_id` INT NOT NULL COMMENT '답변고유번호',
  `writer` VARCHAR(20) NOT NULL COMMENT '작성자',
  `content` TEXT NOT NULL COMMENT '내용',
  `is_picked` TINYINT NULL COMMENT '답변채택여부',
  `create_at` TIMESTAMP NOT NULL COMMENT '등록일',
  `update_at` TIMESTAMP NOT NULL COMMENT '수정일',
  `delete_at` TIMESTAMP NULL COMMENT '삭제일',
  `questions_question_id` INT NOT NULL COMMENT '질문고유번호',
  `users_user_id` INT NOT NULL COMMENT '회원고유번호',
  PRIMARY KEY (`answer_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`routines`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`routines` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`routines` (
  `routine_id` INT NOT NULL COMMENT '루틴고유번호',
  `writer` VARCHAR(20) NOT NULL COMMENT '작성자(닉네임)',
  `title` VARCHAR(50) NOT NULL COMMENT '루틴제목',
  `content` TEXT NOT NULL COMMENT '루틴내용',
  `likeCnt` INT NOT NULL COMMENT '좋아요수',
  `workout_time` INT NOT NULL COMMENT '운동시간',
  `create_at` TIMESTAMP NOT NULL COMMENT '등록일',
  `update_at` TIMESTAMP NOT NULL COMMENT '수정일',
  `delete_at` TIMESTAMP NULL COMMENT '삭제일',
  `origin_user_id` INT NOT NULL COMMENT '원본작성회원',
  `users_user_id` INT NOT NULL COMMENT '현재작성회원',
  PRIMARY KEY (`routine_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`user_keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`user_keyword` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`user_keyword` (
  `user_keyword_id` INT NOT NULL COMMENT '회원키워드고유번호',
  `keyword` VARCHAR(10) NOT NULL COMMENT '키워드명',
  `users_user_id` INT NOT NULL COMMENT '회원고유번호',
  PRIMARY KEY (`user_keyword_id`, `users_user_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`part_keyword`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`part_keyword` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`part_keyword` (
  `part_keyword_id` INT NOT NULL COMMENT '운동부위키워드고유번호',
  `keyword` VARCHAR(10) NOT NULL COMMENT '운동부위키워드',
  `routines_routine_id` INT NOT NULL COMMENT '루틴고유번호',
  PRIMARY KEY (`part_keyword_id`, `routines_routine_id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`user_routine_box`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`user_routine_box` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`user_routine_box` (
  `mybox_id` INT NOT NULL COMMENT '내보관함고유번호',
  `users_user_id` INT NOT NULL COMMENT '회원고유번호',
  `routines_routine_id` INT NOT NULL COMMENT '루틴고유번호',
  PRIMARY KEY (`mybox_id`))
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
