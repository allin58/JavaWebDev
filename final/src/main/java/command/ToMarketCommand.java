package command;

import entity.CryptoPair;
import service.CryptoPairService;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToMarketCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {




        request.getSession().setAttribute("setlimitordermessage",null);
       String pair = request.getParameter("pair");

       if (pair == null) {
           pair = "BTC-USDT";
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
