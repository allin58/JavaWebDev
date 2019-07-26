package by.taining.cryptomarket.command.admin;

import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.command.Command;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ApproveTransactionCommand implements Command {


    /**
     * This method allows approve of transaction.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


       Integer idintity = Integer.valueOf(request.getParameter("identity").trim());
       new TransactionService().approveTransaction(idintity);


        request.getSession().setAttribute("transactionData", new TransactionService().getPendingTransactions());
        return "views/admin.jsp";
    }
}
