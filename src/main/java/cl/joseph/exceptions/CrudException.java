package cl.joseph.exceptions;

public class CrudException extends RuntimeException{

    public CrudException(String message){
        super(message);
    }

    public CrudException(String message, Throwable exception){
        super(message, exception);
    }

}
