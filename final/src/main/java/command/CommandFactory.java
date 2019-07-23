package command;

import command.admin.ApproveTransactionCommand;
import command.admin.RejectTransactionCommand;
import command.general.*;
import command.sec.TogglePairCommand;
import command.user.*;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static Map<String, Command> commands;
    private static CommandFactory factoryInstance;

    private CommandFactory() {
        commands = new HashMap<>();

       commands.put("login", new LoginCommand());
       commands.put("logout", new LogoutCommand());
       commands.put("toregistration", new ToRegistrationCommand());
       commands.put("registration", new RegistrationCommand());
       commands.put("tomarket", new ToMarketCommand());
       commands.put("tocabinet", new ToCabinetCommand());
       commands.put("towallet", new ToWalletCommand());
       commands.put("togglepair", new TogglePairCommand());
       commands.put("approvetransaction", new ApproveTransactionCommand());
       commands.put("rejectransaction", new RejectTransactionCommand());
       commands.put("deposit", new DepositCommand());
       commands.put("todeposit", new ToDepositCommand());
       commands.put("towithdraw", new ToWithdrawCommand());
       commands.put("withdraw", new WithdrawCommand());
       commands.put("toorders", new ToMyOrdersCommand());
       commands.put("rejectorder", new RejectOrderCommand());
       commands.put("setlimitorder", new SetLimitOrderCommand());
       commands.put("executemarketorder", new ExecuteMarketOrderCommand());
       commands.put("changelanguage", new ChangeLanguageCommand());
       commands.put("settypeoforder", new SetTypeOfOrderCommand());


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
        Command toReturn = commands.get(command.trim());
        if (toReturn == null) {
            toReturn = commands.get("view/error.jsp");
        }

        return toReturn;
    }
}
