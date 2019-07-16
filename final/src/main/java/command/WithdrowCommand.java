package command;

import entity.User;
import entity.Wallet;
import entity.mapping.MappingTransaction;
import service.TransactionService;
import service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WithdrowCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("withdrow",null);

        try {
            Double amount = Double.valueOf(request.getParameter("amount"));
            String coin = (String) request.getSession().getAttribute("coin");
            Integer userId = ((User)request.getSession().getAttribute("user")).getIdentity();

            TransactionService transactionService = new TransactionService();

            transactionService.setWithdrowTransaction(userId,amount,coin);

            User user = (User)request.getSession().getAttribute("user");
            List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());
            request.getSession().setAttribute("transactions",transactionList);

            WalletService walletService = new WalletService();
            Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
            request.getSession().setAttribute("wallet",wallet);

            return "views/wallet.jsp";
        } catch (Exception e) {

            request.getSession().setAttribute("withdrowerror","введена некорректная сумма");
            return "views/withdrow.jsp";

        }
    }
}
