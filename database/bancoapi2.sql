-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bancoapi2
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bancoapi2
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bancoapi2` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;

CREATE USER 'snpUser'@'localhost' identified by 'snp-007';
grant select, insert, delete, update on bancoapi2.* to snpUser@'localhost';

USE `bancoapi2` ;

-- -----------------------------------------------------
-- Table `bancoapi2`.`curso`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoapi2`.`curso` (
  `id_curso` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `coordenador` VARCHAR(45) NOT NULL,
  `deletado` TINYINT(1) NULL DEFAULT '0',
  `periodo` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_curso`))
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bancoapi2`.`professor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoapi2`.`professor` (
  `id_professor` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `deletado` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id_professor`))
ENGINE = InnoDB
AUTO_INCREMENT = 35
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bancoapi2`.`disciplina`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoapi2`.`disciplina` (
  `id_disciplina` BIGINT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `id_professor` BIGINT NOT NULL,
  `id_curso` BIGINT NULL DEFAULT NULL,
  `semestre` INT NOT NULL,
  `deletado` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`id_disciplina`),
  INDEX `fk_disciplina_professor_idx` (`id_professor` ASC) VISIBLE,
  INDEX `fk_disciplina_curso_idx` (`id_curso` ASC) VISIBLE,
  CONSTRAINT `fk_disciplina_curso`
    FOREIGN KEY (`id_curso`)
    REFERENCES `bancoapi2`.`curso` (`id_curso`),
  CONSTRAINT `fk_disciplina_professor`
    FOREIGN KEY (`id_professor`)
    REFERENCES `bancoapi2`.`professor` (`id_professor`))
ENGINE = InnoDB
AUTO_INCREMENT = 31
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `bancoapi2`.`aula`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bancoapi2`.`aula` (
  `idaula` BIGINT NOT NULL AUTO_INCREMENT,
  `id_professor` BIGINT NOT NULL,
  `id_disciplina` BIGINT NOT NULL,
  `id_curso` BIGINT NOT NULL,
  `dia_semana` VARCHAR(45) NOT NULL,
  `numero_aula` INT NOT NULL,
  `periodo` VARCHAR(5) NOT NULL,
  `deletado` TINYINT(1) NULL DEFAULT '0',
  PRIMARY KEY (`idaula`),
  INDEX `fk_aula_professor_idx` (`id_professor` ASC) VISIBLE,
  INDEX `fk_aula_disciplina_idx` (`id_disciplina` ASC) VISIBLE,
  INDEX `fk_aula_curso_idx` (`id_curso` ASC) VISIBLE,
  CONSTRAINT `fk_aula_curso`
    FOREIGN KEY (`id_curso`)
    REFERENCES `bancoapi2`.`curso` (`id_curso`),
  CONSTRAINT `fk_aula_disciplina`
    FOREIGN KEY (`id_disciplina`)
    REFERENCES `bancoapi2`.`disciplina` (`id_disciplina`),
  CONSTRAINT `fk_aula_professor`
    FOREIGN KEY (`id_professor`)
    REFERENCES `bancoapi2`.`professor` (`id_professor`))
ENGINE = InnoDB
AUTO_INCREMENT = 215
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
