
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
