package by.training.task1.repository;



import by.training.task1.specifications.SweetnessSort;
import by.training.task1.entity.Sweetness;
import by.training.task1.specifications.SweetnessSpecification;
import by.training.task1.specifications.SweetnessSpecificationBy;

import java.util.ArrayList;
import java.util.List;


/**
 * Gift class implements template "repository" for storing sweetness.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
  public final class Gift {

    /**
     * Static value for gift.
     */
     private static Gift instance;

    /**
     *Private constructor, implements template "singlton".
     */
      private Gift() {

      }


    /**
     *Function to get repository gift, implements template "singlton".
     *@return returns gift
     */
    public static Gift  getInstance() {
        if (instance == null) {
            instance = new Gift();
        }
        return instance;
    }

    /**
     *Collection for storing sweetness.
     */
   private  ArrayList<Sweetness> sweets = new ArrayList();


    /**
     *Function to add sweetnes to collection.
     *@param sweet This is new sweet to collection
     */
    public void add(final Sweetness sweet) {

        sweets.add(sweet);
    }

    /**
     *Function to delete sweetnes from collection.
     *@param index sweetnes in collection
     */
   public void delete(final int index) {
    sweets.remove(index);
   }

    /**
     *Function to delete sweetnes from collection".
     *@param sweetness replacement object
     *@param index sweetnes in collection
     */
    public void update(final Sweetness sweetness, final int index) {
        sweets.set(index, sweetness);
    }



    /**
     *Function to get gift weight.
     *@return returns weight
     */
    public double getWeight() {
        double weight = 0;
        for (Sweetness sweet : sweets) {
            weight += sweet.getWeight();
        }
        return weight;
    }

    /**
     *Function of sorting sweetness.
     *@param spec sorting criterion
     *@return List of corresponding specification
     */
    public List<Sweetness> query(final SweetnessSpecification spec) {
        ArrayList<Sweetness> result = new ArrayList();
        if (spec instanceof SweetnessSort) {
            SweetnessSort sweetnessSort = (SweetnessSort) spec;
            sweets.sort(sweetnessSort);
            result = sweets;

        }
       if (spec instanceof SweetnessSpecificationBy) {
           SweetnessSpecificationBy sweetnessSpecification = (SweetnessSpecificationBy) spec;
           for (Sweetness sweet : sweets) {
              if (sweetnessSpecification.specified(sweet)) {
                  result.add(sweet);
              }
           }
       }

        return result;

    }

}
