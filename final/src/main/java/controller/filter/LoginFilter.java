package controller.filter;


import utf8control.UTF8Control;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;


public class LoginFilter implements Filter {


    public void init (FilterConfig config) throws ServletException
    {

    }

    public void doFilter (ServletRequest servletRequest, ServletResponse servletResponse,
                          FilterChain chain) throws IOException, ServletException
    {



        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        String uri = httpRequest.getRequestURI();



        if ((session != null && session.getAttribute("language") == null) ){
            httpRequest.getSession().setAttribute("language","en");
                }



        if (session != null ) {
            Locale locale = new Locale((String) httpRequest.getSession().getAttribute("language"));

            ResourceBundle resourceBundle = ResourceBundle.getBundle("text", locale, new UTF8Control());
            httpRequest.getSession().setAttribute("login", resourceBundle.getString("button.login"));
            httpRequest.getSession().setAttribute("registration", resourceBundle.getString("button.registration"));
            httpRequest.getSession().setAttribute("logout", resourceBundle.getString("button.logout"));
            httpRequest.getSession().setAttribute("approve", resourceBundle.getString("button.approve"));
            httpRequest.getSession().setAttribute("cabinet", resourceBundle.getString("button.cabinet"));
            httpRequest.getSession().setAttribute("reject", resourceBundle.getString("button.reject"));
            httpRequest.getSession().setAttribute("toggle", resourceBundle.getString("button.toggle"));
            httpRequest.getSession().setAttribute("myorders", resourceBundle.getString("button.myorders"));
            httpRequest.getSession().setAttribute("mywallet", resourceBundle.getString("button.mywallet"));
            httpRequest.getSession().setAttribute("market", resourceBundle.getString("button.market"));
            httpRequest.getSession().setAttribute("buy", resourceBundle.getString("button.buy"));
            httpRequest.getSession().setAttribute("sell", resourceBundle.getString("button.sell"));
            httpRequest.getSession().setAttribute("cancel", resourceBundle.getString("button.cancel"));
            httpRequest.getSession().setAttribute("deposit", resourceBundle.getString("button.deposit"));
            httpRequest.getSession().setAttribute("withdraw", resourceBundle.getString("button.withdraw"));

            httpRequest.getSession().setAttribute("username", resourceBundle.getString("text.username"));
            httpRequest.getSession().setAttribute("name", resourceBundle.getString("text.name"));
            httpRequest.getSession().setAttribute("surname", resourceBundle.getString("text.surname"));
            httpRequest.getSession().setAttribute("password", resourceBundle.getString("text.password"));

            httpRequest.getSession().setAttribute("administrator", resourceBundle.getString("text.administrator"));

            httpRequest.getSession().setAttribute("sec", resourceBundle.getString("text.sec"));

            HashMap<String,String> orderState = new HashMap<>();
            orderState.put("executed",resourceBundle.getString("text.executed"));
            orderState.put("canceled",resourceBundle.getString("text.canceled"));
            orderState.put("active",resourceBundle.getString("text.active"));

            HashMap<String,String> transactionType = new HashMap<>();
            transactionType.put("withdraw",resourceBundle.getString("button.withdraw"));
            transactionType.put("deposit",resourceBundle.getString("button.deposit"));

            HashMap<String,String> transactionStatus = new HashMap<>();
            transactionStatus.put("pending",resourceBundle.getString("text.pending"));
            transactionStatus.put("approved",resourceBundle.getString("text.approved"));
            transactionStatus.put("rejected",resourceBundle.getString("text.rejected"));

            HashMap<String,String> loginFailed = new HashMap<>();
            loginFailed.put("loginfailed",resourceBundle.getString("text.loginfailed"));
            loginFailed.put("protection",resourceBundle.getString("text.protection"));
            loginFailed.put("allfield",resourceBundle.getString("text.allfield"));
            loginFailed.put("useralredyexist",resourceBundle.getString("text.useralredyexist"));
            loginFailed.put("successful",resourceBundle.getString("text.successful"));


            httpRequest.getSession().setAttribute("orderState", orderState);
            httpRequest.getSession().setAttribute("transactionType", transactionType);
            httpRequest.getSession().setAttribute("transactionStatus", transactionStatus);
            httpRequest.getSession().setAttribute("loginFailed", loginFailed);


        }







if ((session != null && session.getAttribute("user") != null) ||
        "/login.html".equals(uri) ||
        "/views/registration.jsp".equals(uri)) {



         chain.doFilter(servletRequest, servletResponse);

        }  else {

            RequestDispatcher rd = servletRequest.getRequestDispatcher("views/login.jsp");
            rd.forward(httpRequest, httpResponse);

        }



    }










    public void destroy()
    {

    }

}
