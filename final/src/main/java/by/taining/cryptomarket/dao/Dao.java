package by.taining.cryptomarket.dao;
import by.taining.cryptomarket.entity.Entity;
import by.taining.cryptomarket.exception.PersistentException;

import java.util.List;

/**
 * Interface for Dao.
 *
 * @author Nikita Karchahin
 * @param <Type> this is type of entity
 * @version 1.0
 */
public interface  Dao<Type extends Entity> {

    /**
     * This method creates a new record in a database.
     * @param entity
     * @return number of record in the database
     * @throws PersistentException
     */
    Integer create(Type entity) throws PersistentException;


    /**
     * This method returns entity by id.
     * @param identity
     * @return entity
     * @throws PersistentException
     */
    Type read(Integer identity) throws PersistentException;

    /**
     * This method returns all entity.
     * @return collection of entities
     * @throws PersistentException
     */
    List<Type> read() throws PersistentException;

    /**
     * This method updates a record.
     * @param entity
     * @throws PersistentException
     */
    void update(Type entity) throws PersistentException;

    /**
     * This method deletes a record.
     * @param identity identity
     * @throws PersistentException
     */
    void delete(Integer identity) throws PersistentException;

}
