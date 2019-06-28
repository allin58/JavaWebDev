package by.training.task4.services;


import by.training.task4.entity.Candy;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;


/**Class for SAX2 event handlers.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CandyHandler extends DefaultHandler {

    /**
     * Current element.
     */
    private String thisElement;

    /**
     * Collection of candy.
     */
    private ArrayList<Candy> candies;

    /**
     * Temp candy.
     */
    private Candy tempCandy;


    /**
     * Constuctor with parameter.
     * @param candies candies
     */
    public CandyHandler(final ArrayList<Candy> candies) {
        this.candies = candies;
    }


    /**
     * Receive notification of the beginning of the document.
     * @throws SAXException SAXException
     */
    @Override
    public void startDocument() throws SAXException {

    }


    /**
     * Receive notification of the start of an element.
     * @param namespaceURI namespaceURI
     * @param localName localName
     * @param qName qName
     * @param atts atts
     * @throws SAXException SAXException
     */
    @Override
    public void startElement(final String namespaceURI, final String localName, final String qName, final Attributes atts) throws SAXException {
        thisElement = qName;

        for (int i = 0; i < atts.getLength(); i++) {
           if ("name".equals(atts.getLocalName(i))) {
               tempCandy = new Candy();
               tempCandy.setName(atts.getValue(i));
           }

            if ("energy".equals(atts.getLocalName(i))) {
                tempCandy.setEnergy(new Integer(atts.getValue(i)));
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


    /**
     * Receive notification of the end of the document.
     * @throws SAXException SAXException
     */
    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }


    /**
     * Receive notification of the end of an element.
     * @param uri uri
     * @param localName localName
     * @param qName qName
     * @throws SAXException SAXException
     */
    @Override
    public void endElement(final String uri, final String localName, final String qName) throws SAXException {
        thisElement = "";
    }


    /**
     * Receive notification of character data inside an element.
     * @param ch ch
     * @param start start
     * @param length length
     * @throws SAXException SAXException
     */
    @Override
    public void characters(final char[] ch, final int start, final int length) throws SAXException {


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
