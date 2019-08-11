package by.taining.cryptomarket.command.general;

import by.taining.cryptomarket.service.WalletService;
import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.entity.User;
import by.taining.cryptomarket.entity.Wallet;
import by.taining.cryptomarket.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The class implements Command interface.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class RegistrationCommand implements Command {


    /**
     * This method allows to do registration.
     * @param request request
     * @param response response
     * @return appropriate jsp
     * @throws Exception
     */
    @Override
    public String execute(final HttpServletRequest request,
                          final HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");

        request.getSession().setAttribute("registrationmessage", null);

        if (!"".equals(username) && !"".equals(name)
                && !"".equals(surname)
                && !"".equals(password)) {
            UserService userService = new UserService();


            if (userService.userIsExist(username)) {

                request.getSession().setAttribute("registrationmessage",
                        "useralredyexist");
                return "views/registration.jsp";
            } else {


                if (username.contains("script")) {

                    request.getSession().setAttribute("registrationmessage",
                            "jsinjection");
                    return "views/registration.jsp";
                }


                User user = new User();
                user.setUserName(username);
                user.setName(name);
                user.setSurname(surname);
                user.setRole("user");
                user.setHashOfPassword(password);
                Integer userId = userService.addUser(user);


                WalletService walletService = new WalletService();
                Wallet wallet = new Wallet();
                wallet.setIdentity(userId);
                wallet.setBtc(0.0);
                wallet.setEth(0.0);
                wallet.setUsdt(0.0);


                 walletService.addNewWallet(wallet);



                request.getSession().setAttribute("loginmessage", "successful");
                return "login.jsp";

            }

        } else {

            request.getSession().setAttribute("registrationmessage",
                    "allfield");
            return "views/registration.jsp";

        }







    }
}
