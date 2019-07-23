package command.user;

import command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SetTypeOfOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        request.getSession().setAttribute("typeoforder",request.getParameter("typeoforder"));
        return "views/market.jsp";
    }
}
