package by.taining.cryptomarket.command;

import by.taining.cryptomarket.command.admin.ApproveTransactionCommand;
import by.taining.cryptomarket.command.admin.RejectTransactionCommand;
import by.taining.cryptomarket.command.general.LoginCommand;
import by.taining.cryptomarket.command.general.LogoutCommand;
import by.taining.cryptomarket.command.general.RegistrationCommand;
import by.taining.cryptomarket.command.general.ToRegistrationCommand;
import by.taining.cryptomarket.command.general.ChangeLanguageCommand;
import by.taining.cryptomarket.command.sec.TogglePairCommand;
import by.taining.cryptomarket.command.user.ExecuteMarketOrderCommand;
import by.taining.cryptomarket.command.user.SetLimitOrderCommand;
import by.taining.cryptomarket.command.user.SetTypeOfOrderCommand;
import by.taining.cryptomarket.command.user.ToMarketCommand;
import by.taining.cryptomarket.command.user.RejectOrderCommand;
import by.taining.cryptomarket.command.user.ToCabinetCommand;
import by.taining.cryptomarket.command.user.ToWalletCommand;
import by.taining.cryptomarket.command.user.DepositCommand;
import by.taining.cryptomarket.command.user.ToDepositCommand;
import by.taining.cryptomarket.command.user.ToWithdrawCommand;
import by.taining.cryptomarket.command.user.WithdrawCommand;
import by.taining.cryptomarket.command.user.ToMyOrdersCommand;


import java.util.HashMap;
import java.util.Map;


/**
 * This is factory for getting instance of command depend on name of command.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
 public final class CommandFactory {


    /**
     * Collection for storage of commands.
     */
    private static Map<String, Command> commands;

    /**
     * Instance of CommandFactory.
     */
    private static CommandFactory factoryInstance;


    /**
     * A private constructor that fills the collection with command instances.
     */
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




    }

    /**
     * Method for getting instance of CommandFactory.
     * @return factoryInstance
     */
    public static synchronized CommandFactory getInstance() {
        if (factoryInstance == null) {
            factoryInstance = new CommandFactory();
        }
        return factoryInstance;
    }


    /**
     * Method for getting instance of command depend on name of command.
     * @param command command
     * @return toReturn
     */
    public Command createCommand(final String command) {
        Command toReturn = commands.get(command.trim());
        if (toReturn == null) {
            toReturn = commands.get("view/error.jsp");
        }

        return toReturn;
    }
}
