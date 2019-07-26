package by.taining.cryptomarket.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Interface for command template.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public interface Command {


    /**
     * Abstract method for getting appropriate jsp.
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws  Exception;
}
