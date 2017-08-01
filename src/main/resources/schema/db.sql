CREATE DATABASE test
  DEFAULT CHARACTER SET utf8
  COLLATE utf8_general_ci;

USE test;

CREATE TABLE IF NOT EXISTS
  test_demo
(
  id   INT(11) NOT NULL AUTO_INCREMENT,
  code VARCHAR(50),
  ts   DATETIME,
  PRIMARY KEY (id)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for `主键生成器表`
-- ----------------------------
DROP TABLE IF EXISTS `sequence`;

CREATE TABLE `sequence` (
  `id`           INT(11) NOT NULL AUTO_INCREMENT,
  `name`         VARCHAR(10)     NOT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = MyISAM
  AUTO_INCREMENT=10
  DEFAULT CHARSET = utf8;