package by.taining.cryptomarket.command.sec;

import by.taining.cryptomarket.service.CryptoPairService;
import by.taining.cryptomarket.command.Command;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class TogglePairCommand implements Command {


    /**
     * This method for toggling state of markets.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request, final HttpServletResponse response) throws Exception {

        CryptoPairService cryptoPairService = new CryptoPairService();

        String identity = request.getParameter("identity");

        cryptoPairService.togglePair(identity);


        request.getSession().setAttribute("secData", new CryptoPairService().getAllPairs());

        return "views/sec.jsp";
    }
}
