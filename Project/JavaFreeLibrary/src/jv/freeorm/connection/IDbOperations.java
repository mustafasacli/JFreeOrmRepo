package jv.freeorm.connection;

import jv.freeorm.base.DbParameter;
import java.sql.ResultSet;
import java.util.List;

/**
 *
 * @author Mustafa SACLI
 */
public interface IDbOperations {

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Exception
     */
    int executeAndGetId(String query,
            DbParameter... paramCollection) throws Exception;
    
    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Exception
     */
    Integer executeUpdateQuery(String query,
            DbParameter... paramCollection) throws Exception;

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    Integer executeUpdateQuery(String query) throws Exception;

    /**
     *
     * @param procedure
     * @param paramCollection
     * @return
     * @throws Exception
     */
    Integer executeUpdateProcedure(String procedure,
            DbParameter... paramCollection) throws Exception;

    /**
     *
     * @param procedure
     * @return
     * @throws Exception
     */
    Integer executeUpdateProcedure(String procedure) throws Exception;

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Exception
     */
    Object[] executeScalarQuery(String query,
            DbParameter... paramCollection) throws Exception;

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    Object[] executeScalarQuery(String query) throws Exception;

    /**
     *
     * @param procedure
     * @param paramCollection
     * @return
     * @throws Exception
     */
    Object[] executeScalarProcedure(String procedure,
            DbParameter... paramCollection) throws Exception;

    /**
     *
     * @param procedure
     * @return
     * @throws Exception
     */
    Object[] executeScalarProcedure(String procedure) throws Exception;

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Exception
     */
    Object[][] getResultSetOfQuery(String query,
            DbParameter... paramCollection) throws Exception;

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    Object[][] getResultSetOfQuery(String query) throws Exception;

    /**
     *
     * @param query
     * @param paramCollection
     * @return
     * @throws Exception
     */
    Object[][] getResultSetOfProcedure(String query,
            DbParameter... paramCollection) throws Exception;

    /**
     *
     * @param procedure
     * @return
     * @throws Exception
     */
    Object[][] getResultSetOfProcedure(String procedure) throws Exception;

    /**
     *
     * @return @throws Exception
     */
    List<String> getCatalogs() throws Exception;

    /**
     *
     * @return @throws Exception
     */
    List<String> getTables() throws Exception;

    /**
     *
     * @param query
     * @param parameters
     * @return
     * @throws Exception
     */
    boolean executeQuery(String query,
            DbParameter... parameters) throws Exception;

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    boolean executeQuery(String query) throws Exception;

    /**
     *
     * @param procedure
     * @param parameters
     * @return
     * @throws Exception
     */
    boolean executeProcedure(String procedure,
            DbParameter... parameters) throws Exception;

    /**
     *
     * @param procedure
     * @return
     * @throws Exception
     */
    boolean executeProcedure(String procedure) throws Exception;

    /**
     * for int[] exceuteBatch(...)
     *
     * @param query
     * @param parameters
     * @return
     * @throws java.lang.Exception
     */
    int[] executeBatchQuery(String query, DbParameter[][] parameters) throws Exception;

}
