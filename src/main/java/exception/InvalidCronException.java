package exception;

public class InvalidCronException extends RuntimeException {

    public InvalidCronException(String message) {
        super(message);
    }
}
