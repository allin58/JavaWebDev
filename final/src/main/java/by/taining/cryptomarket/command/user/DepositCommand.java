package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.mapping.MappingTransaction;
import by.taining.cryptomarket.service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class DepositCommand implements Command {


    /**
     * This method which allows to do deposit.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
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
