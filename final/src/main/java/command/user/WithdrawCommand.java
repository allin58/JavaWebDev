package command.user;

import command.Command;
import entity.User;
import entity.Wallet;
import entity.mapping.MappingTransaction;
import service.TransactionService;
import service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class WithdrawCommand implements Command {

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
