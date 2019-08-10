package by.taining.cryptomarket.command.admin;

import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.service.WalletService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.mapping.MappingTransaction;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class RejectTransactionCommand implements Command {


    /**
     * This method allows reject of transaction.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {

        Integer idintity = Integer.valueOf(request.getParameter("identity").
                trim());
        new TransactionService().rejectTransaction(idintity);

        request.getSession().setAttribute("transactionData",
                new TransactionService().getPendingTransactions());
        String from = request.getParameter("from");

        switch (from) {
            case "admin": return "views/admin.jsp";

            case "wallet":
                TransactionService transactionService = new TransactionService();
                User user = (User) request.getSession().getAttribute("user");
                List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());
                request.getSession().setAttribute("transactions", transactionList);
                WalletService walletService = new WalletService();
                Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
                request.getSession().setAttribute("wallet", wallet);
                return "views/wallet.jsp";

                default:   return "views/admin.jsp";
       }
    }
}
