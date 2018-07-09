-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema miletracker
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `miletracker` ;

-- -----------------------------------------------------
-- Schema miletracker
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `miletracker` DEFAULT CHARACTER SET utf8 ;
USE `miletracker` ;

-- -----------------------------------------------------
-- Table `miletracker`.`miles_ran`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `miletracker`.`miles_ran` ;

CREATE TABLE IF NOT EXISTS `miletracker`.`miles_ran` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `miles_ran` DOUBLE NOT NULL,
  `time` DOUBLE NULL,
  `week` INT NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8;

GRANT SELECT, INSERT, UPDATE, DELETE on student.* to student;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `miletracker`.`miles_ran`
-- -----------------------------------------------------
START TRANSACTION;
USE `miletracker`;
INSERT INTO `miletracker`.`miles_ran` (`id`, `miles_ran`, `time`, `week`) VALUES (1, 1.5, 15.25, 1);
INSERT INTO `miletracker`.`miles_ran` (`id`, `miles_ran`, `time`, `week`) VALUES (2, 2, 15, 1);
INSERT INTO `miletracker`.`miles_ran` (`id`, `miles_ran`, `time`, `week`) VALUES (3, 2, 13, 2);

COMMIT;
