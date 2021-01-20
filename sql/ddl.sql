
    컬럼명 변경 {
        ALTER TABLE 테이블명 CHANGE 기존컬럼명 새컬럼명 새컬럼타입;
    }

    테이블명 변경 {
        RENAME TABLE 테이블명 TO 새테이블명;
    }

    foreign_key 추가 {
        ALTER TABLE 테이블명
        ADD FOREIGN KEY (컬럼명) REFERENCES 상속테이블명(상속컬럼명);
    }

    foreign_key 삭제 {
        ALTER TABLE 테이블명
        DROP FOREIGN KEY foreign_key_id;
    }

    auto_increment 추가 {
        ALTER TABLE auto_test MODIFY id INT NOT NULL AUTO_INCREMENT;
    } --> 데이터 안에 값이 없어야 추가 가능(0, 1, 2...등 있을 경우 중복되므로 x)

    auto_increment 초기화 {
        ALTER TABLE 테이블명 AUTO_INCREMENT = ?;
    } --> ? 시작할 값



CREATE TABLE `customer` (
  `pk` bigint(20) NOT NULL AUTO_INCREMENT,
  `id` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `EMAIL` (`id`),
  UNIQUE KEY `EMAIL_2` (`id`)
)


CREATE TABLE `drink` (
  `pk` bigint(20) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `sales` int(11) DEFAULT NULL,
  PRIMARY KEY (`pk`)
)

CREATE TABLE `payment` (
  `pk` bigint(20) NOT NULL,
  `payment_date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `customer_id` varchar(30) DEFAULT NULL,
  `way` varchar(30) DEFAULT NULL,
  `total` int(11) DEFAULT NULL,
  `cash_receipt` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`pk`),
  UNIQUE KEY `pDATE` (`payment_date`),
  KEY `customer_id` (`customer_id`)
)

