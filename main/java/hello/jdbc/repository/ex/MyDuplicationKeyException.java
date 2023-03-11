package hello.jdbc.repository.ex;

public class MyDuplicationKeyException extends MyDbException {

    public MyDuplicationKeyException(){

    }

    public MyDuplicationKeyException(String msg){
        super(msg);
    }

    public MyDuplicationKeyException(String msg, Throwable cause){
        super(msg, cause);
    }

    public MyDuplicationKeyException(Throwable cause){
        super(cause);
    }

}
