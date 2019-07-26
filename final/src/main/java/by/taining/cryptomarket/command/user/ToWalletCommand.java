package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.service.WalletService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.Transaction;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.mapping.MappingTransaction;
import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ToWalletCommand implements Command {

    /**
     * This method is to redirect to the page of wallet.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       User user = (User)request.getSession().getAttribute("user");



        WalletService walletService = new WalletService();
        Wallet wallet = walletService.getWalletByUserId(user.getIdentity());


        request.getSession().setAttribute("wallet",wallet);


        TransactionService transactionService = new TransactionService();
        List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());




        request.getSession().setAttribute("transactions",transactionList);



        return "views/wallet.jsp";
    }
}
