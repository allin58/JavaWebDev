package dao;

import entity.User;
import exception.PersistentException;

import java.util.List;

public interface UserDao extends Dao<User>   {




    List<User> read() throws PersistentException;
}
