package by.training.task4.services;


import by.training.task4.entity.Candy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;

public class CandyHandler extends DefaultHandler {
private String thisElement;
private ArrayList<Candy> candies;
private Candy tempCandy;

    public CandyHandler(ArrayList<Candy> candies) {
        this.candies = candies;
    }

    @Override
    public void startDocument() throws SAXException {
        System.out.println("Start parse XML...");
    }

    @Override
    public void startElement(String namespaceURI, String localName, String qName, Attributes atts) throws SAXException {
        thisElement = qName;

        for (int i = 0; i < atts.getLength(); i++) {
           if ("name".equals(atts.getLocalName(i))) {
               tempCandy = new Candy();
               tempCandy.setName(atts.getValue(i));
           }

            if ("energy".equals(atts.getLocalName(i))) {
                tempCandy.setEnrgy(new Integer(atts.getValue(i)));
            }

            if ("production".equals(atts.getLocalName(i))) {
                tempCandy.setProduction(atts.getValue(i));
            }

            if ("id".equals(atts.getLocalName(i))) {
                tempCandy.setId(new Integer(atts.getValue(i).split("-")[1]));
            }

            if ("typeOfChocolate".equals(atts.getLocalName(i))) {
                tempCandy.setTypeOfChocolate(atts.getValue(i));
            }

        }


    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {


        if (thisElement.equals("type")) {
            tempCandy.setType(new String(ch, start, length));
          }

        if (thisElement.equals("proteins")) {
            tempCandy.setProteins(new Integer(new String(ch, start, length)));
        }

        if (thisElement.equals("fats")) {
            tempCandy.setFats(new Integer(new String(ch, start, length)));
        }

        if (thisElement.equals("carbohydrates")) {
            tempCandy.setCarbohydrates(new Integer(new String(ch, start, length)));
        }

        if (thisElement.equals("water")) {
            tempCandy.setWater(new Integer(new String(ch, start, length)));
        }

        if (thisElement.equals("sugar")) {
            tempCandy.setSugar(new Integer(new String(ch, start, length)));
        }

        if (thisElement.equals("vanillin")) {
            tempCandy.setVanillin(new Integer(new String(ch, start, length)));

            candies.add(tempCandy);

        }

        if (thisElement.equals("fructose")) {
            tempCandy.setFructose(new Integer(new String(ch, start, length)));
        }


    }
}
