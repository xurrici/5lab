package lab5.command;

import lab5.exceptions.BuildObjectException;

public interface CommandInterface {
    void execute() throws BuildObjectException;
    String getName();
    String getDescr();
    boolean checkArgument(Object argument);
}
