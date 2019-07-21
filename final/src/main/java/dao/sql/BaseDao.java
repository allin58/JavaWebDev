package dao.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;

public class BaseDao {
    final static Logger LOGGER = LogManager.getLogger("by.training.final.DataBaseLogger");

    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
