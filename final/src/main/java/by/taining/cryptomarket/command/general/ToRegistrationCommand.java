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
public class ToRegistrationCommand implements Command {


    /**
     * This method which redirect to page of registration.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("registrationmessage",null);
        return "views/registration.jsp";
    }
}
