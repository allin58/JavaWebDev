package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.OrderService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class ToMyOrdersCommand implements Command {


    /**
     * This method is to redirect to the page of orders.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = (User)request.getSession().getAttribute("user");
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersByUserId(user.getIdentity());



        request.getSession().setAttribute("orders",orderList);

        return "views/orders.jsp";
    }
}
