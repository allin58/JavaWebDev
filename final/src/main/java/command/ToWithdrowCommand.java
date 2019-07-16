package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ToWithdrowCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String coin = request.getParameter("coin");
        request.getSession().setAttribute("coin",coin);
        request.getSession().setAttribute("withdrowerror",null);
        return "views/withdrow.jsp";
    }
}
