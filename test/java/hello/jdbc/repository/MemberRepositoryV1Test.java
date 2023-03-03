package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MemberRepositoryV1Test {



    MemberRepositoryV1 memberRepositoryV1;

    @BeforeEach
    void beforeEach(){
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, ConnectionConst.PASSWORD);
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        memberRepositoryV1 = new MemberRepositoryV1(dataSource);
    }

    @Test
    void crud() throws SQLException {
        Member memberV0 = new Member("memberV101", 10000);
        memberRepositoryV1.save(memberV0);

        Member findMember = memberRepositoryV1.findById(memberV0.getMemberId());
        log.info("findMember={}", findMember);
        log.info("member equals findMember = {}", memberV0.equals(findMember));
        Assertions.assertThat(findMember).isEqualTo(memberV0);

        memberRepositoryV1.update(memberV0.getMemberId(),20000);
        Member updatedMember = memberRepositoryV1.findById(memberV0.getMemberId());
        Assertions.assertThat(updatedMember.getMoney()).isEqualTo(20000);

        memberRepositoryV1.delete(memberV0.getMemberId());
        Assertions.assertThatThrownBy(()->memberRepositoryV1.findById(memberV0.getMemberId())).isInstanceOf(NoSuchElementException.class);

    }
}