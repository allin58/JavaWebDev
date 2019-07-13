package command;

import entity.User;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {




            String username = request.getParameter("username");
            String password = request.getParameter("password");


            if (username != null && password != null) {
                UserService userService = new UserService();
                Integer id = userService.getIdByUserName(username, password);
                User user = userService.getUserById(id);
                if (user != null) {
                    request.getSession().setAttribute("user", user);
                  switch (user.getRole()) {
                      case "admin" :   return "views/admin.jsp";

                      case "sec" :   return "views/sec.jsp";

                      case "user" :   return "views/market.jsp";
                  }
                }
            }


        request.getSession().setAttribute("message","ошибка входа");
/*добавить сообщение ошибки*/
        return "views/login.jsp";




        }




    }

