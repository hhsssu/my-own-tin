SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
-- ----------------------------

-- DROP SCHEMA IF EXISTS myroutine;
-- CREATE SCHEMA myroutine;

CREATE SCHEMA IF NOT EXISTS `myroutine` DEFAULT CHARACTER SET utf8 ;

USE myroutine;

-- -----------------------------------------------------
-- Table `myroutine`.`users` 회원 테이블
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `myroutine`.`users` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`users` (
  `user_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '회원 고유번호', -- 1번부터 시작할건지? = value
  `email` VARCHAR(255) NOT NULL COMMENT '회원 아이디로 사용',
  `nickname` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(4) NOT NULL,
  `age` INT NOT NULL,
  `persnality` VARCHAR(10) NULL COMMENT '성격',
  `other_routine` VARCHAR(45) NULL COMMENT '다른 사람의 루틴 보관',
  `user_routine` VARCHAR(45) NULL COMMENT '나의 루틴 보관',
  `public` TINYINT NOT NULL COMMENT '프로필 공개 여부',
  `is_admin` TINYINT NOT NULL COMMENT '관리자',
  `is_expert` TINYINT NOT NULL COMMENT '전문가',
  UNIQUE INDEX `user_email_UNIQUE` (`email` ASC) VISIBLE,
  UNIQUE INDEX `nickname_UNIQUE` (`nickname` ASC) VISIBLE)
ENGINE = InnoDB; -- default Storage Engine

-- 가입일자도 추가해야하지 않을까?
-- 글로벌 서비스를 운영해야하는 경우에는 TimeZone이 반영되는 TimeStamp로 타입을 주는게 바람직 - 모든 regDate timestamp로 변경!
-- DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP 수정할때마다 업데이트
-- 작성시간, 업데이트 시간 DB 나누어야 하지 않을까?

-- -----------------------------------------------------
-- Table `myroutine`.`personalities` 프로필에 표시할 성격 키워드 (목표, mbti, 성격, 등등) (테이블명을 키워드로 바꿔야하나..?)
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `myroutine`.`personalities` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`personalities` (
  `persnality_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '성격키워드 고유번호',
  `keyword` VARCHAR(10) NOT NULL, -- 한글 기준 5자 이내
  `users_user_id` INT NOT NULL, -- fk 연결
  INDEX `fk_personalities_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_personalities_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




-- -----------------------------------------------------
-- Table `myroutine`.`routines` 루틴
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `myroutine`.`routines` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`routines` (
  `routine_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '루틴 고유번호', -- 1번부터 시작할건지? 
  `writer` VARCHAR(20) NOT NULL COMMENT '회원 닉네임',
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
  `title` VARCHAR(50) NOT NULL,
  `content` TEXT NOT NULL, -- MEDIUMTEXT -> TEXT 변경
  `part` VARCHAR(10) NOT NULL,
  `likeCnt` INT NOT NULL COMMENT '좋아요',
  `workout_time` INT NOT NULL COMMENT '운동 시간',
  `users_user_id` INT NOT NULL COMMENT '회원 고유번호',
  INDEX `fk_routines_users_idx` (`users_user_id` ASC) VISIBLE, -- fk 연결
  CONSTRAINT `fk_routines_users`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`questions` 질문글
-- -----------------------------------------------------
-- DROP TABLE IF EXISTS `myroutine`.`questions` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`questions` (
  `question_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '질문 고유번호', -- 1번부터 시작할건지?
  `writer` VARCHAR(20) NOT NULL COMMENT '회원 닉네임',
  `reg_date` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
  `title` VARCHAR(50) NOT NULL,
  `content` TEXT NOT NULL, -- MEDIUMTEXT -> TEXT 변경
  `like_cnt` INT NOT NULL COMMENT '좋아요',
  `users_user_id` INT NOT NULL, -- fk 연결
  INDEX `fk_questions_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_questions_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;





-- -----------------------------------------------------
-- Table `myroutine`.`answers` 답변 댓글
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`answers` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`answers` (
  `answer_id` INT PRIMARY KEY AUTO_INCREMENT COMMENT '답변 고유번호',
  `writer` VARCHAR(20) NOT NULL COMMENT '회원 닉네임',
  `content` TEXT NOT NULL, -- MEDIUMTEXT -> TEXT 변경
  `is_picked` TINYINT NULL COMMENT '답변채택여부',
  `regDate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '작성일자',
  `users_user_id` INT NOT NULL, -- fk 연결
  INDEX `fk_answers_users1_idx` (`users_user_id` ASC) VISIBLE,
  CONSTRAINT `fk_answers_users1`
    FOREIGN KEY (`users_user_id`)
    REFERENCES `mydb`.`users` (`user_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `myroutine`.`answers_has_questions`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `myroutine`.`answers_has_questions` ;

CREATE TABLE IF NOT EXISTS `myroutine`.`answers_has_questions` (
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

