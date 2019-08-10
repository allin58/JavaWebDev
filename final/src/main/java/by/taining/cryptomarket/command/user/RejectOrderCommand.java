package by.taining.cryptomarket.command.user;

import by.taining.cryptomarket.service.OrderService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.Order;
import by.taining.cryptomarket.entity.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class RejectOrderCommand implements Command {

    /**
     * This method which allows to reject order.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {
       Integer identity = Integer.valueOf(request.getParameter("orderid").trim());

        OrderService orderService = new OrderService();
        orderService.rejectOrderById(identity);

        User user = (User) request.getSession().getAttribute("user");
        List<Order> orderList = orderService.getOrdersByUserId(user.getIdentity());
        request.getSession().setAttribute("orders", orderList);


        return "views/orders.jsp";
    }
}
