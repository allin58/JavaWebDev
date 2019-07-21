package command.user;

import command.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToWithdrawCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String coin = request.getParameter("coin");
        request.getSession().setAttribute("coin",coin);
        request.getSession().setAttribute("withdrawerror",null);
        return "views/withdraw.jsp";
    }
}