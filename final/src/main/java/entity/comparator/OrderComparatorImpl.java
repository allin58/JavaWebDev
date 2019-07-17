package entity.comparator;

import entity.Order;

public class OrderComparatorImpl implements OrderComparator {

    @Override
    public int compare(Order o1, Order o2) {

        return  o2.getPrice().compareTo(o1.getPrice());
    }
}
