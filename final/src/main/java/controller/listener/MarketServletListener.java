package controller.listener;

import entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class MarketServletListener implements ServletRequestListener {
    final static Logger LOGGER = LogManager.getLogger("by.training.final.ServletLogger");
    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {

    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
       if (request.getParameter("command") != null) {
           String userName = "unknown";
           String role = "unknown";
           if (request.getSession().getAttribute("user") != null) {
               User user = (User)request.getSession().getAttribute("user");
               userName = user.getUserName();
               role = user.getRole();
           }

           LOGGER.info("username-" +userName + ", role-" + role + ", command-" +  request.getParameter("command"));

       }

    }
}
