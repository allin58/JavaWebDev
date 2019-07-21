package command.general;

import command.Command;
import entity.User;
import entity.Wallet;
import service.UserService;
import service.WalletService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RegistrationCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        String username = request.getParameter("username");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");



        if (!"".equals(username) && !"".equals(name)  && !"".equals(surname)  &&!"".equals(password)) {
            UserService userService = new UserService();
            if (userService.userIsExist(username)) {

                request.getSession().setAttribute("error","пользователь с таким именем уже существует");
                return "views/registration.jsp";
            } else {

                User user = new User();
                user.setUserName(username);
                user.setName(name);
                user.setSurname(surname);
                user.setRole("user");
                user.setHashOfPassword(password);
                Integer userId =userService.addUser(user);


                WalletService walletService = new WalletService();
                Wallet wallet = new Wallet();
                wallet.setIdentity(userId);
                wallet.setBtc(0.0);
                wallet.setEth(0.0);
                wallet.setUsdt(0.0);


                 walletService.addNewWallet(wallet);



                request.getSession().setAttribute("message","регистрация прошла успешно");
                return "login.jsp";

            }

        } else {

            request.getSession().setAttribute("error","все поля должны быть заполненны");
            return "views/registration.jsp";

        }







    }
}
