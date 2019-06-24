package by.training.task4.services;



import by.training.task4.dal.DALImpl;
import by.training.task4.entity.Candy;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.util.ArrayList;

public class SAXParserService implements ParseService {



    public ArrayList parse(String filename, String schemaname) {
        ArrayList<Candy> candies = new ArrayList<Candy>();

        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        Schema schema = null;
        SchemaFactory factory = SchemaFactory.newInstance(language);



        try {

            DALImpl dalParser = new DALImpl();

            schema = factory.newSchema(dalParser.getFile(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();
            parser.parse(filename, new CandyHandler(candies));

        } catch (Exception e) {
            e.printStackTrace();
        }


        return candies;

    }
}
