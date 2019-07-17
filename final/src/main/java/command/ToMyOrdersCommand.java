package command;

import entity.Order;
import entity.User;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ToMyOrdersCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        User user = (User)request.getSession().getAttribute("user");
        OrderService orderService = new OrderService();
        List<Order> orderList = orderService.getOrdersByUserId(user.getIdentity());



        request.getSession().setAttribute("orders",orderList);

        return "views/orders.jsp";
    }
}
