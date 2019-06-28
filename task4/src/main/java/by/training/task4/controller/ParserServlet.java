package by.training.task4.controller;

import by.training.task4.entity.Candy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import by.training.task4.services.*;
import org.apache.commons.io.FileUtils;

/** This class extends from HttpServlet and implements doGet and doPost methods.
  */
@MultipartConfig
public class ParserServlet extends HttpServlet {


    /**The method that handles GET requests.
     *
     * @param req object of request
     * @param resp object of response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {




        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }

    /**The method that handles POST requests.
     *
     * @param req object of request
     * @param resp object of response
     * @throws ServletException ServletException
     * @throws IOException IOException
     */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException {


        String schemaname = "data/candyschema.xsd";
        String parser;
        String language;

        BufferedReader bufferedReader1 = new BufferedReader(new InputStreamReader(req.getPart("language").getInputStream()));
        language = bufferedReader1.readLine();
        bufferedReader1.close();

        BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(req.getPart("typeOfParser").getInputStream()));
        parser = bufferedReader2.readLine();
        bufferedReader2.close();

        File file = File.createTempFile("txt", "txt");
        FileUtils.copyInputStreamToFile(req.getPart("file").getInputStream(), file);




        AbstractCandiesBuilder candiesBuilder = new CandyBuilderFactory().createCandyBuilder(parser);
        candiesBuilder.buildListCandies(file, schemaname);
        ArrayList<Candy> arrayList = candiesBuilder.getCandies();



        Locale locale = null;
        if ("russian".equals(language)) {
            locale = new Locale("ru");
        }
        if ("english".equals(language)) {
            locale = new Locale("en");
        }

        ResourceBundle resourceBundle = ResourceBundle.getBundle("text", locale, new UTF8Control());


        req.setAttribute("name", resourceBundle.getString("candy.name"));
        req.setAttribute("energy", resourceBundle.getString("candy.energy"));
        req.setAttribute("production", resourceBundle.getString("candy.production"));
        req.setAttribute("id", resourceBundle.getString("candy.id"));
        req.setAttribute("type", resourceBundle.getString("candy.type"));
        req.setAttribute("proteins", resourceBundle.getString("candy.proteins"));
        req.setAttribute("fats", resourceBundle.getString("candy.fats"));
        req.setAttribute("typeOfChocolate", resourceBundle.getString("candy.typeOfChocolate"));
        req.setAttribute("water", resourceBundle.getString("candy.water"));
        req.setAttribute("sugar", resourceBundle.getString("candy.sugar"));
        req.setAttribute("fructose", resourceBundle.getString("candy.fructose"));
        req.setAttribute("vanillin", resourceBundle.getString("candy.vanillin"));
        req.setAttribute("carbohydrates", resourceBundle.getString("candy.carbohydrates"));
        req.setAttribute("typeOfParser", parser);
        req.setAttribute("candyList", arrayList);
        req.getRequestDispatcher("/views/result.jsp").forward(req, resp);


        }



















    }

