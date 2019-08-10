package by.taining.cryptomarket.command.general;

import by.taining.cryptomarket.service.CryptoPairService;
import by.taining.cryptomarket.service.OrderService;
import by.taining.cryptomarket.service.TransactionService;
import by.taining.cryptomarket.service.UserService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.mapping.TraidingCouple;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class LoginCommand implements Command {


    /**
     * This method allows to do logging.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {

       String username = request.getParameter("username");
       String password = request.getParameter("password");

        request.getSession().setAttribute("loginmessage", null);
            if (username != null && password != null) {
                UserService userService = new UserService();
               Integer id = userService.getIdByUserNameAndPassword(username,
                       password);

                User user = userService.getUserById(id);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                  switch (user.getRole()) {
                      case "admin" :
                         request.getSession().setAttribute("transactionData",
                                 new TransactionService().getPendingTransactions());
                          return "views/admin.jsp";

                      case "sec" :
                          request.getSession().setAttribute("secData",
                                  new CryptoPairService().getAllPairs());
                          return "views/sec.jsp";

                      case "user" :
                          String pair = request.getParameter("pair");
                          if (pair == null || "".equals(pair)) {
                              CryptoPairService cryptoPairService = new CryptoPairService();
                              List<TraidingCouple> activePairs = cryptoPairService.getActivePairs();
                              if (activePairs.size() > 0) {

                                  pair = activePairs.get(0).getPair();
                              } else {
                                  request.getSession().setAttribute("activepairs", null);
                                  request.getSession().setAttribute("pair", null);
                                  request.getSession().setAttribute("marketerror", "marketerror");
                                  return "views/market.jsp";
                              }
                          }
                          request.getSession().setAttribute("pair", pair);
                          request.getSession().setAttribute("typeoforder", "limit");

                          CryptoPairService cryptoPairService = new CryptoPairService();
                          List activePairs = cryptoPairService.getActivePairs();
                          request.getSession().setAttribute("activepairs", activePairs);

                          OrderService orderService = new OrderService();
                          List askList = orderService.getAskOrdersByPair(pair.trim());
                          request.getSession().setAttribute("asklist", askList);

                          List bidList = orderService.getBidOrdersByPair(pair.trim());
                          request.getSession().setAttribute("bidlist", bidList);

                          return "views/market.jsp";
                  }
                }
            }


        request.getSession().setAttribute("loginmessage", "loginfailed");

        return "login.jsp";




        }




    }

