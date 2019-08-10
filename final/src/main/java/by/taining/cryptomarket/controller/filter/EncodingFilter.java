package by.taining.cryptomarket.controller.filter;


import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletResponse;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
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
    public void init(final FilterConfig filterConfig) throws ServletException {

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
    public void doFilter(final ServletRequest servletRequest,
                         final ServletResponse servletResponse,
                         final FilterChain filterChain) throws IOException, ServletException {
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
