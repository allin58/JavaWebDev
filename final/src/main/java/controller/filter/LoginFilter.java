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
