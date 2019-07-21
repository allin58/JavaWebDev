package command.sec;

import command.Command;
import service.CryptoPairService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class TogglePairCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        CryptoPairService cryptoPairService = new CryptoPairService();

        String identity = request.getParameter("identity");

        cryptoPairService.togglePair(identity);


        request.getSession().setAttribute("secData", new CryptoPairService().getAllPairs());

        return "views/sec.jsp";
    }
}
