package by.taining.task4.controller;

import by.taining.task4.services.CandyHandler;
import by.taining.task4.entity.Candy;
import by.taining.task4.services.ParseService;
import by.taining.task4.services.SAXParserService;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {


        String filename = "data/data.xml";
        String schemaname = "data/candyschema.xsd";

        ParseService ps = new SAXParserService();
        ArrayList list = ps.parse(filename,schemaname);
        for (Object o : list) {
            System.out.println(((Candy)o).getName());

        }

    }
}
