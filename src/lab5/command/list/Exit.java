package lab5.command.list;

import lab5.command.Command;

public class Exit extends Command {

    public Exit() {
        super(false);
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescr() {
        return "завершить программу (без сохранения в файл)";
    }
    @Override
    public void execute() {
        if (checkArgument(getArgument())) {
            System.exit(0);
        }
    }
    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Аргументы отсутствуют");
            return false;
        }
    }

}
