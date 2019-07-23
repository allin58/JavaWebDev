package command.user;

import command.Command;
import entity.User;
import entity.mapping.MappingTransaction;
import service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class DepositCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("transactionerror",null);
        try {
           Double amount = Double.valueOf(request.getParameter("amount"));
           if(amount < 0){
               throw new Exception();
           }


           String coin = (String) request.getSession().getAttribute("coin");
           Integer userId = ((User)request.getSession().getAttribute("user")).getIdentity();

            TransactionService transactionService = new TransactionService();

            transactionService.setDepositTransaction(userId,amount,coin);

            User user = (User)request.getSession().getAttribute("user");
            List<MappingTransaction> transactionList = transactionService.getTransactionsByUser(user.getUserName());
            request.getSession().setAttribute("transactions",transactionList);
            return "views/wallet.jsp";
        } catch (Exception e) {

            request.getSession().setAttribute("transactionerror","incorrectamount");
            return "views/deposit.jsp";

        }



    }
}
