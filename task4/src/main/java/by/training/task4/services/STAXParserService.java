package by.training.task4.services;



import by.training.task4.dal.DALImpl;
import by.training.task4.entity.Candy;

import javax.xml.XMLConstants;


import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;

public class STAXParserService implements ParseService {


    public ArrayList parse(String filename, String schemaname) {
        ArrayList<Candy> candies = new ArrayList<Candy>();

        Candy tempCandy = null;

        File file = new File(filename);
        XMLInputFactory factory = XMLInputFactory.newInstance();
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        DALImpl dalImpl = new DALImpl();
        SchemaFactory schemaFactory = SchemaFactory.newInstance(language);


        XMLStreamReader streamReader = null ;


        try {
            Schema schema = schemaFactory.newSchema(dalImpl.getFile(schemaname));


            streamReader = factory.createXMLStreamReader(new FileReader(file));


            while(streamReader.hasNext()) {
                streamReader.next();
                if(streamReader.getEventType() == XMLStreamReader.START_ELEMENT) {

                    if(streamReader.getLocalName().equalsIgnoreCase("candy")) {

                        tempCandy = new Candy();


                        if(streamReader.getAttributeCount() > 0) {
                            String id = streamReader.getAttributeValue(null,"id");
                            tempCandy.setId(Integer.valueOf(id.split("-")[1]));

                            String name = streamReader.getAttributeValue(null,"name");
                            tempCandy.setName(name);

                            String production = streamReader.getAttributeValue(null,"production");
                            tempCandy.setProduction(production);

                            String energy = streamReader.getAttributeValue(null,"energy");
                            tempCandy.setEnrgy(Integer.valueOf(energy));

                        }

                    }
                    if(streamReader.getLocalName().equalsIgnoreCase("type")) {
                        tempCandy.setType(streamReader.getElementText());
                    }

                    if(streamReader.getLocalName().equalsIgnoreCase("proteins")) {
                        tempCandy.setProteins(Integer.valueOf(streamReader.getElementText()));
                    }

                    if(streamReader.getLocalName().equalsIgnoreCase("fats")) {
                        tempCandy.setFats(Integer.valueOf(streamReader.getElementText()));
                    }

                    if(streamReader.getLocalName().equalsIgnoreCase("carbohydrates")) {
                        tempCandy.setCarbohydrates(Integer.valueOf(streamReader.getElementText()));
                    }

                    if(streamReader.getLocalName().equalsIgnoreCase("ingredients")) {

                        if (streamReader.getAttributeCount() > 0) {
                            String typeOfChocolate = streamReader.getAttributeValue(null,"typeOfChocolate");
                            tempCandy.setTypeOfChocolate(typeOfChocolate);
                        }

                    }

                    if(streamReader.getLocalName().equalsIgnoreCase("water")) {
                       String water =streamReader.getElementText();
                        if (!"".equals(water)) {
                            Double aDouble = Double.parseDouble(water);
                            Integer integer = aDouble.intValue();
                            tempCandy.setWater(integer);
                        } else {
                            tempCandy.setWater(0);
                        }
                    }

                    if(streamReader.getLocalName().equalsIgnoreCase("sugar")) {
                        String sugar =streamReader.getElementText();
                        if (!"".equals(sugar)) {
                            Double aDouble = Double.parseDouble(sugar);
                            Integer integer = aDouble.intValue();
                            tempCandy.setSugar(integer);
                        } else {
                            tempCandy.setSugar(0);
                        }
                    }

                    if(streamReader.getLocalName().equalsIgnoreCase("fructose")) {
                        String fructose =streamReader.getElementText();
                        if (!"".equals(fructose)) {
                            Double aDouble = Double.parseDouble(fructose);
                            Integer integer = aDouble.intValue();
                            tempCandy.setFructose(integer);
                        } else {
                            tempCandy.setFructose(0);
                        }
                    }


                    if(streamReader.getLocalName().equalsIgnoreCase("vanillin")) {
                        String vanillin =streamReader.getElementText();
                        if (!"".equals(vanillin)) {
                            Double aDouble = Double.parseDouble(vanillin);
                            Integer integer = aDouble.intValue();
                            tempCandy.setVanillin(integer);
                        } else {
                            tempCandy.setVanillin(0);
                        }
                    }



                }


                if(streamReader.getEventType() == XMLStreamReader.END_ELEMENT)
                {
                    if(streamReader.getLocalName().equalsIgnoreCase("candy")) {
                        candies.add(tempCandy);
                    }
                }


            }





        } catch (Exception e) {
            e.printStackTrace();
        }





        return candies;
    }
}
