package by.taining.cryptomarket.controller.filter;


import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * This filter checks access rights.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class LoginFilter implements Filter {


    /**
     * The empty method.
     * @param config config
     * @throws ServletException ServletException
     */
    public void init(final FilterConfig config) throws ServletException {

    }


    /**
     *  This method checks access rights.
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param chain chain
     * @throws IOException IOException
     * @throws ServletException ServletException
     */
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();
        String servletPath = httpRequest.getServletPath();

        if ((session != null && session.getAttribute("user") != null)
                || "/login.html".equals(servletPath)
                || "/views/registration.jsp".equals(servletPath)) {



         chain.doFilter(servletRequest, servletResponse);

        }  else {

            RequestDispatcher rd = servletRequest.getRequestDispatcher("/views/login.jsp");
            rd.forward(httpRequest, httpResponse);

        }



    }


    /**
     * The empty method.
     */
    public void destroy() {

    }

}
