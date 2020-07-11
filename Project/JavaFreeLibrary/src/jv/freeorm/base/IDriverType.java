package jv.freeorm.base;

/**
 *
 * @author Mustafa SACLI
 */
public interface IDriverType {

    public static final String DRIVER_TYPE_UNKNOWN = "";
    public static final String DRIVER_TYPE_DERBY = "org.apache.derby.jdbc.ClientDriver";
    public static final String DRIVER_TYPE_MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    public static final String DRIVER_TYPE_JTDS = "net.sourceforge.jtds.jdbc.Driver";
    public static final String DRIVER_TYPE_SUN_ODBC = "sun.jdbc.odbc.JdbcOdbcDriver";
    public static final String DRIVER_TYPE_ENTERPRISE_DB = "com.edb.Driver";
    public static final String DRIVER_TYPE_DB2 = "com.ibm.as400.access.AS400JDBCDriver";
    public static final String DRIVER_TYPE_ORACLE = "oracle.jdbc.driver.OracleDriver";
    public static final String DRIVER_TYPE_MY_SQL = "com.mysql.jdbc.Driver";
    public static final String DRIVER_TYPE_SQL_LITE = "org.sqlite.JDBC";
    public static final String DRIVER_TYPE_FIREBIRD = "org.firebirdsql.jdbc.FBDriver";
    public static final String DRIVER_TYPE_ACCESS = "sun.jdbc.odbc.JdbcOdbcDriver";
    public static final String DRIVER_TYPE_HSQL = "org.hsqldb.jdbcDriver";
    public static final String DRIVER_TYPE_POSTGRE_SQL = "org.postgresql.Driver";
    public static final String DRIVER_TYPE_H2 = "org.h2.Driver";
    public static final String DRIVER_TYPE_SYBASE = "com.sybase.jdbc3.jdbc.SybDriver";
    public static final String DRIVER_TYPE_INFORMIX = "com.informix.jdbc.IfxDriver";
    public static final String DRIVER_TYPE_U2 = "com.ibm.u2.jdbc.UniJDBCDriver";
    public static final String DRIVER_TYPE_INGRES = "com.ingres.jdbc.IngresDriver";
    public static final String DRIVER_TYPE_FIRST_SQL = "COM.FirstSQL.Dbcp.DbcpDriver";
    public static final String DRIVER_TYPE_MIMER_SQL = "com.mimer.jdbc.Driver";
    public static final String DRIVER_TYPE_OPEN_BASE = "com.openbase.jdbc.ObDriver";
    public static final String DRIVER_TYPE_SAP_DB = "com.sap.dbtech.jdbc.DriverSapDB";
    public static final String DRIVER_TYPE_SMALL_SQL = "smallsql.database.SSDriver";
    public static final String DRIVER_TYPE_CASSANDRA = "org.apache.cassandra.cql.jdbc.CassandraDriver";
    public static final String DRIVER_TYPE_CACHE = "com.intersys.jdbc.CacheDriver";
    public static final String DRIVER_TYPE_TERRA_DATA = "com.teradata.jdbc.TeraDriver";
}
