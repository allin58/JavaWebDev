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
public class SetTypeOfOrderCommand implements Command {

    /**
     * This method which allows to toggle type of order.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {

        request.getSession().setAttribute("typeoforder",
                request.getParameter("typeoforder"));
        return "views/market.jsp";
    }
}
