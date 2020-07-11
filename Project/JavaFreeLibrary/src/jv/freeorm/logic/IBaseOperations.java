package jv.freeorm.logic;

import java.util.List;
import jv.freeorm.base.DbParameter;
import jv.freeorm.entity.IBaseBO;
import jv.freeorm.base.Hashtable;

/**
 *
 * @author Mustafa SACLI
 */
public interface IBaseOperations {

    int Insert(IBaseBO baseBO) throws Exception;

    int Insert(String table_name, Hashtable hash_map) throws Exception;

    int InsertAndGetId(IBaseBO baseBO) throws Exception;

    int Update(IBaseBO baseBO) throws Exception;

    int Update(String table_name, String id_column, Object id,
            Hashtable hash_map) throws Exception;

    int Delete(IBaseBO baseBO) throws Exception;

    int Delete(String table_name, String id_column, Object id) throws Exception;

    Object[][] Select(IBaseBO baseBO) throws Exception;

    Object[][] SelectWhere(IBaseBO baseBO) throws Exception;

    Object[][] SelectChanges(IBaseBO baseBO) throws Exception;

    Object[][] GetResultSet(String query, Hashtable h) throws Exception;

    Object[][] GetResultSetOfQuery(String query, List<DbParameter> h) throws Exception;

    Object[][] GetResultSet(String query) throws Exception;

    int execute(String query, Hashtable h) throws Exception;

    int execute(String query) throws Exception;
}
