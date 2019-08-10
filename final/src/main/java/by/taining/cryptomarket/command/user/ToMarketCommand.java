package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.CryptoPairService;
import by.taining.cryptomarket.service.OrderService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.mapping.TraidingCouple;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ToMarketCommand implements Command {

    /**
     * This method is to redirect to the market.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {



        request.getSession().setAttribute("marketerror", null);
        request.getSession().setAttribute("ordermessage", null);
        request.getSession().setAttribute("typeoforder", "limit");
       String pair = request.getParameter("pair");

       if (pair == null || "".equals(pair)) {


           CryptoPairService cryptoPairService = new CryptoPairService();
           List<TraidingCouple> activePairs = cryptoPairService.getActivePairs();
if (activePairs.size() > 0) {

    pair = activePairs.get(0).getPair();

} else {

    request.getSession().setAttribute("activepairs", null);
    request.getSession().setAttribute("pair", null);
    request.getSession().setAttribute("marketerror", "marketerror");
    return "views/market.jsp";
}



       }




        request.getSession().setAttribute("pair", pair);
        OrderService orderService = new OrderService();

        CryptoPairService cryptoPairService = new CryptoPairService();
        List activePairs = cryptoPairService.getActivePairs();

        request.getSession().setAttribute("activepairs", activePairs);

        List askList = orderService.getAskOrdersByPair(pair.trim());
        request.getSession().setAttribute("asklist", askList);

        List bidList = orderService.getBidOrdersByPair(pair.trim());
        request.getSession().setAttribute("bidlist", bidList);

        return "views/market.jsp";
    }
}
