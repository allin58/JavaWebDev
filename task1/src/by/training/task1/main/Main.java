package by.training.task1.main;

import by.training.task1.entity.Sweetness;
import by.training.task1.reader.SweetnesReader;
import by.training.task1.specifications.SweetnessSortByID;
import by.training.task1.specifications.SweetnessSpecificationById;
import by.training.task1.specifications.SweetnessSpecificationByRangeId;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import by.training.task1.repository.Gift;

import java.util.List;


public class Main {

    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {

        String file = "data/input.txt";
        Gift gift = Gift.getInstance();
        SweetnesReader.readFile(file, gift);
        List<Sweetness> list = gift.query(new SweetnessSpecificationByRangeId(3,8));


    }






}