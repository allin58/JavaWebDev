package command.user;

import command.Command;
import entity.Order;
import entity.User;
import entity.Wallet;
import entity.qualifier.WalletQualifier;
import service.OrderService;
import service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class SetLimitOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
String typeOfOrder = null;
        request.getSession().setAttribute("setlimitordermessage",null);
        request.getSession().setAttribute("executemarketordermessage",null);

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
            request.getSession().setAttribute("setlimitordermessage","введены неверные данные");
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
                    request.getSession().setAttribute("setlimitordermessage","недостаточно средств");
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
                    request.getSession().setAttribute("setlimitordermessage","недостаточно средств");
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

        request.getSession().setAttribute("setlimitordermessage","ваш ордер принят");

        return "views/market.jsp";
    }
}
