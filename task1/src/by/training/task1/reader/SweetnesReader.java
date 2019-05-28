package by.training.task1.reader;

import by.training.task1.entity.*;
import by.training.task1.idgenerator.GeneratorID;
import by.training.task1.repository.Gift;
import by.training.task1.sweetnessexeption.SweetnessReaderExeption;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.BufferedReader;
import java.io.FileReader;





/**
 * SweetnesReader class which reads data from a file and saves it to the repository.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public final class SweetnesReader {

    public static Logger LOGGER = LogManager.getLogger(SweetnesReader.class);
     /**
     * This is empty constructior.
     */
    private  SweetnesReader() {
    }

    /**
     *The function of adding sweets to the collection.
     *@param file This is way to file
     *@param gift This is reference to repository
     */
    public static void readFile(final String file, final Gift gift) {
        String str;
        String[] arr = null;
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                str = br.readLine();
                arr = str.split(" ");
                Sweetness sweetness;


                try {
                    SweetnessFactory sweetnessFactory = createSweetnessFactory(arr[0]);
                    sweetness = sweetnessFactory.createSweetness(arr[1], Double.parseDouble(arr[2]), GeneratorID.getId());
                    gift.add(sweetness);
                } catch (Exception e) {
                    LOGGER.debug("error in line " + str );
                }


            }
        } catch (Exception e) {
            LOGGER.debug("input error" );
        }
    }


    /**
     *The function of defineng sweetss.
     *@param kind kind of sweetnes
     *@throws Exception This exception are related with wrong input data.
     *@return returns the factory corresponding to the requirement
    */
    static SweetnessFactory createSweetnessFactory(final String kind) throws Exception {
        if ("candy".equalsIgnoreCase(kind)) {
            return new CandySwetnessFactory();
        } else {
            if ("chocolate".equalsIgnoreCase(kind)) {
                return new ChocolateSweetnessFactory();
            } else {
                throw new SweetnessReaderExeption("input error");
            }
        }

    }


}
