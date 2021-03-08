package exception;

public class InvalidCronException extends RuntimeException {

    public InvalidCronException(String message) {
        super(message);
    }

    @Override
    public Throwable fillInStackTrace() {
        return this;
    }

}
