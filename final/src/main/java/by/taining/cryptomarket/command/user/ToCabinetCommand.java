package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.CryptoPairService;
import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.Transaction;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.mapping.MappingTransaction;
import by.taining.cryptomarket.service.CryptoPairService;
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
public class ToCabinetCommand implements Command {


    /**
     * This method is to redirect to the cabinet.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String role = ((User)request.getSession().getAttribute("user")).getRole();

        switch (role) {
            case "admin" :

                List<MappingTransaction> transactionList = new TransactionService().getPendingTransactions();

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
