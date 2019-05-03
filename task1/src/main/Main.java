package main;

import comparator.SweetnessSortByWeight;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import repository.Gift;
import sweet.CandySwetnessFactory;
import sweet.ChocolateSweetnessFactory;
import sweet.Sweetness;
import sweet.SweetnessFactory;

import java.io.BufferedReader;
import java.io.FileReader;


public class Main {

    public static Logger logger = LogManager.getLogger(Main.class);

    public static void main(final String[] args) {





        String str;
        String[] arr;
        Gift gift = new Gift();
        try(BufferedReader br =new BufferedReader(new FileReader("data/input.txt"))) {
              while (br.ready()){
                str = br.readLine();
                arr = str.split(" ");

                  try {
                      SweetnessFactory sweetnessFactory = createSweetnessFactory(arr[0]);
                      gift.add(sweetnessFactory.createSweetness(arr[1],Double.parseDouble(arr[2])));
                  } catch (Exception e) {
                      logger.debug("input error "+str);
                  }

              }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(gift.getWeight());

        gift.sort(new SweetnessSortByWeight());

        for (Sweetness sweet : gift.sweets) {
            System.out.println(sweet.getName()+" "+sweet.getWeight());
        }


    }

static SweetnessFactory createSweetnessFactory(String kind) {
        if (kind.equalsIgnoreCase("candy")) {
            return new CandySwetnessFactory();
        } else {
            if (kind.equalsIgnoreCase("chocolate")) {
                return new ChocolateSweetnessFactory();
            }
        }
        return null;
}


}