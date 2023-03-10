package hello.jdbc.repository;


import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

@Slf4j
public class MemberRepositoryV4 implements MemberRepository{
    private final DataSource dataSource;

    public MemberRepositoryV4(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member){
        String sql = "insert into member(member_id, money) value (?, ?) ";

        Connection conn = null;
        PreparedStatement pstmt=null;


        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            int i = pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            throw new MyDbException(e);
        }finally{
            close(conn, pstmt, null);
        }

    }

    @Override
    public Member findById(String memberId){
        String sql = "select * from member where member_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;


        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();

            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            }else{
                throw new NoSuchElementException("member not found " + memberId);
            }
        } catch (SQLException e) {
            throw new MyDbException(e);
        }finally {
            close(conn,pstmt,rs);
        }

    }


//    public Member findById(Connection conn, String memberId){
//        String sql = "select * from member where member_id = ?";
//        PreparedStatement pstmt = null;
//        ResultSet rs = null;
//
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setString(1, memberId);
//            rs = pstmt.executeQuery();
//
//            if(rs.next()){
//                Member member = new Member();
//                member.setMemberId(rs.getString("member_id"));
//                member.setMoney(rs.getInt("money"));
//                return member;
//            }else{
//                throw new NoSuchElementException("member not found " + memberId);
//            }
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }finally {
//            close(conn,pstmt,rs);
//        }
//
//    }
//
//    public void update(Connection conn, String memberId, int money) {
//        String sql = "update member set money = ? where member_id= ?";
//
//        PreparedStatement pstmt = null;
//
//        try {
//            pstmt = conn.prepareStatement(sql);
//            pstmt.setInt(1, money);
//            pstmt.setString(2, memberId);
//            int resultSize = pstmt.executeUpdate();
//            log.info("resultSize = {}", resultSize);
//        } catch (SQLException e) {
//            log.error("db error ", e);
//        } finally {
//            close(conn, pstmt,null);
//        }
//    }

    @Override
    public void update(String memberId, int money) {
        String sql = "update member set money = ? where member_id= ?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);
            int resultSize = pstmt.executeUpdate();
            log.info("resultSize = {}", resultSize);
        } catch (SQLException e) {
            throw new MyDbException(e);
        } finally {
            close(conn, pstmt, null);
        }
    }

    @Override
    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";

        Connection conn = null;
        PreparedStatement pstmt = null;

        try{
            conn = getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, memberId);
            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new MyDbException(e);
        }finally {
            close(conn,pstmt, null);
        }

    }

    private void close(Connection conn, Statement stmt, ResultSet rs){
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        DataSourceUtils.releaseConnection(conn, dataSource);

    }

    private Connection getConnection() throws SQLException {

        Connection connection = DataSourceUtils.getConnection(dataSource);
        log.info("get Connection = {}, class = {}", connection, connection.getClass());
        return connection;
    }
}
