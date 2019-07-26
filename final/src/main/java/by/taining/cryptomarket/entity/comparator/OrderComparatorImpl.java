package by.taining.cryptomarket.entity.comparator;

import by.taining.cryptomarket.entity.Order;


/**
 * The class for comparator by price.
 *
 * @author Nikita Karchahin
 * @version 1.0
 */
public class OrderComparatorImpl implements OrderComparator {


    /**
     * The method which compares orders by price field.
     * @param o1 o1
     * @param o2 o2
     * @return int
     */
    @Override
    public int compare(Order o1, Order o2) {

        return  o2.getPrice().compareTo(o1.getPrice());
    }
}
