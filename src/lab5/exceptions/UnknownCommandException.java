package lab5.exceptions;

public class UnknownCommandException extends Exception {
    public UnknownCommandException(String message)
    {
        super(message);
    }
}