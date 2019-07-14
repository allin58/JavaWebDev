package filter;

import entity.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        HttpSession session = httpRequest.getSession();


        if (session != null && session.getAttribute("user") != null) {
            if (checkRequest(httpRequest)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                session.setAttribute("message","сработала защита от несанкционированного доступа ");
                session.setAttribute("user", null);
                RequestDispatcher rd = servletRequest.getRequestDispatcher("login.jsp");
                rd.forward(httpRequest, httpResponse);
            }



        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }






    }


    public boolean checkRequest( HttpServletRequest httpRequest) {

        User user = (User)httpRequest.getSession().getAttribute("user");

        String role = user.getRole();

        String requestURI = httpRequest.getRequestURI();

if("user".equals(role) && (requestURI.contains("sec") || requestURI.contains("admin"))) {
    return false;
}

if("admin".equals(role) && requestURI.contains("sec") ) {
    return false;
}

        if("sec".equals(role) && requestURI.contains("admin") ) {
            return false;
        }



        return true;
    }


    @Override
    public void destroy() {

    }
}
