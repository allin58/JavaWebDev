package command;

import com.sun.org.apache.xpath.internal.operations.Or;
import dao.transaction.ExecuteOrderTransaction;
import entity.Order;
import entity.User;
import entity.Wallet;
import entity.qualifier.WalletQualifier;
import service.OrderService;
import service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ExecuteMarketOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.getSession().setAttribute("setlimitordermessage",null);
        request.getSession().setAttribute("executemarketordermessage",null);

        String typeOfOrder = null;
        if (request.getParameter("buybutton") != null) {
            typeOfOrder = request.getParameter("buybutton").trim();
        }
        if (request.getParameter("sellbutton") != null) {
            typeOfOrder = request.getParameter("sellbutton").trim();
        }


        String pair = ((String)request.getSession().getAttribute("pair")).trim();
        String firstCoin = pair.split("-")[0];
        String secondCoin = pair.split("-")[1];
        Double amount = 0.0;
        User user = (User)request.getSession().getAttribute("user");
        WalletService walletService = new WalletService();
        Wallet wallet = walletService.getWalletByUserId(user.getIdentity());

        try {
            amount = Double.valueOf(request.getParameter("amount"));
            if (amount < 0) {
                throw new Exception();
            }

        } catch (Exception e) {
            request.getSession().setAttribute("executemarketordermessage","введены неверные данные");
            return "views/market.jsp";
        }


        WalletQualifier walletQualifier = new WalletQualifier();


        switch(typeOfOrder) {
            case "buy" :

                OrderService orderServiceB = new OrderService();
                List<Order> orderList = orderServiceB.getAskOrdersByPair(pair);
                Collections.reverse(orderList);
                Double realAmountOfSecondCurrency = walletQualifier.getAmountByTicker(wallet,secondCoin);
                Double expectedAmountOfSecondCurrency = 0.0;
                Double rest = amount;

                for (int i = 0; i < orderList.size(); i++) {
                    Order order = orderList.get(i);
                     if (amount < order.getAmount()) {
                        if (realAmountOfSecondCurrency < amount * order.getPrice() ) {
                            request.getSession().setAttribute("executemarketordermessage","недостаточно средств");
                            return "views/market.jsp";
                        }
                        break;
                    }
                    if (rest != 0) {
                        if (rest > order.getAmount()){
                            expectedAmountOfSecondCurrency = expectedAmountOfSecondCurrency + (order.getAmount() * order.getPrice());
                            rest = rest - order.getAmount();
                        } else {
                            expectedAmountOfSecondCurrency = expectedAmountOfSecondCurrency + (rest * order.getPrice());
                            break;
                            }
                    }

                }

                if (expectedAmountOfSecondCurrency > realAmountOfSecondCurrency) {
                    request.getSession().setAttribute("executemarketordermessage","недостаточно средств");
                    return "views/market.jsp";
                }


                // если хватает secondCurrency



                break;

            case "sell" :

              if (walletQualifier.getAmountByTicker(wallet,firstCoin) < amount) {
                  request.getSession().setAttribute("executemarketordermessage","недостаточно средств");
                  return "views/market.jsp";
                 }
              OrderService orderService = new OrderService();
              List<Order> orders = orderService.getBidOrdersByPair(pair);
              Double resultAmount = 0.0;
                for (Order order : orders) {
                    if(resultAmount < amount) {

                       if (order.getAmount() > amount - resultAmount) {

                           orderService.executePartlyOrder(order,user, amount - resultAmount );

                           resultAmount = amount;
                       } else {


                           orderService.executeOrder(order,user);


                           resultAmount = resultAmount + order.getAmount();
                       }


                    } else {
                        break;
                    }


                }


                break;
        }

        request.getSession().setAttribute("executemarketordermessage","ваша заявка выполнена");


        return "views/market.jsp";
    }
}
