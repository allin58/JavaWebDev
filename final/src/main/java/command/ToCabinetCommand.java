package command;

import entity.Transaction;
import entity.User;
import entity.mapping.MappingTransaction;
import service.CryptoPairService;
import service.TransactionService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToCabinetCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String role = ((User)request.getSession().getAttribute("user")).getRole();

        switch (role) {
            case "admin" :

                List<MappingTransaction> transactionList = new TransactionService().getPendingTransactions();
                System.out.println(transactionList.size());
                request.getSession().setAttribute("transactionData", transactionList);
                return "views/admin.jsp";

            case "sec" :
                request.getSession().setAttribute("secData", new CryptoPairService().getAllPairs());
                return "views/sec.jsp";

            case "user" :   return "views/market.jsp";
        }

        return "views/error.jsp";
    }
}
