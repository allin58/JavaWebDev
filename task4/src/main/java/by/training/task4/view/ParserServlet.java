package by.training.task4.view;

import by.training.task4.controller.ParserController;
import by.training.task4.entity.Candy;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ParserServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");

        PrintWriter pw = resp.getWriter();

        ParserController parserController = new ParserController();

        ArrayList<Candy> arrayList = parserController.parse();


        for (Candy candy : arrayList) {

            pw.println("<H1>"+candy.getName()+"</H1>");

        }



       // pw.println("<H1>Hello, world!</H1>");

    }
}
