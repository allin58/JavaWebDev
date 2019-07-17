package entity.comparator;

import entity.Order;

import java.util.Comparator;

public interface OrderComparator extends Comparator<Order> {

    @Override
    int compare(Order o1, Order o2);
}
