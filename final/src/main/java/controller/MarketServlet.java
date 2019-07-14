package controller;


import command.Command;
import command.CommandFactory;
import dao.connectionpool.BasicConnectionPool;
import dao.connectionpool.ConnectionPool;
import entity.User;
import exception.PersistentException;
import service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class MarketServlet extends HttpServlet {
    public static final String url = "jdbc:mariadb://localhost:3306/market";
    public static final String username = "market";
    public static final String password = "market";

    public void init() {

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            BasicConnectionPool.create(url,username,password);


        } catch (Exception e) {
            e.printStackTrace();
        }


    }



    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {


        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command;

        String commandName = request.getParameter("command");

        String path = "views/error.jsp";
        command = commandFactory.createCommand(commandName);



        try {
            path = command.execute(request, response);

        } catch (Exception e) {

        }


   /*     RequestDispatcher rd = request.getRequestDispatcher(path);
        rd.forward(request, response);*/

           response.sendRedirect(response.encodeRedirectURL(path));
            //response.sendRedirect(path);


       }


    @Override
    public void destroy() {

        try {
            BasicConnectionPool.getBasicConnectionPool().shutdown();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




