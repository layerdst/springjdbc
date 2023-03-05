package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.repository.MemberRepositoryV2;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Slf4j
public class MemberServiceV3_2 {

//    private final PlatformTransactionManager transactionManager;
    private final TransactionTemplate txTemplate;
    private final MemberRepositoryV3 memberRepositoryV3;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository){
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.memberRepositoryV3 = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money){
        txTemplate.executeWithoutResult((status) ->{
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
//        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());
//        try{
//
//            transactionManager.commit(status);
//        }catch (Exception e){
//            transactionManager.rollback(status);
//            throw new IllegalStateException(e);
//        }
    }

    private void bizLogic( String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepositoryV3.findById(fromId);
        Member toMember = memberRepositoryV3.findById(toId);

        memberRepositoryV3.update(fromId, fromMember.getMoney() - money);

        validation(toMember);
        memberRepositoryV3.update(toId, toMember.getMoney() + money);
    }

    private void release(Connection con) {
        if(con !=null){
            try{
                con.setAutoCommit(true);
                con.close();
            }catch (Exception e){
                log.info("error message = {}" , e);
            }
        }
    }

    private void validation(Member toMember) {
        if (toMember.getMemberId().equals("ex")) {
            throw new IllegalStateException("이체중 예외발생");
        }
    }

}
