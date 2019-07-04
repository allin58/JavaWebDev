package dao;

import entity.Wallet;
import exception.PersistentException;

import java.util.List;


public interface WalletDao extends Dao<Wallet>  {

    List<Wallet> read() throws PersistentException;



}
