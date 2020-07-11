package jv.freeorm.connection;

import jv.freeorm.base.DriverType;
import java.sql.SQLException;

/**
 *
 * @author Mustafa SACLI
 */
public interface IDbConnection extends IDbOperations, AutoCloseable {

    DriverType getConnectionType();
    
    void open() throws Exception;
    
    void beginTransaction() throws SQLException;

    void commitTransaction() throws SQLException;

    /**
     *
     * @throws SQLException
     */
    void rollbackTransaction() throws SQLException;
    
    void closeTransaction() throws SQLException;

    int closeConnection();

}
