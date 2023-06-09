package lab5.command;

import lab5.exceptions.BuildObjectException;

public abstract class Command implements CommandInterface {
    private final boolean hasArgument;
    private Object argument;
    @Override
    public abstract String getName();
    @Override
    public abstract String getDescr();
    public Command(boolean hasArgument) {
        this.hasArgument = hasArgument;
    }
    @Override
    public abstract void execute() throws BuildObjectException;
    @Override
    public abstract boolean checkArgument(Object inputArgument);
    public boolean isHasArgument() {
        return hasArgument;
    }
    public Object getArgument() {
        return argument;
    }
    public void setArgument(String argument) {
        this.argument = argument;
    }
}
