package jv.freeorm.query;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import jv.freeorm.base.DbParameter;
import jv.freeorm.base.DriverType;
import jv.freeorm.collection.JCollection;
import jv.freeorm.entity.IBaseBO;
import jv.freeorm.util.StringUtil;

/**
 *
 * @author Mustafa SACLI
 */
public class QueryBuilder {

    static DbParameter[] get_parameters(IBaseBO bo, QueryTypes query_type)
            throws Exception {

        if (query_type == QueryTypes.Select
                || query_type == QueryTypes.SelectChanges) {
            return null;
        }
        int type_def;

        try {
            JCollection<DbParameter> parameters = new JCollection<>();

            DbParameter db_prm;
            String field_str;
            int counter_ = 0;
            Object val;
            Field f;

            String id_col = bo.getIdColumn();
            List<String> col_list = bo.getChangeList();

            if (query_type == QueryTypes.Insert
                    || query_type == QueryTypes.InsertAndGetId
                    || query_type == QueryTypes.Update) {
                col_list.remove(id_col);
            }

            if (query_type == QueryTypes.Insert
                    || query_type == QueryTypes.InsertAndGetId
                    || query_type == QueryTypes.InsertAnyChange
                    || query_type == QueryTypes.SelectChanges
                    || query_type == QueryTypes.Update) {
                for (String col : col_list) {
                    field_str = "_".concat(col);
                    f = bo.getClass().getDeclaredField(field_str);
                    if (f != null) {
                        f.setAccessible(true);
                        val = f.get(bo);
                        f.setAccessible(false);
                        db_prm = new DbParameter(val);
                        ++counter_;
                        type_def = getSqlType(f);
                        db_prm.setSqlType(type_def);
                        db_prm.setIndex(counter_);
                        parameters.add(db_prm);
                    }
                }

                if (query_type == QueryTypes.Update) {
                    if (StringUtil.isNullOrWhiteSpace(id_col)) {
                        throw new Exception("id column name must be defined.");
                    }

                    field_str = "_".concat(id_col);
                    f = bo.getClass().getDeclaredField(field_str);
                    if (f != null) {
                        f.setAccessible(true);
                        val = f.get(bo);
                        f.setAccessible(false);
                        db_prm = new DbParameter(val);
                        ++counter_;
                        type_def = getSqlType(f);
                        db_prm.setSqlType(type_def);
                        db_prm.setIndex(counter_);
                        parameters.add(db_prm);
                    }
                }
            }
            if (query_type == QueryTypes.Delete) {
                if (StringUtil.isNullOrWhiteSpace(id_col)) {
                    throw new Exception("id column name must be defined.");
                }

                field_str = "_".concat(id_col);
                f = bo.getClass().getDeclaredField(field_str);
                if (f != null) {
                    f.setAccessible(true);
                    val = f.get(bo);
                    f.setAccessible(false);
                    db_prm = new DbParameter(val);
                    db_prm.setIndex(1);
                    type_def = getSqlType(f);
                    db_prm.setSqlType(type_def);
                    parameters.add(db_prm);
                }
            }

            DbParameter[] prm_arr = new DbParameter[parameters.size()];

            Iterator<DbParameter> it = parameters.asIterator();
            counter_ = 0;
            while (it.hasNext()) {
                prm_arr[counter_] = it.next();
                counter_++;
            }

            return prm_arr;
        } catch (Exception e) {
            throw e;
        }
    }

    static String get_columns(IBaseBO bo, QueryTypes query_type) {
        String cols_ = "";
        String id_col = bo.getIdColumn();
        List<String> col_list = bo.getChangeList();

        if (query_type == QueryTypes.Insert
                || query_type == QueryTypes.InsertAndGetId
                || query_type == QueryTypes.Update) {
            col_list.remove(id_col);
        }

        if (query_type == QueryTypes.Insert || query_type == QueryTypes.InsertAndGetId
                || query_type == QueryTypes.InsertAnyChange
                || query_type == QueryTypes.SelectChanges) {

            for (String col : col_list) {
                cols_ = cols_.concat(", ");
                cols_ = cols_.concat(col);
            }

            cols_ = StringUtil.trimStart(cols_, ',');
            cols_ = StringUtil.trimStart(cols_);
            // RETURN
            return cols_;
        }

        if (query_type == QueryTypes.Update) {

            for (String col : col_list) {
                cols_ = cols_.concat(", ");
                cols_ = cols_.concat(col);
                cols_ = cols_.concat(" = ?");
            }

            cols_ = StringUtil.trimStart(cols_, ',');
            cols_ = StringUtil.trimStart(cols_);
            // RETURN
            return cols_;
        }

        return cols_;
    }

    static String get_vals(IBaseBO bo, QueryTypes query_type)
            throws Exception {
        String vals_ = "";
        String id_col = bo.getIdColumn();
        List<String> col_list = bo.getChangeList();

        if (query_type == QueryTypes.Insert
                || query_type == QueryTypes.InsertAndGetId) {
            col_list.remove(id_col);
        }

        if (query_type == QueryTypes.Insert
                || query_type == QueryTypes.InsertAndGetId
                || query_type == QueryTypes.InsertAnyChange) {
            int count = col_list.size();
            for (int counter = 0; counter < count; counter++) {
                vals_ = vals_.concat("?");
                vals_ = vals_.concat(",");
            }

            vals_ = StringUtil.trimEnd(vals_, ',');

            return vals_;
        }

        if (query_type == QueryTypes.Update || query_type == QueryTypes.Delete) {
            if (StringUtil.isNullOrWhiteSpace(id_col)) {
                throw new Exception("id column name must be defined.");
            }

            vals_ = vals_.concat(id_col);
            vals_ = vals_.concat(" = ?");

            return vals_;
        }

        if (query_type == QueryTypes.SelectWhere) {

            for (String col : col_list) {
                vals_ = vals_.concat(col);
                vals_ = vals_.concat(" = ?");
                vals_ = vals_.concat(" AND");
            }

            if (vals_.length() > 4) {
                vals_ = vals_.substring(0, vals_.length() - 4);
            }
        }

        return vals_;
    }

    private static String getIdentityFormat(DriverType driver_type) {
        String result = "";

        if (driver_type == DriverType.Access
                || driver_type == DriverType.Jtds_MsSql
                || driver_type == DriverType.MsSQL
                || driver_type == DriverType.MsSqlCe) {
            result = " SELECT IDENT_CURRENT('%s');";
            return result;
        }

        if (driver_type == DriverType.Oracle) {
            result = " @@IDENTITY;";
        }

        return result;
    }

    public static IQuery Build(IBaseBO bo, DriverType driver_type,
            QueryTypes query_type) throws Exception {
        IQuery q = null;
        try {
            QueryFormat q_format = new QueryFormat(query_type);

            String table_name;
            String columns;
            String vals;

            // Building table_name, columns, vals and Parameter parts.
            table_name = bo.getTable();
            if (StringUtil.isNullOrWhiteSpace(table_name)) {
                throw new Exception("Table Name can not be empty or white space");
            }

            columns = get_columns(bo, query_type);
            vals = get_vals(bo, query_type);
            DbParameter[] prm_arr = get_parameters(bo, query_type);

            String q_text = q_format.getFormat();
            q_text = q_text.replace("#table#", table_name);
            q_text = q_text.replace("#columns#", columns);
            q_text = q_text.replace("#vals#", vals);

            if (query_type == QueryTypes.InsertAndGetId) {
                String identity_ = getIdentityFormat(driver_type);
                q_text = q_text.concat(identity_);
            }

            q = new Query();
            q.setText(q_text);
            q.setParameters(prm_arr);
        } catch (Exception e) {
            throw e;
        }
        return q;
    }

    public static IQuery BuildInsert(IBaseBO bo, DriverType driver_type)
            throws Exception {
        IQuery q = null;

        try {
            q = Build(bo, driver_type, QueryTypes.Insert);
        } catch (Exception e) {
            throw e;
        }

        return q;
    }

    public static IQuery BuildInsertAnyChange(IBaseBO bo, DriverType driver_type)
            throws Exception {
        IQuery q = null;

        try {
            q = Build(bo, driver_type, QueryTypes.InsertAnyChange);
        } catch (Exception e) {
            throw e;
        }

        return q;
    }

    public static IQuery BuildInsertAndGetId(IBaseBO bo, DriverType driver_type)
            throws Exception {
        IQuery q = null;

        try {
            q = Build(bo, driver_type, QueryTypes.InsertAndGetId);
        } catch (Exception e) {
            throw e;
        }

        return q;
    }

    public static IQuery BuildUpdate(IBaseBO bo, DriverType driver_type)
            throws Exception {
        IQuery q = null;

        try {
            q = Build(bo, driver_type, QueryTypes.Update);
        } catch (Exception e) {
            throw e;
        }

        return q;
    }

    public static IQuery BuildDelete(IBaseBO bo, DriverType driver_type)
            throws Exception {
        IQuery q = null;

        try {
            q = Build(bo, driver_type, QueryTypes.Delete);
        } catch (Exception e) {
            throw e;
        }

        return q;
    }

    private static int getSqlType(Field f) {
        int type_def = DbParameter.getDefaultType();

        if (f.getType() == String.class) {
            type_def = Types.VARCHAR;
            return type_def;
        }

        if (f.getType() == BigDecimal.class) {
            type_def = Types.NUMERIC;
            return type_def;
        }

        if (f.getType() == Boolean.class) {
            type_def = Types.BIT;
            return type_def;
        }

        if (f.getType() == byte.class) {
            type_def = Types.TINYINT;
            return type_def;
        }

        if (f.getType() == short.class) {
            type_def = Types.SMALLINT;
            return type_def;
        }

        if (f.getType() == int.class) {
            type_def = Types.INTEGER;
            return type_def;
        }

        if (f.getType() == Integer.class) {
            type_def = Types.INTEGER;
            return type_def;
        }

        if (f.getType() == long.class) {
            type_def = Types.BIGINT;
            return type_def;
        }

        if (f.getType() == Long.class) {
            type_def = Types.BIGINT;
            return type_def;
        }

        if (f.getType() == float.class) {
            type_def = Types.REAL;
            return type_def;
        }

        if (f.getType() == Float.class) {
            type_def = Types.REAL;
            return type_def;
        }

        if (f.getType() == double.class) {
            type_def = Types.DOUBLE;
            return type_def;
        }

        if (f.getType() == Double.class) {
            type_def = Types.DOUBLE;
            return type_def;
        }

        if (f.getType() == java.sql.Date.class) {
            type_def = Types.DATE;
            return type_def;
        }

        if (f.getType() == java.sql.Time.class) {
            type_def = Types.TIME;
            return type_def;
        }

        if (f.getType() == java.sql.Timestamp.class) {
            type_def = Types.TIMESTAMP;
            return type_def;
        }
        /*
         if (f.getType() == Date.class) {
         type_def = Types.DATE;
         return type_def;
         }
         */
        if (f.getType() == byte[].class) {
            type_def = Types.VARBINARY;
            return type_def;
        }

        if (f.getType() == int[].class) {
            type_def = Types.VARBINARY;
            return type_def;
        }

        if (f.getType() == long[].class) {
            type_def = Types.LONGVARBINARY;
            return type_def;
        }

        return type_def;
    }

}
