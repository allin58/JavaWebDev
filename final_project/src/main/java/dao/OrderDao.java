package dao;

import entity.Order;
import exception.PersistentException;

import java.util.List;

public interface OrderDao extends Dao  {

    Order read(String userName) throws PersistentException;

    List<Order> read() throws PersistentException;

    List<Order> readByType() throws PersistentException;


    List<Order> readByState() throws PersistentException;


    List<Order> readByStateAndName() throws PersistentException;



}
