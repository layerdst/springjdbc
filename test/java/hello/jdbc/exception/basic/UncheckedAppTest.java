package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

@Slf4j
public class UncheckedAppTest {

    @Test
    void unchecked(){
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(()->controller.req()).isInstanceOf(Exception.class);
    }

    static class Controller{
        CheckedAppTest.Service service = new CheckedAppTest.Service();
        public void req() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service{
        CheckedAppTest.Repository rep = new CheckedAppTest.Repository();
        CheckedAppTest.NetworkClient net = new CheckedAppTest.NetworkClient();

        public void logic() throws ConnectException, SQLException {
            rep.call();
            net.call();
        }
    }

    static class NetworkClient{
        public void call() {
            throw new RuntimeConnectException("연결실패");
        }
    }

    static class Repository{

        public void call() {
            try{
                runSQL();
            }catch (SQLException e){
                throw new RuntimeSQLException(e);
            }
        }

        public void runSQL () throws SQLException{
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String msg){
            super(msg);
        }
    }

    static class RuntimeSQLException extends RuntimeException{
        public RuntimeSQLException(Throwable msg){
            super(msg);
        }
    }

}
