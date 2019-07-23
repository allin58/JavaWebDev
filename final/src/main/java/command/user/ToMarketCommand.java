package command.user;

import command.Command;
import entity.CryptoPair;
import entity.mapping.TraidingCouple;
import service.CryptoPairService;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToMarketCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {



        request.getSession().setAttribute("marketerror",null);
        request.getSession().setAttribute("ordermessage",null);
        request.getSession().setAttribute("typeoforder","limit");
       String pair = request.getParameter("pair");

       if (pair == null || "".equals(pair)) {


           CryptoPairService cryptoPairService = new CryptoPairService();
           List<TraidingCouple> activePairs = cryptoPairService.getActivePairs();
if (activePairs.size() > 0) {

    pair = activePairs.get(0).getPair();

} else {

    request.getSession().setAttribute("activepairs",null);
    request.getSession().setAttribute("pair",null);
    request.getSession().setAttribute("marketerror","marketerror");
    return "views/market.jsp";
}



       }




        request.getSession().setAttribute("pair",pair);
        OrderService orderService = new OrderService();

        CryptoPairService cryptoPairService = new CryptoPairService();
        List activePairs = cryptoPairService.getActivePairs();

        request.getSession().setAttribute("activepairs",activePairs);

        List askList = orderService.getAskOrdersByPair(pair.trim());
        request.getSession().setAttribute("asklist",askList);

        List bidList = orderService.getBidOrdersByPair(pair.trim());
        request.getSession().setAttribute("bidlist",bidList);

        return "views/market.jsp";
    }
}
