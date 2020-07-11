package jv.freeorm.base;

/**
 *
 * @author Mustafa SACLI
 */
public class DriverType {

    private String m_driver = "";
    private int _driver_id = -2;

    private DriverType(String driver, int driver_id) {
        this.m_driver = driver;

        if (this.m_driver == null) {
            this.m_driver = "";
        }

        this._driver_id = driver_id;
        if (this._driver_id < -2) {
            this._driver_id = -2;
        }
    }

    /**
     *
     * @return Driver
     */
    public String getDriver() {
        return this.m_driver;
    }

    /**
     * @return the _driver_id
     */
    public int getDriverId() {
        return _driver_id;
    }

    public static final DriverType Unknown = new DriverType(DriverType.DRIVER_TYPE_UNKNOWN, -1);
    public static final DriverType External = new DriverType(DriverType.DRIVER_TYPE_UNKNOWN, 0);
    public static final DriverType Derby = new DriverType(DriverType.DRIVER_TYPE_DERBY, 1);
    public static final DriverType MsSQL = new DriverType(DriverType.DRIVER_TYPE_MSSQL, 2);
    public static final DriverType Jtds = new DriverType(DriverType.DRIVER_TYPE_JTDS, 3);
    public static final DriverType SunOdbc = new DriverType(DriverType.DRIVER_TYPE_SUN_ODBC, 4);
    public static final DriverType EnterpriseDb = new DriverType(DriverType.DRIVER_TYPE_ENTERPRISE_DB, 5);
    public static final DriverType Db2 = new DriverType(DriverType.DRIVER_TYPE_DB2, 6);
    public static final DriverType Oracle = new DriverType(DriverType.DRIVER_TYPE_ORACLE, 7);
    public static final DriverType MySQL = new DriverType(DriverType.DRIVER_TYPE_MY_SQL, 8);
    public static final DriverType SQLite = new DriverType(DriverType.DRIVER_TYPE_SQL_LITE, 9);
    public static final DriverType Firebird = new DriverType(DriverType.DRIVER_TYPE_FIREBIRD, 10);
    public static final DriverType Access = new DriverType(DriverType.DRIVER_TYPE_SUN_ODBC, 11);
    public static final DriverType HSql = new DriverType(DriverType.DRIVER_TYPE_HSQL, 12);
    public static final DriverType PostgreSQL = new DriverType(DriverType.DRIVER_TYPE_POSTGRE_SQL, 13);
    public static final DriverType H2 = new DriverType(DriverType.DRIVER_TYPE_H2, 14);
    public static final DriverType Sybase = new DriverType(DriverType.DRIVER_TYPE_SYBASE, 15);
    public static final DriverType Informix = new DriverType(DriverType.DRIVER_TYPE_INFORMIX, 16);
    public static final DriverType U2 = new DriverType(DriverType.DRIVER_TYPE_U2, 17);
    public static final DriverType Ingres = new DriverType(DriverType.DRIVER_TYPE_INGRES, 18);
    public static final DriverType FirstSQL = new DriverType(DriverType.DRIVER_TYPE_FIRST_SQL, 19);
    public static final DriverType MimerSQL = new DriverType(DriverType.DRIVER_TYPE_MIMER_SQL, 20);
    public static final DriverType OpenBase = new DriverType(DriverType.DRIVER_TYPE_OPEN_BASE, 21);
    public static final DriverType SapDb = new DriverType(DriverType.DRIVER_TYPE_SAP_DB, 21);
    public static final DriverType SmallSQL = new DriverType(DriverType.DRIVER_TYPE_SMALL_SQL, 22);
    public static final DriverType Cassandra = new DriverType(DriverType.DRIVER_TYPE_CASSANDRA, 23);
    public static final DriverType Cache = new DriverType(DriverType.DRIVER_TYPE_CACHE, 24);
    public static final DriverType TerraData = new DriverType(DriverType.DRIVER_TYPE_TERRA_DATA, 25);
    public static final DriverType Jtds_MsSql = new DriverType(DriverType.DRIVER_TYPE_JTDS, 26);
    public static final DriverType MsSqlCe = new DriverType(DriverType.DRIVER_TYPE_MSSQL, 27);

    private static final String DRIVER_TYPE_UNKNOWN = "";
    private static final String DRIVER_TYPE_DERBY = "org.apache.derby.jdbc.ClientDriver";
    private static final String DRIVER_TYPE_MSSQL = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
    private static final String DRIVER_TYPE_JTDS = "net.sourceforge.jtds.jdbc.Driver";
    private static final String DRIVER_TYPE_SUN_ODBC = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String DRIVER_TYPE_ENTERPRISE_DB = "com.edb.Driver";
    private static final String DRIVER_TYPE_DB2 = "com.ibm.as400.access.AS400JDBCDriver";
    private static final String DRIVER_TYPE_ORACLE = "oracle.jdbc.driver.OracleDriver";
    private static final String DRIVER_TYPE_MY_SQL = "com.mysql.jdbc.Driver";
    private static final String DRIVER_TYPE_SQL_LITE = "org.sqlite.JDBC";
    private static final String DRIVER_TYPE_FIREBIRD = "org.firebirdsql.jdbc.FBDriver";
    //private static final String DRIVER_TYPE_ACCESS = "sun.jdbc.odbc.JdbcOdbcDriver";
    private static final String DRIVER_TYPE_HSQL = "org.hsqldb.jdbcDriver";
    private static final String DRIVER_TYPE_POSTGRE_SQL = "org.postgresql.Driver";
    private static final String DRIVER_TYPE_H2 = "org.h2.Driver";
    private static final String DRIVER_TYPE_SYBASE = "com.sybase.jdbc3.jdbc.SybDriver";
    private static final String DRIVER_TYPE_INFORMIX = "com.informix.jdbc.IfxDriver";
    private static final String DRIVER_TYPE_U2 = "com.ibm.u2.jdbc.UniJDBCDriver";
    private static final String DRIVER_TYPE_INGRES = "com.ingres.jdbc.IngresDriver";
    private static final String DRIVER_TYPE_FIRST_SQL = "COM.FirstSQL.Dbcp.DbcpDriver";
    private static final String DRIVER_TYPE_MIMER_SQL = "com.mimer.jdbc.Driver";
    private static final String DRIVER_TYPE_OPEN_BASE = "com.openbase.jdbc.ObDriver";
    private static final String DRIVER_TYPE_SAP_DB = "com.sap.dbtech.jdbc.DriverSapDB";
    private static final String DRIVER_TYPE_SMALL_SQL = "smallsql.database.SSDriver";
    private static final String DRIVER_TYPE_CASSANDRA = "org.apache.cassandra.cql.jdbc.CassandraDriver";
    private static final String DRIVER_TYPE_CACHE = "com.intersys.jdbc.CacheDriver";
    private static final String DRIVER_TYPE_TERRA_DATA = "com.teradata.jdbc.TeraDriver";

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof DriverType) {
            DriverType d = (DriverType) obj;
            if (d != null) {
                return (this.m_driver.matches(d.getDriver())
                        && (this._driver_id == d.getDriverId()));
            }

            return false;
        }

        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this._driver_id;
        return hash;
    }

}
