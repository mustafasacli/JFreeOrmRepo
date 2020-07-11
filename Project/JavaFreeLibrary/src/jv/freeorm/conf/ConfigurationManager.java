package jv.freeorm.conf;

import jv.freeorm.base.DriverType;
import java.io.File;

import static java.lang.System.out;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author Mustafa SACLI
 */
public final class ConfigurationManager {

    public static DbConfiguration getConfiguration(String connection_name) {
        DbConfiguration db_conf
                = get_configuration("C:/JvFreeOrm/conf/config.xml", connection_name);
        return db_conf;
    }

    public static DbConfiguration getConfiguration(String file_name,
            String connection_name) {
        DbConfiguration db_conf = get_configuration(file_name, connection_name);
        return db_conf;
    }

    /**
     *
     * @param connTypeName
     * @return returns Driver Type Of Connection.
     */
    public static DriverType GetDriverType(String connTypeName) {
        DriverType dt = DriverType.Unknown;

        try {

            String connType = connTypeName == null ? "" : connTypeName;
            connType = connType.trim();
            connType = connType.toLowerCase();
            connType = connType.replace('ı', 'i');

            // || connType.matches("mssqljtds")
            if (connType.matches("ext") || connType.matches("external")) {
                dt = DriverType.External;
                return dt;
            }

            if (connType.matches("derby") || connType.matches("apachederby")) {
                dt = DriverType.Derby;
                return dt;
            }

            if (connType.matches("mssql") || connType.matches("ms-sql")) {
                dt = DriverType.MsSQL;
                return dt;
            }

            if (connType.matches("jtds")) {
                dt = DriverType.Jtds;
                return dt;
            }

            if (connType.matches("sunodbc") || connType.matches("sun-odbc")) {
                dt = DriverType.SunOdbc;
                return dt;
            }

            if (connType.matches("enterprisedb") || connType.matches("enterprise-db")) {
                dt = DriverType.EnterpriseDb;
                return dt;
            }

            if (connType.matches("db2")) {
                dt = DriverType.Db2;
                return dt;
            }

            if (connType.matches("oracle")) {
                dt = DriverType.Oracle;
                return dt;
            }

            if (connType.matches("mysql") || connType.matches("my-sql")) {
                dt = DriverType.MySQL;
                return dt;
            }

            if (connType.matches("sqlite")) {
                dt = DriverType.SQLite;
                return dt;
            }

            if (connType.matches("fbird") || connType.matches("firebird")
                    || connType.matches("firebirdsql")
                    || connType.matches("firebird-sql")) {
                dt = DriverType.Firebird;
                return dt;
            }

            if (connType.matches("access")) {
                dt = DriverType.Access;
                return dt;
            }

            if (connType.matches("hsql")) {
                dt = DriverType.HSql;
                return dt;
            }

            if (connType.matches("pgsql") || connType.matches("pg-sql")
                    || connType.matches("postgresql")
                    || connType.matches("postgre-sql")) {
                dt = DriverType.PostgreSQL;
                return dt;
            }

            if (connType.matches("h2")) {
                dt = DriverType.H2;
                return dt;
            }

            if (connType.matches("sybase")) {
                dt = DriverType.Sybase;
                return dt;
            }

            if (connType.matches("informix")) {
                dt = DriverType.Informix;
                return dt;
            }

            if (connType.matches("u2")) {
                dt = DriverType.U2;
                return dt;
            }

            if (connType.matches("ingres")) {
                dt = DriverType.Ingres;
                return dt;
            }

            if (connType.matches("first") || connType.matches("firstsql")
                    || connType.matches("first-sql")) {
                dt = DriverType.FirstSQL;
                return dt;
            }

            if (connType.matches("mimer") || connType.matches("mimersql")
                    || connType.matches("mimer-sql")) {
                dt = DriverType.MimerSQL;
                return dt;
            }

            if (connType.matches("openbase") || connType.matches("open-base")) {
                dt = DriverType.OpenBase;
                return dt;
            }

            if (connType.matches("sapdb") || connType.matches("sap-db")) {
                dt = DriverType.SapDb;
                return dt;
            }

            if (connType.matches("small") || connType.matches("smallsql")
                    || connType.matches("small-sql")) {
                dt = DriverType.SmallSQL;
                return dt;
            }

            if (connType.matches("cassandra")) {
                dt = DriverType.Cassandra;
                return dt;
            }

            if (connType.matches("cache")) {
                dt = DriverType.Cache;
                return dt;
            }

            if (connType.matches("terradata") || connType.matches("terra-data")) {
                dt = DriverType.TerraData;
                return dt;
            }

            if (connType.matches("jtdsmssql") || connType.matches("jtds-mssql")
                    || connType.matches("mssqljtds")
                    || connType.matches("mssql-jtds")) {
                dt = DriverType.Jtds_MsSql;
                return dt;
            }
            if (connType.matches("mssqlce") || connType.matches("ms-sqlce")) {
                dt = DriverType.MsSqlCe;
                return dt;
            }
        } catch (Exception e) {
            dt = DriverType.Unknown;
        }

        return dt;
    }

    private static DbConfiguration get_configuration(String file_name,
            String connection_name) {
        DbConfiguration db_conf = new DbConfiguration();

        try {
            File f = new File(file_name);
            DocumentBuilder docBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = docBuilder.parse(f);
            doc.getDocumentElement().normalize();
            Element nd_config = doc.getDocumentElement();
            NodeList nd_list = nd_config.getElementsByTagName("config");
            
            if (nd_list == null || nd_list.getLength() == 0) {
                db_conf.setError("Db Configuration File is empty.");
                return db_conf;
            }
            Node nd;
            NodeList nd_sub_list;
            for (int nd_counter = 0; nd_counter < nd_list.getLength(); nd_counter++) {
                nd=nd_list.item(nd_counter);
                
            }
            
            
            
            
            
        } catch (Exception e) {
            db_conf.setError(e.getMessage());
        }

        return db_conf;
    }

}
