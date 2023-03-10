package hello.jdbc.repository.ex;

public class MyDbException extends RuntimeException{
    public MyDbException(){

    }

    public MyDbException(String msg){
        super(msg);
    }


    public MyDbException(String msg, Throwable cause){
        super(msg, cause);
    }

    public MyDbException(Throwable cause){
        super(cause);
    }


}
