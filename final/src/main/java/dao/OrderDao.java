package dao;

import entity.Order;
import exception.PersistentException;

import java.util.List;

public interface OrderDao extends Dao<Order>   {

    List<Order> read() throws PersistentException;





}
