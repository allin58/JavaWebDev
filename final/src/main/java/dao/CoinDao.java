package dao;

import entity.Coin;
import exception.PersistentException;

import java.util.List;

public interface CoinDao extends  Dao<Coin> {

    List<Coin> read() throws PersistentException;


}
