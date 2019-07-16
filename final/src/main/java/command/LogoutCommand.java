package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogoutCommand implements Command {


    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        //request.getSession().setAttribute("user",null);
        request.getSession().invalidate();

        return "login.jsp";
    }
}
