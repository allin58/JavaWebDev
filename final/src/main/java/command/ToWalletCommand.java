package command;

import entity.Transaction;
import entity.User;
import entity.Wallet;
import entity.mapping.MappingTransaction;
import service.TransactionService;
import service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToWalletCommand implements Command {

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
