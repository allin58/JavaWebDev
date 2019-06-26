package by.training.task4.controller;

import by.training.task4.controller.ParserController;
import by.training.task4.entity.Candy;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class ParserServlet extends HttpServlet {





    private static final int MEMORY_THRESHOLD   = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    // location to store file uploaded
    private static final String UPLOAD_DIRECTORY = "upload";

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

     /*   resp.setContentType("text/html;charset=utf-8");
        PrintWriter pw = resp.getWriter();
        ParserController parserController = new ParserController();
        ArrayList<Candy> arrayList = parserController.parse();
        for (Candy candy : arrayList) {
            pw.println("<H1>"+candy.getName()+" "+candy.getEnrgy() +"</H1>");
        }*/

       // pw.println("<H1>Hello, world!</H1>");


        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/index.jsp");
        requestDispatcher.forward(req, resp);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       /* GregorianCalendar gc = new GregorianCalendar();
        String timeJsp = req.getParameter("time");
        float delta = ((float)(gc.getTimeInMillis() - Long.parseLong(timeJsp)))/1000;
        req.setAttribute("res", delta);
        req.getRequestDispatcher("/views/result.jsp").forward(req, resp);*/

      /*  System.out.println( req.getParameter("typeOfParser"));
        System.out.println( req.getParameter("language"));*/



      /////////////////////////////////////////////////////////////////////////////////////////////


        if(ServletFileUpload.isMultipartContent(req)){
            try {
                List<FileItem> multiparts = new ServletFileUpload(
                        new DiskFileItemFactory()).parseRequest(req);

                for(FileItem item : multiparts){
                    if(!item.isFormField()){
                        String name = new File(item.getName()).getName();
                        item.write( new File(UPLOAD_DIRECTORY + File.separator + name));
                    }
                }

                //File uploaded successfully
                req.setAttribute("message", "File Uploaded Successfully");
            } catch (Exception ex) {
                req.setAttribute("message", "File Upload Failed due to " + ex);
            }

        }else{
            req.setAttribute("message",
                    "Sorry this Servlet only handles file upload request");
        }

        req.getRequestDispatcher("views/result.jsp").forward(req, resp);






        }


    }

