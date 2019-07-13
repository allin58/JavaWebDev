package filter;

import entity.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static java.util.Objects.nonNull;

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

        if (session.getAttribute("user") != null || "/login.html".equals(uri)) {

            chain.doFilter(servletRequest, servletResponse);

        }
        else {

            RequestDispatcher rd = servletRequest.getRequestDispatcher("views/login.jsp");
            rd.forward(httpRequest, httpResponse);
        }



    }










    public void destroy()
    {

    }

}
