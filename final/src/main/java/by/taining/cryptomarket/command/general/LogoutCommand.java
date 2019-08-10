package by.taining.cryptomarket.command.general;

import by.taining.cryptomarket.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class LogoutCommand implements Command {


    /**
     * This method allows do logouting.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {
        request.getSession().invalidate();

        return "login.jsp";
    }
}
