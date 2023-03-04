package hello.jdbc.service;

import hello.jdbc.connection.ConnectionConst.*;
import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.repository.MemberRepositoryV2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceV2Test {

    public static final String MEMBER_A = "memberA12";
    public static final String MEMBER_B = "memberB";
    public static final String MEMBER_EX = "ex";

    private MemberRepositoryV2 memberRepository;
    private MemberServiceV2  memberService;

    @BeforeEach
    void before(){
        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL,USERNAME, PASSWORD);
        memberRepository = new MemberRepositoryV2(dataSource);
        memberService = new MemberServiceV2(dataSource,memberRepository);
    }

    @Test
    @DisplayName("정상이체")
    void accountTransfer() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberB = new Member(MEMBER_B, 10000);

        memberRepository.save(memberA);
        memberRepository.save(memberB);

        memberService.accountTransfer(memberA.getMemberId(), memberB.getMemberId(), 2000);

        Member findByMemberA = memberRepository.findById(memberA.getMemberId());
        Member findByMemberB = memberRepository.findById(memberB.getMemberId());

        Assertions.assertThat(findByMemberA.getMoney()).isEqualTo(8000);
        Assertions.assertThat(findByMemberB.getMoney()).isEqualTo(12000);

    }


    @Test
    @DisplayName("정상이체중 예외")
    void accountTransferEx() throws SQLException {
        Member memberA = new Member(MEMBER_A, 10000);
        Member memberEx = new Member(MEMBER_EX, 10000);

        memberRepository.save(memberA);
        memberRepository.save(memberEx);

        Assertions.assertThatThrownBy(()-> memberService.accountTransfer(memberA.getMemberId(), memberEx.getMemberId(),2000))
                .isInstanceOf(IllegalStateException.class);

        Member findByMemberA = memberRepository.findById(memberA.getMemberId());
        Member findByMemberB = memberRepository.findById(memberEx.getMemberId());

        Assertions.assertThat(findByMemberA.getMoney()).isEqualTo(10000);
        Assertions.assertThat(findByMemberB.getMoney()).isEqualTo(10000);

    }

    @AfterEach
    void after() throws SQLException {
        memberRepository.delete(MEMBER_A);
        memberRepository.delete(MEMBER_B);
        memberRepository.delete(MEMBER_EX);
    }



}