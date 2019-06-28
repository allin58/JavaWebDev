package by.training.task4.services;

import by.training.task4.dal.DALImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.XMLConstants;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;



/** CandySAXBuilder class which extends AbstractCandiesBuilder.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class CandySAXBuilder extends AbstractCandiesBuilder  {

    /**
     * Logger for SAX parser.
     */
    static Logger LOGGER = LogManager.getLogger("by.training.task4.services.CandySAXBuilder");

    /**
     * Method which implementations parse xml file.
     * @param file file
     * @param schemaname schemaname
     */
    public void buildListCandies(File file, String schemaname) {
        String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
        Schema schema = null;
        SchemaFactory factory = SchemaFactory.newInstance(language);



        try {

            DALImpl dalParser = new DALImpl();

            schema = factory.newSchema(dalParser.getFile(schemaname));
            SAXParserFactory spf = SAXParserFactory.newInstance();
            spf.setSchema(schema);
            SAXParser parser = spf.newSAXParser();

            parser.parse(file, new CandyHandler(super.getCandies()));
            LOGGER.debug("File is parsed");

        } catch (Exception e) {
           LOGGER.debug("Error of parsing");
        }

    }
}
