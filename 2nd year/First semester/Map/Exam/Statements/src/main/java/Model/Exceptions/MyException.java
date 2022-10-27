package Model.Exceptions;

public class MyException extends RuntimeException{
    private String exception;

    public MyException(String exp){
        super();
        this.exception = exp;
    }

    @Override
    public String toString() {
        return this.exception;
    }
}
