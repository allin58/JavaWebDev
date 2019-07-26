package by.taining.cryptomarket.controller;


import by.taining.cryptomarket.command.Command;
import by.taining.cryptomarket.command.CommandFactory;
import by.taining.cryptomarket.dao.connectionpool.BasicConnectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.sql.SQLException;


/**
 * Main controller.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class MarketServlet extends HttpServlet {
    public static final String url = "jdbc:mariadb://localhost:3306/market";
    public static final String username = "market";
    public static final String password = "market";
    final static Logger LOGGER = LogManager.getLogger("by.training.final.ServletLogger");


    /**
     * A connection pool is created here.
     */
    public void init() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            BasicConnectionPool.create(url,username,password);


        } catch (Exception e) {
            LOGGER.info("unable to create connection pool");
        }


    }


    /**
     * The method for handling of get requests.
     * @param request request
     * @param response response
     * @throws IOException
     * @throws ServletException
     */
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        process(request, response);
    }

    /**
     * The method for handling of post requests.
     * @param request request
     * @param response response
     * @throws IOException
     * @throws ServletException
     */
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        process(request, response);
    }


    /**The method for handling of all requests.
     *
     * @param request request
     * @param response response
     * @throws IOException
     * @throws ServletException
     */
    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command;

        String commandName = request.getParameter("command");

        String path;
        command = commandFactory.createCommand(commandName);

        try {
            path = command.execute(request, response);

        } catch (Exception e) {
            path = "views/error.jsp";
        }

           response.sendRedirect(response.encodeRedirectURL(path));

       }


    /**
     * A connection pool is shutdowned here.
     */
    @Override
    public void destroy() {

        try {
            BasicConnectionPool.getBasicConnectionPool().shutdown();
        } catch (SQLException e) {
            LOGGER.info("unable to close connection pool");
        }
    }
}




