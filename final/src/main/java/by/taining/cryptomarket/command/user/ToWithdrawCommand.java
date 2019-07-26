package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ToWithdrawCommand implements Command {

    /**
     * This method is to redirect to the page of withdraw.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String coin = request.getParameter("coin");
        request.getSession().setAttribute("coin",coin);
        request.getSession().setAttribute("transactionerror",null);
        return "views/withdraw.jsp";
    }
}
