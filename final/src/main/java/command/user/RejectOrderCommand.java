package command.user;

import command.Command;
import entity.Order;
import entity.User;
import service.OrderService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RejectOrderCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
       Integer identity = Integer.valueOf(request.getParameter("orderid").trim());

        OrderService orderService = new OrderService();
        orderService.rejectOrderById(identity);

        User user = (User)request.getSession().getAttribute("user");
        List<Order> orderList = orderService.getOrdersByUserId(user.getIdentity());
        request.getSession().setAttribute("orders",orderList);


        return "views/orders.jsp";
    }
}
