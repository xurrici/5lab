package lab5.command.list;

import lab5.command.Command;
import lab5.command.CommandManager;
import java.util.Optional;

public class Help extends Command {

    public Help() {
        super(false);
    }

    @Override
    public String getName() {
        return "help";
    }

    @Override
    public String getDescr() {
        return "вывести справку по доступным командам";
    }

    @Override
    public void execute() {
        CommandManager manager = new CommandManager();

        if (this.getArgument() == null)
            manager.getCommandMap().forEach((name, command) -> System.out.println(name + " : " + command.getDescr()));
        else {
            var command = manager.getCommandMap().get(this.getArgument());
            System.out.println(this.getArgument() + " : " + Optional.ofNullable(command).map(Command::getDescr).orElse("Команда не найдена"));
            this.setArgument(null);
        }
    }

    @Override
    public boolean checkArgument(Object inputArgument) {
        if (inputArgument == null)
            return true;
        else {
            System.out.println("Опциональный 1 строковый аргумент");
            return false;
        }
    }

}
