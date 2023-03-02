package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class MemberRepositoryV0 {

    public Member save(Member member){
        String sql = "insert into member(member_id, money) value (?, ?) ";

        Connection conn = null;
        PreparedStatement pstmt=null;

        conn = getConnection();
    }

    private Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }
}
