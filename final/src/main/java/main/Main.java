package main;

import dao.connectionpool.BasicConnectionPool;
import service.UserService;

public class Main {
    public static final String url = "jdbc:mariadb://localhost:3306/market";
    public static final String username = "market";
    public static final String password = "market";

    public static void main(String[] args) throws Exception{
        Class.forName("org.mariadb.jdbc.Driver");
        BasicConnectionPool.create(url,username,password);
        UserService userService = new UserService();

        System.out.println(userService.getUserById(20));
    }
}
