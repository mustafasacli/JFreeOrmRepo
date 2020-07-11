package jv.freeorm.logic;

import java.util.List;
import jv.freeorm.conf.ConfigurationManager;
import jv.freeorm.conf.DbConfiguration;
import jv.freeorm.base.DriverType;
import jv.freeorm.connection.DbConnection;
import jv.freeorm.connection.IDbConnection;
import jv.freeorm.entity.IBaseBO;
import jv.freeorm.query.QueryTypes;
import jv.freeorm.util.StringUtil;
import jv.freeorm.base.DbParameter;
import jv.freeorm.base.Hashtable;
import jv.freeorm.query.IQuery;
import jv.freeorm.query.QueryBuilder;
import jv.freeorm.query.QueryFormat;

/**
 *
 * @author Mustafa SACLI
 */
public abstract class BaseDL implements IBaseDL {

    protected DriverType dt = DriverType.Unknown;
    protected DbConfiguration conf;
    IDbConnection db_conn = null;
    String conn_name = "";
    int ctor_type = 0;

    /**
     *
     * @throws java.lang.Exception
     */
    protected BaseDL() throws Exception {
        this("main");
    }

    /**
     *
     * @param connection_name
     * @throws java.lang.Exception
     */
    protected BaseDL(String connection_name) throws Exception {

        try {
            conn_name = connection_name;
            conf = ConfigurationManager.getConfiguration(conn_name);
            dt = ConfigurationManager.GetDriverType(connection_name);

            if (dt == DriverType.External || dt == DriverType.Unknown) {
                throw new Exception("Driver Type can not be external or unknown.");
            }

            init_connection();

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param driver_type
     * @param url
     * @param user
     * @param pass
     * @throws Exception
     */
    protected BaseDL(DriverType driver_type, String url, String user, String pass)
            throws Exception {
        try {
            conn_name = "build-in";
            conf = new DbConfiguration();
            conf.setUrl(url);
            conf.setUser(user);
            conf.setPassword(pass);
            dt = driver_type;

            init_connection();

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param driver_type
     * @param url
     * @throws Exception
     */
    protected BaseDL(DriverType driver_type, String url)
            throws Exception {
        try {
            conn_name = "build-in";
            conf = new DbConfiguration();
            conf.setUrl(url);
            dt = driver_type;

            init_connection();

        } catch (Exception e) {
            throw e;
        }
    }

    private final void init_connection() throws Exception {

        try {
            db_conn = null;

            if (dt == DriverType.Unknown) {
                throw new Exception(
                        "Connection Type can not be defined.");
            }

            if (dt == DriverType.External) {
                throw new Exception(
                        "External Db Connection can not be populate in Base Data Layer");
            }

            if (StringUtil.isValidString(conf.getUser())) {
                db_conn = new DbConnection(dt, conf.getUrl(),
                        conf.getUser(), conf.getPassword());
            } else {
                db_conn = new DbConnection(dt, conf.getUrl());
            }

        } catch (Exception e) {
            throw e;
        }
    }

    /**
     *
     * @param baseBO
     * @return
     * @throws Exception
     */
    @Override
    public int Insert(IBaseBO baseBO) throws Exception {
        int retInt;

        try {

            IQuery q = QueryBuilder.BuildInsert(baseBO, dt);
            db_conn.open();
            db_conn.beginTransaction();

            retInt = db_conn.executeUpdateQuery(q.getText(), q.getParameters());

            db_conn.commitTransaction();
        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return retInt;
    }

    /**
     *
     * @param table_name
     * @param fields
     * @return Execution Result Of Insert.
     * @throws Exception
     */
    @Override
    public int Insert(String table_name, Hashtable fields) throws Exception {
        int result = -1;

        try {

            if (!StringUtil.isValidString(table_name)) {
                throw new Exception("Table Name can not be null or empty.");
            }

            if (table_name.contains("drop") || table_name.contains("--")) {
                throw new Exception(
                        "Table Name can not be contain restricted characters and text.");
            }

            if (fields == null) {
                throw new Exception(
                        "Column list can not be null.");
            }

            if (fields.isEmpty()) {
                throw new Exception(
                        "Column list can not be empty.");
            }

            String col_list = "";
            String val_list = "";

            String[] s = fields.keys();
            DbParameter[] params = new DbParameter[s.length];
            int i = 1;

            for (String s1 : s) {
                if (s1.contains("drop") || s1.contains("--")) {
                    throw new Exception(s1 + " contains restricted characters like 'drop', '--'");
                }

                col_list += s1;
                val_list += "?";

                if (i < s.length) {
                    col_list += ", ";
                    val_list += ", ";
                }

                params[i - 1] = new DbParameter(i, fields.get(s1));
                i++;
            }

            String query;
            query = (new QueryFormat(QueryTypes.Insert)).getFormat(); //QueryFormats.FORMAT_INSERT;
            query = query.replace("#table#", table_name);
            query = query.replace("#columns#", col_list);
            query = query.replace("#vals#", val_list);

            db_conn.open();
            db_conn.beginTransaction();
            result = db_conn.executeUpdateQuery(query, params);

            db_conn.commitTransaction();
        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param baseBO
     * @return
     * @throws Exception
     *
     */
    @Override
    public int InsertAndGetId(IBaseBO baseBO) throws Exception {
        int retInt;

        try {
            IQuery q = QueryBuilder.BuildInsertAndGetId(baseBO, dt);

            db_conn.open();
            db_conn.beginTransaction();
            //try (IDbConnection dbConn = this.createDbConnection()) {
            retInt = db_conn.executeAndGetId(q.getText(), q.getParameters());
            //}

            db_conn.commitTransaction();
        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }
        return retInt;
    }

    /**
     *
     * @param baseBO
     * @return
     * @throws Exception
     */
    @Override
    public int Update(IBaseBO baseBO) throws Exception {
        int retInt;

        try {

            IQuery q = QueryBuilder.BuildUpdate(baseBO, dt);

            db_conn.open();
            db_conn.beginTransaction();
            //try (IDbConnection dbConn = this.createDbConnection()) {
            retInt = db_conn.executeUpdateQuery(q.getText(), q.getParameters());
            //}

            db_conn.commitTransaction();

        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return retInt;
    }

    /**
     *
     * @param table_name
     * @param id_column
     * @param id_value
     * @param fields
     * @return
     * @throws Exception
     */
    @Override
    public int Update(String table_name, String id_column, Object id_value,
            Hashtable fields) throws Exception {
        int result = -1;

        try {

            if (!StringUtil.isValidString(table_name)) {
                throw new Exception("Table Name can not be null or empty.");
            }

            if (table_name.contains("drop") || table_name.contains("--")) {
                throw new Exception(
                        "Table Name can not be contain restricted characters and text.");
            }

            if (!StringUtil.isValidString(id_column)) {
                throw new Exception("Id Column Name can not be null or empty.");
            }

            if (id_column.contains("drop") || id_column.contains("--")) {
                throw new Exception(
                        "Id Column Name can not be contain restricted characters and text.");
            }

            if (id_value == null) {
                throw new Exception(
                        "Id value can not be null.");
            }

            String query;
            String columns = "";
            query = (new QueryFormat(QueryTypes.Update)).getFormat(); //QueryFormats.FORMAT_UPDATE;
            query = query.replace("#table#", table_name);
            id_column = id_column.concat("=?");
            query = query.replace("#vals#", id_column);

            if (fields.contains(id_column)) {
                fields.remove(id_column);
            }

            String[] s = fields.keys();
            DbParameter[] params = new DbParameter[s.length + 1];
            int i = 1;

            for (String s1 : s) {
                if (s1.contains("drop") || s1.contains("--")) {
                    throw new Exception(s1 + " contains restricted characters like 'drop', '--'");
                }

                columns += s1 + " = ?";

                if (i < s.length) {
                    columns += " , ";
                }

                params[i - 1] = new DbParameter(i, fields.get(s1));
                i++;
            }

            params[s.length] = new DbParameter(s.length + 1, id_value);

            query = query.replace("#columns#", columns);

            db_conn.open();
            db_conn.beginTransaction();
            //try (IDbConnection db_conn = this.createDbConnection()) {
            result = db_conn.executeUpdateQuery(query, params);
            //}

            db_conn.commitTransaction();

        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param baseBO
     * @return
     * @throws Exception
     */
    @Override
    public int Delete(IBaseBO baseBO) throws Exception {
        int retInt;

        try {

            IQuery q = QueryBuilder.BuildDelete(baseBO, dt);

            db_conn.open();
            db_conn.beginTransaction();

            //try (IDbConnection dbConn = this.createDbConnection()) {
            retInt = db_conn.executeUpdateQuery(q.getText(), q.getParameters());
            //}

            db_conn.commitTransaction();

        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return retInt;
    }

    /**
     *
     * @param table_name
     * @param id_column
     * @param id_value
     * @return
     * @throws Exception
     */
    @Override
    public int Delete(String table_name, String id_column, Object id_value) throws Exception {
        int result = -1;

        try {

            if (!StringUtil.isValidString(table_name)) {
                throw new Exception("Table Name can not be null or empty.");
            }

            if (table_name.contains("drop") || table_name.contains("--")) {
                throw new Exception(
                        "Table Name can not be contain restricted characters and text.");
            }

            if (!StringUtil.isValidString(id_column)) {
                throw new Exception("Id Column Name can not be null or empty.");
            }

            if (id_column.contains("drop") || id_column.contains("--")) {
                throw new Exception(
                        "Id Column Name can not be contain restricted characters and text.");
            }

            if (id_value == null) {
                throw new Exception(
                        "Id value can not be null.");
            }

            String query;
            query = (new QueryFormat(QueryTypes.Delete)).getFormat(); //QueryFormats.FORMAT_DELETE;
            query = query.replace("#table#", table_name);
            String id_col = id_column.concat("=?");
            query = query.replace("#vals#", id_col);

            DbParameter[] params = new DbParameter[]{new DbParameter(1, id_value)};

            db_conn.open();
            db_conn.beginTransaction();

            //try (IDbConnection db_conn = this.createDbConnection()) {
            result = db_conn.executeUpdateQuery(query, params);
            //}

            db_conn.commitTransaction();

        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param baseBO
     * @return
     * @throws Exception
     */
    @Override
    public Object[][] Select(IBaseBO baseBO) throws Exception {
        Object[][] result = new Object[][]{};

        try {

            IQuery q = QueryBuilder.Build(baseBO, dt, QueryTypes.Select);

            db_conn.open();

            //try (IDbConnection dbConn = this.createDbConnection()) {
            result = db_conn.getResultSetOfQuery(q.getText(), q.getParameters());
            //}

        } catch (Exception t) {
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param baseBO
     * @return
     * @throws Exception
     */
    @Override
    public Object[][] SelectWhere(IBaseBO baseBO) throws Exception {
        Object[][] result = new Object[][]{};

        try {

            IQuery q = QueryBuilder.Build(baseBO, dt, QueryTypes.SelectWhere);

            db_conn.open();

            //try (IDbConnection dbConn = this.createDbConnection()) {
            result = db_conn.getResultSetOfQuery(q.getText(), q.getParameters());
            //}

        } catch (Exception t) {
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param baseBO
     * @return
     * @throws Exception
     */
    @Override
    public Object[][] SelectChanges(IBaseBO baseBO) throws Exception {
        Object[][] result = new Object[][]{};

        try {

            IQuery q = QueryBuilder.Build(baseBO, dt, QueryTypes.SelectChanges);

            db_conn.open();

            //try (IDbConnection dbConn = this.createDbConnection()) {
            result = db_conn.getResultSetOfQuery(q.getText(), q.getParameters());
            //}

        } catch (Exception t) {
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param query
     * @param h
     * @return
     * @throws Exception
     */
    @Override
    public Object[][] GetResultSet(String query, Hashtable h) throws Exception {
        Object[][] result = new Object[][]{};

        try {
            DbParameter[] parameters = new DbParameter[h.count()];
            int counter = 0;
            for (String key : h.keys()) {
                parameters[counter]
                        = new DbParameter(counter + 1, h.get(key), true);
                counter++;
            }
            db_conn.open();

            result = db_conn.getResultSetOfQuery(query, parameters);

        } catch (Exception t) {
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param query
     * @param h
     * @return
     * @throws Exception
     */
    @Override
    public Object[][] GetResultSetOfQuery(String query, List<DbParameter> h) throws Exception {
        Object[][] result = new Object[][]{};

        try {
            DbParameter[] parameters = new DbParameter[h.size()];
            DbParameter prm;
            for (int prm_counter = 0; prm_counter < h.size(); prm_counter++) {
                prm = h.get(prm_counter);
                prm.setIndex(prm_counter + 1);
                parameters[prm_counter] = prm;
            }

            db_conn.open();

            result = db_conn.getResultSetOfQuery(query, parameters);

        } catch (Exception t) {
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public Object[][] GetResultSet(String query) throws Exception {
        Object[][] result = new Object[][]{};

        try {
            db_conn.open();

            result = db_conn.getResultSetOfQuery(query);

        } catch (Exception t) {
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    /**
     *
     * @param query
     * @param h
     * @return
     * @throws Exception
     */
    @Override
    public int execute(String query, Hashtable h) throws Exception {
        int result = -1;
        try {
            DbParameter[] parameters = new DbParameter[h.count()];
            int counter = 0;
            for (String key : h.keys()) {
                parameters[counter]
                        = new DbParameter(counter + 1, h.get(key), true);
                counter++;
            }
            db_conn.open();
            db_conn.beginTransaction();
            result = db_conn.executeUpdateQuery(query, parameters);
            db_conn.commitTransaction();

        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }
        return result;
    }

    /**
     *
     * @param query
     * @return
     * @throws Exception
     */
    @Override
    public int execute(String query) throws Exception {
        int result = -1;

        try {
            db_conn.open();
            db_conn.beginTransaction();
            result = db_conn.executeUpdateQuery(query);
            db_conn.commitTransaction();

        } catch (Exception t) {
            db_conn.rollbackTransaction();
            throw t;
        } finally {
            db_conn.closeConnection();
        }

        return result;
    }

    @Override
    public void close() {
        try {
            if (db_conn != null) {
                db_conn.close();
            }
        } catch (Exception e) {
        }
    }

}
