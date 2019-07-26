package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.service.WalletService;
import by.taining.cryptomarket.command.Command;
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
public class WithdrawCommand implements Command {


    /**
     * This method which allows to do withdraw.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("transactionerror",null);

        try {


            Double amount = null;
            try {
                amount = Double.valueOf(request.getParameter("amount"));
                if (amount <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {
                request.getSession().setAttribute("transactionerror","incorrectamount");
                return "views/withdraw.jsp";
            }


            String coin = (String) request.getSession().getAttribute("coin");
            Integer userId = ((User)request.getSession().getAttribute("user")).getIdentity();

            TransactionService transactionService = new TransactionService();

            transactionService.setWithdrawTransaction(userId,amount,coin);

            User user = (User)request.getSession().getAttribute("user");
            List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());
            request.getSession().setAttribute("transactions",transactionList);

            WalletService walletService = new WalletService();
            Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
            request.getSession().setAttribute("wallet",wallet);

            return "views/wallet.jsp";
        } catch (Exception e) {

            request.getSession().setAttribute("transactionerror","insufficientfunds");
            return "views/withdraw.jsp";

        }
    }
}
