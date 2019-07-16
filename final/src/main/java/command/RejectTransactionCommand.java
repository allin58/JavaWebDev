package command;

import entity.User;
import entity.Wallet;
import entity.mapping.MappingTransaction;
import service.TransactionService;
import service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RejectTransactionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        Integer idintity = Integer.valueOf(request.getParameter("identity").trim());
        new TransactionService().rejectTransaction(idintity);

        request.getSession().setAttribute("transactionData", new TransactionService().getPendingTransactions());
        String from = request.getParameter("from");

        switch (from){
            case "admin": return "views/admin.jsp";

            case "wallet": TransactionService transactionService = new TransactionService();
                User user = (User)request.getSession().getAttribute("user");
                List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());
                request.getSession().setAttribute("transactions",transactionList);
                WalletService walletService = new WalletService();
                Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
                request.getSession().setAttribute("wallet",wallet);

                return "views/wallet.jsp";

        }


        return "views/admin.jsp";
    }
}
