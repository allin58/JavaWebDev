package command.general;

import command.Command;
import entity.User;
import entity.mapping.TraidingCouple;
import service.CryptoPairService;
import service.OrderService;
import service.TransactionService;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {




            String username = request.getParameter("username");
            String password = request.getParameter("password");

        request.getSession().setAttribute("loginmessage",null);
            if (username != null && password != null) {
                UserService userService = new UserService();

               Integer id = userService.getIdByUserNameAndPassword(username, password);

                User user = userService.getUserById(id);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                  switch (user.getRole()) {
                      case "admin" :
                          request.getSession().setAttribute("transactionData", new TransactionService().getPendingTransactions());
                          return "views/admin.jsp";

                      case "sec" :
                          request.getSession().setAttribute("secData", new CryptoPairService().getAllPairs());
                          return "views/sec.jsp";

                      case "user" :
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
                          request.getSession().setAttribute("typeoforder","limit");

                          CryptoPairService cryptoPairService = new CryptoPairService();
                          List activePairs = cryptoPairService.getActivePairs();
                          request.getSession().setAttribute("activepairs",activePairs);

                          OrderService orderService = new OrderService();
                          List askList = orderService.getAskOrdersByPair(pair.trim());
                          request.getSession().setAttribute("asklist",askList);

                          List bidList = orderService.getBidOrdersByPair(pair.trim());
                          request.getSession().setAttribute("bidlist",bidList);

                          return "views/market.jsp";
                  }
                }
            }


        request.getSession().setAttribute("loginmessage","loginfailed");

        return "login.jsp";




        }




    }

