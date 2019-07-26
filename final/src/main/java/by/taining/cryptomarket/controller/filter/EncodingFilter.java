package by.taining.cryptomarket.controller.filter;

import javax.servlet.*;
import java.io.IOException;


/**
 * This filter sets of encoding.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class EncodingFilter implements Filter {

    /**
     * The empty method.
     * @param filterConfig filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }



    /**
     *  The method for setting of encoding.
     * @param servletRequest servletRequest
     * @param servletResponse servletResponse
     * @param filterChain filterChain
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.setCharacterEncoding("UTF-8");
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /**
     * The empty method.
     */
    @Override
    public void destroy() {

    }
}
