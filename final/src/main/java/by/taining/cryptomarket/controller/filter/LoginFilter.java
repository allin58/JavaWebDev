package by.taining.cryptomarket.controller.filter;


import by.taining.cryptomarket.utf8control.UTF8Control;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * This filter checks access rights.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class LoginFilter implements Filter {


    /**
     * The empty method.
     * @param config
     * @throws ServletException
     */
    public void init (FilterConfig config) throws ServletException
    {

    }


    /**
     *  This method checks access rights.
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param chain chain
     * @throws IOException
     * @throws ServletException
     */
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


    /**
     * The empty method.
     */
    public void destroy()
    {

    }

}
