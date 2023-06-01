package lab5.exceptions;

public class CommandInterruptedException extends RuntimeException {
    public CommandInterruptedException(Exception cause)
    {
        super(cause);
    }
}