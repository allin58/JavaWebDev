package by.training.task1.main;

import by.training.task1.entity.Sweetness;
import by.training.task1.reader.SweetnesReader;
import by.training.task1.specifications.SweetnessSpecificationByRangeId;
import by.training.task1.repository.Gift;

import java.util.List;


public class Main {


    public static void main(final String[] args) {

        String file = "data/input.txt";
        Gift gift = Gift.getInstance();
        SweetnesReader.readFile(file, gift);
        List<Sweetness> list = gift.query(new SweetnessSpecificationByRangeId(3,8));
        for (Sweetness sweetness : list) {
            System.out.println(sweetness.getID());
        }

    }






}