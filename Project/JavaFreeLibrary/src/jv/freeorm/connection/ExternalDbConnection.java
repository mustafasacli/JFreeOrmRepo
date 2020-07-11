package jv.freeorm.connection;

import jv.freeorm.base.DriverType;
import jv.freeorm.base.DbParameter;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 *
 * @author Mustafa SACLI
 *
 * @since 1.7
 */
public final class ExternalDbConnection implements IDbConnection {

    private String driver_ = "";
    private String url_ = "";
    private Properties props_ = null;
    private String user_ = "";
    private String pass_ = "";
    private int ctor_type = 0;
    private Connection db_conn = null;
    private Exception last_closing_error;
    private boolean on_transaction = false;

    public ExternalDbConnection(String driver, String ConnectionUrl) {
        this.driver_ = driver;
        url_ = ConnectionUrl;
        ctor_type = 1;
    }

    public ExternalDbConnection(String driver, String ConnectionUrl, String user, String password) {
        this.driver_ = driver;
        url_ = ConnectionUrl;
        user_ = user;
        pass_ = password;
        ctor_type = 2;
    }

    public ExternalDbConnection(String driver, String ConnectionUrl, Properties props) {
        this.driver_ = driver;
        url_ = ConnectionUrl;
        props_ = props;
        ctor_type = 3;
    }

    @Override
    public DriverType getConnectionType() {
        return DriverType.External;
    }

    private void initConnection() throws Exception {
        try {
            if (db_conn == null) {
                Driver drv = (Driver) Class.forName(driver_).newInstance();
                DriverManager.registerDriver(drv);

                if (ctor_type == 1) {  // URL.
                    db_conn = DriverManager.getConnection(url_);
                    return;
                }

                if (ctor_type == 2) {  // URL, user and pass.
                    db_conn = DriverManager.getConnection(url_,
                            user_, pass_);
                    return;
                }

                if (ctor_type == 3) {// URL and Properties.
                    db_conn = DriverManager.getConnection(url_, props_);
                    return;
                }
            }

            if (!db_conn.isClosed()) {
                Driver drv = (Driver) Class.forName(driver_).newInstance();
                DriverManager.registerDriver(drv);

                if (ctor_type == 1) {  // URL.
                    db_conn = DriverManager.getConnection(url_);
                    return;
                }

                if (ctor_type == 2) {  // URL, user and pass.
                    db_conn = DriverManager.getConnection(url_,
                            user_, pass_);
                    return;
                }

                if (ctor_type == 3) {// URL and Properties.
                    db_conn = DriverManager.getConnection(url_, props_);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void open() throws Exception {
        try {
            try {
                last_closing_error = null;
            } catch (Exception e) {
            }

            if (db_conn == null) {
                initConnection();
                return;
            }

            if (db_conn.isClosed()) {
                initConnection();
            }

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void beginTransaction() throws SQLException {
        try {

            if (!on_transaction) {
                if (db_conn != null) {
                    if (!db_conn.isClosed()) {
                        db_conn.setAutoCommit(false);
                        on_transaction = true;
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
        }
    }

    @Override
    public void commitTransaction() throws SQLException {
        try {
            if (on_transaction) {
                if (db_conn != null) {
                    if (!db_conn.isClosed()) {
                        db_conn.commit();
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            on_transaction = false;
            this.closeTransaction();
        }
    }

    @Override
    public void rollbackTransaction() throws SQLException {
        try {

            if (on_transaction) {
                if (db_conn != null) {
                    if (!db_conn.isClosed()) {
                        db_conn.rollback();
                    }
                }
            }
        } catch (Exception e) {
            throw e;
        } finally {
            on_transaction = false;
            this.closeTransaction();
        }
    }

    @Override
    public void closeTransaction() throws SQLException {
        try {

            if (db_conn != null) {
                if (!db_conn.isClosed()) {
                    db_conn.setAutoCommit(true);
                }
            }

        } catch (Exception e) {
        } finally {
            on_transaction = false;
        }
    }

    @Override
    public int closeConnection() {
        int result = -1;

        try {
            if (db_conn != null) {
                result = 0;
                if (!db_conn.isClosed()) {
                    result = 1;
                    db_conn.close();
                }
            }
        } catch (Exception e) {
            last_closing_error = e;
        } finally {
            db_conn = null;
        }

        return result;
    }

    @Override
    public void close() throws Exception {
        try {
            closeConnection();
            Runtime.getRuntime().gc();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<String> getCatalogs() throws Exception {
        List<String> databases = new ArrayList<String>();

        try {
            try (ResultSet rS = db_conn.getMetaData().getCatalogs()) {

                while (rS.next()) {
                    databases.add(rS.getString(1));
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return databases;
    }

    @Override
    public List<String> getTables() throws Exception {
        List<String> tables = new ArrayList<String>();

        try {
            DatabaseMetaData dbmd = db_conn.getMetaData();

            // Specify the type of object; in this case we want tables
            String[] types = {"TABLE"};

            try (ResultSet resultSet = dbmd.getTables(null, null, "%", types)) {

                while (resultSet.next()) {
                    // Get the table name
                    tables.add(resultSet.getString(3));

                    /*
                     // Get the table's catalog and schema names (if any)
                     String tableCatalog = resultSet.getString(1);
                     String tableSchema = resultSet.getString(2);
                     */
                }
            }
        } catch (Exception e) {
            throw e;
        }

        return tables;
    }

    /**
     *
     *
     * @param paramCollection parameter List
     * @throws java.lang.Exception
     */
    @Override
    public boolean executeQuery(String query, DbParameter... paramCollection) throws Exception {
        boolean result;

        try {

            try (PreparedStatement prepared = db_conn.prepareStatement(query)) {
                if (paramCollection != null) {
                    int param_counter = 0;
                    for (DbParameter prm : paramCollection) {
                        if (prm != null) {
                            ++param_counter;
                            if (prm.isInput()) {
                                prepared.setObject(param_counter,
                                        prm.getValue());
                            }
                        }
                    }
                }
                result = prepared.execute();
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public boolean executeQuery(String query) throws Exception {
        boolean result;

        try {

            try (Statement _statement = db_conn.createStatement()) {
                result = _statement.execute(query);
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public boolean executeProcedure(String procedure,
            DbParameter... paramCollection) throws Exception {
        boolean result;

        try {

            try (CallableStatement callable = db_conn.prepareCall(procedure)) {

                if (paramCollection != null) {
                    int param_counter = 0;
                    for (DbParameter prm : paramCollection) {
                        if (prm != null) {
                            ++param_counter;
                            if (prm.isInput()) {
                                callable.setObject(param_counter,
                                        prm.getValue());
                            }
                        }
                    }
                }

                result = callable.execute();
                callable.clearParameters();
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public boolean executeProcedure(String procedure) throws Exception {
        boolean result;

        try {

            try (CallableStatement callable = db_conn.prepareCall(procedure)) {
                result = callable.execute();
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public int executeAndGetId(String query,
            DbParameter... paramCollection) throws Exception {
        int result = -1;

        try {

            try (PreparedStatement prepared = db_conn.prepareStatement(query,
                    Statement.RETURN_GENERATED_KEYS)) {

                if (paramCollection != null) {
                    int param_counter = 0;
                    for (DbParameter prm : paramCollection) {
                        if (prm != null) {
                            ++param_counter;
                            if (prm.isInput()) {
                                prepared.setObject(param_counter,
                                        prm.getValue());

                            }
                        }
                    }
                }

                result = prepared.executeUpdate();
                if (result > 0) {
                    ResultSet rs = prepared.getGeneratedKeys();
                    if (rs.next()) {
                        result = rs.getInt(1);
                    }
                }
                prepared.clearParameters();
                prepared.clearWarnings();
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public Integer executeUpdateQuery(String query, DbParameter... paramCollection) throws Exception {
        int result = -1;

        try {

            try (PreparedStatement prepared = db_conn.prepareStatement(query)) {

                if (paramCollection != null) {
                    int param_counter = 0;
                    for (DbParameter prm : paramCollection) {
                        if (prm != null) {
                            ++param_counter;
                            if (prm.isInput()) {
                                prepared.setObject(param_counter,
                                        prm.getValue());
                            }
                        }
                    }
                }
                result = prepared.executeUpdate();
                prepared.clearParameters();
                prepared.clearWarnings();
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public Integer executeUpdateQuery(String query) throws Exception {
        int result;

        try {
            try (Statement _statement = db_conn.createStatement()) {
                result = _statement.executeUpdate(query);
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public Integer executeUpdateProcedure(String procedure, DbParameter... paramCollection) throws Exception {
        int result;

        try {

            try (CallableStatement callable = db_conn.prepareCall(procedure)) {
                if (paramCollection != null) {
                    int param_counter = 0;
                    for (DbParameter prm : paramCollection) {
                        if (prm != null) {
                            ++param_counter;
                            if (prm.isInput()) {
                                callable.setObject(param_counter,
                                        prm.getValue());
                            }
                        }
                    }
                }

                result = callable.executeUpdate();
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public Integer executeUpdateProcedure(String procedure) throws Exception {
        int result;

        try {

            try (CallableStatement callable = db_conn.prepareCall(procedure)) {
                result = callable.executeUpdate();
            }

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public Object[] executeScalarQuery(String query,
            DbParameter... paramCollection) throws Exception {
        Object[] result = new Object[]{};

        try {
            List<Object> obj_list = new ArrayList<Object>();

            try (CallableStatement callable = db_conn.prepareCall(query)) {
                if (paramCollection != null) {
                    List<DbParameter> prms = new ArrayList<DbParameter>();
                    DbParameter outPrm;
                    int param_counter = 0;
                    for (DbParameter prm : paramCollection) {
                        if (prm != null) {
                            ++param_counter;
                            if (prm.isInput()) {
                                callable.setObject(param_counter,
                                        prm.getValue());
                            } else {
                                callable.registerOutParameter(param_counter,
                                        java.sql.Types.JAVA_OBJECT);
                                outPrm = new DbParameter(prm.getValue(), false);
                                outPrm.setIndex(param_counter);
                                prms.add(outPrm);
                            }
                        }
                    }

                    callable.executeUpdate();

                    for (DbParameter prm : prms) {
                        if (!prm.isInput()) {
                            obj_list.add(callable.getObject(prm.getIndex()));
                        }
                    }

                } else {

                    try (ResultSet rs = callable.executeQuery()) {
                        while (rs.next()) {
                            int colInt = rs.getMetaData().getColumnCount();
                            for (int i = 0; i < colInt; i++) {
                                obj_list.add(rs.getObject(i + 1));
                            }
                            rs.close();
                            break;
                        }
                    }
                }
            }

            result = obj_list.toArray();

        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public Object[] executeScalarQuery(String query) throws Exception {
        Object[] result = new Object[]{};

        try {
            List<Object> objList = new ArrayList<Object>();

            try (Statement _statement = db_conn.createStatement()) {

                try (ResultSet rS = _statement.executeQuery(query)) {
                    int cols = rS.getMetaData().getColumnCount();
                    while (rS.next()) {
                        for (int i = 0; i < cols; i++) {
                            objList.add(rS.getObject(i + 1));
                        }
                        break;
                    }
                }
            }

            result = objList.toArray();

        } catch (Exception ex) {
            throw ex;
        }

        return result;
    }

    @Override
    public Object[] executeScalarProcedure(String procedure,
            DbParameter... paramCollection) throws Exception {
        Object[] result = new Object[]{};

        try {
            List<Object> obj_list = new ArrayList<Object>();

            try (CallableStatement callable = db_conn.prepareCall(procedure)) {

                if (paramCollection != null) {

                    List<DbParameter> prms = new ArrayList<DbParameter>();
                    DbParameter outPrm;
                    int param_counter = 0;
                    for (DbParameter prm : paramCollection) {
                        if (prm != null) {
                            ++param_counter;
                            if (prm.isInput()) {
                                callable.setObject(param_counter,
                                        prm.getValue());
                            } else {
                                callable.registerOutParameter(param_counter,
                                        java.sql.Types.JAVA_OBJECT);
                                outPrm = new DbParameter(prm.getValue(), false);
                                outPrm.setIndex(param_counter);
                                prms.add(outPrm);
                            }
                        }
                    }

                    callable.executeUpdate();

                    for (DbParameter prm : prms) {
                        if (!prm.isInput()) {
                            obj_list.add(callable.getObject(prm.getIndex()));
                        }
                    }

                } else {
                    try (ResultSet rS = callable.executeQuery()) {
                        int cols = rS.getMetaData().getColumnCount();
                        while (rS.next()) {
                            for (int col_counter = 0; col_counter < cols; col_counter++) {
                                obj_list.add(rS.getObject(col_counter + 1));
                            }
                            rS.close();
                            break;
                        }
                    }
                }
            }

            result = obj_list.toArray();

        } catch (Exception ex) {
            throw ex;
        }

        return result;
    }

    @Override
    public Object[] executeScalarProcedure(String procedure) throws Exception {
        Object[] result = new Object[]{};

        try {
            result = executeScalarQuery(procedure, new DbParameter[]{});
        } catch (Exception e) {
            throw e;
        }

        return result;
    }

    @Override
    public Object[][] getResultSetOfQuery(String query,
            DbParameter... paramCollection) throws Exception {
        Object[][] result_array = new Object[][]{};
        try {

            try (PreparedStatement prepared = db_conn.prepareStatement(query,
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {

                if (paramCollection != null) {
                    int prm_counter = 0;
                    for (DbParameter p : paramCollection) {
                        if (p != null) {
                            ++prm_counter;
                            if (p.isInput()) {
                                prepared.setObject(prm_counter, p.getValue());
                            }
                        }
                    }
                }

                ResultSet rs;
                rs = prepared.executeQuery();
                if (rs.last()) {
                    int col = rs.getMetaData().getColumnCount();
                    int row;
                    row = rs.getRow();
                    rs.beforeFirst();
                    result_array = new Object[row][col];
                    int row_counter = 0;
                    while (rs.next()) {
                        for (int col_counter = 0; col_counter < col; col_counter++) {
                            result_array[row_counter][col_counter] = rs.getObject(col_counter + 1);
                        }
                        row_counter++;
                    }
                }

            }

        } catch (Exception e) {
            throw e;
        }

        return result_array;
    }

    @Override
    public Object[][] getResultSetOfQuery(String query) throws Exception {
        Object[][] result_array = new Object[][]{};

        try {

            try (Statement _statement = db_conn.createStatement(
                    ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_READ_ONLY)) {
                ResultSet rs;
                rs = _statement.executeQuery(query);
                if (rs.last()) {
                    int col = rs.getMetaData().getColumnCount();
                    int row;
                    row = rs.getRow();
                    rs.beforeFirst();
                    result_array = new Object[row][col];
                    int row_counter = 0;
                    while (rs.next()) {
                        for (int col_counter = 0; col_counter < col; col_counter++) {
                            result_array[row_counter][col_counter] = rs.getObject(col_counter + 1);
                        }
                        row_counter++;
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        }

        return result_array;
    }

    @Override
    public Object[][] getResultSetOfProcedure(String query,
            DbParameter... paramCollection) throws Exception {
        Object[][] result_array = new Object[][]{};

        try {
            try (CallableStatement callable = db_conn.prepareCall(query)) {
                if (paramCollection != null) {
                    int prm_counter = 0;
                    for (DbParameter p : paramCollection) {
                        if (p != null) {
                            ++prm_counter;
                            if (p.isInput()) {
                                callable.setObject(prm_counter, p.getValue());
                            }
                        }
                    }
                }

                ResultSet rs;
                rs = callable.executeQuery();
                if (rs.last()) {
                    int col = rs.getMetaData().getColumnCount();
                    int row;
                    row = rs.getRow();
                    rs.beforeFirst();
                    result_array = new Object[row][col];
                    int row_counter = 0;
                    while (rs.next()) {
                        for (int col_counter = 0; col_counter < col; col_counter++) {
                            result_array[row_counter][col_counter] = rs.getObject(col_counter + 1);
                        }
                        row_counter++;
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        }

        return result_array;
    }

    @Override
    public Object[][] getResultSetOfProcedure(String procedure) throws Exception {
        Object[][] result_array = new Object[][]{};

        try {
            result_array = this.getResultSetOfProcedure(procedure, new DbParameter[]{});
        } catch (Exception e) {
            throw e;
        }

        return result_array;
    }

    @Override
    public int[] executeBatchQuery(String query, DbParameter[][] parameters) throws Exception {
        int[] retIntArray = null;

        try {
            if (parameters == null || parameters.length == 0) {
                throw new Exception("Parameters list can not be null or empty.");
            }

            try (PreparedStatement prep = db_conn.prepareStatement(query)) {
                int param_counter;
                for (DbParameter[] prms : parameters) {
                    param_counter = 0;
                    for (DbParameter p : prms) {
                        if (p != null) {
                            ++param_counter;
                            if (p.isInput()) {
                                prep.setObject(param_counter, p.getValue());
                            }
                        }
                    }
                    prep.addBatch();
                }

                retIntArray = prep.executeBatch();
            }

        } catch (Exception t) {
            throw t;
        }

        return retIntArray;
    }

    /**
     * @return the last closing error
     */
    public Exception getLastClosingError() {
        return last_closing_error;
    }
}
