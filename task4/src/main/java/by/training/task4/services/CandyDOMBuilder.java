package by.training.task4.services;

import by.training.task4.dal.DALImpl;
import by.training.task4.entity.Candy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;


/** CandyDOMBuilder class which extends AbstractCandiesBuilder.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CandyDOMBuilder extends AbstractCandiesBuilder {

    /**
     * Logger for DOM parser.
     */
    final static Logger LOGGER = LogManager.getLogger("by.training.task4.services.CandyDOMBuilder");


    /**
     * Method which implementations parse xml file.
     * @param file file
     * @param schemaname schemaname
     */
    public void buildListCandies(File file, String schemaname) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        SchemaFactory factory = SchemaFactory.newInstance(language);
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();

        Candy tempCandy;

        try {

            DALImpl dalImpl = new DALImpl();
            Schema schema = factory.newSchema(dalImpl.getFile(schemaname));
            documentBuilderFactory.setSchema(schema);

            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();



            Document document = documentBuilder.parse(file);
            NodeList nodeList = document.getElementsByTagName("candy");
            schema = factory.newSchema(new File(schemaname));

            Validator validator = schema.newValidator();
            validator.validate(new DOMSource(document));


            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                tempCandy = new Candy();
                tempCandy.setName(node.getAttributes().getNamedItem("name").getTextContent());
                tempCandy.setEnergy(new Integer(node.getAttributes().getNamedItem("energy").getTextContent()));
                tempCandy.setProduction(node.getAttributes().getNamedItem("production").getTextContent());
                tempCandy.setId(new Integer(node.getAttributes().getNamedItem("id").getTextContent().split("-")[1]));
                tempCandy.setType(((Element)node).getElementsByTagName("type").item(0).getTextContent());
                tempCandy.setProteins(new Integer(  ((Element)((Element)node).getElementsByTagName("value").item(0)).getElementsByTagName("proteins").item(0).getTextContent()));
                tempCandy.setFats(new Integer(  ((Element)((Element)node).getElementsByTagName("value").item(0)).getElementsByTagName("fats").item(0).getTextContent()));
                tempCandy.setCarbohydrates(new Integer(  ((Element)((Element)node).getElementsByTagName("value").item(0)).getElementsByTagName("carbohydrates").item(0).getTextContent()));

                if (((Element)node).getElementsByTagName("ingredients").item(0).getAttributes().getNamedItem("typeOfChocolate") != null ) {
                    tempCandy.setTypeOfChocolate(((Element)node).getElementsByTagName("ingredients").item(0).getAttributes().getNamedItem("typeOfChocolate").getTextContent());
                }
                tempCandy.setWater(new Integer(((Element)((Element)node).getElementsByTagName("ingredients").item(0)). getElementsByTagName("water").item(0).getTextContent()));
                tempCandy.setSugar(new Integer(((Element)((Element)node).getElementsByTagName("ingredients").item(0)). getElementsByTagName("sugar").item(0).getTextContent()));
                tempCandy.setFructose(new Integer(((Element)((Element)node).getElementsByTagName("ingredients").item(0)). getElementsByTagName("fructose").item(0).getTextContent()));
                tempCandy.setVanillin(new Integer(((Element)((Element)node).getElementsByTagName("ingredients").item(0)). getElementsByTagName("vanillin").item(0).getTextContent()));

                super.getCandies().add(tempCandy);

            }
            LOGGER.debug("File is parsed");
        }catch (Exception e) {
            LOGGER.debug("Error of parsing");
        }

    }
}
