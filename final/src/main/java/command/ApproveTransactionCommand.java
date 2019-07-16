package command;

import service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveTransactionCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {


       Integer idintity = Integer.valueOf(request.getParameter("identity").trim());
       new TransactionService().approveTransaction(idintity);


        request.getSession().setAttribute("transactionData", new TransactionService().getPendingTransactions());
        return "views/admin.jsp";
    }
}
