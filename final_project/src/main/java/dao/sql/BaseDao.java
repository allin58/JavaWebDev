package dao.sql;

import java.sql.Connection;

public class BaseDao {


    protected Connection connection;

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
