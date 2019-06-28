package by.training.task4.services;

import by.training.task4.entity.Candy;

import java.io.File;
import java.util.ArrayList;

/**
 * Abstract class taking part in the implementation of the pattern Builder.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public abstract class AbstractCandiesBuilder {

    /**
     * The collection of candy.
     */
    private ArrayList<Candy> candies;

    /**
     * Constructor without parameters.
     */
    public AbstractCandiesBuilder() {
        this.candies = new ArrayList<Candy>();
    }

    /**
     * Constructor without parameters.
     * @param candies candies
     */
    public AbstractCandiesBuilder(final ArrayList<Candy> candies) {
        this.candies = candies;
    }

    /**
     * Getter for collection of candy.
     * @return candies
     */
    public ArrayList<Candy> getCandies() {
        return candies;
    }

    /**
     * Abstract method, which implementations parse xml file.
     * @param file file
     * @param schemaname schemaname
     */
    public abstract void buildListCandies(File file, String schemaname);


}
