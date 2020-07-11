package test;

import jv.freeorm.connection.IDbConnection;
import jv.freeorm.base.DriverType;
import jv.freeorm.base.DbParameter;
import java.util.Date;
import jv.freeorm.connection.ExternalDbConnection;
import jv.freeorm.query.IQuery;
import jv.freeorm.query.QueryBuilder;
import jv.freeorm.query.QueryTypes;
import static java.lang.System.out;

/**
 *
 * @author Mustafa SACLI
 */
public class TestMain {

    /**
     * @param args the command line arguments
     * @throws java.lang.Throwable
     */
    public static void main(String[] args) throws Throwable {

        /*
         DbConnection dbConn = new DbConnection(ConnectionType.MSSQL,
         "jdbc:sqlserver://10.0.0.145:1433;databaseName=InCareTest;", 
         ConnProp 
         //"jdbc:sqlserver://10.0.0.145:1433;databaseName=InCareTest;user=incareadmin;password=incareadmin;"
         );
         Object[] objArray = dbConn.executeScalarQuery("Select * From  t_UnitGroups;");
         for (Object obj : objArray) {
         System.out.println(obj);
         }*/
        //localhost ya da 127.0.0.1
        /*
         String[] strS = null;//new String[]{};
         for (String str : strS) {
         out.println(str);
         }
         out.println("******************************");
         strS=new String[]{"ali","veli","ibrahim"};
         for (String str : strS) {
         out.println(str);
         }*/
        //Manager dbMan;
        /*
         Properties props = new Properties();
         props.setProperty("user", "root");
         props.setProperty("password", "");

         props.setProperty("user", "postgres");
         props.setProperty("password", "374phjg2346rgy84j67uwye387");
         * */
        //dbMan = new Manager(DriverType.MySQL, "jdbc:mysql://localhost:3306/pydb", "root", "");
        //"jdbc:postgresql://localhost:5432/template_postgis_20?protocol=3"
        // query1 için template_postgis_20 veritabanı yerine mydb yazılmalıdır.
       /* String query1 = "Select Count(*) From  IdentityTest;";
         String query2 = "Select * From spatial_ref_sys;";
         Object[] objArray;
         ResultSet rS = null;
         Date date1 = new Date();
         rS = dbCon.getResultSetOfQuery(query2);
         Date date2 = new Date();
         */ /*
         for (Object obj : objArray) {
         System.out.println(obj);
         }*/

        /*     out.println("***********************");
         out.println(date1.toString());
         out.println(date2.toString());
         out.println("***********************");
         out.println(date2.getTime() - date1.getTime());
         */
        IDbConnection conn = new ExternalDbConnection("com.mysql.jdbc.Driver",
                "jdbc:mysql://localhost:3306/pydb", "root", "");
        //DbConnection(DriverType.MySQL, "jdbc:mysql://localhost:3306/pydb", "root", "");
        Person p = new Person();
        p.setPerson_id(3);
        p.setFirstName("Ahmet");
        p.setLastName("Ozer");
        p.setSalary(4250.00d);

        int i = 0;
        //i = dbMan.Update(p);
        QueryTypes qt = QueryTypes.Insert;
        DriverType dt = DriverType.MySQL;

        IQuery q = QueryBuilder.Build(p, dt, qt);
        //QueryBuilder_Old qb = new QueryBuilder_Old(p, qt, dt);
        Date d1 = new Date();
        /*
         conn.open();
         conn.beginTransaction();
         i = conn.executeUpdateQuery(q.getText(), q.getParameters());
         conn.commitTransaction();
         conn.closeConnection();
         */
        Date d2 = new Date();

        System.out.printf("Date 1: %s\n", d1.toString());
        System.out.printf("Date 2: %s\n", d2.toString());
        System.out.println("Sonuc: " + i);
        System.out.printf("Geçen Süre : %d\n", d2.getTime() - d1.getTime());

        //IQuery q = QueryBuilder.Build(p, dt, qt);
        out.println(q.getText());
        DbParameter[] params = q.getParameters();

        if (params == null) {
            out.println("Parameters are null.");
        } else {
            for (DbParameter prm : params) {
                out.printf("Index : %d; \nValue : %s;\n***\n", prm.getIndex(), prm.getValue());
            }
        }

    }

}
