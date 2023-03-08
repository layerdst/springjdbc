package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

@Slf4j
public class CheckedAppTest {

    @Test
    void checked(){
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(()->controller.req()).isInstanceOf(Exception.class);
    }

    static class Controller{
        Service service = new Service();
        public void req() throws SQLException, ConnectException {
            service.logic();
        }
    }

    static class Service{
        Repository rep = new Repository();
        NetworkClient net = new NetworkClient();

        public void logic() throws ConnectException, SQLException {
            rep.call();
            net.call();
        }
    }

    static class NetworkClient{
        public void call() throws ConnectException{
            throw new ConnectException("연결실패");
        }
    }

    static class Repository{
        public void call() throws SQLException{
            throw new SQLException("ex");
        }
    }
}
