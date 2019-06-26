package by.training.task4.controller;

import by.training.task4.services.DOMParserService;
import by.training.task4.services.ParseService;
import by.training.task4.services.SAXParserService;
import by.training.task4.services.STAXParserService;

import java.util.ArrayList;

public class ParserController {



    public ArrayList parse() {

        String filename = "data/data.xml";
        String schemaname = "data/candyschema.xsd";




        //ParseService ps = new SAXParserService();
        ParseService ps = new STAXParserService();
        ArrayList list = ps.parse(filename,schemaname);
        return list;

    }
}
