package dao;



import entity.CryptoPair;
import exception.PersistentException;

import java.util.List;

public interface CryptoPairDao extends  Dao<CryptoPair>  {

    List<CryptoPair> read() throws PersistentException;
}
