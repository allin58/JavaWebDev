package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.OrderService;
import by.taining.cryptomarket.service.WalletService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.entity.qualifier.WalletQualifier;
import by.taining.cryptomarket.service.OrderService;
import by.taining.cryptomarket.service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class SetLimitOrderCommand implements Command {

    /**
     * This method which allows to set limit order.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
String typeOfOrder = null;
       /* request.getSession().setAttribute("setlimitordermessage",null);
        request.getSession().setAttribute("executemarketordermessage",null);*/
        request.getSession().setAttribute("ordermessage",null);

        if (request.getParameter("buybutton") != null) {
         typeOfOrder = request.getParameter("buybutton").trim();
             }
        if (request.getParameter("sellbutton") != null) {
            typeOfOrder = request.getParameter("sellbutton").trim();
        }
        String pair = ((String)request.getSession().getAttribute("pair")).trim();
        String firstCoin = pair.split("-")[0];
        String secondCoin = pair.split("-")[1];
        Double price = 0.0;
        Double amount = 0.0;

        try {
             price = Double.valueOf(request.getParameter("price"));
             amount = Double.valueOf(request.getParameter("amount"));
            if (price < 0 || amount < 0) {
                throw new Exception();
            }

        } catch (Exception e) {
            request.getSession().setAttribute("ordermessage","incorrectamount");
            return "views/market.jsp";
        }

        User user = (User)request.getSession().getAttribute("user");
        WalletService walletService = new WalletService();
        Wallet wallet = walletService.getWalletByUserId(user.getIdentity());
        WalletQualifier walletQualifier = new WalletQualifier();

        switch(typeOfOrder) {
            case "buy" :
                Double sum = amount * price;
                if (walletQualifier.getAmountByTicker(wallet,secondCoin) < sum) {
                    request.getSession().setAttribute("ordermessage","insufficientfunds");
                    return "views/market.jsp";
                }

                OrderService orderService = new OrderService();
                Order order = new Order();
                order.setType("Bid");
                order.setState("active");
                order.setPair(pair);
                order.setUserId(user.getIdentity());
                order.setPrice(price);
                order.setAmount(amount);
                orderService.createNewOrder(order);
                break;

            case "sell" :

                if (walletQualifier.getAmountByTicker(wallet,firstCoin) < amount) {
                    request.getSession().setAttribute("ordermessage","insufficientfunds");
                    return "views/market.jsp";
                }

                OrderService orderService2 = new OrderService();
                Order order2 = new Order();
                order2.setType("Ask");
                order2.setState("active");
                order2.setPair(pair);
                order2.setUserId(user.getIdentity());
                order2.setPrice(price);
                order2.setAmount(amount);
                orderService2.createNewOrder(order2);

                break;
        }


        OrderService orderService = new OrderService();
        List askList = orderService.getAskOrdersByPair(pair.trim());
        request.getSession().setAttribute("asklist",askList);

        List bidList = orderService.getBidOrdersByPair(pair.trim());
        request.getSession().setAttribute("bidlist",bidList);

        request.getSession().setAttribute("ordermessage","yourorderisaccepted");

        return "views/market.jsp";
    }
}
