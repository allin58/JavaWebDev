package command;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands;
    private static CommandFactory factoryInstance;

    private CommandFactory() {
        commands = new HashMap<>();

       commands.put("login", new LoginCommand());

        commands.put("logout", new LogoutCommand());
  /*      commands.put("setLanguage", new SetLanguageCommand());
        commands.put("showIndexPage", new ShowIndexPageCommand());
*/

    }

    public static synchronized CommandFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new CommandFactory();
        }
        return factoryInstance;
    }

    public Command createCommand(String command) {
        Command toReturn = commands.get(command);
        if (toReturn == null) {
            toReturn = commands.get("showIndexPage");
        }
        return toReturn;
    }
}
