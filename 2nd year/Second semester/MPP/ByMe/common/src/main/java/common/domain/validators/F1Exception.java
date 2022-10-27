package common.domain.validators;

public class F1Exception extends RuntimeException{

    public F1Exception(String message) {
        super(message);
    }

    public F1Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public F1Exception(Throwable cause) {
        super(cause);
    }
}
