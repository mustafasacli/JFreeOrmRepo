/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jv.freeorm.base;

/**
 *
 * @author Mustafa SACLI
 */
public enum DriverTypes {

    ApacheDerby(IConnectionType.CONN_TYPE_DERBY),
    MsSQL(IConnectionType.CONN_TYPE_MSSQL),
    Jtds(IConnectionType.CONN_TYPE_JTDS),
    Sun_Odbc(IConnectionType.CONN_TYPE_SUN_ODBC),
    PostgreSQL(IConnectionType.CONN_TYPE_POSTGRE_SQL),
    Db2(IConnectionType.CONN_TYPE_DB2),
    Oracle(IConnectionType.CONN_TYPE_ORACLE),
    MySQL(IConnectionType.CONN_TYPE_MY_SQL),
    SQLite(IConnectionType.CONN_TYPE_SQL_LITE),
    FireBird(IConnectionType.CONN_TYPE_FIREBIRD),
    Access(IConnectionType.CONN_TYPE_ACCESS),
    HSQL(IConnectionType.CONN_TYPE_HSQL),
    H2(IConnectionType.CONN_TYPE_H2),
    MariaDb(IConnectionType.CONN_TYPE_MARIA_DB),
    Sybase(IConnectionType.CONN_TYPE_SYBASE),
    Sybase_Jtds(IConnectionType.CONN_TYPE_SYBASE_JTDS),
    Informix(IConnectionType.CONN_TYPE_INFORMIX),
    U2(IConnectionType.CONN_TYPE_U2),
    Ingres(IConnectionType.CONN_TYPE_INGRES),
    MsSqlCe(IConnectionType.CONN_TYPE_MS_SQL_CE),
    FirstSql(IConnectionType.CONN_TYPE_FIRST_SQL),
    MimerSql(IConnectionType.CONN_TYPE_MIMER_SQL),
    OpenBase(IConnectionType.CONN_TYPE_OPEN_BASE),
    SapDb(IConnectionType.CONN_TYPE_SAP_DB),
    SmallSql(IConnectionType.CONN_TYPE_SMALL_SQL),
    VistaDB(IConnectionType.CONN_TYPE_VISTA_DB),
    Cassandra(IConnectionType.CONN_TYPE_CASSANDRA),
    Cache(IConnectionType.CONN_TYPE_CACHE),
    TerraData(IConnectionType.CONN_TYPE_TERRA_DATA),
    External(IConnectionType.CONN_TYPE_EXTERNAL);
    
    private final int _connType;

    private DriverTypes(int ConType) {
        _connType = ConType;
    }

    public int getConnectionType() {
        return _connType;
    }

    public String getDriverOfConnectionType() {
        switch (this) {
            case ApacheDerby:
                return IDriverType.DRIVER_TYPE_DERBY;

            case MsSqlCe:
            case MsSQL:
                return IDriverType.DRIVER_TYPE_MSSQL;

            case Jtds:
            case Sybase_Jtds:
                return IDriverType.DRIVER_TYPE_JTDS;

            case PostgreSQL:
                return IDriverType.DRIVER_TYPE_POSTGRE_SQL;

            case Db2:
                return IDriverType.DRIVER_TYPE_DB2;

            case Oracle:
                return IDriverType.DRIVER_TYPE_ORACLE;

            case MySQL:
            case MariaDb:
                return IDriverType.DRIVER_TYPE_MY_SQL;

            case SQLite:
                return IDriverType.DRIVER_TYPE_SQL_LITE;

            case FireBird:
                return IDriverType.DRIVER_TYPE_FIREBIRD;

            case Access:
                return IDriverType.DRIVER_TYPE_ACCESS;

            case HSQL:
                return IDriverType.DRIVER_TYPE_HSQL;

            case H2:
                return IDriverType.DRIVER_TYPE_H2;

            case Sybase:
                return IDriverType.DRIVER_TYPE_SYBASE;

            case Informix:
                return IDriverType.DRIVER_TYPE_INFORMIX;

            case U2:
                return IDriverType.DRIVER_TYPE_U2;

            case Ingres:
                return IDriverType.DRIVER_TYPE_INGRES;

            case FirstSql:
                return IDriverType.DRIVER_TYPE_FIRST_SQL;

            case MimerSql:
                return IDriverType.DRIVER_TYPE_MIMER_SQL;

            case OpenBase:
                return IDriverType.DRIVER_TYPE_OPEN_BASE;

            case SapDb:
                return IDriverType.DRIVER_TYPE_SAP_DB;

            case SmallSql:
                return IDriverType.DRIVER_TYPE_SMALL_SQL;

            case Cassandra:
                return IDriverType.DRIVER_TYPE_CASSANDRA;

            case Cache:
                return IDriverType.DRIVER_TYPE_CACHE;

            case TerraData:
                return IDriverType.DRIVER_TYPE_TERRA_DATA;

            case Sun_Odbc:
                return IDriverType.DRIVER_TYPE_SUN_ODBC;

            case External:
            default:
                return IDriverType.DRIVER_TYPE_UNKNOWN;
        }
    }
}
