package dao;

import java.sql.Connection;
import java.sql.SQLException;

class BaseDAO {

    protected static Connection con() throws SQLException {
        return ConnectionFactory.getInstance().get();
    }
}