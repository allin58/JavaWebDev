package controller.filter;


import utf8control.UTF8Control;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
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
